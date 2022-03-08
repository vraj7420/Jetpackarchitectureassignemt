package com.example.jetpackarchitectureassignemt.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.databinding.ItemPageBinding
import com.example.jetpackarchitectureassignemt.model.PageModel


class PageInfoAdapter(private var pageDataList: ArrayList<PageModel>?) :
    RecyclerView.Adapter<PageInfoAdapter.PageInfoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageInfoHolder {
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
    }

    override fun getItemCount(): Int{
        return pageDataList?.size ?: 0
    }

    class PageInfoHolder(itemDataBinding:ItemPageBinding) : RecyclerView.ViewHolder(itemDataBinding.root){
        private val itemBinding=itemDataBinding
        fun bind(pageData: PageModel?){
            itemBinding.pageItems=pageData
        }
    }
}