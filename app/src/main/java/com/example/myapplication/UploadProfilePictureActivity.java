// Inside UploadProfilePictureActivity.java
package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.provider.MediaStore;
import android.graphics.Bitmap;
import android.net.Uri;
import java.io.IOException;

public class UploadProfilePictureActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView ivProfilePicture;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_profile_picture);

        ivProfilePicture = findViewById(R.id.ivProfilePicture);
        Button btnSelectImage = findViewById(R.id.btnSelectImage);
        Button btnUploadImage = findViewById(R.id.btnUploadImage);
        ImageButton backButton = findViewById(R.id.backButton);

        // Back button logic to navigate to ProfileActivity
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(UploadProfilePictureActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish(); // Finish this activity so it does not remain in the back stack
        });

        // Image selection logic
        btnSelectImage.setOnClickListener(v -> openImageChooser());
        btnUploadImage.setOnClickListener(v -> uploadImage());
    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                ivProfilePicture.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage() {
        if (imageUri != null) {
            // Implement the upload logic here, such as uploading to Firebase or a server
            Toast.makeText(this, "Image uploaded successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
        }
    }
}
