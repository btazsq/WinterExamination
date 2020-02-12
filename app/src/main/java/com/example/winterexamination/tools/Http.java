package com.example.winterexamination.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import com.example.winterexamination.R;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Http {
    private static final String TAG="*****Http*****";

    public static HttpURLConnection getHttpConnection(String address)throws Exception{
        URL url=new URL(address);
        HttpURLConnection connection=(HttpURLConnection)url.openConnection();
        connection.setReadTimeout(8000);
        connection.setConnectTimeout(8000);
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        return connection;
    }
    /*
    返回服务器传来的数据
     */
    public static String getResponse(HttpURLConnection connection){
        try {
            int k=connection.getResponseCode();
            Log.d(TAG, k+"");
            if (k==200){
                InputStream in =connection.getInputStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                StringBuilder response=new StringBuilder();
                String line;
                while ((line=reader.readLine())!=null){
                    response.append(line);
                }
                in.close();
                reader.close();
                return response.toString();
            }
            return "";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
    /*
    把参数连接成字符串
     */
    public static String linkParam(String[] key,String[] value){
        int length = key.length>value.length?value.length:key.length;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<length;i++){
            stringBuilder.append(key[i]+"="+value[i]);
            if (i<length-1){
                stringBuilder.append("&");
            }
        }
        return stringBuilder.toString();
    }
    /*
    提交参数之类的数据
     */
    public static void postData(HttpURLConnection connection,String data)throws Exception{
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.writeBytes(data);
        dataOutputStream.flush();
        dataOutputStream.close();
    }
    /*
    加载网络图片
     */
    public static Bitmap getImageBitmap(String url){
        Bitmap bitmap = null;
        try {
            HttpURLConnection connection = (HttpURLConnection)(new URL(url).openConnection());
            connection.setRequestMethod("GET");
            connection.setReadTimeout(8000);
            connection.setConnectTimeout(8000);
            connection.setDoInput(true);
            connection.connect();
            InputStream in = connection.getInputStream();
            Log.d(TAG, "code:"+connection.getResponseCode());
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
            connection.disconnect();
        } catch (Exception e) {
            Log.d(TAG, "getImageBitmap: "+e.toString());
        }
        return bitmap;
    }

    public static byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream outstream = new ByteArrayOutputStream();
        byte[] buffer = new byte[0xffff]; // 用数据装
        int len = -1;
        while ((len = is.read(buffer)) != -1) {
            outstream.write(buffer, 0, len);
        }
        outstream.close();   // 关闭流一定要记得。
        return outstream.toByteArray();
    }

}
