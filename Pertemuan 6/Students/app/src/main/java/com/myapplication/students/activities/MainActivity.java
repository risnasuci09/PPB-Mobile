package com.myapplication.students.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.myapplication.students.R;
import com.myapplication.students.adapter.KelasAdapter;
import com.myapplication.students.database.SiswaDatabase;
import com.myapplication.students.model.KelasModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvKelas)
    RecyclerView rvKelas;

    private SiswaDatabase siswaDatabase;
    private List<KelasModel> kelasModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Membuat object database
        siswaDatabase = SiswaDatabase.createDatabase(this);

        // Membuat membuat object List
        kelasModelList = new ArrayList<>();

        ExtendedFloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TambahKelasActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Mengosongkan List agar dipastikan list dapat disi dengan data yg paling baru
        kelasModelList.clear();

        // Mengambil data dari Sqlite
        getData();

        // Mensetting dan proses menampilkan data ke RecyclerView
        rvKelas.setLayoutManager(new GridLayoutManager(this, 2));
        rvKelas.setAdapter(new KelasAdapter(this, kelasModelList));
    }

    private void getData() {

        // Operasi mengambil data dari database Sqlite
        kelasModelList = siswaDatabase.kelasDao().select();
    }
}