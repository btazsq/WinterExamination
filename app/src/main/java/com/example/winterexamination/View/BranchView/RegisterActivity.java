package com.example.winterexamination.View.BranchView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.winterexamination.Contract.totalContract;
import com.example.winterexamination.Part.EditPassword;
import com.example.winterexamination.Presenter.EditPassPres;
import com.example.winterexamination.Presenter.StartPresent;
import com.example.winterexamination.R;
import com.example.winterexamination.Unit.TitleOne;
import com.example.winterexamination.Model.TxtData;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity implements totalContract.totalView.StartView {
    EditPassword editPassword[]=new EditPassword[2];
    EditPassPres editPassPres[]=new EditPassPres[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ((TitleOne)findViewById(R.id.register_title_1)).setTitle("注册");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null)actionBar.hide();
        editPassword[0]=new EditPassword((Button)findViewById(R.id.register_edit_2_b),(EditText)findViewById(R.id.register_edit_2));
        editPassword[1]=new EditPassword((Button)findViewById(R.id.register_edit_3_b),(EditText)findViewById(R.id.register_edit_3));
        editPassPres[0]=new EditPassPres(editPassword[0]);
        editPassPres[1]=new EditPassPres(editPassword[1]);
        StartPresent startPresent = new StartPresent(this);
        Button button = (Button) findViewById(R.id.register_button_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPresent.start();
            }
        });
    }

    @Override
    public void show() {
        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
        this.finish();
    }

    @Override
    public EditText[] getEdit() {
        EditText editText[] = new EditText[3];
        editText[0]=(EditText)findViewById(R.id.register_edit_1);
        editText[1]=(EditText)findViewById(R.id.register_edit_2);
        editText[2]=(EditText)findViewById(R.id.register_edit_3);
        return editText;
    }

    @Override
    public void failShow() {
        Toast.makeText(this,"注册失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void show(String mes, String take) {
        TxtData txtData = new TxtData("user",this);
        try {
            txtData.delTxt();
            txtData.add(mes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
