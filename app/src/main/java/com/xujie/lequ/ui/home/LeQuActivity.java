package com.xujie.lequ.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.xujie.lequ.R;
import com.xujie.lequ.base.AppActivity;
import com.xujie.lequ.base.BaseFragment;

import butterknife.BindView;

public class LeQuActivity extends AppActivity {

    private ActionBarDrawerToggle mDrawerToggle;
    private String[] data = {"我的","设置","会员"};
    private ArrayAdapter arrayAdapter;

    @BindView(R.id.home_toolbar)
    Toolbar homeToolbar;
    @BindView(R.id.iv_menu)
    ImageView ivMenu;
    @BindView(R.id.menu_listview)
    ListView menuListview;
    @BindView(R.id.home_slidemenu)
    DrawerLayout homeSlidemenu;

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_lequ;
    }

    @Override
    protected int getFragmentContentId() {
        return super.getFragmentContentId();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lequ, menu);
        return true;
    }


    protected void initView() {
        homeToolbar.setTitle("乐趣");
        homeToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(homeToolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //创建返回键，并实现打开关/闭监听
        //ActionBarDrawerToggle作用是在toolbar上创建一个点击弹出drawer的按钮而已
        mDrawerToggle = new ActionBarDrawerToggle(this, homeSlidemenu, R.string.navigation_drawer_open, R.string.navigation_drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

        };
        homeSlidemenu.addDrawerListener(mDrawerToggle);
        //不写这句话，是没有按钮显示的
        mDrawerToggle.syncState();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,data);
        menuListview.setAdapter(arrayAdapter);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}

