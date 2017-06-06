package com.xujie.lequ.ui.home;

import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewStub;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.xujie.lequ.R;
import com.xujie.lequ.adapter.GirlsAdapter;
import com.xujie.lequ.base.BaseFragment;
import com.xujie.lequ.data.bean.GirlsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author wj
 * @date 2017/6/6
 * @discription
 */
public class GirlsFragment extends BaseFragment<GirlsPresenter> implements GirlsContract.View {

    @BindView(R.id.network_error_layout)
    ViewStub networkErrorLayout;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;

    private View netWorkErrorView;

    private List<GirlsBean.ResultsEntity> data;
    private GirlsAdapter mAdapter;
    private int page = 1;
    private int size = 20;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new GirlsPresenter();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        initRecycleView();
        mPresenter.start();
    }

    private void initRecycleView(){
        data = new ArrayList<>(0);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new GirlsAdapter(getContext());

        recyclerView.setAdapterWithProgress(mAdapter);



    }

    @Override
    public void refresh(List<GirlsBean.ResultsEntity> datas) {

    }

    @Override
    public void load(List<GirlsBean.ResultsEntity> datas) {
        data.addAll(datas);
        mAdapter.addAll(datas);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showNormal() {

    }
}
