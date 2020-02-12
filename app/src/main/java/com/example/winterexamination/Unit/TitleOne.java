package com.example.winterexamination.Unit;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.winterexamination.R;

public class TitleOne extends LinearLayout {
    private final String TAG = "*****TitleOne*****";

    private Button back;
    private TextView title;

    public TitleOne(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_1,this);
        back = (Button)findViewById(R.id.title_1_back);
        title = (TextView)findViewById(R.id.title_1_txt);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });
        title.setText("");
    }

    public void setTitle(String show){
        title.setText(show);
    }

    public void setBack(OnClickListener onClickListener){
        back.setOnClickListener(onClickListener);
    }
}
