package com.example.winterexamination.view.mainView;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.winterexamination.MyApplication;
import com.example.winterexamination.R;
import com.example.winterexamination.unit.TitleOne;
import com.example.winterexamination.tools.RealizeAPI;
import com.google.android.material.tabs.TabLayout;

public class EnterActivity extends AppCompatActivity {

    private static final String TAG = "*****EnterActivity*****";
    public RealizeAPI realizeAPI;
    private final int count =20;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        ((TitleOne)findViewById(R.id.enter_title)).setTitle("BiHu");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null)actionBar.hide();
        realizeAPI = new RealizeAPI(((MyApplication)getApplication()).getToken());
        ((MyApplication)getApplication()).setCount(count);
        ((MyApplication)getApplication()).setRealizeAPI(realizeAPI);
        TabLayout tabLayout = findViewById(R.id.enter_tab);
        tabLayout.addTab(tabLayout.newTab().setText("收藏"));
        tabLayout.addTab(tabLayout.newTab().setText("浏览"));
        tabLayout.addTab(tabLayout.newTab().setText("个人"));
        ViewPager viewPager = findViewById(R.id.enter_viewpager);
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        adapter.application=(MyApplication)getApplication();
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
