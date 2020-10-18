package com.dnovaes.marvelheroes.utils

import android.content.Context
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat.getFont
import com.dnovaes.marvelheroes.R
import trikita.anvil.Anvil
import com.dnovaes.marvelheroes.utils.FontWeight.W100
import com.dnovaes.marvelheroes.utils.FontWeight.W300
import com.dnovaes.marvelheroes.utils.FontWeight.W400
import com.dnovaes.marvelheroes.utils.FontWeight.W500
import com.dnovaes.marvelheroes.utils.FontWeight.W700
import com.dnovaes.marvelheroes.utils.FontWeight.W900

enum class FontWeight {
    W100, W300, W400, W500, W700, W900
}

object Font {
    fun fontWeight(context: Context, weight: FontWeight) {
        val view = Anvil.currentView() as? TextView ?: return
        view.typeface = getFont(context, when (weight) {
            W100 -> R.font.roboto_black_thin
            W300 -> R.font.roboto_black_light
            W400 -> R.font.roboto_black_regular
            W500 -> R.font.roboto_black_medium
            W700 -> R.font.roboto_black_bold
            W900 -> R.font.roboto_black_black
        })
    }
}