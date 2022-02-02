package com.github.seanse.ean13

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat

class Ean13View @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        typeface = ResourcesCompat.getFont(context, R.font.ean_p72t_normal)
    }

    fun setEan(text: String) {
        val codeBuilder = EAN13CodeBuilder(text)
        setText(codeBuilder.getCode())
    }
}
