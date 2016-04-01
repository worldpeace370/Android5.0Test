package com.lebron.android50test.fragment;

import java.util.HashMap;
import java.util.Map;

/**用来得到不同Fragment的工具类
 * Created by wuxiangkun on 2016/3/31.
 */
public class FragmentFactory {
    public static final int FRAGEMENT_STYLE = 0;
    public static final int FRAGEMENT_SHADOW = 1;
    public static final int FRAGEMENT_DRAWABLE = 2;
    public static final int FRAGEMENT_ANIMATION = 3;
    public static final int FRAGEMENT_WIDGET = 4;
    public static final int FRAGEMENT_API = 5;

    private static Map<Integer, BaseFragment> mFragmentCache = new HashMap<>();

    public static BaseFragment createFragment(int position){
        BaseFragment fragment = mFragmentCache.get(position);
        if (fragment == null){
            switch (position){
                case FRAGEMENT_STYLE:
                    fragment = new StyleFragment();
                    break;
                case FRAGEMENT_SHADOW:
                    fragment = new ShadowFragment();
                    break;
                case FRAGEMENT_DRAWABLE:
                    fragment = new DrawableFragment();
                    break;
                case FRAGEMENT_ANIMATION:
                    fragment = new AnimationFragment();
                    break;
                case FRAGEMENT_WIDGET:
                    fragment = new WidgetFragment();
                    break;
            }
            mFragmentCache.put(position, fragment);
        }
        return fragment;
    }

    public static void clear(){
        mFragmentCache.clear();
    }
}
