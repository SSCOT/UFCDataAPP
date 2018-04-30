package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FighterActivity extends AppCompatActivity {

    @BindView(R.id.imgLuchadorDetail)
    ImageView imgLuchadorDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fighter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        String url = "http://imagec.ufc.com/http%253A%252F%252Fmedia.ufc.tv%252Ffighter_images%252FKhabib_Nurmagomedov%252F1NURMAGOMEDOV_KHABIB.png?w600-h600-tc1";

        setTitle("Nurmagumedov");
        Picasso.with(this).load(url).into(imgLuchadorDetail);

    }
}
