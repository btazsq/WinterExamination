package com.example.winterexamination.View.BranchView.Answer;

import android.annotation.SuppressLint;
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

import com.example.winterexamination.Part.HolderData;
import com.example.winterexamination.R;
import com.example.winterexamination.tools.RealizeAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerHolder> {
    private static final String TAG = "*****AnswerAdapter*****";
    RealizeAPI realizeAPI = null;
    Answer answer = null;

    public class AnswerHolder extends RecyclerView.ViewHolder{
        private static final String TAG = "*****AnswerHolder*****";

        public View view;
        public int position;
        public HolderData holderData = null;
        ImageView avatar,image;
        Button exciting,naive,favor;
        TextView name,title,content,exciteNum,naiveNum;

        public AnswerHolder(@NonNull View itemView) {
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
            favor.setTextColor(0x00ffffff);
        }
    }
    JSONArray jsonArray;

    public AnswerAdapter(String an){
        try {
            jsonArray = new JSONArray(an);
        } catch (JSONException e) {
            Log.d(TAG, "AnswerAdapter: "+e.toString());
        }
    }

    @NonNull
    @Override
    public AnswerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_re,parent,false);
        AnswerHolder holder = new AnswerHolder(view);
        holder.position = holder.getAdapterPosition();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerHolder holder, int position) {
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(position);
            holder.holderData = new HolderData(jsonObject);
            if (holder.holderData.avatar!=null)
            Glide.with(holder.view).load(holder.holderData.avatar).into(holder.avatar);
            if (holder.holderData.image!=null)
            Glide.with(holder.view).load(holder.holderData.image).into(holder.image);
            if (holder.holderData.name!=null)
            holder.name.setText(holder.holderData.name);
            holder.title.setText(holder.holderData.title);
            holder.content.setText(holder.holderData.content);
            holder.exciteNum.setText(String.valueOf(holder.holderData.excitingNum));
            holder.naiveNum.setText(String.valueOf(holder.holderData.naiveNum));

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Message message = new Message();
                                if (realizeAPI.accept(answer.qid,Integer.parseInt(holder.holderData.id))){
                                    message.obj = "采纳";
                                }else {
                                    message.obj = "采纳失败";
                                }
                                handler.sendMessage(message);
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
                                if (!holder.holderData.exciting){
                                    realizeAPI.exciting(Integer.parseInt(holder.holderData.id),2);
                                    message.obj = "赞";
                                }else {
                                    realizeAPI.cancelExciting(Integer.parseInt(holder.holderData.id),2);
                                    message.obj = "取消赞";
                                }
                                holder.holderData.exciting = !holder.holderData.exciting;
                                handler.sendMessage(message);
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
                                if (!holder.holderData.naive){
                                    realizeAPI.naive(Integer.parseInt(holder.holderData.id),2);
                                    message.obj = "踩";
                                }else {
                                    realizeAPI.cancelNaive(Integer.parseInt(holder.holderData.id),2);
                                    message.obj = "取消踩";
                                }
                                holder.holderData.naive = !holder.holderData.naive;
                                handler.sendMessage(message);
                            } catch (Exception e) {
                                Log.d(TAG, "naive,onClick: "+e.toString());
                            }
                        }
                    }).start();
                }
            });
        } catch (Exception e) {
            Log.d(TAG, "onBindViewHolder: "+ e.toString());
        }
    }

    @SuppressLint("HandlerLeak")
    android.os.Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Toast.makeText(answer,(String)msg.obj,Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public int getItemCount() {
        //if (jsonArray==null)return 0;
        return jsonArray.length();
    }
}
