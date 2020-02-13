package com.example.winterexamination.view.mainView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.winterexamination.contract.TotalContract;
import com.example.winterexamination.MyApplication;
import com.example.winterexamination.part.HolderData;
import com.example.winterexamination.R;
import com.example.winterexamination.tools.RealizeAPI;

import org.json.JSONArray;
import org.json.JSONObject;

public class SurfAdapter extends RecyclerView.Adapter<SurfAdapter.ViewHolder> {
    private static final String TAG = "*****SurfAdapter*****";

    public MyApplication application;
    public JSONArray jsonArray;
    public RealizeAPI realizeAPI = null;
    public TotalContract.Surf.SurfView surfView = null;

    public class ViewHolder extends RecyclerView.ViewHolder{

        public View view;
        public int position;
        public HolderData holderData = null;
        ImageView avatar,image;
        Button exciting,naive,favor;
        TextView name,title,content,exciteNum,naiveNum;

        @SuppressLint("HandlerLeak")
        android.os.Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                //SurfAdapter.this.surfView.getData();
                //ViewHolder.this.position = ViewHolder.this.getAdapterPosition();
                //SurfAdapter.this.notifyDataSetChanged();
                if (msg.what == 100){
                    Toast.makeText(view.getContext(),(String)msg.obj,Toast.LENGTH_SHORT).show();
                }
            }
        };

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            avatar = itemView.findViewById(R.id.item_avatar);
            image = itemView.findViewById(R.id.item_image);
            exciting = itemView.findViewById(R.id.item_excite_button);
            naive = itemView.findViewById(R.id.item_naive_button);
            favor = itemView.findViewById(R.id.item_favor_button);
            name = itemView.findViewById(R.id.item_name);
            title = itemView.findViewById(R.id.item_title);
            content = itemView.findViewById(R.id.item_content);
            exciteNum = itemView.findViewById(R.id.item_excite_text);
            naiveNum = itemView.findViewById(R.id.item_naive_text);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    handler.sendMessage(new Message());
                }
            },16);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_re,parent,false);

        return new ViewHolder(view);
    }

    @SuppressLint("HandlerLeak")
    android.os.Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            SurfAdapter.this.notifyDataSetChanged();
        }
    };

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            if (realizeAPI==null)realizeAPI = new RealizeAPI(application.getToken());

                holder.holderData = new HolderData(jsonArray.getJSONObject(position));

            Glide.with(holder.view).load(holder.holderData.avatar).into(holder.avatar);
            Glide.with(holder.view).load(holder.holderData.image).into(holder.image);
            holder.name.setText(holder.holderData.name);
            holder.title.setText(holder.holderData.title);
            holder.content.setText(holder.holderData.content);

            holder.position = holder.getAdapterPosition();
            Log.d(TAG, "onBindViewHolder: "+ holder.position);

            try {
                //holder.position = holder.getAdapterPosition();
                int p = holder.position;
                Log.d(TAG, "holder.p:"+p);
                if (realizeAPI==null)realizeAPI = new RealizeAPI(application.getToken());
                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Intent intent = new Intent("com.example.winterexamination.POST_2");
                                    JSONObject jsonObject = realizeAPI.getAnswerList(1,10,Integer.parseInt(holder.holderData.id));
                                    JSONObject data = jsonObject.getJSONObject("data");
                                    intent.putExtra("JSON",data.getString("answers"));
                                    intent.putExtra("qid",holder.holderData.id);
                                    if (jsonObject.getString("info").equals("success"))
                                    v.getContext().startActivity(intent);
                                } catch (Exception e) {
                                    Log.d(TAG, "run: "+e.toString());
                                }
                            }
                        }).start();
                    }
                });
                holder.exciting.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Message message = new Message();
                                    message.what = 100;
                                    if (!holder.holderData.exciting){
                                        realizeAPI.exciting(Integer.parseInt(holder.holderData.id),1);
                                        message.obj = "赞";
                                    }else {
                                        realizeAPI.cancelExciting(Integer.parseInt(holder.holderData.id),1);
                                        message.obj = "取消赞";
                                    }
                                    holder.holderData.exciting = !holder.holderData.exciting;
                                    holder.handler.sendMessage(message);
                                } catch (Exception e) {
                                    Log.d(TAG, "exciting,onClick: "+e.toString());
                                }
                            }
                        }).start();
                    }
                });
                holder.naive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Message message = new Message();
                                    message.what = 100;
                                    if (!holder.holderData.naive){
                                        realizeAPI.naive(Integer.parseInt(holder.holderData.id),1);
                                        message.obj = "踩";
                                    }else {
                                        realizeAPI.cancelNaive(Integer.parseInt(holder.holderData.id),1);
                                        message.obj = "取消踩";
                                    }
                                    holder.holderData.naive = !holder.holderData.naive;
                                    holder.handler.sendMessage(message);
                                } catch (Exception e) {
                                    Log.d(TAG, "naive,onClick: "+e.toString());
                                }
                            }
                        }).start();
                    }
                });
                holder.favor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Message message = new Message();
                                    message.what = 100;
                                    if (!holder.holderData.favor){
                                        realizeAPI.favorite(Integer.parseInt(holder.holderData.id));
                                        message.obj = "收藏";
                                    }else {
                                        realizeAPI.cancelFavorite(Integer.parseInt(holder.holderData.id));
                                        message.obj = "取消收藏";
                                    }
                                    holder.holderData.favor = !holder.holderData.favor;
                                    holder.handler.sendMessage(message);
                                } catch (Exception e) {
                                    Log.d(TAG, "favorite,onClick: "+e.toString());
                                }
                            }
                        }).start();
                    }
                });
                //Log.d(TAG, "onCreateViewHolder: "+"sssssssssssssssssssssssssssssssssssss");
            } catch (Exception e) {
                Log.d(TAG, "onCreateViewHolder: "+e.toString());
            }

            holder.exciteNum.setText(String.valueOf(holder.holderData.excitingNum));
            holder.naiveNum.setText(String.valueOf(holder.holderData.naiveNum));

            if (holder.holderData.exciting){
                holder.exciting.setBackgroundColor(0x00CCDD);
            }else {
                holder.exciting.setBackgroundColor(0x000000);
            }
            if (holder.holderData.naive){
                holder.naive.setBackgroundColor(0xD81B60);
            }else {
                holder.naive.setBackgroundColor(0x000000);
            }
            if (holder.holderData.favor){
                holder.favor.setBackgroundColor(0x00CCDD);
            }else {
                holder.favor.setBackgroundColor(0x000000);
            }
        } catch (Exception e) {
            Log.d(TAG, "run: "+e.toString()+";holder.exciteNum:"+holder.exciteNum);
        }
    }

    @Override
    public int getItemCount() {
        if (jsonArray==null)return 0;
        return jsonArray.length();
    }

    public void setData(JSONArray jsonArray){
        this.jsonArray = jsonArray;
    }
}
