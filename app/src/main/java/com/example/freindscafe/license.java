package com.example.freindscafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class license extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);
        getSupportActionBar().hide();


        PDFView pdfView=findViewById(R.id.pdfview);
        pdfView.fromAsset("license.pdf").load();


    }
}