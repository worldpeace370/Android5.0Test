package com.lebron.android50test.activity;

import android.app.ActionBar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.lebron.android50test.R;
import com.lebron.android50test.fragment.DrawerFragment;

public class MainActivity extends AppCompatActivity implements DrawerLayout.DrawerListener, DrawerFragment.OnDrawerItemSelectedListener{

    //当前主题标识，用于切换不同的主题用
    public static int mTheme = -1;
    private DrawerLayout mDrawerLayout;
    private DrawerFragment mDrawerFragment;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (mTheme != -1){
            setTheme(mTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDrawer();
        initToolBar();
        initActionBar();
    }

    /**
     * 用ToolBar代替ActionBar
     */
    private void initToolBar() {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();
    }

    /**
     * 初始化Actionbar
     */
    private void initActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);//设置显示左侧按钮
        actionBar.setHomeButtonEnabled(true);//设置左侧按钮可点
        actionBar.setDisplayShowTitleEnabled(true);//设置显示标题

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mToggle.syncState();
        actionBar.setTitle(getString(R.string.app_name));//设置标题
    }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            getMenuInflater().inflate(R.menu.main, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    /**
     * DrawerFragment.OnDrawerItemSelectedListener接口的实现方法
     * 通过DrawerFragment里面的selectItem(position)方法回调，在这里进行切换Fragment
     * @param position
     */
    @Override
    public void onDrawerItemSelected(int position) {

    }
}
