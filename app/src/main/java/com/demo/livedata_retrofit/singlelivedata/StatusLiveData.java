package com.demo.livedata_retrofit.singlelivedata;

import androidx.lifecycle.MutableLiveData;

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/24 11:20 PM
 **/
public class StatusLiveData extends MutableLiveData<Status> {
    private StatusLiveData() {
    }

    private static class Holder {
        public static final StatusLiveData INSTANCE = new StatusLiveData();
    }

    public static StatusLiveData getInstance() {
        return Holder.INSTANCE;
    }
}//MutableLiveData在LiveData基础上暴露两个设值接口public class MutableLiveData<T> extends LiveData<T> {    @Override public void postValue(T value) {        super.postValue(value); }    @Override public void setValue(T value) {        super.setValue(value); } }
