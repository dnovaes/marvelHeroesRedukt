package com.dnovaes.marvelheroes.ui.components

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import com.dnovaes.marvelheroes.R
import com.dnovaes.marvelheroes.extensions.color
import com.dnovaes.marvelheroes.extensions.dp
import com.dnovaes.marvelheroes.extensions.sp
import com.dnovaes.marvelheroes.models.Character
import com.dnovaes.marvelheroes.ui.anvil.LinearLayoutComponent
import com.dnovaes.marvelheroes.ui.anvil.highOrderComponent
import com.dnovaes.marvelheroes.utils.Font.fontWeight
import com.dnovaes.marvelheroes.utils.FontWeight
import com.dnovaes.marvelheroes.utils.GlideHelper.glideBitmap
import com.dnovaes.marvelheroes.utils.GlideHelper.replaceToHttps
import trikita.anvil.Anvil.currentView
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.WRAP
import trikita.anvil.BaseDSL.margin
import trikita.anvil.BaseDSL.padding
import trikita.anvil.BaseDSL.size
import trikita.anvil.BaseDSL.text
import trikita.anvil.BaseDSL.textSize
import trikita.anvil.DSL.backgroundColor
import trikita.anvil.DSL.imageView
import trikita.anvil.DSL.orientation
import trikita.anvil.DSL.textView
import trikita.anvil.DSL.view

inline fun characterDetailView(crossinline func: CharacterDetailView.() -> Unit) {
    highOrderComponent(func)
}

class CharacterDetailView(context: Context): LinearLayoutComponent(context) {

    private var character: Character? = null

    override fun view() {
        val character = character ?: return
        size(MATCH, MATCH)
        orientation(VERTICAL)

        renderExtendedImage(character)
        renderDetails(character)
        renderBottomDivider()
    }

    private fun renderExtendedImage(character: Character) {
        val thumbnail = character.thumbnail
        val filePath = replaceToHttps("${thumbnail.path}/portrait_uncanny.${thumbnail.extension}")
        imageView {
            size(MATCH, WRAP)
            glideBitmap(context, filePath, currentView())
            margin(context.dp(R.dimen.margin_large), 0)
            padding(0)
        }
    }

    private fun renderDetails(character: Character) {
        renderDescriptionField(formatTitle(context.getString(R.string.name), character.name))
        renderDescriptionField(formatTitle(context.getString(R.string.description), character.description))
        renderDescriptionField(formatTitle(context.getString(R.string.more_info), character.resourceURI))
    }

    private fun renderDescriptionField(content: SpannableString) {
        textView {
            size(MATCH, WRAP)
            margin(context.dp(R.dimen.margin_large), context.dp(R.dimen.margin_default))
            fontWeight(context, FontWeight.W500)
            text(content)
            textSize(context.sp(R.dimen.body_size))
        }
    }

    private fun formatTitle(label: String, content: String): SpannableString {
        val spanText = SpannableString("$label: $content")
        spanText.setSpan(
            ForegroundColorSpan(context.color(R.color.colorPrimary)), 0, label.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spanText
    }

    private fun renderBottomDivider() {
        view {
            size(MATCH, context.dp(R.dimen.divider_footer))
            backgroundColor(context.color(R.color.colorPrimary))
        }
    }

    fun character(character: Character) {
        this.character = character
        render()
    }
}
