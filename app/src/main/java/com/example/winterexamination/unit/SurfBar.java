package com.example.winterexamination.unit;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.winterexamination.contract.TotalContract;
import com.example.winterexamination.R;

public class SurfBar extends LinearLayout implements TotalContract.Surf.SurfBar, View.OnClickListener {
    private static final String TAG = "*****SurfBar*****";

    public Button ahead;
    public Button back;
    public Button jump;
    public EditText page;
    public TextView viewing;

    private TotalContract.Surf.SurfView context;

    public SurfBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_2,this);
        ahead = findViewById(R.id.title_2_ahead);
        back = findViewById(R.id.title_2_back);
        jump = findViewById(R.id.title_2_jump);
        page = findViewById(R.id.title_2_page);
        viewing = findViewById(R.id.title_2_viewing);
        ahead.setOnClickListener(this);
        back.setOnClickListener(this);
        jump.setOnClickListener(this);
    }

    @Override
    public void setContext(TotalContract.Surf.SurfView view){
        this.context = view;
        try {
            setWhichPage(context.getWhichPage());
            context.getData();
        } catch (Exception e) {
            Log.d(TAG, "setContext: "+e.toString());
        }
    }

    @Override
    public void setWhichPage(int num)throws Exception{
        viewing.setText("第"+num+"页");
    }

    @Override
    public void onClick(View v) {
        int page = 0;
        switch (v.getId()){
            case R.id.title_2_ahead :{
                try {
                    page = context.getWhichPage()+1;
                } catch (Exception e) {
                    Log.d(TAG, "ahead: "+e.toString());
                }
            }break;
            case R.id.title_2_back :{
                try {
                    if ((page = context.getWhichPage())>1)
                        page--;
                } catch (Exception e) {
                    Log.d(TAG, "back: "+e.toString());
                }
            }break;
            case R.id.title_2_jump :{
                try {
                    page = Integer.parseInt(this.page.getText().toString());
                } catch (NumberFormatException e) {
                    Log.d(TAG, "jump: "+e.toString());
                }
            }break;
        }
        try {
            setWhichPage(context.getWhichPage());
            context.getData();
            context.setWhichPage(page);
        } catch (Exception e) {
            Log.d(TAG, "setWhichPage: "+e.toString());
        }
    }

}
