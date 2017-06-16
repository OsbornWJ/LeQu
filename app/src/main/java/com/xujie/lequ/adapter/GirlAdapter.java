package com.xujie.lequ.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.xujie.lequ.R;
import com.xujie.lequ.data.bean.GirlsBean;
import com.xujie.lequ.widget.PinchImageView;

import java.util.ArrayList;

/**
 * @author 文杰
 * @date 2017/6/18.
 * @Description 图片查看adapter
 */
public class GirlAdapter extends PagerAdapter {
    
    private Context mContext;
    private ArrayList<GirlsBean.ResultsEntity> mDatas;
    private LayoutInflater mInflate;
    private View mCurrnetView;
    
    public GirlAdapter(Context context, ArrayList<GirlsBean.ResultsEntity> datas){
        mContext = context;
        mDatas = datas;
        mInflate = LayoutInflater.from(context);
    }
    
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        mCurrnetView = (View) object;
    }
    
    public View getPrimaryItem(){
        return mCurrnetView;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final String imageUrl = mDatas.get(position).getUrl();
        View view = mInflate.inflate(R.layout.item_girl_detail, container, false);
        PinchImageView imageView = (PinchImageView) view.findViewById(R.id.img);
        Glide.with(mContext)
                .load(imageUrl)
                .thumbnail(0.2f)
                .into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
