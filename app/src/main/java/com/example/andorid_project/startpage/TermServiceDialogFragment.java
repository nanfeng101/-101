package com.example.andorid_project.startpage;

import android.os.Bundle;
import android.os.Process;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.fragment.app.FragmentManager;

import com.example.andorid_project.Fragement.BaseDialogFragment;
import com.example.andorid_project.R;
import com.example.andorid_project.until.DefaultPreferenceUtil;

public class TermServiceDialogFragment extends BaseDialogFragment {
    private View.OnClickListener onClickListener;

    @Override
    protected void initViews() {
        super.initViews();
        //设置触摸其它地方对话框不会消失
        setCancelable(false);

    }

    @Override
    protected void initDatum() {
        super.initDatum();
        TextView textView=findViewById(R.id.content);
        //设置连接的颜色
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setLinkTextColor(getResources().getColor(R.color.md_theme_dark_primary));
        Spanned spanned=Html.fromHtml(getString(R.string.dialog_term_content));
        textView.setText(spanned);
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        //同意按钮的监听事件
        TextView agreeview = findViewById(R.id.primary);
        agreeview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                onClickListener.onClick(v);
            }
        });
        //不同意按钮的监听事件
        TextView disagreeview=findViewById(R.id.disgree);
        disagreeview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Process.killProcess(Process.myPid());
            }
        });
    }

    @Override
    protected View getLayoutView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //将一个xml转为一个View
        return inflater.inflate(R.layout.fragment_dialog_term_service,container,false);
    }
    public void show(FragmentManager fragmentManager,View.OnClickListener onClickListener){
        TermServiceDialogFragment termServiceDialogFragment = new TermServiceDialogFragment();
        termServiceDialogFragment.onClickListener=onClickListener;
        termServiceDialogFragment.show(fragmentManager,"TermServiceDialogFragment");
    }

}
