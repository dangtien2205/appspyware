package com.example.tienbi.appspyware.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tienbi.appspyware.mode.SMSInfo;
import com.example.tienbi.appspyware.R;

import java.util.ArrayList;

/**
 * Created by TienBi on 16/09/2016.
 */
public class SmsAdapter extends ArrayAdapter<SMSInfo> {
    private Activity context;
    private int resource;
    private ArrayList<SMSInfo> objects;
    public SmsAdapter(Activity context, int resource, ArrayList<SMSInfo> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        convertView=layoutInflater.inflate(resource,null);

        TextView txtPhone =(TextView)convertView.findViewById(R.id.txtPhone);
        TextView txtTime =(TextView)convertView.findViewById(R.id.txtTime);
        TextView txtBody=(TextView)convertView.findViewById(R.id.txtBody);

        SMSInfo smsInfo= (SMSInfo)objects.get(position);

        txtBody.setText(smsInfo.getBody());
        txtTime.setText(smsInfo.getTimeStamp());
        txtPhone.setText(smsInfo.getPhoneNumber());

        return convertView;
    }
}
