package com.example.andorid_project.config.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.andorid_project.R;
import com.example.andorid_project.shouye.enity.Bangdan;

public class MusicDialog {
    public void show(Context context, Bangdan item){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.music_dialog);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        TextView music_dialog_name = (TextView) dialog.findViewById(R.id.music_dialog_name);
        music_dialog_name.setText(item.getName());
        //
        TextView music_dialog_quxiao = (TextView) dialog.findViewById(R.id.music_dialog_quxiao);
        music_dialog_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }
}
