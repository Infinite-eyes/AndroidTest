package com.demo.livedata_retrofit.diffutil;

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/25 11:39 PM
 **/
public class TestBean implements Cloneable {
    private String name;
    private String desc;
    private int pic;

    public TestBean(String name, String desc, int pic) {
        this.name = name;
        this.desc = desc;
        this.pic = pic;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    //仅写DEMO 用 实现克隆方法
    @Override
    public TestBean clone() throws CloneNotSupportedException {
        TestBean bean = null;
        try {
            bean = (TestBean) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return bean;
    }
}