package com.broliveira.extensions

import android.widget.EditText
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.Subject

fun <T> Observable<T>.observeOnMainThread(): Observable<T> {
  return this.observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.goToBackgroundThread(backgroundScheduler: Scheduler = Schedulers.computation()): Observable<T> {
  return this.subscribeOn(backgroundScheduler)
}

fun <T> Observable<T>.bindTo(subject: Subject<T>): Disposable {
  return this.subscribe { subject.onNext(it) }
}

fun Observable<String>.bindToInitialText(editText: EditText): Disposable {
  return this.subscribe { editText.updateInitialText(it) }
}

fun Observable<String>.bindTo(editText: EditText): Disposable {
  return this.subscribe { editText.setText(it) }
}

fun <T> Observable<T>.debug(message: String = "", printNext: (value: T) -> String = { "$it" }): Observable<T> {
  return this
      .doOnNext { android.util.Log.e("RxDebug $message", "OnNext(${printNext(it)})") }
      .doOnError { android.util.Log.e("RxDebug $message", "OnError($it})") }
      .doOnComplete { android.util.Log.e("RxDebug $message", "OnComplete()") }
      .doOnDispose { android.util.Log.e("RxDebug $message", "OnDispose()") }
}