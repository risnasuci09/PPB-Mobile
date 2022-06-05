package com.myapplication.reportapps.ui.history;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import com.bumptech.glide.Glide;
import com.myapplication.reportapps.R;
import com.myapplication.reportapps.viewmodel.HistoryViewModel;
import com.myapplication.reportapps.viewmodel.InputDataViewModel;

import java.io.IOException;

public class HistoryDetailActivity extends AppCompatActivity {
    public static final String DATA_TITLE = "TITLE";
    String strTitle, kategori, image, nama, lokasi, tanggal, isi, telepon;
    InputDataViewModel inputDataViewModel;
    Toolbar toolbar;
    TextView tvTitle;
    int uid;
    ImageView imageLaporan;
    LinearLayout layoutImage;
    EditText inputNama, inputTelepon, inputLokasi, inputTanggal, inputLaporan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);

        setStatusBar();
        setInitLayout();
        getData();
    }

    private void setInitLayout() {
        toolbar = findViewById(R.id.toolbar);
        tvTitle = findViewById(R.id.tvTitle);
        imageLaporan = findViewById(R.id.imageLaporan);
        layoutImage = findViewById(R.id.layoutImage);
        inputNama = findViewById(R.id.inputNama);
        inputTelepon = findViewById(R.id.inputTelepon);
        inputLokasi = findViewById(R.id.inputLokasi);
        inputTanggal = findViewById(R.id.inputTanggal);
        inputLaporan = findViewById(R.id.inputLaporan);

        //get data intent
        strTitle = getIntent().getExtras().getString(DATA_TITLE);
        uid = getIntent().getExtras().getInt("uid");

        if (strTitle != null) {
            tvTitle.setText(strTitle);
        }

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        inputDataViewModel = new ViewModelProvider(this, ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(this.getApplication()))
                .get(InputDataViewModel.class);
    }

    private void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        if (on) {
            layoutParams.flags |= bits;
        } else {
            layoutParams.flags &= ~bits;
        }
        window.setAttributes(layoutParams);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getData(){
        HistoryViewModel dataHistoryViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        dataHistoryViewModel.getDataLaporanById(uid).observe(this, modelDatabases -> {
            byte[] decodedString = Base64.decode(modelDatabases.getImage(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            Glide.with(this).load(decodedByte).into(imageLaporan);
            inputNama.setText(modelDatabases.getNama());
            inputTelepon.setText(modelDatabases.getTelepon());
            inputTanggal.setText(modelDatabases.getTanggal());
            inputLokasi.setText(modelDatabases.getLokasi());
            inputLaporan.setText(modelDatabases.getIsiLaporan());
        });
    }

}