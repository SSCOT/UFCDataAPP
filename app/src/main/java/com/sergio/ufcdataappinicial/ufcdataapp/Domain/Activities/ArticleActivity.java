package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.News.ArticleFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.R;

public class ArticleActivity extends AppCompatActivity {

    public static final String key = "noticiaUrl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        ArticleFragment articleFragment = ArticleFragment.newInstance((String) getIntent().getExtras().get(key));
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentArticulo, articleFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
}
