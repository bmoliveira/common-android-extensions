package com.broliveira.extensions

import android.widget.EditText

var EditText.currentText: String
  get() = text.toString()
  set(value) = setText(value)

fun EditText.updateInitialText(text: CharSequence) {
  setText("")
  append(text)
}
