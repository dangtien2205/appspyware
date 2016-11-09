package com.example.tienbi.appspyware.asystask;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.tienbi.appspyware.activity.SelectionActivity;
import com.example.tienbi.appspyware.App;
import com.example.tienbi.appspyware.mode.WebService;
/**
 * Created by TienBi on 16/09/2016.
 */
public class LogInTask extends AsyncTask<String,Void,String>{
    private ProgressDialog progressDialog;
    private Context context;
    public LogInTask(ProgressDialog progressDialog) {
        this.progressDialog=progressDialog;
        this.context= App.getContext();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String user =params[0];
        String pass =params[1];
        WebService ws=new WebService();
        return ws.logIn(user,pass);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        progressDialog.dismiss();
        if(result.equals("-1")){
            Toast.makeText(context,"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
            App.setKey(result);
            Intent intent=new Intent(context,SelectionActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
