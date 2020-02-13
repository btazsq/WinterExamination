package com.example.winterexamination.view.mainView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.winterexamination.contract.TotalContract;
import com.example.winterexamination.MyApplication;
import com.example.winterexamination.R;
import com.example.winterexamination.tools.MyException;
import com.example.winterexamination.tools.RealizeAPI;

import org.json.JSONArray;
import org.json.JSONObject;

public class FavorFragment extends Fragment implements TotalContract.Surf.SurfView {
    private static final String TAG = "*****FavorFragment*****";

    private MyException exception = null;
    public MyApplication application;
    public View thisView;
    private int whichPage;
    public SurfBar surfBar;
    public RecyclerView recyclerView;
    SurfAdapter surfAdapter;
    RealizeAPI realizeAPI;

    LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_favor_fragment, container, false);
        surfBar = view.findViewById(R.id.favor_bar);
        surfBar.setContext(this);
        this.thisView = view;
        recyclerView = view.findViewById(R.id.favor_re);
        surfAdapter = new SurfAdapter();
        recyclerView.setAdapter(surfAdapter);
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case 1:{
                    exception = null;
                    try {
                        surfBar.setWhichPage(whichPage);
                        surfAdapter.jsonArray = (JSONArray) msg.obj;
                        SurfAdapter adapter = (SurfAdapter) recyclerView.getAdapter();
                        if (adapter.surfView == null) {
                            adapter.surfView = FavorFragment.this;
                        }
                        adapter.application = application;
                        adapter.notifyDataSetChanged();
                    } catch (Exception e) {
                        Log.d(TAG, "handleMessage: "+e.toString());
                    }
                }break;
                case 2:{
                    exception = new MyException("null");
                }break;
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        whichPage = 1;
        realizeAPI = new RealizeAPI(application.getToken());
    }

    @Override
    public int getWhichPage() {
        return whichPage;
    }

    @Override
    public void setWhichPage(int whichPage) throws Exception{
        if(exception!=null)throw exception;
        this.whichPage = whichPage;
    }

    @Override
    public RecyclerView getRe() {
        return recyclerView;
    }

    @Override
    public SurfAdapter getAdapter() {
        return surfAdapter;
    }

    @Override
    public void getData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject object = realizeAPI.getFavoriteList(whichPage,10);
                    if (!object.getString("info").equals("success")){
                        Message message = new Message();
                        message.what = 2;
                        handler.sendMessage(message);
                        throw new MyException("获取列表失败");
                    }
                    JSONObject jsonObject = new JSONObject(object.getString("data"));
                    JSONArray jsonArray = jsonObject.getJSONArray("questions");
                    Message message = new Message();
                    message.what = 1;
                    message.obj = jsonArray;
                    handler.sendMessage(message);
                } catch (Exception e) {
                    Log.d(TAG, "run: "+e.toString());
                }
            }
        }).start();
    }
}
