package com.example.winterexamination.tools;

import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;

public class RealizeAPI {

    private static final String TAG = "*****RealizeAPI*****";
    private String token;

    public RealizeAPI(String take){
        token = "token=" + take + "&";
    }
    /*
    改变头像，传入头像网址
     */
    public boolean changeAvatar(String avatar)throws Exception{
        boolean isSuccess = false;
        HttpURLConnection connection = Http.getHttpConnection("http://bihu.jay86.com/modifyAvatar.php");
        connection.connect();
        Http.postData(connection,token+"avatar="+avatar);
        String back = Http.getResponse(connection);
        JSONObject jsonObject = new JSONObject(back);
        Log.d(TAG, "changeAvatar: "+back);
        if (jsonObject.getString("info").equals("success")){
            isSuccess = true;
        }
        return isSuccess;
    }
    /*
    改变密码，传入新密码
     */
    public boolean changePassword(String newPassword)throws Exception{
        boolean isSuccess = false;
        HttpURLConnection connection = Http.getHttpConnection("http://bihu.jay86.com/changePassword.php");
        connection.connect();
        Http.postData(connection,token+"password="+newPassword);
        String back = Http.getResponse(connection);
        JSONObject jsonObject = new JSONObject(back);
        if (jsonObject.getString("info").equals("success")){
            isSuccess = true;
        }
        Log.d(TAG, "changePassword: "+back);
        return isSuccess;
    }
    /*
    提交问题，传入标题，正文，图像地址数组
     */
    public boolean postQuestion(String title,String content,String image[])throws Exception{
        boolean isSuccess = false;
        HttpURLConnection connection = Http.getHttpConnection("http://bihu.jay86.com/question.php");
        connection.connect();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(token+"title="+title+"&content="+content+"&images=");
        if (image!=null)
        for (int i=0;i<image.length;i++){
            stringBuilder.append(image[i]);
            if (i<image.length-1){
                stringBuilder.append(",");
            }
        }
        Http.postData(connection,stringBuilder.toString());
        String back = Http.getResponse(connection);
        JSONObject jsonObject = new JSONObject(back);
        if (jsonObject.getString("info").equals("success")){
            isSuccess = true;
        }
        Log.d(TAG, "postQuestion: "+back);
        return isSuccess;
    }
    /*
    提交回答，传入标题，正文，图像地址数组
     */
    public boolean postAnwer(int qid,String content,String image[])throws Exception{
        boolean isSuccess = false;
        HttpURLConnection connection = Http.getHttpConnection("http://bihu.jay86.com/answer.php");
        connection.connect();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(token+"qid="+qid+"&content="+content+"&images=");
        if (image!=null)
            for (int i=0;i<image.length;i++){
                stringBuilder.append(image[i]);
                if (i<image.length-1){
                    stringBuilder.append(",");
                }
            }
        Http.postData(connection,stringBuilder.toString());
        String back = Http.getResponse(connection);
        JSONObject jsonObject = new JSONObject(back);
        if (jsonObject.getString("info").equals("success")){
            isSuccess = true;
        }
        Log.d(TAG, "postAnwer: "+back);
        return isSuccess;
    }
    /*
    采纳回答，传入问题和回答的id
     */
    public boolean accept(int qid,int aid)throws Exception{
        boolean isSuccess = false;
        HttpURLConnection connection = Http.getHttpConnection("http://bihu.jay86.com/accept.php");
        connection.connect();
        Http.postData(connection,token+"qid="+qid+"&aid="+aid);
        String back = Http.getResponse(connection);
        JSONObject jsonObject = new JSONObject(back);
        if (jsonObject.getString("info").equals("success")){
            isSuccess = true;
        }
        Log.d(TAG, "accept: "+back);
        return isSuccess;
    }

    public boolean exciting(int id,int type)throws Exception{
        boolean isSuccess = false;
        HttpURLConnection connection = Http.getHttpConnection("http://bihu.jay86.com/exciting.php");
        connection.connect();
        Http.postData(connection,token+"id="+id+"&type="+type);
        String back = Http.getResponse(connection);
        JSONObject jsonObject = new JSONObject(back);
        if (jsonObject.getString("info").equals("success")){
            isSuccess = true;
        }
        Log.d(TAG, "exciting: "+back);
        return isSuccess;
    }

    public boolean cancelExciting(int id,int type)throws Exception{
        boolean isSuccess = false;
        HttpURLConnection connection = Http.getHttpConnection("http://bihu.jay86.com/cancelExciting.php");
        connection.connect();
        Http.postData(connection,token+"id="+id+"&type="+type);
        String back = Http.getResponse(connection);
        JSONObject jsonObject = new JSONObject(back);
        if (jsonObject.getString("info").equals("success")){
            isSuccess = true;
        }
        Log.d(TAG, "cancelExciting: "+back);
        return isSuccess;
    }

    public boolean naive(int id,int type)throws Exception{
        boolean isSuccess = false;
        HttpURLConnection connection = Http.getHttpConnection("http://bihu.jay86.com/naive.php");
        connection.connect();
        Http.postData(connection,token+"id="+id+"&type="+type);
        String back = Http.getResponse(connection);
        JSONObject jsonObject = new JSONObject(back);
        if (jsonObject.getString("info").equals("success")){
            isSuccess = true;
        }
        Log.d(TAG, "naive: "+back);
        return isSuccess;
    }

    public boolean cancelNaive(int id,int type)throws Exception{
        boolean isSuccess = false;
        HttpURLConnection connection = Http.getHttpConnection("http://bihu.jay86.com/cancelNaive.php");
        connection.connect();
        Http.postData(connection,token+"id="+id+"&type="+type);
        String back = Http.getResponse(connection);
        JSONObject jsonObject = new JSONObject(back);
        if (jsonObject.getString("info").equals("success")){
            isSuccess = true;
        }
        Log.d(TAG, "cancelNaive: "+back);
        return isSuccess;
    }

    public JSONObject getQuestionList(int page,int count)throws Exception{
        HttpURLConnection connection = Http.getHttpConnection("http://bihu.jay86.com/getQuestionList.php");
        connection.connect();
        Http.postData(connection,token+"page="+(page-1)+"&count="+count);
        String back = Http.getResponse(connection);
        Log.d(TAG, "getQuestionList: "+back);
        return new JSONObject(back);
    }

    public JSONObject getAnswerList(int page,int count,int qid)throws Exception{
        HttpURLConnection connection = Http.getHttpConnection("http://bihu.jay86.com/getAnswerList.php");
        connection.connect();
        Http.postData(connection,token+"page="+(page-1)+"&count="+count+"&qid="+qid);
        String back = Http.getResponse(connection);
        Log.d(TAG, "getAnswerList: "+back);
        return new JSONObject(back);
    }

    public JSONObject getFavoriteList(int page,int count)throws Exception{
        HttpURLConnection connection = Http.getHttpConnection("http://bihu.jay86.com/getFavoriteList.php");
        connection.connect();
        Http.postData(connection,token+"page="+(page-1)+"&count="+count);
        String back = Http.getResponse(connection);
        Log.d(TAG, "getFavoriteList: "+back);
        return new JSONObject(back);
    }

    public boolean favorite(int qid)throws Exception{
        boolean isSuccess = false;
        HttpURLConnection connection = Http.getHttpConnection("http://bihu.jay86.com/favorite.php");
        connection.connect();
        Http.postData(connection,token+"qid="+qid);
        String back = Http.getResponse(connection);
        JSONObject jsonObject = new JSONObject(back);
        if (jsonObject.getString("info").equals("success")){
            isSuccess = true;
        }
        Log.d(TAG, "favor: "+back);
        return isSuccess;
    }

    public boolean cancelFavorite(int qid)throws Exception{
        boolean isSuccess = false;
        HttpURLConnection connection = Http.getHttpConnection("http://bihu.jay86.com/cancelFavorite.php");
        connection.connect();
        Http.postData(connection,token+"qid="+qid);
        String back = Http.getResponse(connection);
        JSONObject jsonObject = new JSONObject(back);
        if (jsonObject.getString("info").equals("success")){
            isSuccess = true;
        }
        Log.d(TAG, "cancelFavor: "+back);
        return isSuccess;
    }

}
