package com.broliveira.extensions.viewhelper

import android.view.View
import android.view.ViewGroup
import java.util.*

class ViewChildSequence(private val view: View) : Sequence<View> {
  override fun iterator(): Iterator<View> {
    if (view !is ViewGroup) return emptyList<View>().iterator()
    return ViewIterator(view)
  }

  private class ViewIterator(private val view: ViewGroup) : Iterator<View> {
    private var index = 0
    private val count = view.childCount

    override fun next(): View {
      if (!hasNext()) throw NoSuchElementException()
      return view.getChildAt(index++)
    }

    override fun hasNext(): Boolean {
      checkCount()
      return index < count
    }

    private fun checkCount() {
      if (count != view.childCount) throw ConcurrentModificationException()
    }
  }
}