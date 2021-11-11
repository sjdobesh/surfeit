package com.tfsg.surfeit

class Food(
    var title: String,
    var purchase_date: String,
    var expiration_date: String,
    var count: Int,
) {
    // id
    var id: String? = null

    // increment & decrement
    fun incCount() {
        count += 1
    }

    fun decCount() {
        count -= 1
    }
}