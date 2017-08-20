package com.jxkj.readapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jxkj.readapp.R;
import com.jxkj.readapp.adapter.BusAdapter;
import com.jxkj.readapp.bean.TestBean;
import com.jxkj.readapp.view.DisInterceptNestedRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColDemoFragment extends Fragment {

    @BindView(R.id.rcl_bus)
    DisInterceptNestedRecyclerView rclBus;
    private BusAdapter adapter;

    public ColDemoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_col_demo, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        rclBus.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BusAdapter(TestBean.getDataList());
        rclBus.setAdapter(adapter);

    }

}
