package com.example.tienbi.appspyware.asystask;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.tienbi.appspyware.adapter.CallAdapter;
import com.example.tienbi.appspyware.mode.CallInfo;
import com.example.tienbi.appspyware.mode.WebService;

import java.util.ArrayList;

/**
 * Created by TienBi on 17/09/2016.
 */
public class CallTask extends AsyncTask<String,Void,ArrayList<CallInfo>> {
    private ProgressDialog progressDialog;
    private CallAdapter callAdapter;
    public CallTask(ProgressDialog progressDialog, CallAdapter callAdapter) {
        this.progressDialog=progressDialog;
        this.callAdapter=callAdapter;
    }

    @Override
    protected ArrayList<CallInfo> doInBackground(String... params) {
        String idmember=params[0];
        WebService ws = new WebService();
        return ws.LoadCallResult(idmember);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        callAdapter.clear();
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(ArrayList<CallInfo> smsInfos) {
        super.onPostExecute(smsInfos);
        callAdapter.clear();
        callAdapter.addAll(smsInfos);
        progressDialog.dismiss();
    }
}
