package com.example.winterexamination.presenter;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.winterexamination.contract.TotalContract;
import com.example.winterexamination.tools.RealizeAPI;

public class DataUp implements TotalContract.TotalPresenter.DataUpPresenter {
    private TotalView.DataUpView myDataView;
    private String type;
    private String parseString[] = new String[2];
    private int parseInt[] = new int[3];
    private String image[];
    private RealizeAPI realizeAPI;

    private static final String TAG = "*****DataUp*****";

    @SuppressLint("HandlerLeak")
    android.os.Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case 1:{
                    myDataView.show();
                }break;
                case 2:{
                    myDataView.failShow();
                }break;
            }
        }
    };

    public DataUp(TotalView.DataUpView myDataView, String type, String take) {
        this.myDataView = myDataView;
        this.type = type;
        this.realizeAPI = new RealizeAPI(take);
    }

    @Override
    public void upLoad(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                try {
                    boolean isS = false;
                    switch (type){
                        case "http://bihu.jay86.com/modifyAvatar.php":{
                            isS = realizeAPI.changeAvatar(parseString[0]);
                        }break;
                        case "http://bihu.jay86.com/changePassword.php":{
                            isS = realizeAPI.changePassword(parseString[0]);
                        }break;
                    }
                    if (isS)
                    message.what=1;
                } catch (Exception e) {
                    message.what=2;
                    Log.d(TAG, "upLoad: "+e.toString());
                } finally {
                    handler.sendMessage(message);
                }
            }
        }).start();
    }

    @Override
    public void setData(String a, String b) {
        parseString[0]=a;
        parseString[1]=b;
    }

    @Override
    public void setData(int a, int b, int c) {
        parseInt[0]=a;
        parseInt[1]=b;
        parseInt[2]=c;
    }

    @Override
    public void setData(String[] image) {
        this.image=image;
    }

}
