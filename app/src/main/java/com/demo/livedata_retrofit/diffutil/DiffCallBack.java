package com.demo.livedata_retrofit.diffutil;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/25 11:24 PM
 **/
class DiffCallBack extends DiffUtil.Callback {

    private List<TestBean> mOldDatas, mNewDatas;

    public DiffCallBack(List<TestBean> mOldDatas, List<TestBean> mNewDatas) {
        this.mOldDatas = mOldDatas;
        this.mNewDatas = mNewDatas;
    }

    @Override

    public int getOldListSize() {
        return mOldDatas != null ? mOldDatas.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return mNewDatas != null ? mNewDatas.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldDatas.get(oldItemPosition).getName().equals(mNewDatas.get(newItemPosition).getName());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        TestBean beanOld = mOldDatas.get(oldItemPosition);
        TestBean beanNew = mNewDatas.get(newItemPosition);
        if (!beanOld.getDesc().equals(beanNew.getDesc())) {//如果有内容不同，就返回false
            return false;
        }
        if (beanOld.getPic() != beanNew.getPic()) {
            return false;//如果有内容不同，就返回false
        }
        return true;//默认两个data内容是相同的
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {

        TestBean oldBean = mOldDatas.get(oldItemPosition);
        TestBean newBean = mNewDatas.get(newItemPosition);

        Bundle payload = new Bundle();
        if (!oldBean.getDesc().equals(newBean.getDesc())) {
            payload.putString("KEY_DESC", newBean.getDesc());
        }
        if (oldBean.getPic() != newBean.getPic()) {
            payload.putInt("KEY_PIC", newBean.getPic());
        }

        if (payload.size() == 0) {
            return null;
        }

        return payload;
    }

//    onAdapte
//@Override
//    public void onBindViewHolder(DiffVH holder, int position, List<Object> payloads) {
//        if (payloads.isEmpty()) {
//            onBindViewHolder(holder, position);
//        } else {
//            //文艺青年中的文青
//            Bundle payload = (Bundle) payloads.get(0);//取出我们在getChangePayload（）方法返回的bundle
//            TestBean bean = mDatas.get(position);//取出新数据源，（可以不用）
//            /**
//             * 有几个item改变就会走几次(else)这个方法里
//             * 05-03 22:48:01.168 19214-19214/com.mcxtzhang.diffutils I/System.out: bean.getDesc()=Android+position==0
//             05-03 22:48:01.168 19214-19214/com.mcxtzhang.diffutils I/System.out: bean.getDesc()=android++position==1
//             */
//            System.out.println("bean.getDesc()="+bean.getDesc()+"position=="+position);
//            for (String key : payload.keySet()) {
//                switch (key) {
//                    case "KEY_DESC":
//                        //这里可以用payload里的数据，不过data也是新的 也可以用
//                        holder.tv2.setText(bean.getDesc());
//                        break;
//                    case "KEY_PIC":
//                        holder.iv.setImageResource(payload.getInt(key));
//                        break;
//                    default:
//                        break;
//                }
//            }
//        }
}



}
