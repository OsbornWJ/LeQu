package com.xujie.lequ.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xujie.lequ.R;

/**
 * Created by Administrator on 2017/2/3.
 */
public class BackFragment extends Fragment {

    private LayoutInflater mInflate = null;
    private View mConvertView = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mInflate = inflater;
        this.mConvertView = mInflate.inflate(R.layout.back, null);

        return mConvertView;
    }
}
