package com.xujie.lequ.app;

import com.xujie.lequ.util.FileUtil;

/**
 * @author wj
 * @date 2017/6/5
 * @discription
 */
public class Constant {

    public static final String GANHUO_API = "http://gank.io/api/data/";
    public static final String GANHUO_FULI = "福利";
    public static final String GANHUO_IOS = "IOS";
    public static final String GANHUO_ANDROID = "Android";

    public static final String dir = FileUtil.getDiskCacheDir(LeQuApp.getInstance()) + "/girls";
}
