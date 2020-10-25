package com.happs.ximand.clothingtags.model

interface Repository<T> {
    fun add(item: T)
    fun add(items: Iterable<T>)
    fun update(item: T)
    fun remove(item: T)
    fun queryAll(): List<T>
}