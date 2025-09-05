package org.example

class DVD(
    id: String,
    title: String,
    val director: String,
    val duration: Int,
    val genre: String
) : LibraryItem(id, title) {

    override fun getItemType(): String {
        return "DVD"
    }

    override fun calculateLateFee(daysLate: Int): Double {
        return daysLate * 1.00
    }

    override fun displayInfo(): String {
        return super.displayInfo() + ", Director: $director, Duration: $duration min, Genre: $genre"
    }
}