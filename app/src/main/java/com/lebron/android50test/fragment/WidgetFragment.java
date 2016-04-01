package com.lebron.android50test.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.lebron.android50test.R;
import com.lebron.android50test.activity.WidgetActivity;

/**
 * Created by wuxiangkun on 2016/4/1.
 * Contacts wuxiangkun@live.com
 */
public class WidgetFragment extends BaseFragment implements View.OnClickListener{
    Button bt1,bt2,bt3,bt4,bt5,bt6;
    public View initView(){


        View view = View.inflate(getActivity(), R.layout.fragment_widget, null);

        bt1 = (Button) view.findViewById(R.id.bt1);
        bt2 = (Button) view.findViewById(R.id.bt2);
        bt3 = (Button) view.findViewById(R.id.bt3);
        bt4 = (Button) view.findViewById(R.id.bt4);
        bt5 = (Button) view.findViewById(R.id.bt5);
        bt6 = (Button) view.findViewById(R.id.bt6);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);

        return view;
    }


    @Override
    public String getUrl() {
        return "file:///android_asset/widget.html";
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), WidgetActivity.class);
        switch (v.getId()) {
            case R.id.bt1:
                intent.putExtra("orientation",WidgetActivity.LIST_V);
                break;
            case R.id.bt2:
                intent.putExtra("orientation",WidgetActivity.LIST_H);
                break;
            case R.id.bt3:
                intent.putExtra("orientation",WidgetActivity.GRID_V);
                break;
            case R.id.bt4:
                intent.putExtra("orientation",WidgetActivity.GRID_H);
                break;
            case R.id.bt5:
                intent.putExtra("orientation",WidgetActivity.STAGGERED_GRID_V);
                break;
            case R.id.bt6:
                intent.putExtra("orientation",WidgetActivity.STAGGERED_GRID_H);
                break;
        }

        getActivity().startActivity(intent);
    }
}
