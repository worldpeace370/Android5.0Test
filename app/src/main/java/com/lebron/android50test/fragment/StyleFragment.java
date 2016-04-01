package com.lebron.android50test.fragment;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lebron.android50test.R;

/**样式Fragment，各种控件在5.0的新表现，还包括了主题切换功能
 * Created by wuxiangkun on 2016/4/1.
 */
public class StyleFragment extends BaseFragment implements MenuItem.OnMenuItemClickListener{

    private View mRootView;
    private String[] menuTitles;
    public StyleFragment() {

    }

    @Override
    public View initView() {
        mRootView = View.inflate(getActivity(), R.layout.fragment_style, null);
        return mRootView;
    }

    @Override
    public String getUrl() {
        return "file:///android_asset/theme.html";
    }

    /**
     * 继承自父类BaseFragment，因为此StyleFragment()需要往ToolBar里面添加选项菜单
     * 所以需要重写该方法。添加的选项菜单用来改变不同的主题
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (null == menuTitles){
            menuTitles = getActivity().getResources().getStringArray(R.array.style_names);
        }
        for (String name:menuTitles){
            MenuItem menuItem = menu.add(name);
            //不可见，点击三个小点才可见
            menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
            //为每个选项菜单注册监听事件，重写onMenuItemClick(MenuItem item)
            menuItem.setOnMenuItemClickListener(this);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
