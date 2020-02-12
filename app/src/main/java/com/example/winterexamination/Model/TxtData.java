package com.example.winterexamination.Model;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.winterexamination.Contract.totalContract;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class TxtData implements totalContract.totalModel{
    private final String TAG = "*****TxtData*****";
    private String txtName;
    private AppCompatActivity activity;

    /*
    txt文件名字
     */
    public TxtData(String txt,AppCompatActivity nowActivity){
        txtName=txt;
        activity=nowActivity;
        try {
            activity.openFileOutput(txtName,Context.MODE_APPEND).close();
        } catch (Exception e) {
            Log.d(TAG, "TxtData: "+e.toString());
        }
    }

    /*
    存入JSON字符串，以'\n'分割
    返回过程中的有没有出现异常，一切正常就是true
     */
    public boolean add(String data){
        boolean isSucess = true;
        FileOutputStream outputStream = null;
        BufferedWriter writer = null;
        try{
            outputStream = activity.openFileOutput(txtName,Context.MODE_APPEND);
            writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(data);
            writer.write('\n');
        }catch (Exception e){
            Log.d(TAG, e.toString());
            isSucess = false;
        }finally {
            try {
                if (writer!=null){
                    writer.close();
                }
            }catch (Exception e){
                Log.d(TAG, e.toString());
            }
        }
        return isSucess;
    }

    /*
    读取文件返回list
    每个元素应该是一个JSON字符串
     */
    public List<String> load()throws Exception{
        List<String> list = new ArrayList<String>();
        FileInputStream inputStream = null;
        BufferedReader reader = null;
        inputStream = activity.openFileInput(txtName);
        reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line=reader.readLine())!=null){
            list.add(line);
        }
        if (reader!=null){
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /*
    删除该TXT的内容
     */
    public void delTxt(){
        FileOutputStream outputStream = null;
        BufferedWriter writer = null;
        try{
            outputStream = activity.openFileOutput(txtName,Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        }catch (Exception e){
            Log.d(TAG, e.toString());
        }finally {
            try {
                if (writer!=null){
                    writer.close();
                }
            }catch (Exception e){
                Log.d(TAG, e.toString());
            }
        }
    }

    /*
    删除某一个元素
    效率很低
     */
    public void delString(int which)throws Exception{ ;
        List<String> list = this.load();
        this.delTxt();
        try {
            list.remove(which);
        }catch (Exception e){
            Log.d(TAG, e.toString());
        }
        for (int i=0;i<list.size();i++){
            this.add(list.get(i));
        }
    }

}
