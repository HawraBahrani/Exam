package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL

class ImagesAdapter(var list: List<Int>, var ctx: Context, var act: Activity) : PagerAdapter() {

    lateinit var layoutInflater:LayoutInflater
    lateinit var searchView: SearchView
    lateinit var listView: ListView
    lateinit var tabLayout: TabLayout
    lateinit var main_layout: LinearLayout
    val flowers = arrayOf<String>("Abutilon","Aconite","Agapanthus","Tulip","Tuberose","Tuberose","Wallflower","Wandflower","lilies","Yarrow","Yellow Bell","Zenobia","Tiger Flower")
    val imageId = arrayOf<Int>(
        R.drawable.image1,R.drawable.image2,R.drawable.image3,
        R.drawable.image1,R.drawable.image2,R.drawable.image3,
        R.drawable.image1,R.drawable.image2,R.drawable.image3,
        R.drawable.image1,R.drawable.image2,R.drawable.image3,
        R.drawable.image1
    )
    val imageLan = arrayOf<Int>(
        R.drawable.image3,R.drawable.image1,R.drawable.image2,
        R.drawable.image3,R.drawable.image1,R.drawable.image2,
        R.drawable.image3,R.drawable.image1,R.drawable.image2,
        R.drawable.image3,R.drawable.image1,R.drawable.image2,
        R.drawable.image3
    )

    val languages = arrayOf<String>("PHP","Kotlin","Python","Java","C++","C#","Swift","Javascript")

        val names = arrayOf<String>("Hawra","Mohd","Najma","Mustafa","Ali","Hussain","Hassan","Fatima","Zahra","Zainab","Haider","Ruqaya","Mariam")
    //lateinit var listAdapter: ArrayAdapter<*>


    override fun getCount(): Int {
        return list.size
    }



    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view.equals(`object`)
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(ctx)
        var view = layoutInflater.inflate(R.layout.images,container,false)
        lateinit var listAdapter: ArrayAdapter<*>
        lateinit var img: ImageView
        lateinit var layout_dot: LinearLayoutCompat
        img = view.findViewById(R.id.pager_img)
        layout_dot = view.findViewById(R.id.layout_dot)



        img.setImageResource(list[position])
        container.addView(view,0)

        //dots
        tabLayout = view.findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(act.pager, true);


        // declare elements
        searchView = view.findViewById(R.id.searchView)
        listView = view.findViewById(R.id.listView)


        when (position) {
            0 -> listAdapter = ListAdapter(act, flowers, imageId)
            1 -> listAdapter = ListAdapter(act, languages, imageLan)
            2 -> listAdapter = ListAdapter(act, names, imageId)
            3 -> listAdapter = ListAdapter(act, flowers, imageId)
            4 -> listAdapter = ListAdapter(act, languages, imageId)
        }
        // to add image in the list view, something gets wrong with the searchView!


        // add adapter to list view
        //listAdapter = ArrayAdapter<String>(ctx, android.R.layout.simple_list_item_1, flowers)
        listView.adapter = listAdapter

        listView.setOnScrollListener(object : AbsListView.OnScrollListener {
            private var lastFirstVisibleItem = 0
            override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {}
            override fun onScroll(
                view: AbsListView,
                firstVisibleItem: Int,
                visibleItemCount: Int,
                totalItemCount: Int
            )
            {
                if (lastFirstVisibleItem < firstVisibleItem) {
                    img.visibility = GONE
                    layout_dot.visibility = GONE
//                    Toast.makeText(
//                        ctx, "Scrolling down the listView",
//                        Toast.LENGTH_SHORT
//                    ).show()
                }
                if (lastFirstVisibleItem > firstVisibleItem) {
                    img.visibility = View.VISIBLE
                    layout_dot.visibility = View.VISIBLE
//                    Toast.makeText(
//                        ctx, "Scrolling up the listView",
//                        Toast.LENGTH_SHORT
//                    ).show()
                }
                lastFirstVisibleItem = firstVisibleItem
            }
        })

//
//        //Search function
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (names.contains(query)) {
                    listAdapter.filter.filter(query)
                } else {
                    Toast.makeText(ctx, "No Match found", Toast.LENGTH_LONG).show()
                }
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                listAdapter.filter.filter(newText)
                return false
            }
        })
        return view
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}