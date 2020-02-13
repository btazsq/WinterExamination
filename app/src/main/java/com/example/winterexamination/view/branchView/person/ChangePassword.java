package com.example.winterexamination.view.branchView.person;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.winterexamination.contract.TotalContract;
import com.example.winterexamination.model.TxtData;
import com.example.winterexamination.MyApplication;
import com.example.winterexamination.presenter.DataUp;
import com.example.winterexamination.R;
import com.example.winterexamination.unit.TitleOne;

public class ChangePassword extends AppCompatActivity implements TotalContract.TotalView.DataUpView {
    private EditText editText;
    private Button button;
    private TotalPresenter.DataUpPresenter dataUpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null)actionBar.hide();
        ((TitleOne)findViewById(R.id.change_password)).setTitle("修改密码");
        editText=findViewById(R.id.change_pass_edit);
        button=findViewById(R.id.change_pass_button);
        dataUpPresenter = new DataUp(this,"http://bihu.jay86.com/changePassword.php",((MyApplication)getApplication()).getToken());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataUpPresenter.setData(editText.getText().toString(),null);
                dataUpPresenter.upLoad();
            }
        });
    }

    @Override
    public void show() {
        Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
        TxtData txtData = new TxtData("user",this);
        try {
            txtData.delTxt();
            txtData.add("{\"username\": \""+((MyApplication)getApplication()).getUsername()+
                    "\",\"password\": \""+editText.getText().toString()+"\"}");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
    }

    @Override
    public void failShow() {
        Toast.makeText(this,"修改失败",Toast.LENGTH_SHORT).show();
    }

}
