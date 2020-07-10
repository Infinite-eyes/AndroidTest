package com.demo.jetpack.viewmodel.java;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/10 7:15 PM
 **/
public class UserModel extends ViewModel {

    public final MutableLiveData<User> mUserLiveData = new MutableLiveData<>();

    public UserModel() {
        mUserLiveData.postValue(new User(1, ""));
    }


    public void doSomething() {
        User user = mUserLiveData.getValue();
        if (user != null) {
            user.age += 1;
            user.name = "name15";
            mUserLiveData.setValue(user);
        }
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
