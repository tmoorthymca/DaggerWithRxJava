package com.thiru.telstratest.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.thiru.telstratest.R
import com.thiru.telstratest.adapter.CountryListAdapter
import com.thiru.telstratest.model.Row
import com.thiru.telstratest.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel:MainViewModel
    private lateinit var adapter:CountryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        viewModel = MainViewModel(this)
        adapter = CountryListAdapter(this)
        listView.setHasFixedSize(true)
        listView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        listView.adapter = adapter


        swipeContainer.setOnRefreshListener(object :SwipeRefreshLayout.OnRefreshListener{
            override fun onRefresh() {
                swipeContainer.setRefreshing(true)
                getDataFromServer()
            }

        })
        swipeContainer.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark)

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        swipeContainer.post({
            swipeContainer.setRefreshing(true)
            getDataFromServer()
        })
    }

    // get date from server and load to adapter
    private fun getDataFromServer(){
        if(viewModel.isNetworkAvailable()) {
            viewModel.compositeDisposable.add(viewModel.getCountryData()
                    .subscribe({
                        supportActionBar?.title = it.title
                        setViewStatus(it.rows)
                        adapter.addData(viewModel.filterListItem(it.rows))
                    }, {
                        it.printStackTrace()
                        if (adapter.itemCount <= 0) {
                            listView.visibility = View.GONE
                            dataNotFoundTxt.text = getString(R.string.data_not_found)
                            dataNotFoundTxt.visibility = View.VISIBLE
                        }
                        swipeContainer.setRefreshing(false)
                    }, {
                        swipeContainer.setRefreshing(false)
                    }))
        }else{
            dataNotFoundTxt.text = getString(R.string.network_error_msg)
            dataNotFoundTxt.visibility = View.VISIBLE
            swipeContainer.setRefreshing(false)
        }
    }

    // Display "Data not found" message when return empty list item from array
    private fun setViewStatus(rows: MutableList<Row>){
        if(adapter.itemCount>0 || rows.size>0){
            listView.visibility = View.VISIBLE
            dataNotFoundTxt.visibility = View.GONE
        }else{
            listView.visibility = View.GONE
            dataNotFoundTxt.text = getString(R.string.data_not_found)
            dataNotFoundTxt.visibility = View.VISIBLE
        }
    }

    //clear compositeDisposable (Rxjava2)
    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }
}
