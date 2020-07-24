package com.demo.livedata_retrofit.singlelivedata;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/24 11:21 PM
 **/
class Test extends AppCompatActivity implements View.OnClickListener, Observer<Status> {

    private int level;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusLiveData.getInstance().observe(this, this);
    }


    @Override
    public void onClick(View view) {
        changeArrowStatus(++level);
        putStatus(level);
    }

    private void putStatus(int level) {
        Status status = new Status();
        status.setLevel(level);
        StatusLiveData.getInstance().setValue(status);
    }

    @Override
    public void onChanged(Status status) {
        int level = status.getLevel();
        changeArrowStatus(level);
    }

    private void changeArrowStatus(int level) {
//        ...
//        ImageView ivArrow = findViewById(R.id.iv_arrow);
//        LevelListDrawable levelListDrawable = ((LevelListDrawable) ivArrow.getDrawable());
//        levelListDrawable.setLevel(level % 2);
    }

}
