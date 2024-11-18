package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class SubCategorySelectionActivity extends AppCompatActivity {

    private ListView subCategoryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category_selection);

        // Back button setup
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to the previous activity
            }
        });

        subCategoryListView = findViewById(R.id.subCategoryListView);

        String category = getIntent().getStringExtra("CATEGORY");

        // Sample subcategories based on category
        String[] subCategories = {"Luxury", "Economy", "Compact"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, subCategories);
        subCategoryListView.setAdapter(adapter);

        subCategoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedSubCategory = subCategories[position];
                openCarDetailsScreen(selectedSubCategory);
            }
        });
    }

    private void openCarDetailsScreen(String subCategory) {
        Intent intent = new Intent(SubCategorySelectionActivity.this, CarDetailsActivity.class);
        intent.putExtra("SUB_CATEGORY", subCategory);
        startActivity(intent);
    }
}
