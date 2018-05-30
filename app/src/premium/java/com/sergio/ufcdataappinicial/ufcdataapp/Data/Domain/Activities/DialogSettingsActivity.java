package com.sergio.ufcdataappinicial.ufcdataapp.Data.Domain.Activities;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.EventoLocalProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.LuchadorLocalProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.NoticiaLocalProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.R;

public class DialogSettingsActivity extends DialogFragment {

    Button btnCancel;
    Button btnOk;
    Button btnDeleteDatabase;
    Switch swNotifications;
    RadioButton rdGlobal;
    RadioButton rdLatin;

    TextView txt;
    Boolean deletePermission = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_settings, container, false);
        rdGlobal = view.findViewById(R.id.rdGlobal);
        rdLatin = view.findViewById(R.id.rdLatin);
        swNotifications = view.findViewById(R.id.swNotificaciones);
        btnDeleteDatabase = view.findViewById(R.id.btnDeleteDatabase);
        btnCancel = view.findViewById(R.id.btnCancel);
        btnOk = view.findViewById(R.id.btnOK);

        // TODO borrar esto
        txt = view.findViewById(R.id.txtTitulo);

        SharedPreferences preferences = getActivity().getSharedPreferences("dbAuxiliar", Context.MODE_PRIVATE);
        int idLocalization = preferences.getInt("idLocalization", 1);
        if (idLocalization == 1) {
            rdGlobal.setChecked(true);
            rdLatin.setChecked(false);
        } else if (idLocalization == 5) {
            rdLatin.setChecked(true);
            rdGlobal.setChecked(false);
        }

        Boolean allowNotifications = preferences.getBoolean("allowNotifications", true);
        if (allowNotifications)
            swNotifications.setChecked(true);
        else
            swNotifications.setChecked(false);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        btnDeleteDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Hacemos un borrado de dos pasos
                if (deletePermission) {
                    // Borra las tablas principales
                    EventoLocalProvider eventoLocalProvider = new EventoLocalProvider(getActivity());
                    LuchadorLocalProvider luchadorLocalProvider = new LuchadorLocalProvider(getActivity());
                    NoticiaLocalProvider noticiaLocalProvider = new NoticiaLocalProvider(getActivity());
                    eventoLocalProvider.deleteAll();
                    luchadorLocalProvider.deleteAll();
                    noticiaLocalProvider.deleteAll();

                    // Restauramos las flags para que vuelva a guardar los datos en local
                    restoreLocalStorageFlags();

                    // Reestablecemos el texto
                    btnDeleteDatabase.setText(getResources().getString(R.string.push_restore_data_3));
                } else {
                    // Cambiamos texto y permiso
                    btnDeleteDatabase.setText(getResources().getString(R.string.push_restore_data_2));
                    deletePermission = true;
                }
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
                    editor.putBoolean("allowNotifications", true);
                else
                    editor.putBoolean("allowNotifications", false);

                editor.apply();
                getDialog().dismiss();
            }
        });

        return view;
    }

    private void restoreLocalStorageFlags() {
        SharedPreferences preferences = getActivity().getSharedPreferences("dbAuxiliar", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("fightersUpdated", false);
        editor.putBoolean("championsUpdated", false);
        editor.apply();
    }
}
