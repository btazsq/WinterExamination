package com.example.winterexamination.View.BranchView.Person;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.winterexamination.Contract.totalContract;
import com.example.winterexamination.MyApplication;
import com.example.winterexamination.Presenter.DataUp;
import com.example.winterexamination.R;
import com.example.winterexamination.Unit.TitleOne;

import java.util.Objects;

public class ChangeAvatar extends AppCompatActivity implements totalContract.totalView.DataUpView {
    private EditText editText;
    private Button button;
    private totalPresenter.DataUpPresenter dataUpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_avatar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null)actionBar.hide();
        ((TitleOne)findViewById(R.id.change_avatar)).setTitle("修改头像");
        editText=findViewById(R.id.change_avatar_edit);
        button=findViewById(R.id.change_avatar_button);
        dataUpPresenter = new DataUp(this,"http://bihu.jay86.com/modifyAvatar.php",((MyApplication)getApplication()).getToken());
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
        ((MyApplication)getApplication()).setAvatar(editText.getText().toString());
        finish();
    }

    @Override
    public void failShow() {
        Toast.makeText(this,"修改失败",Toast.LENGTH_SHORT).show();
    }

}
