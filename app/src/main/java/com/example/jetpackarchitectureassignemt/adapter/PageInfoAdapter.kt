package com.example.jetpackarchitectureassignemt.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.Util
import com.example.jetpackarchitectureassignemt.databinding.ItemPageBinding
import com.example.jetpackarchitectureassignemt.model.PageModel
import java.text.SimpleDateFormat


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
    }

    override fun getItemCount(): Int{
        return pageDataList?.size ?: 0
    }

  inner class PageInfoHolder(itemDataBinding:ItemPageBinding) : RecyclerView.ViewHolder(itemDataBinding.root){
        private val itemBinding=itemDataBinding
        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        fun bind(pageData: PageModel?){
            val dateFormat = SimpleDateFormat(Util.dataFormat)
            val date = ctx.getString(R.string.created_at)+dateFormat.parse(pageData?.created_at?:"")
            itemBinding.tvCreatedDate.text =date
            itemBinding.tvTitle.text=ctx.getString(R.string.title)+ (pageData?.title ?: "")
            itemBinding.tvUrl.text= pageData?.url ?:" "
        }
    }
}