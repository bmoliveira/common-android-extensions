package com.broliveira.extensions

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.View

fun Bundle.removeEnum(key: Enum<*>) {
  remove(key.name)
}

fun Bundle.putStringEnum(key: Enum<*>, value: String) {
  putString(key.name, value)
}

fun Bundle.getStringEnum(key: Enum<*>) : String? {
  return getString(key.name)
}

fun Bundle.putCharSequenceEnum(key: Enum<*>, value: CharSequence) {
  putCharSequence(key.name, value)
}

fun Bundle.getCharSequence(key: Enum<*>) : CharSequence? {
  return getCharSequence(key.name)
}

fun Bundle.putDoubleEnum(key: Enum<*>, value: Double?, default: Double = 0.toDouble()) {
  putDouble(key.name, value ?: default)
}

fun Bundle.getDoubleEnum(key: Enum<*>) : Double? {
  return getDouble(key.name)
}

fun Bundle.putFloatEnum(key: Enum<*>, value: Float?, default: Float = 0f) {
  putFloat(key.name, value ?: default)
}

fun Bundle.getFloatEnum(key: Enum<*>) : Float? {
  return getFloat(key.name)
}

fun Bundle.putLongEnum(key: Enum<*>, value: Long) {
  putLong(key.name, value)
}

fun Bundle.getLongEnum(key: Enum<*>) : Long {
  return getLong(key.name, 0L)
}

fun Bundle.putIntEnum(key: Enum<*>, value: Int) {
  putInt(key.name, value)
}

fun Bundle.getIntEnum(key: Enum<*>) : Int {
  return getInt(key.name, 0)
}

fun Bundle.putBooleanEnum(key: Enum<*>, value: Boolean) {
  putBoolean(key.name, value)
}

fun Bundle.getBooleanEnum(key: Enum<*>) : Boolean {
  return getBoolean(key.name)
}

fun Bundle.putParcelableEnum(key: Enum<*>, value: Parcelable) {
  putParcelable(key.name, value)
}

fun <T: Parcelable> Bundle.getParcelableEnum(key: Enum<*>) : T {
  return getParcelable(key.name)
}

fun Bundle.putTransitionEnum(key: Enum<*>, value: View?) {
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
    return
  }
  value?.transitionName?.let {
    putStringEnum(key, it)
  }
}
