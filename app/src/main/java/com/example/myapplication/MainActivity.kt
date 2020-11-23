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
    lateinit var searchView: SearchView
    lateinit var listView: ListView
    lateinit var tabLayout: TabLayout
    lateinit var listAdapter: ArrayAdapter<*>
    val flowers = arrayOf<String>("Abutilon","Aconite","Agapanthus","Tulip","Tuberose","Tuberose","Wallflower","Wandflower","lilies","Yarrow","Yellow Bell","Zenobia","Tiger Flower")
    val imageId = arrayOf<Int>(
        R.drawable.image1,R.drawable.image2,R.drawable.image3,
    R.drawable.image1,R.drawable.image2,R.drawable.image3,
    R.drawable.image1,R.drawable.image2,R.drawable.image3,
    R.drawable.image1,R.drawable.image2,R.drawable.image3,
    R.drawable.image1
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // image carousel
        var imgs = listOf(R.drawable.image1,R.drawable.image2,R.drawable.image3, R.drawable.image1,R.drawable.image2,R.drawable.image3)
        var adapter = ImagesAdapter(imgs,this)
        pager.adapter = adapter

        //dots
        tabLayout = findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(pager, true);


        // declare elements
        searchView = findViewById(R.id.searchView)
        listView = findViewById(R.id.listView)



        // to add image in the list view, something gets wrong with the searchView!
        //listAdapter = ListAdapter(this, flowers, imageId)

        // add adapter to list view
        listAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, flowers)
        listView.adapter = listAdapter

        //change height of list view
        setListViewHeightBasedOnChildren(listView)


        //Search function
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (flowers.contains(query)) {
                    listAdapter.filter.filter(query)
                } else {
                    Toast.makeText(this@MainActivity, "No Match found", Toast.LENGTH_LONG).show()
                }
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                listAdapter.filter.filter(newText)
                return false
            }
        })
    }

    fun setListViewHeightBasedOnChildren(listView: ListView) {
        val listAdapter = listView.adapter
            ?: // pre-condition
            return

        var totalHeight = 0
        for (i in 0 until listAdapter.count) {
            val listItem = listAdapter.getView(i, null, listView)
            listItem.measure(0, 0)
            totalHeight += listItem.measuredHeight
        }

        val params = listView.layoutParams
        params.height = totalHeight + listView.dividerHeight * (listAdapter.count - 1)
        listView.layoutParams = params
        listView.requestLayout()
    }





}
