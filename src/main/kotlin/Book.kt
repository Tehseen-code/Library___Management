package org.example

class Book(
    id: String,
    title: String,
    val author: String,
    val isbn: String,
    val pages: Int
) : LibraryItem(id, title) {

    override fun getItemType(): String {
        return "Book"
    }

    override fun calculateLateFee(daysLate: Int): Double {
        return daysLate * 0.50
    }

    override fun displayInfo(): String {
        return super.displayInfo() + ", Author: $author, ISBN: $isbn, Pages: $pages"
    }
}
