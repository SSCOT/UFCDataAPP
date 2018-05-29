package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities;

import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Switch;

import com.sergio.ufcdataappinicial.ufcdataapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogSettingsActivity extends DialogFragment {

    Button btnCancel;
    Button btnOk;
    Button btnDeleteDatabase;
    Switch swNotifications;
    RadioButton rdGlobal;
    RadioButton rdLatin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_settings, container, false);
        btnCancel = view.findViewById(R.id.btnCancel);
        btnOk = view.findViewById(R.id.btnOK);
        rdGlobal = view.findViewById(R.id.rdGlobal);
        rdLatin = view.findViewById(R.id.rdLatin);
        swNotifications = view.findViewById(R.id.swNotificaciones);
        btnDeleteDatabase = view.findViewById(R.id.btnDeleteDatabase);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getActivity().getSharedPreferences("dbAuxiliar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                if (rdLatin.isChecked())
                    editor.putInt("idLocalization", 5);
                else
                    editor.putInt("idLocalization", 1);

                if (swNotifications.isChecked())
                    editor.putBoolean("notifications", true);
                else
                    editor.putBoolean("notifications", false);

                editor.apply();
                getDialog().dismiss();
            }
        });

        return view;
    }
}
