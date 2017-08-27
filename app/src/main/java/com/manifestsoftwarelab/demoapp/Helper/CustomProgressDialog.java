package com.manifestsoftwarelab.demoapp.Helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager.BadTokenException;

import com.manifestsoftwarelab.demoapp.R;


public class CustomProgressDialog {

	
	 public static ProgressDialog showProgressDialog(Context mContext) {
         ProgressDialog dialog = new ProgressDialog(mContext);
         try {
                 dialog.show();
         } catch (BadTokenException e) {
        	 e.printStackTrace();
         }
         dialog.setCancelable(false);
         dialog.setContentView(R.layout.custom_progress_dialog);
         return dialog;
 }
    public static ProgressDialog showProgressDialog(Context mContext, View id) {

        ProgressDialog dialog = new ProgressDialog(mContext);
        try {
            dialog.show();
        } catch (BadTokenException e) {
            e.printStackTrace();
        }
        dialog.setCancelable(false);
        dialog.setContentView(id);
        return dialog;
    }
}