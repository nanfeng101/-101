package com.example.andorid_project.Fragement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.DialogFragment;

public abstract class BaseDialogFragment extends DialogFragment {
    /**找控件
     *
     */
    protected void initViews(){};

    /**
     * 设置数据
     */
    protected void initDatum(){};

    /**
     * 设置监听器
     */
    protected void initListeners(){};

    public final <T extends View> T findViewById( int id) {
        return requireView().findViewById(id);
    }

    /**
     * 返回要显示的控件
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getLayoutView(inflater,container,savedInstanceState);
        return view;
    }

    protected abstract View getLayoutView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDatum();
        initViews();
        initListeners();
    }

}
