package com.xujie.lequ.http.service;


import com.xujie.lequ.data.bean.GirlsBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author wj
 * @date 2017/6/2
 * @discription  干货集中营service
 */
public interface GankService {

    interface GirlsService {
        @GET("{type}/{count}/{page}")
        Observable<GirlsBean> getGirls(
                @Path("type") String type,
                @Path("count") int count,
                @Path("page") int page
        );
    }

}
