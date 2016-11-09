package com.example.tienbi.appspyware.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tienbi.appspyware.R;

/**
 * Created by TienBi on 16/09/2016.
 */
public class SelectionActivity extends Activity {
    private TextView txtSMS;
    private TextView txtCall;
    public static final int SMS=1;
    public static final int CALL=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_layout);

        addControls();
        addEvents();
    }

    private void addEvents() {
        txtSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SelectionActivity.this,ResultActivity.class);
                intent.putExtra("x",SMS);
                startActivity(intent);
            }
        });
        txtCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SelectionActivity.this,ResultActivity.class);
                intent.putExtra("x",CALL);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        txtSMS=(TextView)findViewById(R.id.txtSMS);
        txtCall=(TextView)findViewById(R.id.txtCall);
    }
}
