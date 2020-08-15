package com.demo.mvpchart

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.demo.androidtest.R
import com.demo.androidtest.databinding.MvpchartActivityBinding
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import java.util.*


/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/8/15 11:36 AM
 **/
class MvpChartActivity : AppCompatActivity() {


    lateinit var binding: MvpchartActivityBinding

    protected var tfRegular: Typeface? = null
    protected var tfLight: Typeface? = null

    protected val parties = arrayOf(
        "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
        "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
        "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
        "Party Y", "Party Z"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.mvpchart_activity)

//        binding.chart.setUsePercentValues(true)
//        binding.chart.description.isEnabled = false
//        binding.chart.setExtraOffsets(5f, 10f, 5f, 5f)
//
//        binding.chart.dragDecelerationFrictionCoef = 0.95f
//
//        binding.chart.setCenterTextTypeface(tfLight)
//        binding.chart.centerText = generateCenterSpannableText()
//
//        binding.chart.isDrawHoleEnabled = true
//        binding.chart.setHoleColor(Color.WHITE)
//
//        binding.chart.setTransparentCircleColor(Color.WHITE)
//        binding.chart.setTransparentCircleAlpha(110)
//
//        binding.chart.holeRadius = 58f
//        binding.chart.transparentCircleRadius = 61f
//
//        binding.chart.setDrawCenterText(true)
//
//        binding.chart.setRotationAngle(0f)
//        // enable rotation of the chart by touch
//        // enable rotation of the chart by touch
//        binding.chart.isRotationEnabled = true
//        binding.chart.isHighlightPerTapEnabled = true

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener
//        binding.chart.setOnChartValueSelectedListener(this)


//        binding.chart.animateY(1400, Easing.EaseInOutQuad)
//        // chart.spin(2000, 0, 360);
//
//        // chart.spin(2000, 0, 360);
//        val l: Legend = binding.chart.getLegend()
//        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
//        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
//        l.orientation = Legend.LegendOrientation.VERTICAL
//        l.setDrawInside(false)
//        l.xEntrySpace = 7f
//        l.yEntrySpace = 0f
//        l.yOffset = 0f
//
//        // entry label styling
//
//        // entry label styling
//        binding.chart.setEntryLabelColor(Color.WHITE)
//        binding.chart.setEntryLabelTypeface(tfRegular)
//        binding.chart.setEntryLabelTextSize(12f)
//        val description = Description()
//        description.text = ""
//        binding.chart.description = description
        setData()
    }


    private fun generateCenterSpannableText(): SpannableString? {
        val s = SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda")
        s.setSpan(RelativeSizeSpan(1.7f), 0, 14, 0)
        s.setSpan(StyleSpan(Typeface.NORMAL), 14, s.length - 15, 0)
        s.setSpan(ForegroundColorSpan(Color.GRAY), 14, s.length - 15, 0)
        s.setSpan(RelativeSizeSpan(.8f), 14, s.length - 15, 0)
        s.setSpan(StyleSpan(Typeface.ITALIC), s.length - 14, s.length, 0)
        s.setSpan(ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length - 14, s.length, 0)
        return s
    }

    private val colors = intArrayOf(
        ColorTemplate.VORDIPLOM_COLORS[0],
        ColorTemplate.VORDIPLOM_COLORS[1],
        ColorTemplate.VORDIPLOM_COLORS[2]
    )

    private fun setData() {


        val entries = ArrayList<PieEntry>()

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (i in 0 until 10) {
            entries.add(
                PieEntry(
                    (Math.random() * 10 + 10 / 5).toFloat(),
                    parties[i % parties.size],
                    resources.getDrawable(R.drawable.star)
                )
            )
        }

        val dataSet = PieDataSet(entries, "Election Results")

        dataSet.setDrawIcons(false)

//        dataSet.sliceSpace = 3f
//        dataSet.iconsOffset = MPPointF(0f, 40f)
//        dataSet.selectionShift = 5f

        // add a lot of colors


        // add a lot of colors
        val colors = ArrayList<Int>()

        for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)

        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)

        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)

        for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)

        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)

        colors.add(ColorTemplate.getHoloBlue())

        dataSet.colors = colors
        //dataSet.setSelectionShift(0f);

        //dataSet.setSelectionShift(0f);
        val data = PieData(dataSet)
        data.setDrawValues(false)



//        data.setValueFormatter(PercentFormatter(binding.chart))
//        data.setValueTextSize(11f)
//        data.setValueTextColor(Color.WHITE)
//        data.setValueTypeface(tfLight)
        binding.chart.data = data
        binding.chart.holeRadius = 80f

//
        binding.chart.description.isEnabled = false //设置pieChart图表的描述
        binding.chart.setBackgroundColor(Color.WHITE) //设置pieChart图表背景色
        binding.chart.isRotationEnabled = false //可以手动旋转
//        binding.chart.setDragDecelerationFrictionCoef(0.95f) //设置pieChart图表转动阻力摩擦系数[0,1]
        binding.chart.isHighlightPerTapEnabled = false //设置piecahrt图表点击Item高亮是否可用
//        binding.chart.highlightValues(null)
//        binding.chart.animateY(1400, Easing.EasingOption.EaseInOutQuad) // 设置pieChart图表展示动画效果
//
        val l: Legend = binding.chart.legend
        l.isEnabled = false //是否启用图列（true：下面属性才有意义

//        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
//        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
//        l.orientation = Legend.LegendOrientation.VERTICAL
//        l.form = Legend.LegendForm.CIRCLE //设置图例的形状
//        l.formSize = 10f //设置图例的大小
//        l.formToTextSpace = 10f //设置每个图例实体中标签和形状之间的间距
//        l.setDrawInside(false)
//        l.isWordWrapEnabled = true //设置图列换行(注意使用影响性能,仅适用legend位于图表下面)
//        l.xEntrySpace = 10f //设置图例实体之间延X轴的间距（setOrientation = HORIZONTAL有效）
//
//        l.yEntrySpace = 8f //设置图例实体之间延Y轴的间距（setOrientation = VERTICAL 有效）
//
//        l.yOffset = 0f //设置比例块Y轴偏移量
//
//        l.textSize = 14f //设置图例标签文本的大小
//        l.textColor = Color.parseColor("#333333") //设置图例标签文本的颜色

        binding.chart.setExtraOffsets(50f, 100f, 50f, 100f)
        binding.chart.dragDecelerationFrictionCoef = 0.5f

//        binding.chart.isDrawHoleEnabled = false
        binding.chart.setDrawCenterText(false)

        binding.chart.setUsePercentValues(false)

//        binding.chart.setTransparentCircleColor(Color.TRANSPARENT)
        binding.chart.setTransparentCircleAlpha(0)
        // entry label styling

        // entry label styling
        binding.chart.setEntryLabelColor(Color.WHITE)
        binding.chart.setEntryLabelTypeface(tfRegular)
        binding.chart.setEntryLabelTextSize(0f)

        binding.chart.description.isEnabled = false;

        binding.chart.invalidate()


    }


}