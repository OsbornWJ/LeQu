package com.xujie.lequ.ui.home;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.xujie.lequ.R;
import com.xujie.lequ.data.bean.GirlsBean;

/**
 * @author wj
 * @date 2017/6/6
 * @discription
 */
public class GirlsViewHolder extends BaseViewHolder<GirlsBean.ResultsEntity> {

    private ImageView img;

    public GirlsViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_girl);
        img = $(R.id.girl_img);
    }

    @Override
    public void setData(GirlsBean.ResultsEntity data) {
        super.setData(data);
        Glide.with(getContext())
                .load(data.getUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(img);
    }
}
