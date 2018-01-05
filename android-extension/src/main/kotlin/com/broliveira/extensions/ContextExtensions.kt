package com.broliveira.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.AttrRes

fun Context.drawableFromAttributes(@AttrRes attrRes: Int): Drawable {
  val attrs = intArrayOf(attrRes)
  val ta = obtainStyledAttributes(attrs)
  return ta.getDrawable(0).also { ta.recycle() }
}
