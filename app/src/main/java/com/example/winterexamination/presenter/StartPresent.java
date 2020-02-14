package com.example.winterexamination.Presenter;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;

import com.example.winterexamination.Contract.TotalContract;
import com.example.winterexamination.MyApplication;
import com.example.winterexamination.tools.Http;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class StartPresent implements TotalContract.TotalPresenter.StartPres {
    private final String TAG = "*****StartPresent*****";
    private TotalView.StartView startView;
    private EditText editText[];
    private boolean isSuccess = false;
    private MyApplication application;

    public StartPresent(TotalView.StartView s){
        startView = s;
    }

    public StartPresent(TotalView.StartView s, MyApplication application){
        startView = s;
        this.application = application;
    }

    @Override
    public void start() {
        editText =startView.getEdit();
        for (int i=0;i<editText.length;i++){
            if (editText[i].getText().toString().equals("")){
                startView.failShow();
                return;
            }
        }
        if (editText.length>2&&!editText[1].getText().toString().equals(editText[2].getText().toString())){
            startView.failShow();
            return;
        }
        try {
            if (editText.length==2){
                login();
            }else {
                register();
            }
        }catch (Exception e){
            Log.d(TAG, "start:"+e.toString());
            startView.failShow();
            return;
        }
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case 1:{
                    startView.show();
                }break;
                case 2:{
                    startView.failShow();
                }break;
            }
        }
    };

    /*
    登录事件
     */
    private void login(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                DataOutputStream dataOutputStream = null;
                HttpURLConnection connection = null;
                Message message = new Message();
                try {
                    connection = Http.getHttpConnection("http://bihu.jay86.com/login.php");
                    connection.connect();
                    dataOutputStream = new DataOutputStream(connection.getOutputStream());
                    dataOutputStream.writeBytes("username="+editText[0].getText().toString()+
                            "&password="+editText[1].getText().toString());
                    dataOutputStream.flush();
                    dataOutputStream.close();
                    //
                    InputStream in =connection.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                    StringBuilder response=new StringBuilder();
                    String line;
                    while ((line=reader.readLine())!=null){
                        response.append(line);
                    }
                    in.close();
                    reader.close();
                    String JSON = response.toString();
                    Log.d(TAG, JSON);
                    //
                    JSONObject back = new JSONObject(JSON);
                    //
                    if (back.getString("info").equals("success")){
                        message.what=1;
                        String data = back.getString("data");
                        String take = new JSONObject(data).getString("token");
                        application.setAvatar(new JSONObject(data).getString("avatar"));
                        startView.show("{\"username\": \""+editText[0].getText().toString()+
                                "\",\"password\": \""+editText[1].getText().toString()+"\"}",take);
                    }else message.what=2;
                } catch (Exception e) {
                    Log.d(TAG, "login:"+e.toString());
                    message.what=2;
                }finally {
                    if (connection != null){
                        connection.disconnect();
                    }
                    handler.sendMessage(message);
                }
            }
        }).start();
    }
    /*
    注册事件
     */
    private void register(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                DataOutputStream dataOutputStream;
                HttpURLConnection connection = null;
                Message message = new Message();
                try {
                    connection = Http.getHttpConnection("http://bihu.jay86.com/register.php");
                    connection.connect();
                    dataOutputStream = new DataOutputStream(connection.getOutputStream());
                    dataOutputStream.writeBytes("username="+editText[0].getText().toString()+
                            "&password="+editText[1].getText().toString());
                    dataOutputStream.flush();
                    dataOutputStream.close();
                    //
                    InputStream in =connection.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                    StringBuilder response=new StringBuilder();
                    String line;
                    while ((line=reader.readLine())!=null){
                        response.append(line);
                    }
                    in.close();
                    reader.close();
                    String JSON = response.toString();
                    Log.d(TAG, JSON);
                    //
                    JSONObject back = new JSONObject(JSON);
                    //
                    if (back.getString("info").equals("success")){
                        message.what=1;
                        String data = back.getString("data");
                        Log.d(TAG, data);
                        startView.show("{\"username\": \""+new JSONObject(data).getString("username")+
                                "\",\"password\": \""+new JSONObject(data).getString("password")+"\"}",new JSONObject(data).getString("token"));
                    }else message.what=2;
                } catch (Exception e) {
                    Log.d(TAG, e.toString());
                    message.what=2;
                }finally {
                    if (connection != null){
                        connection.disconnect();
                    }
                    handler.sendMessage(message);
                }
            }
        }).start();
    }

}
