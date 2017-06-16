package com.xujie.lequ.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

public class HomeActivity extends AppActivity {


    private ActionBarDrawerToggle mDrawerToggle;
    private String[] data = {"我的","设置","会员"};
    private ArrayAdapter arrayAdapter;

    @BindView(R.id.menu_listview)
    ListView menuListview;
    @BindView(R.id.home_slidemenu)
    DrawerLayout homeSlidemenu;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;


    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lequ, menu);
        return true;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return GirlsFragment.getInstance();
    }

    protected void initView() {
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //创建返回键，并实现打开关/闭监听
        //ActionBarDrawerToggle作用是在toolbar上创建一个点击弹出drawer的按钮而已
        mDrawerToggle = new ActionBarDrawerToggle(this, homeSlidemenu, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerToggle.syncState();
        homeSlidemenu.addDrawerListener(mDrawerToggle);
        //不写这句话，是没有按钮显示的
       
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,data);
        menuListview.setAdapter(arrayAdapter);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.girls_fragment;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //两秒之内按返回键就会退出
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Snackbar.make(fab, "再按一次退出程序哦~", Snackbar.LENGTH_LONG).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
