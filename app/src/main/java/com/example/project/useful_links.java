package com.example.project;

import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class useful_links extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.useful_links);

        LinearLayout skincare = findViewById(R.id.skincare);
        // Skin care array
        String[] skinCareList = {
                "https://www.everydayhealth.com/skin-and-beauty-pictures/skin-conditions-you-should-know-about.aspx",
                "https://www.everydayhealth.com/skin-and-beauty-pictures/skin-conditions-you-should-know-about.aspx",
                "https://www.everydayhealth.com/skin-beauty/clean-beauty-a-comprehensive-guide/",
                "https://www.everydayhealth.com/skin-beauty/what-are-the-benefits-of-fish-oil-for-your-hair/",
                "https://www.everydayhealth.com/skin-and-beauty/natural-skin-remedies.aspx"
        };

        for( int i = 0; i < skinCareList.length; i++ ) {
            TextView textView = new TextView(this);
            textView.setText(skinCareList[i]);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize((float) 20);
            Linkify.addLinks(textView, Linkify.WEB_URLS);
            textView.setLinksClickable(true);
            textView.setPadding(50, 50, 50, 50);

            skincare.addView(textView);
        }

        LinearLayout weightloss = findViewById(R.id.weightloss);
        // Skin care array
        String[] weightlossList = {
                "https://www.medicalnewstoday.com/articles/303409",
                "https://www.medicalnewstoday.com/articles/215100",
                "https://www.helpguide.org/articles/diets/how-to-lose-weight-and-keep-it-off.htm",
                "https://www.health.harvard.edu/topics/diet-and-weight-loss",
                "https://www.ncbi.nlm.nih.gov/pmc/articles/PMC4061651/"
        };

        for( int i = 0; i < weightlossList.length; i++ ) {
            TextView textView = new TextView(this);
            textView.setText(weightlossList[i]);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize((float) 20);
            Linkify.addLinks(textView, Linkify.WEB_URLS);
            textView.setLinksClickable(true);
            textView.setPadding(50, 50, 50, 50);

            weightloss.addView(textView);
        }

    }
}