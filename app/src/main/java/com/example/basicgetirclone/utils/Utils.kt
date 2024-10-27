package com.example.basicgetirclone.utils

import android.widget.ImageView
import com.example.basicgetirclone.R
import com.squareup.picasso.Picasso

class Utils {
    companion object {
        fun covertToPicasso(url:String,image:ImageView) {
            Picasso.get()
                .load(url)
                .placeholder(R.drawable.selected_subcategory)
                .error(R.drawable.ic_launcher_background)
                .into(image)
        }
    }
}