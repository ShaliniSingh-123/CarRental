package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class EditProfileActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPhone, etAddress;
    private Button btnChange, btnUpdate;
    private ImageView profileImage, backButton;

    private static final int REQUEST_CAMERA = 1;
    private static final int REQUEST_GALLERY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Initialize views
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);
        etAddress = findViewById(R.id.et_address);
        btnChange = findViewById(R.id.btn_change);
        btnUpdate = findViewById(R.id.btn_update);
        profileImage = findViewById(R.id.profile_image);
        backButton = findViewById(R.id.back_button);

        // Handle "Change" Button click
        btnChange.setOnClickListener(v -> showBottomSheetDialog());

        // Set default profile image
        profileImage.setImageResource(R.drawable.profile); // Replace with actual image resource if needed

        // Handle Back Button click
        backButton.setOnClickListener(v -> {
            // Navigate back to the previous screen
            finish();
        });

        // Handle Update Button click
        btnUpdate.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String phone = etPhone.getText().toString();
            String address = etAddress.getText().toString();

            // Check if any field is empty
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(EditProfileActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            } else {
                // Save the updated profile information (e.g., store in database or shared preferences)
                Toast.makeText(EditProfileActivity.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void showBottomSheetDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View bottomSheetView = getLayoutInflater().inflate(R.layout.menu_profile_photo_options, null);
        bottomSheetDialog.setContentView(bottomSheetView);

        // Handle Camera option
        Button btnCamera = bottomSheetView.findViewById(R.id.btn_camera);
        btnCamera.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
            openCamera();
        });

        // Handle Gallery option
        Button btnGallery = bottomSheetView.findViewById(R.id.btn_gallery);
        btnGallery.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
            openGallery();
        });

        // Handle Remove Image option
        Button btnRemove = bottomSheetView.findViewById(R.id.btn_remove);
        btnRemove.setOnClickListener(v -> {
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
        profileImage.setImageResource(R.drawable.profile); // Replace with placeholder image
        Toast.makeText(this, "Profile image removed", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA && data != null) {
                // Handle Camera image
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                profileImage.setImageBitmap(imageBitmap);
            } else if (requestCode == REQUEST_GALLERY && data != null) {
                // Handle Gallery image
                Uri selectedImage = data.getData();
                profileImage.setImageURI(selectedImage);
            }
        }
    }
}
