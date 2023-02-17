package com.umesh_singh_verma.kotlinapi

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://hp-api.onrender.com/api/"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getMyData();
    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
                val responseBody = response.body()!!

                val myStringBuilder = StringBuilder()
                val myStringBuildera = StringBuilder()
                for (myData in responseBody) {
                    myStringBuilder.append(myData.yearOfBirth)
                    myStringBuilder.append("\n")
                    myStringBuildera.append(myData.hairColour)
                    myStringBuildera.append("\n")
                }
                val finalshow = findViewById<View>(R.id.txtId) as TextView
                finalshow.setText(myStringBuilder)
                val finalshowa = findViewById<View>(R.id.textView3) as TextView
                finalshowa.setText(myStringBuildera)
            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                Log.d("MainActivity","onFailure"+t.message)
            }
        })



    }
}