package com.example.winterexamination.view.mainView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.winterexamination.MyApplication;
import com.example.winterexamination.R;

public class PensonFragment extends Fragment {
    public final String TAG = "*****PensonFragment*****";
    public MyApplication application;
    LayoutInflater inflater;
    ViewGroup container;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_penson_fragment, container, false);
        Button button1 = view.findViewById(R.id.person_avatar);
        Button button2 = view.findViewById(R.id.person_pass);
        Button button3 = view.findViewById(R.id.person_post);
        application.view[0] = view;
        Glide.with(view).load(application.getAvatar()).into((ImageView)view.findViewById(R.id.person_image));
        this.inflater=inflater;
        this.container=container;
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.example.winterexamination.CHAVA"));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.example.winterexamination.CHPAS"));
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.example.winterexamination.POST_1"));
            }
        });
        return view;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        new Thread(new Runnable() {
//            @SuppressLint("LongLogTag")
//            @Override
//            public void run() {
//                try {
//                    View view1 = inflater.inflate(R.layout.activity_penson_fragment, container, false);
//                    ImageView imageView = view1.findViewById(R.id.person_image);
//                    imageView.setImageBitmap(Http.getImageBitmap("https://s2.ax1x.com/2020/02/08/1Wnh36.th.png"));
//                } catch (Exception e) {
//                    Log.d(TAG, "run: "+e.toString());
//                }
//            }
//        }).start();
//    }
}
