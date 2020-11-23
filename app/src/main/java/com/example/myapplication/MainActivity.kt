package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {

    lateinit var listAdapter: ArrayAdapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // image carousel
        var imgs = listOf(R.drawable.image1,R.drawable.image2,R.drawable.image3, R.drawable.image1,R.drawable.image2)
        var adapter = ImagesAdapter(imgs,this, this)
        pager.adapter = adapter


    }







}
