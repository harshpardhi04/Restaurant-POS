package com.example.freindscafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;


public class table extends AppCompatActivity {
ImageView imageView;
CardView cardView,card3,card4,card5,card6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        imageView=findViewById(R.id.table1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(table.this,table1.class));
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
            }
        });

        cardView=findViewById(R.id.table2);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(table.this,table2.class));
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);

            }
        });
        card3=findViewById(R.id.table3);
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(table.this, table3.class));
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);

            }
        });
        card4=findViewById(R.id.table44);
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(table.this, table4.class));
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);

            }
        });
        card5=findViewById(R.id.table5);
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(table.this, table5.class));
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);

            }
        });
        card6=findViewById(R.id.table6);
        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(table.this, table6.class));
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);

            }
        });


    }
}