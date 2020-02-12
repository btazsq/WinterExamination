package com.example.winterexamination.Part;

import android.util.Log;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

public class HolderData {
    private static final String TAG = "*****HolderData*****";

    public String id;
    public String title;
    public String content;
    public String name;
    public String avatar;
    public String image;
    public int excitingNum;
    public int naiveNum;
    public boolean exciting;
    public boolean naive;
    public boolean favor;

    public HolderData(JSONObject jsonObject){
        try {
            id = jsonObject.getString("id");
            title = jsonObject.getString("title");
            content =jsonObject.getString("content");
            name = jsonObject.getString("authorName");
            avatar = jsonObject.getString("authorAvatar");
            image = jsonObject.getString("images");
            excitingNum = Integer.parseInt(jsonObject.getString("exciting"));
            naiveNum = Integer.parseInt(jsonObject.getString("naive"));
            exciting = Boolean.parseBoolean(jsonObject.getString("is_exciting"));
            naive = Boolean.parseBoolean(jsonObject.getString("is_naive"));
            favor = Boolean.parseBoolean(jsonObject.getString("is_favorite"));
        } catch (JSONException e) {
            Log.d(TAG, "HolderData: "+e.toString());
        }
    }

}
