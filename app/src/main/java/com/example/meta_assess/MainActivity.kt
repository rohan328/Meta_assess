package com.example.meta_assess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meta_assess.network.GiphyApi
import com.example.meta_assess.network.GiphyResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val APIKEY = "EEjeWKnay8eNwJ091mC2ffGuQe96tdBN"

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.rvPhotos)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        launch {
            val data = GiphyApi.retrofitService.getPhotos(APIKEY)
            onResult(data, recyclerview)
        }

    }

    fun onResult(result: GiphyResponse, recyclerView: RecyclerView) {
//        Log.d("TAG", result.data.toString())
        val adapter = RVAdapter(result.data)
        recyclerView.adapter = adapter
    }
}