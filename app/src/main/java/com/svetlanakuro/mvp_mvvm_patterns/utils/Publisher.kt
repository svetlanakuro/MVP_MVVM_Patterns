package com.svetlanakuro.mvp_mvvm_patterns.utils

import android.os.Handler

private class Subscriber<T>(
    private val uiHandler: Handler, private val callback: (T) -> Unit
) {

    fun invoke(value: T) {
        uiHandler.post {
            callback.invoke(value)
        }
    }
}

class Publisher<T> {

    private val subscribers: MutableSet<Subscriber<T>> = mutableSetOf()
    private var value: T? = null

    fun subscribe(uiHandler: Handler, callback: (T) -> Unit) {
        val subscriber = Subscriber(uiHandler, callback)
        subscribers.add(subscriber)
        value?.let {
            uiHandler.post {
                subscriber.invoke(it)
            }
        }
    }

    fun unsubscribe(uiHandler: Handler, callback: (T) -> Unit) {
        val subscriber = Subscriber(uiHandler, callback)
        subscribers.remove(subscriber)
    }

    fun unsubscribeAll() {
        subscribers.clear()
    }

    fun post(value: T) {
        this.value = value
        subscribers.forEach {
            it.invoke(value)
        }
    }
}