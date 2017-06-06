package com.xujie.lequ.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.xujie.lequ.data.bean.GirlsBean;
import com.xujie.lequ.ui.home.GirlsViewHolder;

/**
 * @author wj
 * @date 2017/6/6
 * @discription
 */
public class GirlsAdapter extends RecyclerArrayAdapter<GirlsBean.ResultsEntity> {

    private OnItemClicklistener mOnItemClicklistener;

    public GirlsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new GirlsViewHolder(parent);
    }

    @Override
    public void OnBindViewHolder(final BaseViewHolder holder, final int position) {
        super.OnBindViewHolder(holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClicklistener != null){
                    mOnItemClicklistener.onItemClick(position, holder);
                }
            }
        });
    }

    public interface OnItemClicklistener {
        void onItemClick(int position, BaseViewHolder viewHolder);
    }

    public void setmOnItemClicklistener(OnItemClicklistener mOnItemClicklistener) {
        this.mOnItemClicklistener = mOnItemClicklistener;
    }
}
