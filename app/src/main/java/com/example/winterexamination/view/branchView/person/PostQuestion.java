package com.example.winterexamination.View.BranchView.Person;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.winterexamination.MyApplication;
import com.example.winterexamination.R;
import com.example.winterexamination.tools.RealizeAPI;

public class PostQuestion extends AppCompatActivity {

    private static final String TAG = "*****PostQuestion*****";
    RealizeAPI realizeAPI;
    EditText title,content,image;
    Button button;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Toast.makeText(PostQuestion.this,"提交成功",Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_question);
        try {
            getSupportActionBar().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
        realizeAPI = new RealizeAPI(((MyApplication)getApplication()).getToken());
        title = findViewById(R.id.post_title);
        content = findViewById(R.id.post_content);
        image = findViewById(R.id.post_image);
        button = findViewById(R.id.post_question_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String images[] = new String[1];
                        images[0] = image.getText().toString();
                        try {
                            if(realizeAPI.postQuestion(title.getText().toString(),content.getText().toString(),images))
                            handler.sendMessage(new Message());
                        } catch (Exception e) {
                            Log.d(TAG, "run: ");
                        }
                    }
                }).start();
            }
        });

    }
}
