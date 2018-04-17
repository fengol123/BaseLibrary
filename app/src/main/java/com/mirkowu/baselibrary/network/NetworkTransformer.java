package com.mirkowu.baselibrary.network;


import com.mirkowu.baselibrary.bean.ResponseResult;
import com.mirkowu.baselibrary.utils.NetworkUtil;
import com.softgarden.baselibrary.base.IBaseDisplay;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * RxJava2 转换器 用于网络加载数据 已实现功能有：
 * <p>
 * 1.检测有无网络
 * 2.加载网络时显示加载框 结束是隐藏
 * 3.控制RxJava生命周期，防止内存泄漏
 */
public class NetworkTransformer<T> implements ObservableTransformer<ResponseResult<T>, T> {
    private IBaseDisplay mView;

    public NetworkTransformer(IBaseDisplay mView) {
        if (mView == null)
            throw new RuntimeException("IBaseDisplay is not NULL");
        this.mView = mView;
    }

    //    @Override
    //    public ObservableSource<T> apply(Observable<BaseBean<T>> upstream) {
    //        return upstream
    //                .subscribeOn(Schedulers.io())
    //                .observeOn(AndroidSchedulers.mainThread())
    //                .doOnSubscribe(disposable -> {
    //                    if (!NetworkUtil.isConnected(mView.getContext())) {
    //                        disposable.dispose();
    //                        NetworkUtil.showNoNetWorkDialog(mView.getContext());
    //                    } else {
    //                        mView.showProgressDialog();
    //                    }
    //                })
    //                .doFinally(() -> mView.hideProgressDialog())
    //                .map(new Function<BaseBean<T>, BaseBean<T>>() {
    //                    @Override
    //                    public BaseBean<T> apply(BaseBean<T> baseBean) throws Exception {
    //                        if (baseBean.code == 1) {
    //                            return baseBean;
    //                        } else {
    //                            //                        if (baseBean.code == -1) {
    //                            //                            mView.showReLoginDialog();
    //                            //                        }
    //                            throw new ApiException(baseBean.code, baseBean.msg);
    //                        }
    //                    }
    //                })
    //                .map(new Function<BaseBean<T>, T>() {
    //                    @Override
    //                    public T apply(BaseBean<T> tBaseBean) throws Exception {
    //                        if (tBaseBean.data != null)
    //                            return tBaseBean.data;
    //                        //返回空数据时 抛出一个异常让CallBack处理
    //                        throw new RxJava2NullException();
    //                    }
    //                })
    //                .compose(mView.bindToLifecycle());
    //
    //    }

    @Override
    public ObservableSource<T> apply(Observable<ResponseResult<T>> upstream) {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    if (!NetworkUtil.isConnected(mView.getContext())) {
                        disposable.dispose();
                        NetworkUtil.showNoNetWorkDialog(mView.getContext());
                    } else {
                        mView.showProgressDialog();
                    }
                })
                .doFinally(() -> mView.hideProgressDialog())
                .map(new Function<ResponseResult<T>, ResponseResult<T>>() {
                    @Override
                    public ResponseResult<T> apply(ResponseResult<T> responseResult) throws Exception {
                        if (responseResult.status == 1) {
                            return responseResult;
                        } else if (responseResult.status == 0) {
                            mView.hideProgressDialog();
                            //TODO   ToastUtil.s(baseBean.errorMsg); 还是放到activity 和fragment 显示吧
                            throw Exceptions.propagate(new ApiException(responseResult.status, responseResult.info));
                        }
                        return responseResult;
                    }
                })
                .map(new Function<ResponseResult<T>, T>() {
                    @Override
                    public T apply(ResponseResult<T> responseResult) throws Exception {
                        if (responseResult.data != null)
                            return responseResult.data;
                        //返回空数据时 抛出一个异常让CallBack处理
                        throw new RxJava2NullException();
                    }
                })
                .compose(mView.bindToLifecycle());

    }
}
