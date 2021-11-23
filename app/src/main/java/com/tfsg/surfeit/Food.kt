package com.tfsg.surfeit

// class to represent a food entry
class Food(
    var title: String,
    var purchaseDate: String,
    var expirationDate: String,
    var count: Int,
) {
    // id
    var id: Int? = null

    // increment & decrement
    fun incCount() {
        count += 1
    }

    fun decCount() {
        count -= 1
    }
}