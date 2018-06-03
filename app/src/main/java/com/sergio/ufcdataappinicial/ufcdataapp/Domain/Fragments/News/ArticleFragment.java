package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.News;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sergio.ufcdataappinicial.ufcdataapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleFragment extends Fragment {

    @BindView(R.id.articleWeb)
    WebView webView;

    public static ArticleFragment newInstance(String url) {
        ArticleFragment articleFragment = new ArticleFragment();
        Bundle arguments = new Bundle();
        arguments.putString("noticiaUrl", url);
        articleFragment.setArguments(arguments);
        return articleFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        ButterKnife.bind(this,view);

        String url = "";

        if (getArguments() != null) {
            url = (String) getArguments().get("noticiaUrl");
        }

        setWeb(view, url);
        return view;
    }

    public void setWeb(View view, String url) {
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }

}
