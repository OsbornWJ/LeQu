package com.xujie.lequ.ui.girl;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xujie.lequ.R;
import com.xujie.lequ.base.AppActivity;
import com.xujie.lequ.base.BaseFragment;
import butterknife.BindView;

public class GirlActivity extends AppActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    GirlFragment girlFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_girl;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.girl_fragment;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        girlFragment = GirlFragment.newInstance(getIntent().getParcelableArrayListExtra("girls"), getIntent().getIntExtra("position", 0));
        return girlFragment;
    }

    private void initView(){
        toolbar.setTitle("乐趣");
        toolbar.setTitleTextColor(Color.WHITE);
//        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishActivity();
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    private void finishActivity() {
        finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }
}
