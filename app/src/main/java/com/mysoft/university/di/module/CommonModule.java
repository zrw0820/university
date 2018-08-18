package com.mysoft.university.di.module;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatDialog;

import com.mysoft.university.R;

import dagger.Module;
import dagger.Provides;

@Module
public class CommonModule {
    private Context context;

    public CommonModule(Context context) {
        this.context = context;
    }

    @Provides
    Dialog provideLoadingDialog() {
        AppCompatDialog dialog = new AppCompatDialog(context, R.style.load_dialog_style);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        return dialog;
    }
}