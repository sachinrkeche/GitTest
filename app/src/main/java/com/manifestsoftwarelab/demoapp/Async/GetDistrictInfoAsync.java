package com.manifestsoftwarelab.demoapp.Async;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.manifestsoftwarelab.demoapp.Helper.CommonUtilities;
import com.manifestsoftwarelab.demoapp.Helper.CustomProgressDialog;
import com.manifestsoftwarelab.demoapp.Helper.ServerDataManager;
import com.manifestsoftwarelab.demoapp.R;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

/**
 * Created by shree on 11/29/16.
 */

public class GetDistrictInfoAsync extends AsyncTask<String,Void,Boolean> {
    AsyncListener listener;
    String ServiceUrl = "", responseBody;
    ProgressDialog dialog;
    private Context context;
    private String Lang;

    public GetDistrictInfoAsync(Context context, AsyncListener listener) {
        this.listener = listener;
        this.context = context;
        this.Lang = Lang;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = CustomProgressDialog.showProgressDialog(context);
        dialog.setContentView(R.layout.custom_progress_dialog);
        dialog.show();
    }

    @Override
    protected Boolean doInBackground(String... params) {
        Boolean isValidate = false;
        ServiceUrl = CommonUtilities.SERVER_API + CommonUtilities.DISTRICT_INFO;
        ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        postParameters.add(new BasicNameValuePair("Lang",Lang));
        try {
            ServerDataManager sm = ServerDataManager.getInstant();
            responseBody = sm.getPostData(postParameters, ServiceUrl);
            System.out.print("response is " + responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValidate;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if (dialog != null)
            dialog.dismiss();
        listener.onTaskCompleted(responseBody);
    }
}

