package com.broliveira.extensions

import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import com.broliveira.extensions.viewhelper.ViewChildSequence
import java.util.*

var View.transitionNameCompat: String
  get() {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      this.transitionName
    } else {
      return ""
    }
  }
  set(value) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      this.transitionName = value
    }
  }


fun View.addViewId(usedIds: MutableMap<Int, View> = this.usedIds()): MutableMap<Int, View> {
  if (id <= 0) {
    addRandomId(usedIds)
  }
  return usedIds
}

fun View.addRandomId(usedIds: MutableMap<Int, View> = this.usedIds()) {
  var id = -1
  while(id == -1 || usedIds.containsKey(id)) {
    id = Random().nextInt(Integer.MAX_VALUE)
  }
  this.id = id
  usedIds.put(id, this)
}

private fun View.usedIds(checkedViews: MutableMap<View, Int> = this.buildViewTree()): MutableMap<Int, View> {
  return mutableMapOf<Int, View>().also { map ->
    checkedViews.forEach {
      map.put(it.value, it.key)
    }
  }
}

private fun View.buildViewTree(): MutableMap<View, Int> {
  return mutableMapOf<View, Int>().also { map -> this.buildViewTree(map) }
}

private fun View.buildViewTree(checkedViews: MutableMap<View, Int>) {
  if (checkedViews.containsKey(this))
    return

  checkedViews.put(this, this.id)

  if(this is ViewGroup) {
    childrenSequence().forEach {
      it.buildViewTree(checkedViews)
    }
  }
  parent?.buildViewTree(checkedViews)
}

private fun ViewParent.buildViewTree(checkedViews: MutableMap<View, Int>) {
  if(this is View) {
    if (checkedViews.containsKey(this))
      return

    checkedViews.put(this, this.id)
  }

  if (this is ViewGroup) {
    childrenSequence().forEach {
      it.buildViewTree(checkedViews)
    }
  }
}

/**
 * Return the [Sequence] of all children of the received [View], recursively.
 * Note that the sequence is not thread-safe.
 *
 * @return the [Sequence] of children.
 */
fun View.childrenSequence(): Sequence<View> = ViewChildSequence(this)
