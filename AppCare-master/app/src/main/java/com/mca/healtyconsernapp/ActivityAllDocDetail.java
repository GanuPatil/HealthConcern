package com.mca.healtyconsernapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ActivityAllDocDetail extends AppCompatActivity {

    TextView tv1,tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_doc_detail);

        tv1=findViewById(R.id.docname);
        tv2=findViewById(R.id.docexp2);

        tv1.setText(getIntent().getStringExtra("docname"));
        tv2.setText(getIntent().getStringExtra("docexp"));

    }
}