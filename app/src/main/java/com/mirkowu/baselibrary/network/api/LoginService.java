package com.mirkowu.baselibrary.network.api;


import com.mirkowu.baselibrary.bean.LoginResultBean;
import com.mirkowu.baselibrary.bean.ResponseResult;
import com.mirkowu.baselibrary.network.BaseBean;

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

    //    /**
    //     * 登录验证
    //     * @param reqAct
    //     * @param userName
    //     * @param password
    //     * @return
    //     */
    //    @FormUrlEncoded
    //    @POST(HostUrl.LOGIN_APP_LOGIN)
    //    Observable<ResponseResult<LoginResultBean>> login(@Field("reqAct") String reqAct,
    //                                                      @Field("userName") String userName,
    //                                                      @Field("password") String password
    //    );


    @FormUrlEncoded
    @POST(HostUrl.LOGIN_APP_LOGIN)
    Observable<BaseBean<String>> loginPhone(@Field("loginType") int loginType,
                                            @Field("phone") String phone,
                                            @Field("password") String password
    );


    @FormUrlEncoded
    @POST(HostUrl.LOGIN_APP_LOGIN)
    Observable<BaseBean<String>> loginThridParty(@Field("loginType") int loginType,
                                                 @Field("openid") String openid,
                                                 @Field("name") String name,
                                                 @Field("headerImg") String headerImg);

}
