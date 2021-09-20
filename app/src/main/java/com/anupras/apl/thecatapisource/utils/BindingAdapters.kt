package com.anupras.apl.thecatapisource.utils

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Created by Anamika Painuly on 19/09/21.
 */

@BindingAdapter("load")
fun loadImage(view:ImageView, url:String)
{
    Glide.with(view).load(url).into(view)
    Log.d("Check--","imageUrl is :$url")
}