package com.happs.ximand.clothingtags.model.repository.mapper

interface Mapper<F, T> {

    fun map(from: F) : T

}