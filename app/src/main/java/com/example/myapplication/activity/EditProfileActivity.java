package com.example.myapplication.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.models.ImageResponse;
import com.example.myapplication.models.UserProfile;
import com.example.myapplication.network.ApiService;
import com.example.myapplication.network.RetrofitClient;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditProfileActivity extends AppCompatActivity {
    private EditText etName, etEmail, etPhone, etAddress;
    private Button btnChange, btnUpdate;
    private ImageView profileImage, backButton;
    private static final int REQUEST_CAMERA = 1;
    private static final int REQUEST_GALLERY = 2;
    private Uri selectedImageUri;
    private Bitmap selectedImageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Initialize views
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);
        etAddress = findViewById(R.id.et_address);
        profileImage = findViewById(R.id.profile_image);
        btnChange = findViewById(R.id.btn_change);
        btnUpdate = findViewById(R.id.btn_update);
        backButton = findViewById(R.id.back_button);

        // Show the bottom sheet dialog for changing profile image
        btnChange.setOnClickListener(v -> showBottomSheetDialog());

        // Handle Back Button click
        backButton.setOnClickListener(v -> finish());

        // Handle Update Button click
        btnUpdate.setOnClickListener(v -> updateUserProfile());

        // Fetch and display user profile information
        fetchUserProfile();
    }

    private void showBottomSheetDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View bottomSheetView = getLayoutInflater().inflate(R.layout.menu_profile_photo_options, null);
        bottomSheetDialog.setContentView(bottomSheetView);

        // Handle Camera option
        bottomSheetView.findViewById(R.id.btn_camera).setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
            openCamera();
        });

        // Handle Gallery option
        bottomSheetView.findViewById(R.id.btn_gallery).setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
            openGallery();
        });

        // Handle Remove Image option
        bottomSheetView.findViewById(R.id.btn_remove).setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
            removeProfileImage();
        });

        bottomSheetDialog.show();
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cameraIntent, REQUEST_CAMERA);
        } else {
            Toast.makeText(this, "Camera not available", Toast.LENGTH_SHORT).show();
        }
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, REQUEST_GALLERY);
    }

    private void removeProfileImage() {
        profileImage.setImageResource(R.drawable.profile); // Placeholder image
        selectedImageUri = null;
        selectedImageBitmap = null;
        Toast.makeText(this, "Profile image removed", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == REQUEST_CAMERA) {
                selectedImageBitmap = (Bitmap) data.getExtras().get("data");
                profileImage.setImageBitmap(selectedImageBitmap);
            } else if (requestCode == REQUEST_GALLERY) {
                selectedImageUri = data.getData();
                profileImage.setImageURI(selectedImageUri);
            }
        }
    }

    private void updateUserProfile() {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String phone = etPhone.getText().toString();
        String address = etAddress.getText().toString();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        } else {
            if (selectedImageUri != null || selectedImageBitmap != null) {
                uploadImageToServer(name, email, phone, address);
            } else {
                updateProfile(name, email, phone, address);
            }
        }
    }

    private void uploadImageToServer(String name, String email, String phone, String address) {
        File file = getImageFile();
        if (file == null) return;

        ApiService apiService = RetrofitClient.getRetrofitInstance(EditProfileActivity.this).create(ApiService.class);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

        apiService.uploadImage(part).enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    updateProfile(name, email, phone, address);
                } else {
                    Toast.makeText(EditProfileActivity.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateProfile(String name, String email, String phone, String address) {
        ApiService apiService = RetrofitClient.getRetrofitInstance(EditProfileActivity.this).create(ApiService.class);

        RequestBody nameRequestBody = RequestBody.create(MultipartBody.FORM, name);
        RequestBody emailRequestBody = RequestBody.create(MultipartBody.FORM, email);
        RequestBody phoneRequestBody = RequestBody.create(MultipartBody.FORM, phone);
        RequestBody addressRequestBody = RequestBody.create(MultipartBody.FORM, address);



        apiService.updateUserProfileWithImage(nameRequestBody, emailRequestBody,addressRequestBody)
                .enqueue(new Callback<UserProfile>() {
                    @Override
                    public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(EditProfileActivity.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(EditProfileActivity.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserProfile> call, Throwable t) {
                        Toast.makeText(EditProfileActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private MultipartBody.Part getImagePart(Uri imageUri) {
        File file = new File(getRealPathFromURI(imageUri));
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        return MultipartBody.Part.createFormData("file", file.getName(), requestBody);
    }

    private File getImageFile() {
        File file = null;
        if (selectedImageUri != null) {
            file = new File(getRealPathFromURI(selectedImageUri));
        } else if (selectedImageBitmap != null) {
            file = new File(getCacheDir(), "profile_image.jpg");
            try (FileOutputStream out = new FileOutputStream(file)) {
                selectedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    private String getRealPathFromURI(Uri uri) {
        ContentResolver resolver = getContentResolver();
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = resolver.query(uri, projection, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            String path = cursor.getString(columnIndex);
            cursor.close();
            return path;
        }
        return uri.getPath();
    }

    private void fetchUserProfile() {


        String userId = "67443a51e088ba69d945c527"; // Replace with actual user ID
        ApiService apiService = RetrofitClient.getRetrofitInstance(EditProfileActivity.this).create(ApiService.class);
        apiService.getUserProfile(userId).enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UserProfile.User user = response.body().getUser();
                    if (user != null) {
                        etName.setText(user.getFullName());
                        etEmail.setText(user.getEmail());
                        etPhone.setText(user.getMobile());
                        etAddress.setText(user.getAddress() != null ? user.getAddress() : "");
                        Glide.with(EditProfileActivity.this)
                                .load(user.getPhoto())
                                .into(profileImage);
                    }
                } else {
                    Toast.makeText(EditProfileActivity.this, "Failed to fetch profile", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
