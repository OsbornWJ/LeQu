package com.xujie.lequ.ui.girl;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import com.xujie.lequ.R;
import com.xujie.lequ.base.BaseFragment;

import java.util.ArrayList;

/**
 * @author wj
 * @date 2017/6/7
 * @discription
 */
public class GirlFragment extends BaseFragment {



    public static GirlFragment newInstance(ArrayList<Parcelable> datas, int position){
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("girls", datas);
        bundle.putInt("position", position);
        GirlFragment fragment = new GirlFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_girl;
    }

    @Override
    protected void initPresenter() {}


}
