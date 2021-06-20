package com.investment

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset


class MainActivity: AppCompatActivity() {
    var title = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setLineChartData();

        val linearLayoutManager = LinearLayoutManager(applicationContext)
        rvList.layoutManager = linearLayoutManager

        try {
            // get JSONObject from JSON file
            val obj = JSONObject(loadJSONFromAsset())
            // fetch JSONArray named users
            val dataArray: JSONArray = obj.getJSONArray("data")
            // implement for loop for getting users list data
            for (i in 0 until dataArray.length()) {
                val userDetail = dataArray.getJSONObject(i)
                Log.d("TAG", "onCreate: $userDetail")
                val sahamArray: JSONArray = userDetail.getJSONArray("jenis_saham")
                for (a in 0 until sahamArray.length()) {
                    val jenisSahamDetail = dataArray.getJSONObject(a)
                    title.add(jenisSahamDetail.getString("title"))
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val mainAdapter = this.let { MainAdapter(this, title) }

        rvList.adapter = mainAdapter // set the Adapter to RecyclerView


    }

    fun loadJSONFromAsset(): String? {
        var json: String? = null
        json = try {
            val `is` = assets.open("data_list.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            Charset.forName("UTF-8").toString()
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    fun setLineChartData() {

        val xValue = ArrayList<String>()
        xValue.add("Jul 19")
        xValue.add("Okt 19")
        xValue.add("Jan 20")
        xValue.add("Apr 20")
        xValue.add("Jul 20")
        val lineEntry = ArrayList<Entry>()
        lineEntry.add(Entry(20f, 0))
        lineEntry.add(Entry(25f, 1))
        lineEntry.add(Entry(25f, 2))
        lineEntry.add(Entry(30f, 3))
        lineEntry.add(Entry(40f, 4))

        val lineEntry2 = ArrayList<Entry>()
        lineEntry2.add(Entry(10f, 0))
        lineEntry2.add(Entry(20f, 1))
        lineEntry2.add(Entry(30f, 2))
        lineEntry2.add(Entry(40f, 3))
        lineEntry2.add(Entry(50f, 4))

        val lineEntry3 = ArrayList<Entry>()
        lineEntry3.add(Entry(40f, 0))
        lineEntry3.add(Entry(50f, 1))
        lineEntry3.add(Entry(60f, 2))
        lineEntry3.add(Entry(50f, 3))
        lineEntry3.add(Entry(80f, 4))

        val lineDataSet = LineDataSet(lineEntry, "")
        lineDataSet.color = resources.getColor(R.color.colorAccentBlue)
        lineDataSet.circleRadius = 6f
        lineDataSet.setDrawCubic(true)
        lineDataSet.setCircleColorHole(resources.getColor(R.color.white))
        lineDataSet.lineWidth = 2f

        val lineDataSet2 = LineDataSet(lineEntry2, "")
        lineDataSet2.color = resources.getColor(R.color.colorAccentOrange)
        lineDataSet2.circleRadius = 6f
        lineDataSet2.setDrawCubic(true)
        lineDataSet2.setCircleColorHole(resources.getColor(R.color.white))
        lineDataSet2.lineWidth = 2f

        val lineDataSet3 = LineDataSet(lineEntry3, "")
        lineDataSet3.color = resources.getColor(R.color.purple_500)
        lineDataSet3.circleRadius = 6f
        lineDataSet3.setDrawCubic(true)
        lineDataSet3.setCircleColorHole(resources.getColor(R.color.white))
        lineDataSet3.lineWidth = 2f

        val finalDataSet = ArrayList<LineDataSet>()
        finalDataSet.add(lineDataSet)
        finalDataSet.add(lineDataSet2)
        finalDataSet.add(lineDataSet3)

        val data = LineData(xValue, finalDataSet as List<ILineDataSet>?)

        lineChart.data = data
        lineChart.setBackgroundColor(resources.getColor(R.color.white))
        lineChart.animateXY(3000,3000)

//        lineChart.xAxis.valueFormatter = MonthFormatter()
        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        lineChart.setDescription("")

        val xAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.setDrawLabels(true)

    }
}