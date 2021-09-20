package com.anupras.apl.thecatapisource.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.anupras.apl.thecatapisource.BR
import com.anupras.apl.thecatapisource.databinding.ViewHolderItemBinding
import com.anupras.apl.thecatapisource.model.CatImagesResponseItem

/**
 * Created by Anamika Painuly on 19/09/21.
 */

class CatPagingAdapter: PagingDataAdapter<CatImagesResponseItem,CatPagingAdapter.MyViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<CatImagesResponseItem>() {
            override fun areItemsTheSame(oldItem: CatImagesResponseItem, newItem: CatImagesResponseItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: CatImagesResponseItem, newItem: CatImagesResponseItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class MyViewHolder(val viewDataBinding: ViewHolderItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewDataBinding.setVariable(BR.catSearchResponse,getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ViewHolderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

}