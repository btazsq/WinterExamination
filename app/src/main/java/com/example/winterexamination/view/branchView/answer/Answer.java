package com.example.winterexamination.view.branchView.answer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
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

public class Answer extends AppCompatActivity {
    private static final String TAG = "*****Answer*****";

    RecyclerView recyclerView;
    EditText editText;
    Button button;
    AnswerAdapter adapter;
    int qid;
    RealizeAPI realizeAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        try {
            getSupportActionBar().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = getIntent();
        qid = Integer.parseInt(intent.getStringExtra("qid"));
        adapter = new AnswerAdapter(intent.getStringExtra("JSON"));
        Log.d(TAG, "onCreate: "+intent.getStringExtra("JSON"));
        adapter.answer = this;
        realizeAPI = new RealizeAPI(((MyApplication)getApplication()).getToken());
        adapter.realizeAPI = new RealizeAPI(((MyApplication)getApplication()).getToken());
        recyclerView = findViewById(R.id.answer_re);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        button = findViewById(R.id.answer_button);
        editText = findViewById(R.id.answer_txt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (realizeAPI.postAnwer(qid,editText.getText().toString(),null)){
                                handler.sendMessage(new Message());
                            }
                        } catch (Exception e) {
                            Log.d(TAG, "run: "+e.toString());
                        }
                    }
                }).start();
            }
        });
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Toast.makeText(Answer.this,"提交成功",Toast.LENGTH_SHORT).show();
        }
    };
}
