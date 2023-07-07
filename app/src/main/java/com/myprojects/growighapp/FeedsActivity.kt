package com.myprojects.growighapp

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FeedsActivity : AppCompatActivity() {

    val ApiKey = "36149652-dda7ec45359a6175b979daaa7"
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FeedsRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feeds)

        recyclerView = findViewById(R.id.feeds_rv)
        recyclerView.layoutManager = LinearLayoutManager(this) // number of tiles in one row

        // Set the status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = Color.TRANSPARENT
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

            // Set gradient color for status bar
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val gradientDrawable = ContextCompat.getDrawable(this, R.drawable.status_bar_gradient)
                window.statusBarColor = Color.TRANSPARENT
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
                window.setBackgroundDrawable(gradientDrawable)
            }
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pixabay.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val pixabayService = retrofit.create(ApiInterface::class.java)
        val call = pixabayService.searchImages(ApiKey, "science", "photo")

        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(
                call: Call<ApiResponse>,
                response: Response<ApiResponse>
            ) {
                if (response.isSuccessful) {
                    val pixabayResponse = response.body()
                    pixabayResponse?.let {
                        adapter = FeedsRecyclerViewAdapter(it.hits)
                        recyclerView.adapter = adapter
                    }
                } else {
                    println("Request failed: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                println("Request failed: ${t.message}")
            }
        })

    }

//    private fun fetchData(): List<PixabayImage> {
//        val item = List<PixabayImage>()
//        for (i in 1..10) {
//            // Add dummy data to the list
//            item.add(apiUrl)
//        }
//        return item
//    }

}