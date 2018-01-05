package com.broliveira.extensions

import android.annotation.SuppressLint
import android.os.Build
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.transition.*
import android.view.View
import com.broliveira.extensions.android.R

fun<T : Fragment> FragmentTransaction.addWithBackStack(containerView: Int, fragment: T) : FragmentTransaction {
  return add(containerView, fragment).addToBackStack(fragment.javaClass.simpleName)
}

fun<T : Fragment> FragmentTransaction.replaceAddToBackStack(containerView: Int, fragment: T, tag: String? = null) : FragmentTransaction {
  return replace(containerView, fragment, tag).addToBackStack(fragment.javaClass.simpleName)
}

fun FragmentTransaction
    .addCustomAnimations(
    leftInAnimation: Int = R.anim.slide_in_left,
    leftOutAnimation: Int = R.anim.slide_out_left,
    rightInAnimation: Int = R.anim.slide_in_right,
    rightOutAnimation: Int = R.anim.slide_out_right
) : FragmentTransaction {
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
    this.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
    return this
  }
  this.setCustomAnimations(leftInAnimation, leftOutAnimation, rightInAnimation, rightOutAnimation)
  return this
}

@SuppressLint("NewApi")
fun<T: Fragment, V: View> FragmentTransaction
    .addSharedElement(destination: T?,
                      sharedViews: List<V>?,
                      sharedTransform: TransitionSet? = null,
                      enterTransition: Transition? = null,
                      exitTransition: Transition? = null
) : FragmentTransaction {
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
    this.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
    return this
  }
  this.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_out, android.R.anim.fade_in)
  if (destination == null || sharedViews == null || sharedViews.isEmpty()) {
    return this
  }

  val fade = Fade().apply { duration = 150 }
  destination.enterTransition = enterTransition ?: fade
  destination.allowEnterTransitionOverlap = true
  destination.allowReturnTransitionOverlap = true
  destination.exitTransition = exitTransition ?: fade

  destination.sharedElementEnterTransition = sharedTransform ?: DetailsTransition()
  destination.sharedElementReturnTransition = sharedTransform ?: DetailsTransition()
  sharedViews.forEach {
    this.addSharedElement(it, it.transitionName)
  }
  return this
}

@SuppressLint("NewApi")
class DetailsTransition : TransitionSet() {
  init {
    duration = 300
    ordering = ORDERING_TOGETHER
    addTransition(ChangeBounds())
    addTransition(ChangeTransform())
  }
}
