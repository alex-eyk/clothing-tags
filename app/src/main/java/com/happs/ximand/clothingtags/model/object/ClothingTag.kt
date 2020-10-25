package com.happs.ximand.clothingtags.model.`object`

data class ClothingTag(var title: String) {
    var id: Int = -1
    var imagePath: String? = null
    var description: String? = null
    var washingType: Int = 0
    var washingMaximumTemp: Int = 0
    var whiteningType: Int = 0
    var ironingType: Int = 0
    var dryCleaningType: Int = 0
    var spinningType: Int = 0
    var dryingType: Int = 0
    var canBeTwisted: Boolean = true
}