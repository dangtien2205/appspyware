package com.example.tienbi.appspyware.asystask;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.tienbi.appspyware.adapter.SmsAdapter;
import com.example.tienbi.appspyware.mode.SMSInfo;
import com.example.tienbi.appspyware.mode.WebService;

import java.util.ArrayList;

/**
 * Created by TienBi on 16/09/2016.
 */
public class SmsTask extends AsyncTask<String,Void,ArrayList<SMSInfo>> {
    private ProgressDialog progressDialog;
    private SmsAdapter smsAdapter;
    public SmsTask(ProgressDialog progressDialog, SmsAdapter smsAdapter) {
        this.progressDialog=progressDialog;
        this.smsAdapter=smsAdapter;
    }

    @Override
    protected ArrayList<SMSInfo> doInBackground(String... params) {
        String idmember=params[0];
        WebService ws = new WebService();
        return ws.LoadSmsResult(idmember);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        smsAdapter.clear();
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(ArrayList<SMSInfo> smsInfos) {
        super.onPostExecute(smsInfos);
        smsAdapter.clear();
        smsAdapter.addAll(smsInfos);
        progressDialog.dismiss();
    }
}
