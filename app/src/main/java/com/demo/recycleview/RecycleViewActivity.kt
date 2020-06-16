package com.demo.recycleview

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.demo.androidtest.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import java.lang.reflect.Type


/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/6/16 3:44 PM
 **/
class RecycleViewActivity : AppCompatActivity() {


    class Data() {

        private var type = 0
        private var list: List<ListBean?>? = null

        fun getType(): Int {
            return type
        }

        fun setType(type: Int) {
            this.type = type
        }

        fun getList(): List<ListBean?>? {
            return list
        }

        fun setList(list: List<ListBean?>?) {
            this.list = list
        }

        class ListBean {
            var id = 0
            var imgUrl: String? = null
            var link: String? = null
            var type = 0
            var sort = 0
            var status = 0
            var loginStatus = 0
            var memStatus = 0
            var equityId = 0
            var createTime: String? = null
            var updateTime: String? = null

        }
    }

    var json = "{\n" +
            "    \"type\": 4,\n" +
            "    \"list\": [\n" +
            "        {\n" +
            "            \"id\": 29,\n" +
            "            \"imgUrl\": \"http://huabeifile.grayoss.com/data/20200611/1591866022263216717164.png\",\n" +
            "            \"link\": \"https://www/baidu.com\",\n" +
            "            \"type\": 4,\n" +
            "            \"sort\": 1,\n" +
            "            \"status\": 1,\n" +
            "            \"loginStatus\": 1,\n" +
            "            \"memStatus\": 2,\n" +
            "            \"equityId\": 1,\n" +
            "            \"createTime\": \"2020-06-10T13:43:15\",\n" +
            "            \"updateTime\": \"2020-06-10T13:43:15\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 30,\n" +
            "            \"imgUrl\": \"http://huabeifile.grayoss.com/data/20200611/1591866012754764610942.png\",\n" +
            "            \"link\": \"#\",\n" +
            "            \"type\": 4,\n" +
            "            \"sort\": 2,\n" +
            "            \"status\": 1,\n" +
            "            \"loginStatus\": 2,\n" +
            "            \"memStatus\": 2,\n" +
            "            \"equityId\": 1,\n" +
            "            \"createTime\": \"2020-06-11T17:00:15\",\n" +
            "            \"updateTime\": \"2020-06-11T17:00:15\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 31,\n" +
            "            \"imgUrl\": \"http://huabeifile.grayoss.com/data/20200611/1591866048128744046842.png\",\n" +
            "            \"link\": \"1\",\n" +
            "            \"type\": 4,\n" +
            "            \"sort\": 3,\n" +
            "            \"status\": 1,\n" +
            "            \"loginStatus\": 2,\n" +
            "            \"memStatus\": 2,\n" +
            "            \"equityId\": 1,\n" +
            "            \"createTime\": \"2020-06-11T17:00:53\",\n" +
            "            \"updateTime\": \"2020-06-11T17:00:53\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 32,\n" +
            "            \"imgUrl\": \"http://huabeifile.grayoss.com/data/20200611/1591866071892783027887.png\",\n" +
            "            \"link\": \"1\",\n" +
            "            \"type\": 4,\n" +
            "            \"sort\": 4,\n" +
            "            \"status\": 1,\n" +
            "            \"loginStatus\": 2,\n" +
            "            \"memStatus\": 2,\n" +
            "            \"equityId\": 1,\n" +
            "            \"createTime\": \"2020-06-11T17:01:14\",\n" +
            "            \"updateTime\": \"2020-06-11T17:01:14\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 33,\n" +
            "            \"imgUrl\": \"http://huabeifile.grayoss.com/data/20200611/1591866110909396282429.png\",\n" +
            "            \"link\": \"1\",\n" +
            "            \"type\": 4,\n" +
            "            \"sort\": 5,\n" +
            "            \"status\": 1,\n" +
            "            \"loginStatus\": 2,\n" +
            "            \"memStatus\": 2,\n" +
            "            \"equityId\": 1,\n" +
            "            \"createTime\": \"2020-06-11T17:01:53\",\n" +
            "            \"updateTime\": \"2020-06-11T17:01:53\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 34,\n" +
            "            \"imgUrl\": \"http://huabeifile.grayoss.com/data/20200611/1591866136942565717452.png\",\n" +
            "            \"link\": \"https://www.baidu.com\",\n" +
            "            \"type\": 4,\n" +
            "            \"sort\": 6,\n" +
            "            \"status\": 1,\n" +
            "            \"loginStatus\": 1,\n" +
            "            \"memStatus\": 1,\n" +
            "            \"equityId\": 1,\n" +
            "            \"createTime\": \"2020-06-11T17:02:19\",\n" +
            "            \"updateTime\": \"2020-06-11T17:02:19\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 35,\n" +
            "            \"imgUrl\": \"http://huabeifile.grayoss.com/data/20200611/1591866152792561921984.png\",\n" +
            "            \"link\": \"https://v.qq.com\",\n" +
            "            \"type\": 4,\n" +
            "            \"sort\": 7,\n" +
            "            \"status\": 1,\n" +
            "            \"loginStatus\": 2,\n" +
            "            \"memStatus\": 2,\n" +
            "            \"equityId\": 1,\n" +
            "            \"createTime\": \"2020-06-11T17:02:38\",\n" +
            "            \"updateTime\": \"2020-06-11T17:02:38\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";


    companion object {
        //        val data = arrayOf<Int>();
        val data = IntArray(101);
        lateinit var beanList: Data;
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycleview_activity)

        for (index in 0..50) {
            data[index] = index;
        }

        var rv = findViewById<RecyclerView>(R.id.rv);
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
//        rv.setItemAnimator(null);
//        rv.setHasFixedSize(true);
        rv.layoutManager = layoutManager

        rv.addItemDecoration(StaggeredDividerItemDecoration(baseContext, 10))
//        rv.addOnScrollListener(object : OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                layoutManager.invalidateSpanAssignments() //防止第一行到顶部有空白区域
//            }
//        })


        rv.adapter = MyAdapter()

        val type: Type = object : TypeToken<Data>() {}.type
        beanList = Gson().fromJson(json, type)


    }

    class MyAdapter : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

        @NonNull
        override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycleview_item, parent, false)
            return ViewHolder(view)
        }


        override fun getItemCount(): Int {
//            return data.size
            return beanList.getList()!!.size;
        }

        open inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            //            var tv: TextView
            var iv: ImageView

            init {
//                tv = itemView.findViewById(R.id.tv)
                iv = itemView.findViewById(R.id.iv)
            }
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//            holder.tv.text = data.get(position).toString();

            if (position == 0) {

                holder.iv.layoutParams.height =
                    TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        120f,
                        holder.itemView.context.resources.displayMetrics
                    ).toInt()

//                    (holder.itemView.context.resources.displayMetrics.density * 130).toInt();
            }

            Glide.with(holder.itemView.context).load(beanList.getList()!!.get(position)!!.imgUrl)
                .apply(
                    RequestOptions.bitmapTransform(
                        RoundedCornersTransformation(
                            10,
                            0,
                            RoundedCornersTransformation.CornerType.ALL
                        )
                    )
                )
                .into(holder.iv)
        }
    }

}