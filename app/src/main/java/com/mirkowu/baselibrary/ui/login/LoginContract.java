package com.mirkowu.baselibrary.ui.login;

import com.mirkowu.baselibrary.bean.LoginResultBean;
import com.softgarden.baselibrary.base.IBaseDisplay;
import com.softgarden.baselibrary.base.IBasePresenter;

/**
 * @author by DELL
 * @date on 2018/4/12
 * @describe
 */
public class LoginContract {
    interface Display extends IBaseDisplay {
        void enterMain(LoginResultBean bean);
    }

    interface Presenter extends IBasePresenter<Display> {
        void login(String username,String pwd);
    }
}
