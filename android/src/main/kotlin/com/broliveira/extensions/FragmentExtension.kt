package com.broliveira.extensions

import android.os.Bundle
import android.support.v4.app.Fragment

val Fragment.safeArguments: Bundle?
  get() {
    if (arguments != null)
      return arguments
    return null
  }