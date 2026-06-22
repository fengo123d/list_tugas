package com.example.list_tugas;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Ambil data buah dari Intent
        Fruit fruit = (Fruit) getIntent().getSerializableExtra("fruit");

        if (fruit != null) {
            // Hubungkan dengan View di XML
            TextView toolbarTitle = findViewById(R.id.toolbarTitle);
            TextView detailIcon = findViewById(R.id.detailIcon);
            TextView detailName = findViewById(R.id.detailName);
            TextView detailOrigin = findViewById(R.id.detailOrigin);
            TextView detailTaste = findViewById(R.id.detailTaste);
            TextView detailDescription = findViewById(R.id.detailDescription);
            TextView detailBenefits = findViewById(R.id.detailBenefits);
            ImageView btnBack = findViewById(R.id.btnBack);

            // Set Data ke View
            toolbarTitle.setText("Detail: " + fruit.getName());
            detailIcon.setText(fruit.getIcon());
            detailName.setText(fruit.getName());
            detailOrigin.setText(fruit.getOrigin());
            detailTaste.setText(fruit.getTaste());
            detailDescription.setText(fruit.getDescription());

            // Format List Manfaat Kesehatan
            StringBuilder benefitsStr = new StringBuilder();
            for (int i = 0; i < fruit.getHealthBenefits().size(); i++) {
                benefitsStr.append("• ").append(fruit.getHealthBenefits().get(i));
                if (i < fruit.getHealthBenefits().size() - 1) {
                    benefitsStr.append("\n");
                }
            }
            detailBenefits.setText(benefitsStr.toString());

            // Fungsi Tombol Back
            btnBack.setOnClickListener(v -> finish());
        }
    }
}
