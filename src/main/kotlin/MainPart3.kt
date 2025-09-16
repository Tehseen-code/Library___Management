
import org.example.Book
import org.example.DVD
import org.example.Library
import org.example.LibraryItem
import org.example.Magazine
import org.example.Member
import org.example.filterByAvailability
import org.example.getFormattedInfo
import org.example.isValidEmail

fun main() {
    val library = Library()

    // access private maps via reflection
    val libClass = library::class.java
    val itemsField = libClass.getDeclaredField("itemsById").apply { isAccessible = true }
    val membersField = libClass.getDeclaredField("members").apply { isAccessible = true }
    val borrowedField = libClass.getDeclaredField("borrowedItems").apply { isAccessible = true }

    @Suppress("UNCHECKED_CAST")
    val itemsMap = itemsField.get(library) as MutableMap<String, LibraryItem>

    @Suppress("UNCHECKED_CAST")
    val membersMap = membersField.get(library) as MutableMap<String, Member>

    @Suppress("UNCHECKED_CAST")
    val borrowedMap = borrowedField.get(library) as MutableMap<String, MutableList<String>>

    // create items as anonymous subclasses that implement the abstract methods
    val book1 = object : Book("b1", "Effective Kotlin", "Marcin", "ISBN-001", 350, true) {
        override fun getItemType(): String = "Book"
        override fun calculateLateFee(daysLate: Int): Double = daysLate * 0.5
    }

    val book2 = object : Book("b2", "Kotlin Cookbook", "Marcin", "ISBN-002", 250, true) {
        override fun getItemType(): String = "Book"
        override fun calculateLateFee(daysLate: Int): Double = daysLate * 0.5
    }

    val dvd1 = object : DVD("d1", "Kotlin Conf Talk", "Director X", 90, "Education") {
        override fun getItemType(): String = "DVD"
        override fun calculateLateFee(daysLate: Int): Double = daysLate * 1.0
    }

    val magazine1 = object : Magazine("mg1", "Kotlin Monthly", 12, "JetBrains Press") {
        override fun getItemType(): String = "Magazine"
        override fun calculateLateFee(daysLate: Int): Double = daysLate * 0.25
    }

    // set some availability states to test filterByAvailability
    book2.isAvailable = false
    dvd1.isAvailable = true
    magazine1.isAvailable = true

    // put items into library's private itemsById
    itemsMap[book1.id] = book1
    itemsMap[book2.id] = book2
    itemsMap[dvd1.id] = dvd1
    itemsMap[magazine1.id] = magazine1

    // create members and put into private members map
    val member1 = Member("u1", "Ali", "ali@example.com")
    val member2 = Member("u2", "Sara", "sara_at_example.com") // intentionally invalid email to test validator
    membersMap["u1"] = member1
    membersMap["u2"] = member2

    // create borrowedItems entries
    borrowedMap["u1"] = mutableListOf("b1", "d1") // Ali borrowed book1 and dvd1
    borrowedMap["u2"] = mutableListOf("b2")       // Sara borrowed book2 (which we set unavailable)

    // --- Tests ---

    // 1) findBooksByAuthor
    val marcinsBooks = library.findBooksByAuthor("Marcin")
    println("Books by Marcin:")
    marcinsBooks.forEach { println("  ${it.getFormattedInfo()}") }

    // 2) findItemsBy - all DVDs longer than 60 minutes
    val longDvds = library.findItemsBy(DVD::class.java) { it.duration > 60 }
    println("\nLong DVDs (>60min):")
    longDvds.forEach { println("  ${it.getFormattedInfo()}") }

    // 3) extension filterByAvailability
    val allItems = itemsMap.values.toList()
    println("\nAvailable items:")
    allItems.filterByAvailability(true).forEach { println("  ${it.getFormattedInfo()}") }

    println("\nUnavailable items:")
    allItems.filterByAvailability(false).forEach { println("  ${it.getFormattedInfo()}") }

    // 4) email validator
    println("\nEmail checks:")
    println("  ali@example.com -> ${"ali@example.com".isValidEmail()}")
    println("  sara_at_example.com -> ${"sara_at_example.com".isValidEmail()}")

    // 5) getFormattedInfo on individual items
    println("\nFormatted info samples:")
    println("  ${book1.getFormattedInfo()}")
    println("  ${dvd1.getFormattedInfo()}")
    println("  ${magazine1.getFormattedInfo()}")

    // 6) getLibraryStatistics (uses getItemType implemented in anonymous subclasses)
    println("\nLibrary statistics:")
    val stats = library.getLibraryStatistics()
    stats.forEach { (k, v) -> println("  $k -> $v") }

    // 7) processOverdueItems - use an action that prints fee and member reference
    println("\nProcessing overdue items:")
    library.processOverdueItems { item, member, daysLate ->
        val fee = item.calculateLateFee(daysLate)
        println("  Overdue item: ${item.getFormattedInfo()}")
        println("    Borrower object: $member")
        println("    Days late: $daysLate, Fee: $fee")
    }
}