package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.dialog.EarningFragment;
import com.example.myapplication.models.response.LogoutResponse;
import com.example.myapplication.network.ApiService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.utils.SharedPreferencesManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartnerProfileActivity extends AppCompatActivity {

    private ImageView profileImage;
    private TextView profileName, profilePhone;
    private ProgressBar progressBar;
    private BottomNavigationView bottomNav;
    private RelativeLayout optionEditProfile, optionChangePassword, optionContactUs, optionDeleteAccount, optionLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_profile);

        // Initialize Views
        profileImage = findViewById(R.id.profile_image);
        profileName = findViewById(R.id.profile_name);
        profilePhone = findViewById(R.id.profile_phone);
        progressBar = findViewById(R.id.progressBar);

        optionEditProfile = findViewById(R.id.option_edit_profile);
        optionChangePassword = findViewById(R.id.option_change_password);
        optionContactUs = findViewById(R.id.option_contact_us);
        optionDeleteAccount = findViewById(R.id.option_delete_account);
        optionLogout = findViewById(R.id.option_logout);
        bottomNav = findViewById(R.id.bottomNavView);
        // BottomNavigationView listener
        bottomNav.setSelectedItemId(R.id.nav_home);
        bottomNav.setOnNavigationItemSelectedListener(this::navigateTo);

        // Load Profile Data
        loadProfileData();

        // Set Click Listeners
        optionEditProfile.setOnClickListener(v -> navigateToEditProfile());
        optionChangePassword.setOnClickListener(v -> navigateToChangePassword());
        optionContactUs.setOnClickListener(v -> navigateToContactUs());
        optionDeleteAccount.setOnClickListener(v -> showDeleteAccountDialog());
        optionLogout.setOnClickListener(v -> showLogoutDialog());
    }

    private void loadProfileData() {
        progressBar.setVisibility(View.VISIBLE);

        // Simulate loading data (replace with actual API/database logic)
        profileName.setText("Cameron Williamson");
        profilePhone.setText("(219) 555-0114");
        profileImage.setImageResource(R.drawable.profile); // Replace with dynamic image loading if needed

        progressBar.setVisibility(View.GONE);
    }

    private void navigateToEditProfile() {
        Intent intent = new Intent(PartnerProfileActivity.this, PartnerEditProfileActivity.class);
        startActivity(intent);
    }


    private void navigateToChangePassword() {
        Intent intent =new Intent(PartnerProfileActivity.this,PartnerChangePasswordActivity.class);
        startActivity(intent);
    }

    private void navigateToContactUs() {
     Intent intent =new Intent(PartnerProfileActivity.this, PartnerContactActivity.class);
     startActivity(intent);
    }

    private void showDeleteAccountDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.delete_account_dialog, null);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();
        dialog.show();

        Button cancelButton = dialogView.findViewById(R.id.cancelDeleteButton);
        Button deleteButton = dialogView.findViewById(R.id.confirmDeleteButton);

        cancelButton.setOnClickListener(v -> dialog.dismiss());

        deleteButton.setOnClickListener(v -> {
            dialog.dismiss();
            deleteAccount();
        });
    }

    private void deleteAccount() {
        progressBar.setVisibility(View.VISIBLE);

        ApiService apiService = RetrofitClient.getRetrofitInstance(getApplicationContext()).create(ApiService.class);
        String accessToken = SharedPreferencesManager.getAccessToken(getApplicationContext());

        if (accessToken != null) {
            apiService.deleteAccount().enqueue(new Callback<LogoutResponse>() {
                @Override
                public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                    progressBar.setVisibility(View.GONE);

                    if (response.isSuccessful()) {
                        Toast.makeText(PartnerProfileActivity.this, "Account deleted successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(PartnerProfileActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(PartnerProfileActivity.this, "Failed to delete account. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LogoutResponse> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(PartnerProfileActivity.this, "Network error. Please try again.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No active session found. Please log in.", Toast.LENGTH_SHORT).show();
        }
    }

    private void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_logout_confirmation, null);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();
        dialog.show();

        Button cancelButton = dialogView.findViewById(R.id.cancelButton);
        Button logoutButton = dialogView.findViewById(R.id.logoutButton);

        cancelButton.setOnClickListener(v -> dialog.dismiss());

        logoutButton.setOnClickListener(v -> {
            dialog.dismiss();
            progressBar.setVisibility(View.VISIBLE);

            ApiService apiService = RetrofitClient.getRetrofitInstance(getApplicationContext()).create(ApiService.class);
            String accessToken = SharedPreferencesManager.getAccessToken(getApplicationContext());

            if (accessToken != null) {
                apiService.logoutUser().enqueue(new Callback<LogoutResponse>() {
                    @Override
                    public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                        progressBar.setVisibility(View.GONE);

                        if (response.isSuccessful()) {
                            SharedPreferencesManager.clearTokens(PartnerProfileActivity.this);
                            Toast.makeText(PartnerProfileActivity.this, "Successfully logged out.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(PartnerProfileActivity.this, LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(PartnerProfileActivity.this, "Logout failed. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LogoutResponse> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(PartnerProfileActivity.this, "Network error. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(PartnerProfileActivity.this, "No active session found. Please log in.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private boolean navigateTo(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            startActivity(new Intent(PartnerProfileActivity.this, PartnerDashboardActivity.class));
            return true;
        } else if (id == R.id.nav_profile) {
            startActivity(new Intent(PartnerProfileActivity.this, PartnerProfileActivity.class));
            return true;
        } else if (id == R.id.nav_booking) {
            startActivity(new Intent(PartnerProfileActivity.this, PartnerBookingActivity.class));
            return true;
        }
        return false;
    }
}
