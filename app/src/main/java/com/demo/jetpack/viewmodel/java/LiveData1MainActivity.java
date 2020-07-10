package com.demo.jetpack.viewmodel.java;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.demo.androidtest.R;

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/10 7:56 PM
 **/
public class LiveData1MainActivity extends FragmentActivity {

    private TextView mContentTV;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jepack_livedata1_activity);

        mContentTV = findViewById(R.id.tv_content);

        final UserModel userModel = ViewModelProviders.of(this).get(UserModel.class);

        userModel.mUserLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                mContentTV.setText(user.toString());
            }
        });

        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userModel.doSomething();
            }
        });


    }
}
