package com.anupras.apl.thecatapisource.adapter

import android.content.Context
import android.database.DataSetObserver
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.SpinnerAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.anupras.apl.thecatapisource.R
import com.anupras.apl.thecatapisource.databinding.SpinnerHolderItemBinding
import com.anupras.apl.thecatapisource.model.Categories
import com.bumptech.glide.Glide

/**
 * Created by Anamika Painuly on 25/09/21.
 */
class CustomSpinner(context: Context, list: List<Categories>) :
    ArrayAdapter<Categories>(context, ViewHolder.LAYOUT, list) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return onBindView(parent, position)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return onBindView(parent, position)
    }

    private fun onBindView(parent: ViewGroup, position: Int): View {
        var categories = getItem(position)
        val dataBinding: SpinnerHolderItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.spinner_holder_item,
            parent,
            false
        )
        dataBinding.category = categories
        dataBinding.catTitle.text = categories?.name
        return dataBinding.root
    }

    private class ViewHolder {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.spinner_holder_item
        }
    }


}