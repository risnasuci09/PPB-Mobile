package com.myapplication.mainstay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    CardView pagi_activity;
    CardView sore_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.pagi_activity)
    public void onCrpagiClicked() {
        startActivity(new Intent(MainActivity.this, PagiActivity.class));

    }

    @OnClick(R.id.sore_activity)
    public void onCrpetangClicked() {
        startActivity(new Intent(MainActivity.this, SoreActivity.class));
    }
}