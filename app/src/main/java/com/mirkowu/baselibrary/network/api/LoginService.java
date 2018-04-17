package com.mirkowu.baselibrary.network.api;


import com.mirkowu.baselibrary.bean.LoginResultBean;
import com.mirkowu.baselibrary.bean.ResponseResult;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface LoginService {

    @FormUrlEncoded
    @POST(HostUrl.LOGIN_APP_LOGIN)
    Observable<ResponseResult<LoginResultBean>> login(@Field("phone") String reqAct,
                                                      @Field("password") String userName
    );

}
