package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.News;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.BuildConfig;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Noticia;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.NoticiaProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities.ArticleActivity;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters.ArticlesAdapter;
import com.sergio.ufcdataappinicial.ufcdataapp.R;

public class NewsListArticlesFragment extends Fragment {

    ProgressBar progressBar;
    RecyclerView recycler;
    NoticiaProvider noticiaProvider;
    Noticia[] news;

    public NewsListArticlesFragment() {
    }

    public static NewsListArticlesFragment newInstance() {
        NewsListArticlesFragment fragment = new NewsListArticlesFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_list_articles, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        noticiaProvider = new NoticiaProvider(getActivity().getApplicationContext());
        progressBar = getView().findViewById(R.id.progressBarArticles);
        recyclerConf(getView());
        setLoading(true);
        getNews();
    }

    private void recyclerConf(View view) {
        recycler = view.findViewById(R.id.rvArticles);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycler.setItemAnimator(new DefaultItemAnimator());
    }

    private void getNews() {
        noticiaProvider.getArticles(new NoticiaProvider.NoticiasProviderListener() {
            @Override
            public void onResponse(Noticia[] response) {
                news = response;
                setLoading(false);
                ArticlesAdapter adapter = new ArticlesAdapter(getActivity(), news, new ArticlesAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Noticia noticia, int position) {
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), ArticleActivity.class);
                        String urlFinal = news[position].getUrl();
                        Log.d("--A>", "URL =========> " + urlFinal);
                        intent.putExtra("noticiaUrl", urlFinal);
                        startActivity(intent);
                    }
                });
                recycler.setAdapter(adapter);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                setLoading(false);
                Toast.makeText(getActivity(), "Error al recoger los datos", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setLoading(boolean loading) {
        if (loading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}
