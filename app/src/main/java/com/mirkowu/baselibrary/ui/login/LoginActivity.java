package com.mirkowu.baselibrary.ui.login;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mirkowu.baselibrary.R;
import com.mirkowu.baselibrary.base.ToolbarActivity;
import com.mirkowu.baselibrary.bean.LoginResultBean;
import com.mirkowu.basetoolbar.BaseToolbar;
import com.softgarden.baselibrary.utils.ToastUtil;

import butterknife.BindView;

public class LoginActivity extends ToolbarActivity<LoginPresenter> implements LoginContract.Display {
    @BindView(R.id.login)
    Button btnLogin;
    @BindView(R.id.accountInput)
    EditText accountInput;
    @BindView(R.id.passwordInput)
    EditText passwordInput;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initialize() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickLogin();
            }
        });
    }

    private void clickLogin() {
        String account = accountInput.getText().toString().trim();
        String pwd = passwordInput.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            ToastUtil.s("账号不能为空");
            return;
        }

        if (TextUtils.isEmpty(pwd)) {
            ToastUtil.s("密码不能为空");
            return;
        }
        getPresenter().login(account, pwd);
    }

    @Nullable
    @Override
    protected BaseToolbar.Builder setToolbar(@NonNull BaseToolbar.Builder builder) {
        return builder.setTitle("登录验证");
    }

    @Override
    public void enterMain(LoginResultBean bean) {
        Toast.makeText(this, "登录成功，进入首页", Toast.LENGTH_SHORT).show();
    }
}
