package com.mirkowu.baselibrary.ui.login;

import android.support.annotation.Nullable;

import com.mirkowu.baselibrary.bean.LoginResultBean;
import com.mirkowu.baselibrary.network.NetworkTransformer;
import com.mirkowu.baselibrary.network.RetrofitClient;
import com.mirkowu.baselibrary.network.RxCallback;
import com.softgarden.baselibrary.base.BasePresenter;

/**
 * @author by DELL
 * @date on 2018/4/12
 * @describe
 */
public class LoginPresenter extends BasePresenter<LoginContract.Display> implements LoginContract.Presenter {
    @Override
    public void login(String username, String pwd) {
        RetrofitClient.getLoginService()
                .login(username, pwd)
                .compose(new NetworkTransformer<>(mView))
                .subscribe(new RxCallback<LoginResultBean>() {
                    @Override
                    public void onSuccess(@Nullable LoginResultBean data) {
                        mView.enterMain(data);
                    }
                });
    }

    //
    //    @Override
    //    public void getIndexData() {
    ////        RetrofitClient.getHomeService()
    ////                .getIndexData()
    ////                .compose(new NetworkTransformer<>(mView))
    ////                .subscribe(new RxCallback<IndexBean>() {
    ////                    @Override
    ////                    public void onSuccess(@Nullable IndexBean data) {
    ////                        mView.getIndexData(data);
    ////                    }
    ////                });
    //    }
    //
    //    @Override
    //    public void switchOnOff() {
    ////        RetrofitClient.getHomeService()
    ////                .switchOnOff()
    ////                .compose(new NetworkTransformer<>(mView))
    ////                .subscribe(new RxCallback<String>() {
    ////                    @Override
    ////                    public void onSuccess(@Nullable String data) {
    ////                        mView.switchOnOff(data);
    ////                    }
    ////                });
    //    }
    //
    //    @Override
    //    public void switchBluetooth() {
    ////        RetrofitClient.getHomeService()
    ////                .switchBluetooth()
    ////                .compose(new NetworkTransformer<>(mView))
    ////                .subscribe(new RxCallback<String>() {
    ////                    @Override
    ////                    public void onSuccess(@Nullable String data) {
    ////                        mView.switchBluetooth(data);
    ////                    }
    ////                });
    //    }
}
