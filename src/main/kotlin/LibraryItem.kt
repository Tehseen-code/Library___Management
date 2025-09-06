package org.example

abstract class LibraryItem(
    val id: String,
    val title: String,
    var isAvailable: Boolean = true
) {
    abstract fun getItemType(): String
    abstract fun calculateLateFee(daysLate: Int): Double
    open fun displayInfo(): String {
        return "ID: $id, Title: $title, Available: $isAvailable"
    }
}