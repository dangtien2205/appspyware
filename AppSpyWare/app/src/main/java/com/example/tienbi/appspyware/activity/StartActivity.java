package com.example.tienbi.appspyware.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tienbi.appspyware.App;
import com.example.tienbi.appspyware.asystask.LogInTask;
import com.example.tienbi.appspyware.R;

public class StartActivity extends Activity {
    private Button btnLogIn;
    private EditText edtUser;
    private EditText edtPass;
    private ProgressDialog progressDialog;
    private CheckBox chkSave;
    private String nameSP="LogIn_State";
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
        loadInformationLogIn();
    }

    private void addEvents() {
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=edtUser.getText().toString();
                String pass=edtPass.getText().toString();
                if(user.length()==0){
                    Toast.makeText(App.getContext(),"Chưa nhập tên tài khoản",Toast.LENGTH_SHORT).show();
                    edtUser.requestFocus();
                    return;
                }
                if(pass.length()==0){
                    Toast.makeText(App.getContext(),"Chưa nhập mật khẩu",Toast.LENGTH_SHORT).show();
                    edtPass.requestFocus();
                    return;
                }
                SharedPreferences.Editor edit=sharedPreferences.edit();
                if(chkSave.isChecked()){
                    edit.putBoolean("STATE",true);
                    edit.putString("USER",user);
                    edit.putString("PASS",pass);
                }else {
                    edit.putBoolean("STATE",false);
                    edit.putString("USER",null);
                    edit.putString("PASS",null);
                }
                edit.commit();
                if(isConnect(App.getContext())){
                    LogInTask logInTask=new LogInTask(progressDialog);
                    logInTask.execute(user,pass);
                }else {
                    Toast.makeText(App.getContext(),"Không có kết nối mạng",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
    public boolean isConnect(Context context){
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        boolean b= netInfo != null && netInfo.isConnectedOrConnecting();
        return b;
    }
    private void addControls() {
        btnLogIn=(Button)findViewById(R.id.btnLogIn);
        edtUser=(EditText)findViewById(R.id.edtUser);
        edtPass=(EditText)findViewById(R.id.edtPassWord);
        chkSave=(CheckBox)findViewById(R.id.chkSave);
        sharedPreferences=getSharedPreferences(nameSP,MODE_PRIVATE);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Thông báo");
        progressDialog.setMessage("Vui lòng chờ");
        progressDialog.setCanceledOnTouchOutside(false);
    }
    private void loadInformationLogIn(){
        if(sharedPreferences.getBoolean("STATE",false)){
            chkSave.setChecked(true);
            edtUser.setText(sharedPreferences.getString("USER",null));
            edtPass.setText(sharedPreferences.getString("PASS",null));
        }
    }
}
