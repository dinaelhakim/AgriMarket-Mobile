package com.example.lenovo.testslidenerd;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import chemicals.all_chemicals.Chemicals;
import chemicals.specific_chemicals.SpecificChemical;
import crops.crops_categories.fruits.specific_fruit.SpecificFruit;
import crops.crops_categories.seeds.specific_seed.SpecificSeed;
import crops.crops_categories.vegetables.specific_vegetable.SpecificVegetable;
import fertilizers.specific_fertilizer.SpecificFertilizer;
import luncher.landing_page.MainActivity;
import ta2awi.specific_t2awi.SpecificT2awi;

public class ProductDetails extends AppCompatActivity {

    Intent callIntent;
    TextView textViewPhone;
    Button okButton;
    String activityThatIComeFrom = null;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        Intent comeFromIntent = getIntent();
        activityThatIComeFrom = comeFromIntent.getStringExtra("from");

        initUI();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //to display back button

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=getIntentToLastActivity();
                startActivity(intent);
                finish();
            }
        });

        textViewPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone_no = textViewPhone.getText().toString().trim();
                callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phone_no));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(callIntent);
            }
        });
    }

    private void initUI() {
        okButton = (Button) findViewById(R.id.okButton);
        textViewPhone = (TextView) findViewById(R.id.text_view_phone);
    }

    private Intent getIntentToLastActivity(){
        Intent intent=null;
        if (activityThatIComeFrom.equals("SpecificVegetable")) {
            intent = new Intent(ProductDetails.this, SpecificVegetable.class);
        } else if (activityThatIComeFrom.equals("SpecificFruit")) {
            intent = new Intent(ProductDetails.this, SpecificFruit.class);
        } else if (activityThatIComeFrom.equals("SpecificSeed")) {
            intent = new Intent(ProductDetails.this, SpecificSeed.class);
        } else if (activityThatIComeFrom.equals("SpecificFertilizer")) {
            intent = new Intent(ProductDetails.this, SpecificFertilizer.class);
        } else if (activityThatIComeFrom.equals("SpecificChemical")) {
            intent = new Intent(ProductDetails.this, SpecificChemical.class);
        } else if (activityThatIComeFrom.equals("SpecificT2awi")) {
            intent = new Intent(ProductDetails.this, SpecificT2awi.class);
        }
        return intent;
    }


    //To be back when you click back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=getIntentToLastActivity();
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent=getIntentToLastActivity();
        startActivity(intent);
        finish();
    }
}