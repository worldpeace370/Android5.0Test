package com.lebron.android50test.activity;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lebron.android50test.R;
import com.lebron.android50test.fragment.BaseFragment;
import com.lebron.android50test.fragment.DrawerFragment;
import com.lebron.android50test.fragment.FragmentFactory;

public class MainActivity extends AppCompatActivity implements DrawerLayout.DrawerListener, DrawerFragment.OnDrawerItemSelectedListener{

    //当前主题标识，用于切换不同的主题用
    public static int mTheme = -1;
    private DrawerLayout mDrawerLayout;
    private DrawerFragment mDrawerFragment;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolBar;
    private BaseFragment mCurrentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (mTheme != -1){
            setTheme(mTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDrawer();
        initToolBar();
        //initActionBar();
    }

    /**
     * 用ToolBar代替ActionBar
     * 需要在Activity中设置android:theme="@style/Theme.AppCompat.Light.NoActionBar"
     * style中每个主题设置如下
     * <item name="android:windowActionBar">false</item>
     * <item name="android:windowNoTitle">true</item>
     */
    private void initToolBar() {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        mToggle.syncState();
    }

    /**
     * 初始化Actionbar
     */
//    private void initActionBar() {
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);//设置显示左侧按钮
//        actionBar.setHomeButtonEnabled(true);//设置左侧按钮可点
//        actionBar.setDisplayShowTitleEnabled(true);//设置显示标题
//
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
//        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
//                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        mToggle.syncState();
//        actionBar.setTitle(getString(R.string.app_name));//设置标题
//    }

    /**
     * 初始化DrawerLayout和侧拉菜单布局
     */
    private void initDrawer() {
        //content部分的View
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        //设置DrawerListener监听器，重写四个方法
        mDrawerLayout.setDrawerListener(this);
        //侧拉菜单部分
        mDrawerFragment = (DrawerFragment)getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mDrawerFragment.setOnDrawerItemSelectedListener(this);
        //默认选中mDrawerFragment中的ListView的第一条，即加载第一个Fragment
        mDrawerFragment.selectItem(0);
    }

    /**
     * 不同的Fragment有可能会在ToolBar中创建选项菜单
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mDrawerLayout.isDrawerOpen(GravityCompat.START)){//如果抽屉打开了就不绘制选项菜单了
            getMenuInflater().inflate(R.menu.main, menu);
            /**
             * 动态添加选项菜单，并设置可以显示
             * MenuItem menuItem = menu.add("测试");
              menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
             */
            //在此处调用mCurrentFragment.onCreateOptionsMenu(menu)(属于自定义方法);相当于上面的步骤，将菜单添加进来
            //方法内部也是通过menu.add()方法实现添加菜单
            //这样实现了根据不同的Fragment显示不同的选项菜单效果,
            //Fragment添加的菜单项在它自己里面处理
            mCurrentFragment.onCreateOptionsMenu(menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 将MainActivity中ToolBar的点击事件传递到Fragment中去处理
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item)||mCurrentFragment.onOptionsItemSelected(item)||mToggle.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {//drawer滑动的时候回调
        mToggle.onDrawerSlide(drawerView, slideOffset);
    }

    @Override
    public void onDrawerOpened(View drawerView) { //打开drawer
        mToggle.onDrawerOpened(drawerView);  //需要把开关变为打开
        /**重新绘制选项菜单，会回调onCreateOptionsMenu(Menu)在下一次需要显示的时候
         * Declare that the options menu has changed, so should be recreated.
         * The onCreateOptionsMenu(Menu) method will be called the next time it needs to be displayed.
         */
        invalidateOptionsMenu();
    }

    @Override
    public void onDrawerClosed(View drawerView) { //关闭drawer
        mToggle.onDrawerClosed(drawerView);  //需要把开关也变为关闭
        invalidateOptionsMenu();//重新绘制选项菜单
    }

    @Override
    public void onDrawerStateChanged(int newState) {    //drawer状态改变的回调
        mToggle.onDrawerStateChanged(newState);
    }

    /**
     * DrawerFragment.OnDrawerItemSelectedListener接口的实现方法
     * 通过DrawerFragment里面的selectItem(position)方法回调，在这里进行切换Fragment
     * @param position
     */
    @Override
    public void onDrawerItemSelected(int position) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mCurrentFragment = FragmentFactory.createFragment(position);
        fragmentManager.beginTransaction().replace(R.id.container, mCurrentFragment).commit();
    }
}
