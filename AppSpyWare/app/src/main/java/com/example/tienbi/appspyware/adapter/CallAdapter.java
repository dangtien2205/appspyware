package com.example.tienbi.appspyware.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tienbi.appspyware.mode.CallInfo;
import com.example.tienbi.appspyware.R;

import java.util.ArrayList;

/**
 * Created by TienBi on 17/09/2016.
 */
public class CallAdapter extends ArrayAdapter<CallInfo> {
    private Activity context;
    private int resource;
    private ArrayList<CallInfo> objects;
    public CallAdapter(Activity context, int resource, ArrayList<CallInfo> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        convertView=layoutInflater.inflate(resource,null);

        TextView txtNumberCall =(TextView)convertView.findViewById(R.id.txtNumberCall);
        TextView txtTime =(TextView)convertView.findViewById(R.id.txtTimeCall);
        ImageView iwState = (ImageView) convertView.findViewById(R.id.iwState);

        CallInfo callInfo= (CallInfo) objects.get(position);

        txtNumberCall.setText(callInfo.getPhoneNumber());
        txtTime.setText(callInfo.getTime());
        if(callInfo.getStatus()==1) {
            iwState.setImageResource(R.drawable.ic_out);
        }else {
            iwState.setImageResource(R.drawable.ic_in);
        }
        return convertView;
    }
}
