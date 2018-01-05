package com.broliveira.extensions

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

class FragmentExtensions {
  companion object {
    var defaultFragmentId: Int? = null
  }
}

fun FragmentManager.currentFragment(id: Int? = FragmentExtensions.defaultFragmentId): Fragment? {
  if (id == null) return null
  if (backStackEntryCount == 0) return findFragmentById(id)
  return findFragmentByTag(getBackStackEntryAt(backStackEntryCount-1).name) ?: findFragmentById(id)
}