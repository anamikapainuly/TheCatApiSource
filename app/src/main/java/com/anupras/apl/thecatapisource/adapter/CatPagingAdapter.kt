package com.anupras.apl.thecatapisource.adapter

import android.util.Log
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

class CatPagingAdapter :
    PagingDataAdapter<CatImagesResponseItem, CatPagingAdapter.CatImagesViewHolder>(DIFF_UTIL) {

    var onClick: ((String) -> Unit)? = null

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<CatImagesResponseItem>() {
            override fun areItemsTheSame(
                oldItem: CatImagesResponseItem,
                newItem: CatImagesResponseItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: CatImagesResponseItem,
                newItem: CatImagesResponseItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun onCatClick(listener: (String) -> Unit) {
        onClick = listener
    }

    inner class CatImagesViewHolder(val viewDataBinding: ViewHolderItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)

    override fun onBindViewHolder(holder: CatImagesViewHolder, position: Int) {
        val data = getItem(position)
        holder.viewDataBinding.setVariable(BR.catSearchResponse, data)
        holder.viewDataBinding.root.setOnClickListener {
            onClick?.let {
                it(data?.id!!)
                Log.d("Check--ID IS1", ":${data.id}")
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatImagesViewHolder {
        val binding =
            ViewHolderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatImagesViewHolder(binding)
    }

}