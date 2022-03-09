package com.example.jetpackarchitectureassignemt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackarchitectureassignemt.R
import com.example.jetpackarchitectureassignemt.Util
import com.example.jetpackarchitectureassignemt.databinding.ItemPageBinding
import com.example.jetpackarchitectureassignemt.model.PageModel
import java.util.*


class PageInfoAdapter(
    private var pageDataList: ArrayList<PageModel>?,
    private val itemClick: (itemPosition: Int, item: PageModel) -> Unit
) : RecyclerView.Adapter<PageInfoAdapter.PageInfoHolder>() {
    private lateinit var ctx: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageInfoHolder {
        ctx = parent.context
        val binding: ItemPageBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_page, parent,
            false
        )
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
        holder.itemView.setOnClickListener {
            itemClick.invoke(position, pageData ?: PageModel())
        }
    }

    override fun getItemCount() = pageDataList?.size ?: 0

    inner class PageInfoHolder(itemDataBinding: ItemPageBinding) :
        RecyclerView.ViewHolder(itemDataBinding.root) {
        private val itemBinding = itemDataBinding

        fun bind(pageData: PageModel?) {
            val date = Util().dateConverter(pageData?.created_at?:"")
            val dateText = ctx.getString(R.string.created_at) + date
            itemBinding.tvCreatedDate.text = dateText
            itemBinding.pageItems = pageData
        }
    }
}