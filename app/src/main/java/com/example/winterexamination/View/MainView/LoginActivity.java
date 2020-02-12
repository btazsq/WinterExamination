package com.example.winterexamination.View.MainView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.winterexamination.Contract.totalContract;
import com.example.winterexamination.MyApplication;
import com.example.winterexamination.Part.EditPassword;
import com.example.winterexamination.Presenter.EditPassPres;
import com.example.winterexamination.Presenter.StartPresent;
import com.example.winterexamination.R;
import com.example.winterexamination.Unit.TitleOne;
import com.example.winterexamination.View.BranchView.RegisterActivity;
import com.example.winterexamination.Model.TxtData;

import org.json.JSONException;
import org.json.JSONObject;

import static android.widget.Toast.*;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, totalContract.totalView.StartView {
    private final String TAG = "*****Login*****";
    private EditPassword editPassword;
    private EditPassPres editPassPres;
    private totalPresenter.StartPres startPres;
    EditText editText1;
    EditText editText2;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        startPres = new StartPresent(this,(MyApplication)getApplication());
        ((TitleOne)findViewById(R.id.login_title_1)).setTitle("登录");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null)actionBar.hide();
        Button buttonLogin = (Button)findViewById(R.id.login_button_1);
        Button buttonRegister = (Button)findViewById(R.id.login_button_2);
        buttonLogin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
        editPassword=new EditPassword((Button)findViewById(R.id.login_edit_2_b),(EditText)findViewById(R.id.login_edit_2));
        editPassPres=new EditPassPres(editPassword);
        editText1 = (EditText)findViewById(R.id.login_edit_1);
        editText2 = (EditText)findViewById(R.id.login_edit_2);
        //
        loadEdit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button_1:
            {
                startPres.start();
            }break;
            case R.id.login_button_2:
            {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }break;

        }
    }

    @Override
    public void show() {
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public EditText[] getEdit() {
        EditText editText[] = new EditText[2];
        editText[0]=(EditText)findViewById(R.id.login_edit_1);
        editText[1]=(EditText)findViewById(R.id.login_edit_2);
        return editText;
    }

    @Override
    public void failShow() {
        Toast.makeText(LoginActivity.this,"登录失败", LENGTH_SHORT).show();
    }

    @Override
    public void show(String mes, String take) {
        MyApplication application = (MyApplication)getApplication();
        application.setToken(take);
        startActivity(new Intent(LoginActivity.this,EnterActivity.class));
        //
        TxtData txtData = new TxtData("user",this);
        try {
            application.setUsername(new JSONObject(mes).getString("username"));
            txtData.delTxt();
            txtData.add(mes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadEdit(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TxtData txtData = new TxtData("user",LoginActivity.this);
                    String data = txtData.load().get(0);
                    JSONObject jsonObject = new JSONObject(data);
                    editText1 = (EditText)findViewById(R.id.login_edit_1);
                    editText2 = (EditText)findViewById(R.id.login_edit_2);
                    editText1.setText(jsonObject.getString("username"));
                    editText2.setText(jsonObject.getString("password"));
                } catch (Exception e) {
                    Log.d(TAG, e.toString());
                }
            }
        }).start();
    }

}
