package com.dnovaes.marvelheroes.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders

object GlideHelper {

    fun glideUrl(url: String): GlideUrl {
        return GlideUrl(url, LazyHeaders.Builder().build())
    }

    fun loadFilePath(path: String, usingLocalPicture: Boolean): Any {
        return if (!usingLocalPicture) glideUrl(path) else path
    }

    fun glideBitmap(context: Context, filePath: String, currentView: ImageView) {
        Glide.with(context)
            .asBitmap()
            .load(loadFilePath(filePath, false))
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(currentView)
    }
}
