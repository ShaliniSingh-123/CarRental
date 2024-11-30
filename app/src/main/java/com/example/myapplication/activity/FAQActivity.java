package com.example.myapplication.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class FAQActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqactivity);

        RecyclerView faqRecyclerView = findViewById(R.id.faqRecyclerView);
        faqRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create FAQ list
        List<FAQ> faqList = new ArrayList<>();
        faqList.add(new FAQ("What is AYA Rental?", "AYA Rental is a mobile app that helps people in Cameroon find and book cars for rent. Whether it’s for personal use or business, AYA Rental makes it easy to connect with car owners and get the car you need."));
        faqList.add(new FAQ("How does AYA Rental work?", "1. Download the AYA Rental app and sign up.\n\n" +
                "2. Search for cars available in your area, based on your travel dates and preferences.\n\n" +
                "3. Choose your car, confirm the booking, and pay through the app.\n\n" +
                "4. Pick up the car or request delivery if the Owner offers it."));
        faqList.add(new FAQ("How do I book a car?", "Booking a car on AYA Rental is simple:\n" +
                "\n" +
                "1. Open the app and search for cars.\n\n" +
                "2. Select the car that fits your needs.\n\n" +
                "3. Choose your rental dates and location.\n\n" +
                "4. Make payment, and your booking is confirmed."));
        faqList.add(new FAQ(" What payment methods can I use?", "You can pay using:\n" +
                "\n" +
                "\u2022 Mobile Money (MTN or Orange).\n\n" +
                "\u2022 Credit or debit cards."));
        faqList.add(new FAQ("Can I book a car for someone else?", "No. The person booking must be the one driving the car. You’ll need to show your own valid driver’s license and ID when picking up the car."));
        faqList.add(new FAQ(
                "Who can rent a car on AYA Rental?",
                "To rent a car, you must:\n\n" +
                        "\u2022 Be at least 21 years old.\n\n" +
                        "\u2022 Have a valid driver’s license (Cameroonian or international).\n\n" +
                        "\u2022 Provide a government-issued ID or passport."
        ));
        faqList.add(new FAQ(" Can tourists use AYA Rental?", "Yes, tourists are welcome! Just make sure your driver’s license is internationally accepted in Cameroon."));
        faqList.add(new FAQ("Where will I pick up the car?", "The pickup location will be shown in your booking details. If the Owner offers delivery, you can have the car brought to your location for an extra fee."));
        faqList.add(new FAQ("What happens if I return the car late?", "Late returns may attract extra charges. These details will be shared with you during the booking."));
        faqList.add(new FAQ("Do I need to refuel the car before returning it?", "Yes, you must return the car with the same fuel level it had when you collected it. If not, extra fuel charges will apply."));
        faqList.add(new FAQ("Are the cars insured?", "Yes, all cars on AYA Rental come with basic insurance as required by law in Cameroon. But you’ll still be responsible for any damages not covered by the insurance."));
        faqList.add(new FAQ("What should I do if there’s an accident?", "If an accident happens:\n" +
                "\n" +
                "\u2022 Ensure everyone is safe and follow road safety steps.\n\n" +
                "\u2022 Call the car Owner and report it to AYA Rental through the app.\n\n" +
                "\u2022 File a police report if needed."));
        faqList.add(new FAQ(" Can I cancel my booking?", "Yes, you can cancel anytime through the app. The refund depends on how early you cancel:\n" +
                "\n" +
                "\u2022 Cancel 24 hours or more before your booking starts: Full refund.\n\n" +
                "\u2022 Cancel less than 24 hours before: A cancellation fee may apply."));
        faqList.add(new FAQ("How do I get a refund?", "Refunds are automatic for eligible cancellations. If there’s an issue, contact our support team to assist you."));
        faqList.add(new FAQ("How can I talk to the car Owner?", "Once your booking is confirmed, you can message the Owner directly through the app."));
        faqList.add(new FAQ("Can I extend my rental?", "Yes, you can request an extension through the app. The Owner must approve it, and extra charges will apply."));
        faqList.add(new FAQ("What should I do if there’s a problem with the car?", "If there’s any issue during your rental, like a breakdown, contact the car Owner and report it to AYA Rental support."));
        faqList.add(new FAQ("What’s not allowed with the car?", "You can’t use the car for:\n" +
                "\n" +
                "\u2022 Illegal activities or carrying banned items.\n\n" +
                "\u2022 Racing or risky driving.\n\n" +
                "\u2022 Commercial purposes (e.g., taxi services) unless approved by the Owner."));
        faqList.add(new FAQ("Can I request a driver?", "Some cars may come with an option for a driver. Check the car listing in the app to see if this service is available."));
        faqList.add(new FAQ("Is AYA Rental available outside Cameroon?", "For now, AYA Rental serves users within Cameroon. We’re working on expanding to other regions in the future."));

        FAQAdapter adapter = new FAQAdapter(faqList);
        faqRecyclerView.setAdapter(adapter);
    }

    // FAQ Data Model
    static class FAQ {
        String question, answer;
        boolean isExpanded;

        FAQ(String question, String answer) {
            this.question = question;
            this.answer = answer;
            this.isExpanded = false;
        }
    }

    // RecyclerView Adapter
    static class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.FAQViewHolder> {

        private final List<FAQ> faqList;

        FAQAdapter(List<FAQ> faqList) {
            this.faqList = faqList;
        }

        @NonNull
        @Override
        public FAQViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_item, parent, false);
            return new FAQViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull FAQViewHolder holder, int position) {
            FAQ faq = faqList.get(position);

            // Set question and answer text
            holder.questionText.setText(faq.question);
            holder.answerText.setText(faq.answer);

            // Toggle answer visibility
            holder.answerText.setVisibility(faq.isExpanded ? View.VISIBLE : View.GONE);

            // Expand/collapse logic on click
            holder.itemView.setOnClickListener(v -> {
                faq.isExpanded = !faq.isExpanded;
                notifyItemChanged(position);
            });
        }

        @Override
        public int getItemCount() {
            return faqList.size();
        }

        // ViewHolder
        static class FAQViewHolder extends RecyclerView.ViewHolder {
            TextView questionText, answerText;

            FAQViewHolder(@NonNull View itemView) {
                super(itemView);
                questionText = itemView.findViewById(R.id.questionText);
                answerText = itemView.findViewById(R.id.answerText);
            }
        }
    }
}
