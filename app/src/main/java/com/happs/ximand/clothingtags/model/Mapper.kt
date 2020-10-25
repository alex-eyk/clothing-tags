package com.happs.ximand.clothingtags.model

interface Mapper<F, T> {

    fun map(from: F) : T

}