package com.thiru.telstratest.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.thiru.telstratest.R
import com.thiru.telstratest.model.Row
import kotlinx.android.synthetic.main.list_item_view.view.*
/**
 * Created by Thirumoorthy on 24/11/2018.
 */
class CountryListAdapter(val context: Context): RecyclerView.Adapter<CountryListAdapter.ViewHolder>() {

    private var listItem  = mutableListOf<Row>()

    fun addData(rowList:MutableList<Row>){
        listItem.addAll(rowList)
        listItem = listItem.distinct().toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_view, parent,false))
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        fun bind(row: Row){
                itemView.titleTxt.text = row.title
                itemView.descTxt.text = row.description
                if(!TextUtils.isEmpty(row.imageHref) && URLUtil.isNetworkUrl(row.imageHref)) {
                    Glide.with(context)
                            .load(row.imageHref)
                            .apply(RequestOptions()
                                    .override(100 ,100))
                            .into(itemView.imageView)
                }else{
                    itemView.imageView.visibility = View.GONE
                }
        }
    }
}