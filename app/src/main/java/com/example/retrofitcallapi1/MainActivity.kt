package com.example.retrofitcallapi1

import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import android.util.Log.d
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST

const val BASE_URL ="https://jsonplaceholder.typicode.com/"
class MainActivity : AppCompatActivity() {

    lateinit var dataAdapter: DataAdapter
    private lateinit var rcvdata: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val ds = mutableListOf<DataItem>()
        dataAdapter = DataAdapter(ds)
        rcvdata = findViewById(R.id.rcvdata)
        rcvdata.layoutManager = GridLayoutManager(this,1)
        rcvdata.setHasFixedSize(true)



   /*     rcvdata = findViewById(R.id.rcvdata)
        rcvdata.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        rcvdata.layoutManager = linearLayoutManager*/

        getMyData()


        /*val btnpost = findViewById<Button>(R.id.btnpost)*/
/*
        btnpost.setOnClickListener {
            postMyData()
        }*/
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    /*private fun postMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val post = MyDataItem("taatt",1,"huhaa",3)
        val retrofitData = retrofitBuilder.postdata(post)

        retrofitData.enqueue(object : Callback<MyDataItem?> {
            override fun onResponse(call: Call<MyDataItem?>, response: Response<MyDataItem?>) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    val txtid: TextView = findViewById(R.id.txtid1)
                    txtid.text ="Body: ${responseBody?.body} \nTitle: ${responseBody?.title}"
                } else {
                }
            }

            override fun onFailure(call: Call<MyDataItem?>, t: Throwable) {
            }
        })


    }*/

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getdata()
        retrofitData.enqueue(object : Callback<List<DataItem>?> {
            override fun onResponse(
                call: Call<List<DataItem>?>,
                response: Response<List<DataItem>?>
            ) {
                /*val myStringBuilder = StringBuilder()*/
                val responseBody = response.body()!!
                dataAdapter = DataAdapter(responseBody)
                dataAdapter.notifyDataSetChanged()
                rcvdata.adapter = dataAdapter

            }

            override fun onFailure(call: Call<List<DataItem>?>, t: Throwable) {
                d("MainActivity","OnFailune"+t.message)
            }
        })
    }
}