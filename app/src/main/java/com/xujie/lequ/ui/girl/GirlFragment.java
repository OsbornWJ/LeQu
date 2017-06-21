package com.xujie.lequ.ui.girl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xujie.lequ.R;
import com.xujie.lequ.adapter.GirlAdapter;
import com.xujie.lequ.app.Constant;
import com.xujie.lequ.base.BaseFragment;
import com.xujie.lequ.data.bean.GirlsBean;
import com.xujie.lequ.util.BitmapUtil;
import com.xujie.lequ.util.LogUtil;
import com.xujie.lequ.widget.PinchImageView;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author wj
 * @date 2017/6/7
 * @discription
 */
public class GirlFragment extends BaseFragment implements ViewPager.OnPageChangeListener {


    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.rootView)
    LinearLayout rootView;
    private GirlAdapter girlAdapter;
    
    private ArrayList<GirlsBean.ResultsEntity> datas;
    private int current;
    
    
    private OnGirlChange changeListener;

    public interface OnGirlChange{
        void change(int color);
    }

    public static GirlFragment newInstance(ArrayList<Parcelable> datas, int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("girls", datas);
        bundle.putInt("position", position);
        GirlFragment fragment = new GirlFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            changeListener = (OnGirlChange) activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
    

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null){
            datas = bundle.getParcelableArrayList("girls");
            current = bundle.getInt("position");
        }
        girlAdapter = new GirlAdapter(mActivity, datas);
        viewPager.setAdapter(girlAdapter);
        viewPager.setCurrentItem(current);
        viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        getColor();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_girl;
    }

    @Override
    protected void initPresenter() {}

    public void getColor(){
        PinchImageView imageView = getCurrentImageView();
        Bitmap bitmap = BitmapUtil.drawableToBitmap(imageView.getDrawable());
        Palette.Builder builder = Palette.from(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vir = palette.getVibrantSwatch();
                if (vir == null)
                    return;
                changeListener.change(vir.getRgb());
            }
        });
    }
    
    public void saveGirl(){
        String imgUrl = datas.get(viewPager.getCurrentItem()).getUrl();
        PinchImageView imageView = getCurrentImageView();
        Bitmap bitmap = BitmapUtil.drawableToBitmap(imageView.getDrawable());

        Observable.just(BitmapUtil.saveBitmap(bitmap, Constant.dir, imgUrl.substring(imgUrl.lastIndexOf("/") + 1, imgUrl.length()), true))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean isSuccess) {
                        if (isSuccess){
                            Snackbar.make(rootView, "下载完成", Snackbar.LENGTH_LONG).show();
                        } else {
                            Snackbar.make(rootView, "下载出错了", Snackbar.LENGTH_LONG).show();
                        }
                    }
                });
    }
    
    public void shareGirl(){
        PinchImageView imageView = getCurrentImageView();
        Drawable drawable = imageView.getDrawable();
        if (drawable != null){
            Bitmap bitmap = BitmapUtil.drawableToBitmap(drawable);
            Observable.just(BitmapUtil.saveBitmap(bitmap, Constant.dir, "share.jpg", false))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean isSuccess) {
                            if (isSuccess){
                                //文件得到url
                                Uri imageUri = Uri.fromFile(new File(Constant.dir + "share.jpg"));
                                LogUtil.d("share", "Uri:" + imageUri);
                                Intent shareIntent = new Intent();
                                shareIntent.setAction(Intent.ACTION_SEND);
                                shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                                shareIntent.setType("image/*");
                                startActivity(Intent.createChooser(shareIntent, "分享到"));
                            } else {
                                Snackbar.make(rootView, "分享出错了", Snackbar.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }
    
    private PinchImageView getCurrentImageView(){
        View currentView = girlAdapter.getPrimaryItem();
        if (currentView == null){
            return null;
        }
        PinchImageView imageView = (PinchImageView) currentView.findViewById(R.id.img);
        if (imageView == null){
            return null;
        }
        return imageView;
    }
    
}
