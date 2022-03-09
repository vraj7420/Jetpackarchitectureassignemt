package com.example.jetpackarchitectureassignemt.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.Util
import com.example.jetpackarchitectureassignemt.databinding.ItemPageBinding
import com.example.jetpackarchitectureassignemt.model.PageModel
import com.google.android.material.snackbar.Snackbar
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class PageInfoAdapter(private var pageDataList: ArrayList<PageModel>?) :
    RecyclerView.Adapter<PageInfoAdapter.PageInfoHolder>() {
    private  lateinit var  ctx:Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageInfoHolder {
        ctx=parent.context
        val binding: ItemPageBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_page, parent,
            false)
        return PageInfoHolder(binding)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: PageInfoHolder, position: Int) {
        val pageData = pageDataList?.get(position)
        holder.bind(pageData)
        holder.itemView.setOnClickListener{
            Snackbar.make((ctx as Activity).findViewById(android.R.id.content), position.toString()+","+pageData?.title.toString(), Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int{
        return pageDataList?.size ?: 0
    }
  inner class PageInfoHolder(itemDataBinding:ItemPageBinding) : RecyclerView.ViewHolder(itemDataBinding.root){
        private val itemBinding=itemDataBinding
        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        fun bind(pageData: PageModel?){
            val originalFormat: DateFormat = SimpleDateFormat(Util.dateOldFormat, Locale.ENGLISH)
            val targetFormat: DateFormat = SimpleDateFormat(Util.dataFormat)
            val origFormatDate=originalFormat.parse(pageData?.created_at ?:" ")
            val date=targetFormat.format(origFormatDate?: "")
             val dateText = ctx.getString(R.string.created_at)+date
            itemBinding.tvCreatedDate.text=dateText
            itemBinding.pageItems=pageData

        }
    }
}