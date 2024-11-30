package com.example.myapplication.activity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class TermsConditionsActivity extends AppCompatActivity {

    private TextView termsContentText;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_conditions);

        // Initialize the views
        termsContentText = findViewById(R.id.termsContent);
        backButton = findViewById(R.id.backButton);

        // Set the content of the terms and conditions with bold key points
        String termsText = "<b>1. Key Terms</b><br>" +
                "• \"AYA Rental\" refers to our company, the mobile application, and all related services.<br>" +
                "• \"Renter\" means anyone booking a car through AYA Rental.<br>" +
                "• \"Owner\" refers to the person or company providing the car for rent.<br>" +
                "• \"Service\" is the platform, app, and features that connect Renters with Owners.<br><br>" +

                "<b>2. Who Can Rent a Car?</b><br>" +
                "• Be 21 years or older.<br>" +
                "• Hold a valid driver’s license issued in Cameroon or an internationally recognized one accepted by Cameroonian authorities.<br>" +
                "• Provide a government-issued ID or passport at the time of booking.<br><br>" +

                "<b>3. Booking and Payments</b><br>" +
                "• All bookings must be done through the AYA Rental app. You’ll see a detailed breakdown of charges, including rental fees, deposits, and other applicable costs.<br>" +
                "• We accept mobile money (MTN, Orange), credit cards, and other secure options.<br>" +
                "• Currency: All payments are made in Central African CFA Franc (XAF).<br>" +
                "• Any extra costs (like fuel, late returns, or cleaning fees) will be clearly communicated during the booking process.<br><br>" +

                "<b>4. How to Use the Car</b><br>" +
                "• Use the car for personal or approved business travel only.<br>" +
                "• Do not engage in any illegal activities, racing, or unauthorized commercial use (like acting as a taxi).<br>" +
                "• Keep the car within Cameroon’s borders unless you receive written approval from the Owner.<br>" +
                "• Follow all Cameroonian traffic laws, including speed limits and safety rules.<br><br>" +

                "<b>5. Insurance and Responsibility</b><br>" +
                "• All cars rented through AYA Rental come with basic insurance as required by Cameroonian law.<br>" +
                "• You will be responsible for any damages, theft, or accidents not covered by the insurance policy.<br>" +
                "• A refundable deposit may be required, which will be returned after the rental ends if there are no issues.<br>" +
                "• Owners must ensure their cars are roadworthy, properly maintained, and insured.<br><br>" +

                "<b>6. Cancellations and Refunds</b><br>" +
                "• You can cancel your booking anytime through the app. If canceled at least 24 hours before the start time, you’ll receive a full refund.<br>" +
                "• If you cancel less than 24 hours before the rental starts, or if you don’t show up, cancellation fees may apply.<br>" +
                "• Refunds for cancellations due to unexpected events like political unrest or natural disasters will be evaluated on a case-by-case basis.<br><br>" +

                "<b>7. Data Privacy</b><br>" +
                "• Your privacy is important to us at AYA Rental.<br>" +
                "• We collect your details (like your name, ID, and payment info) to provide and improve our services.<br>" +
                "• All personal data is stored securely and will not be shared with unauthorized third parties.<br>" +
                "• We follow all Cameroonian data protection laws to keep your information safe.<br><br>" +

                "<b>8. Prohibited Activities</b><br>" +
                "• Subleasing the car to another person.<br>" +
                "• Transporting illegal goods or hazardous materials.<br>" +
                "• Tampering with the car’s systems or documents.<br><br>" +

                "<b>9. Solving Problems</b><br>" +
                "• First, try resolving issues directly with the other party (Owner or Renter) using the in-app messaging system.<br>" +
                "• If the problem persists, contact AYA Rental’s support team for assistance.<br>" +
                "• For legal matters, disputes will be handled under Cameroonian law, and courts in Yaoundé or Douala will have jurisdiction.<br><br>" +

                "<b>10. Changes to These Terms</b><br>" +
                "• AYA Rental may update these terms from time to time. If any changes are made, we’ll notify you via the app or email. By continuing to use the app, you accept the updated terms.<br><br>" +

                "<b>11. Contact Us</b><br>" +
                "• Phone: +237 XXX-XXXX<br>" +
                "• Email: support@ayarental.com<br>" +
                "• Address: [Insert Office Address in Cameroon]<br>";

        // Set the terms content with bold formatting
        termsContentText.setText(Html.fromHtml(termsText, Html.FROM_HTML_MODE_LEGACY));

        // Handle the back button click
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to the previous activity
                onBackPressed();
            }
        });
    }
}
