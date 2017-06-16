package com.xujie.lequ.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewStub;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.xujie.lequ.R;
import com.xujie.lequ.adapter.GirlsAdapter;
import com.xujie.lequ.base.BaseFragment;
import com.xujie.lequ.data.bean.GirlsBean;
import com.xujie.lequ.ui.girl.GirlActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author wj
 * @date 2017/6/6
 * @discription
 */
public class GirlsFragment extends BaseFragment<GirlsPresenter> implements GirlsContract.View, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.network_error_layout)
    ViewStub networkErrorLayout;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;

    private View netWorkErrorView;

    private ArrayList<GirlsBean.ResultsEntity> data;
    private GirlsAdapter mAdapter;
    private int page = 1;
    private int size = 20;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    public static GirlsFragment getInstance() {
        GirlsFragment mainFragment = new GirlsFragment();
        return mainFragment;
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

        mAdapter.setMore(R.layout.load_more_layout, this);
        mAdapter.setNoMore(R.layout.no_more_layout);
        mAdapter.setError(R.layout.error_layout);

        recyclerView.setRefreshListener(this);
        mAdapter.setmOnItemClicklistener(new GirlsAdapter.OnItemClicklistener() {
            @Override
            public void onItemClick(int position, BaseViewHolder holder) {
                Intent intent = new Intent(mActivity, GirlActivity.class);
                intent.putParcelableArrayListExtra("girls", data);
                intent.putExtra("position", position);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(holder.itemView, holder.itemView.getWidth() / 2, holder.itemView.getHeight() / 2, 0, 0);
                startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public void refresh(List<GirlsBean.ResultsEntity> datas) {
        data.clear();
        data.addAll(datas);
        mAdapter.clear();
        mAdapter.addAll(datas);
    }

    @Override
    public void load(List<GirlsBean.ResultsEntity> datas) {
        data.addAll(datas);
        mAdapter.addAll(datas);
    }

    @Override
    public void showError() {
        recyclerView.showError();

        if (netWorkErrorView != null){
            netWorkErrorView.setVisibility(View.VISIBLE);
            return;
        }
        netWorkErrorView = networkErrorLayout.inflate();
    }

    @Override
    public void showNormal() {
        if (netWorkErrorView != null){
            netWorkErrorView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoadMore() {
        if (data.size() % 2 == 0){
            page ++;
            mPresenter.getGirls(page, size, false);
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.getGirls(1, size, true);
        page = 1;
    }
}
