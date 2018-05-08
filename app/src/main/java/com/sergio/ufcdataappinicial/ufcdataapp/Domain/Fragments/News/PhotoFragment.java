package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.News;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sergio.ufcdataappinicial.ufcdataapp.R;

public class PhotoFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;


    private OnFragmentInteractionListener mListener;

    public PhotoFragment() {
        // Required empty public constructor
    }

    public static PhotoFragment newInstance(int param1) {
        PhotoFragment fragment = new PhotoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photo, container, false);
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
