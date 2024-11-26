package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);  // Enable edge-to-edge UI, making the app utilize the full screen.
        setContentView(R.layout.activity_register);

        // Setting padding for system bars (Status Bar, Navigation Bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set color for "Terms and Conditions" and "Privacy Policy" in TextView
        TextView termsPrivacyText = findViewById(R.id.terms_privacy_text);
        String text = "By signing up, you accept our Terms and Conditions and Privacy Policy.";
        SpannableString spannable = new SpannableString(text);

        int customRedColor = ContextCompat.getColor(this, R.color.red);
        int termsStart = text.indexOf("Terms and Conditions");
        int termsEnd = termsStart + "Terms and Conditions".length();
        spannable.setSpan(new ForegroundColorSpan(customRedColor), termsStart, termsEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        int privacyStart = text.indexOf("Privacy Policy");
        int privacyEnd = privacyStart + "Privacy Policy".length();
        spannable.setSpan(new ForegroundColorSpan(customRedColor), privacyStart, privacyEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        termsPrivacyText.setText(spannable);

        TextView loginRedirectText = findViewById(R.id.loginRedirectText);
        loginRedirectText.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        LinearLayout signupRedirectText = findViewById(R.id.signupRedirectText);
        signupRedirectText.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, SignupActivity.class);
            startActivity(intent);
        });

        // Enable the cross button and set click listener for redirection
        ImageView crossButton = findViewById(R.id.crossButton);
        crossButton.setClickable(true);
        crossButton.setEnabled(true);

        crossButton.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, InterfaceActivity.class);
            startActivity(intent);
            finish();  // Optionally close the RegisterActivity
        });
    }
}


