package com.lebron.android50test.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.lebron.android50test.R;


/** 侧拉菜单中点击不同选项加载的Fragment继承BaseFragment，可以省去好多代码
 * Created by wuxiangkun on 2016/3/31.
 */
public abstract class BaseFragment extends Fragment{
    //通过onCreateView()方法返回的View
    private View mRootView;
    //用来显示android5.0新特性的View集合，和WebView一起布局，放进帧布局形成重叠，点击查看教程时设置其中之一可见
    private View mDemoView;
    //WebView用来显示查看教程
    private WebView mWebView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null){
            mRootView = initView();
            //此处的R.id.demo可以泛指，所有的Fragment布局中都可以有此id，不会影响，因为在initView()方法中
            //会调用inflater.inflate()方法去填充不同的布局
            mDemoView = mRootView.findViewById(R.id.demo);
            mWebView = (WebView) mRootView.findViewById(R.id.web);
            mWebView.getSettings().setDisplayZoomControls(false);
            mWebView.loadUrl(getUrl());
        }
        return mRootView;
    }

    /**
     * 用于子类重写的抽象方法，获取onCreateView()中的View
     * @return
     */
    public abstract View initView();

    /**
     * 用于子类显示 查看教程 时WebView加载的url，本地assets文件夹下的html文件
     * @return
     */
    public abstract String getUrl();
}
