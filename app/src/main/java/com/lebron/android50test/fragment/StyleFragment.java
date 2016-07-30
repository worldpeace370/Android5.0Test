package com.lebron.android50test.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.lebron.android50test.R;
import com.lebron.android50test.activity.MainActivity;

/**样式Fragment，各种控件在5.0的新表现，还包括了主题切换功能
 * Created by wuxiangkun on 2016/4/1.
 */
public class StyleFragment extends BaseFragment implements MenuItem.OnMenuItemClickListener{
    public static final int NONE_THEME = 0;
    public static final int DEFAULT_THEME = 1;
    public static final int DEFAULT_LIGHT_THEME = 2;
    public static final int DEFAULT_LIGH_DARK_THEME = 3;
    public static final int RED_THEME = 4;
    public static final int PINK_THEME = 5;
    public static final int PURPLE_THEME = 6;
    public static final int DEEP_PURPLE_THEME = 7;
    public static final int INDIGO_THEME = 8;
    public static final int BLUE_THEME = 9;
    public static final int LIGHT_BLUE_THEME = 10;
    public static final int CYAN_THEME = 11;
    public static final int TEAL_THEME = 12;
    public static final int GREEN_THEME = 13;
    public static final int LIGHT_THEME = 14;
    public static final int LIME_THEME = 15;
    public static final int YELLOW_THEME = 16;
    public static final int AMBER_THEME = 17;
    public static final int ORANGE_THEME = 18;
    public static final int DEEP_ORANGE_THEME = 19;
    public static final int BROWN_THEME = 20;
    public static final int GREY_THEME = 21;
    public static final int BLUE_GREY_THEME = 22;

    private View mRootView;
    private String[] menuTitles;
    private String TAG = "StyleFragment";

    public StyleFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach: 执行了");
    }

    @Override
    public View initView() {
        Log.i(TAG, "onCreateView: 执行了");
        mRootView = View.inflate(getActivity(), R.layout.fragment_style, null);
        //点击Spinner的不同选项切换主题
        Spinner spinner = (Spinner) mRootView.findViewById(R.id.spinner);
        String[] arraySpinner = getActivity().getResources().getStringArray(R.array.style_names);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, arraySpinner);
        spinner.setAdapter(arrayAdapter);
        //切换主题
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // position表示列表中每一个item的位置。该位置从0开始,系统自带适配器的id参数没用,与position值相同.
                //在这里只需要处理position，来切换主题，重新加载Activity
                if (position != NONE_THEME){//position = NONE_THEME时，属于标题--主题选择，所以不对此进行操作
                    setTheme(position);
                    reloadApp();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 这个方法是处理如果不选择的时候的情况，目前版本里无法触发.
            }
        });
        return mRootView;
    }

    /**
     * 改变主题后需要重新启动App,在MainActivity的onCreate()方法中最先执行setTheme(mTheme);
     */
    private void reloadApp() {
        Intent intent = getActivity().getIntent();
        getActivity().overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        getActivity().finish();
        FragmentFactory.clear();
        getActivity().overridePendingTransition(0, 0);
        startActivity(intent);
    }

    /**
     * 根据spinner或者选项菜单点中的位置来切换主题，给MainActivity.mTheme赋新值
     * 重启MainActivity时通过setTheme(int mTheme);来改变主题，mTheme为R.style.XXX
     * @param position
     */
    private void setTheme(int position) {
        switch (position){
            case DEFAULT_THEME:
                MainActivity.mTheme = R.style.DefaultTheme;
                break;
            case DEFAULT_LIGHT_THEME:
                MainActivity.mTheme = R.style.DefaultLightTheme;
                break;
            case DEFAULT_LIGH_DARK_THEME:
                MainActivity.mTheme = R.style.DefaultLightDarkTheme;
                break;
            case RED_THEME:
                MainActivity.mTheme = R.style.RedTheme;
                break;
            case PINK_THEME:
                MainActivity.mTheme = R.style.PinkTheme;
                break;
            case PURPLE_THEME:
                MainActivity.mTheme = R.style.PurpleTheme;
                break;
            case DEEP_PURPLE_THEME:
                MainActivity.mTheme = R.style.DeepPurpleTheme;
                break;
            case INDIGO_THEME:
                MainActivity.mTheme = R.style.IndigoTheme;
                break;
            case BLUE_THEME:
                MainActivity.mTheme = R.style.BlueTheme;
                break;
            case LIGHT_BLUE_THEME:
                MainActivity.mTheme = R.style.LightBlueTheme;
                break;
            case CYAN_THEME:
                MainActivity.mTheme = R.style.CyanTheme;
                break;
            case TEAL_THEME:
                MainActivity.mTheme = R.style.TealTheme;
                break;
            case GREEN_THEME:
                MainActivity.mTheme = R.style.GreenTheme;
                break;
            case LIGHT_THEME:
                MainActivity.mTheme = R.style.LightGreenTheme;
                break;
            case LIME_THEME:
                MainActivity.mTheme = R.style.LimeTheme;
                break;
            case YELLOW_THEME:
                MainActivity.mTheme = R.style.YellowTheme;
                break;
            case AMBER_THEME:
                MainActivity.mTheme = R.style.AmberTheme;
                break;
            case ORANGE_THEME:
                MainActivity.mTheme = R.style.OrangeTheme;
                break;
            case DEEP_ORANGE_THEME:
                MainActivity.mTheme = R.style.DeepOrangeTheme;
                break;
            case BROWN_THEME:
                MainActivity.mTheme = R.style.BrownTheme;
                break;
            case GREY_THEME:
                MainActivity.mTheme = R.style.GreyTheme;
                break;
            case BLUE_GREY_THEME:
                MainActivity.mTheme = R.style.BlueGreyTheme;
                break;
            default:
                break;
        }
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
            MenuItem menuItem = menu.add(name); //相当于添加title
            //不可见，点击三个小点才可见
            menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
            //为每个选项菜单注册监听事件，重写onMenuItemClick(MenuItem item)
            menuItem.setOnMenuItemClickListener(this);
        }
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 选项菜单的菜单选中事件，切换不同的主题
     * @param item
     * @return
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        String title = item.getTitle().toString();
        for (int i = 0; i < menuTitles.length; i++) {
            if (title.equals(menuTitles[i])){
                setTheme(i);
                reloadApp();
                return true;
            }
        }
        return false;
    }

    /**
     * 可以通过参数savedInstanceState来得到onSaveInstanceState(Bundle outState)方法保存的数据
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated: 执行了");
    }

    /**
     * 将需要保存的数据保存在下面的outState中
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }
}
