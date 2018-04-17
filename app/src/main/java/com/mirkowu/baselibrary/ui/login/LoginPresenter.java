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

}
