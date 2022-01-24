package com.metehanbolat.homeworkproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.metehanbolat.homeworkproject.adapter.ProductRecyclerAdapter
import com.metehanbolat.homeworkproject.databinding.ActivityFeedBinding
import com.metehanbolat.homeworkproject.repository.FeedActivityRepository

class FeedActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFeedBinding
    private lateinit var adapter : ProductRecyclerAdapter

    private var userId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        intent.extras?.let {
            userId = it.getString("userId")
        }

        val repo = FeedActivityRepository()

        repo.getData(view)

        repo.data.observe(this) { productBilgiler ->
            userId?.let {
                adapter = ProductRecyclerAdapter(productBilgiler, it, this@FeedActivity)
                binding.recyclerView.adapter = adapter
            }
        }

    }

}