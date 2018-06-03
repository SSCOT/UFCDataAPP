package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.News;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Noticia;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.NoticiaProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities.ArticleActivity;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters.ArticlesAdapter;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.sergio.ufcdataappinicial.ufcdataapp.Utilidades;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsListArticlesFragment extends Fragment {

    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.progressBarArticles)
    ProgressBar progressBar;
    @BindView(R.id.rvArticles)
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
        View view = inflater.inflate(R.layout.fragment_news_list_articles, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        noticiaProvider = new NoticiaProvider(getActivity().getApplicationContext());
        recyclerConf(getView());
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        setLoading(true);
        getNews();

        // Swipe refresh
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNews();
            }
        });
    }

    private void recyclerConf(View view) {
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycler.setItemAnimator(new DefaultItemAnimator());
    }

    private void getNews() {
        noticiaProvider.getArticles(new NoticiaProvider.NoticiasProviderListener() {
            @Override
            public void onResponse(Noticia[] response) {
                news = response;
                swipeRefreshLayout.setRefreshing(false);
                setLoading(false);
                ArticlesAdapter adapter = new ArticlesAdapter(getActivity(), news, new ArticlesAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Noticia noticia, int position) {
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), ArticleActivity.class);
                        String urlFinal = noticia.getUrl();
                        intent.putExtra("noticiaUrl", urlFinal);
                        startActivity(intent);
                    }
                });
                recycler.setAdapter(adapter);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                swipeRefreshLayout.setRefreshing(false);
                setLoading(false);
                Utilidades.messageWithOk(getContext(), getView(), getResources().getString(R.string.error_recuperacion_datos));
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
