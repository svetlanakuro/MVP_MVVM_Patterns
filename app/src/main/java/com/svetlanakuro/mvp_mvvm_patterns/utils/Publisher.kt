package com.svetlanakuro.mvp_mvvm_patterns.utils

private typealias Subscriber<T> = (T) -> Unit

class Publisher<T> {

    private val subscribers: MutableSet<Subscriber<T>> = mutableSetOf()
    private var value: T? = null

    fun subscribe(subscriber: Subscriber<T>) {
        subscribers.add(subscriber)
        value?.let {
            subscriber.invoke(it)
        }
    }

    fun unsubscribe(subscriber: Subscriber<T>) {
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