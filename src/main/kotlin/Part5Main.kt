package org.example
import com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

fun main() {
// Sample data
    val book1 = Book("B001", "The Kotlin Guide", "John Doe", "978-1234567890", 300)
    val book2 = Book("B002", "Harry Potter", "RJ Simmons", "877-1958567888", 250)
    val dvd1 = DVD("D001", "Kotlin Tutorial", "Jane Smith", 120, "Educational")
    val mag1 = Magazine("MG1", "Times", 66, "NY")

// Transaction and TransactionType TESTS

    println("-----Transaction and TransactionType TESTS-----")

    val borrowItem1: Transaction = Transaction(
        memberId = "M1",
        itemId = "I1",
        type = TransactionType.Borrow,
        date = LocalDate.now(),
        dueDate = LocalDate.of(2025, 9, 10)
    )

    println(borrowItem1)

    val borrowItem2: Transaction = Transaction(
        memberId = "M1",
        itemId = "I2",
        type = TransactionType.Borrow,
        date = LocalDate.now(),
        dueDate = LocalDate.of(2025, 9, 11)
    )

    println(borrowItem2)

    val returnItem1: Transaction = Transaction(
        memberId = "M1",
        itemId = "I1",
        type = TransactionType.Return,
        date = LocalDate.now()
    )

    println(returnItem1)

    val renewItem2: Transaction = Transaction(
        memberId = "M1",
        itemId = "I1",
        type = TransactionType.Renew(newDueDate = LocalDate.of(2025, 9, 15)),
        date = LocalDate.now(),
        dueDate = LocalDate.of(2025, 9, 15)
    )

    println(renewItem2)

    // Search Result TEST

    println("-----Search Result TEST-----")

    val listOfItems:List<LibraryItem> = listOf(book1, book2, dvd1, mag1)

    val searchCriteria = "Book"

    val searchResult: SearchResult = SearchResult(
        items = listOfItems,
        totalFound = listOfItems.count{ item ->
            item.getItemType() == searchCriteria
        },
        searchCriteria = searchCriteria
    )

    println(searchResult)

    // Library Utils TESTS

    println("-----Library Utils TESTS-----")

    println(LibraryUtils.MAX_BORROW_LIMIT)
    println(LibraryUtils.DEFAULT_BORROW_DAYS)

    println(LibraryUtils.generateItemId(type = book1.getItemType()))
    println(LibraryUtils.generateItemId(type = book2.getItemType()))
    println(LibraryUtils.generateItemId(type = dvd1.getItemType()))
    println(LibraryUtils.generateItemId(type = mag1.getItemType()))

    val goodISBN1 = book1.isbn
    val goodISBN2 = book2.isbn
    val badISBN1 = "978-123456789"//too short
    val badISBN2 = "9781-234567890"// - at wrong position
    val badISBN3 = "9A8-1234567890"// letter in 2nd character
    val badISBN4 = "978-123456789Q"// letter in last character

    println(LibraryUtils.validateISBN(goodISBN1))
    println(LibraryUtils.validateISBN(goodISBN2))
    println(LibraryUtils.validateISBN(badISBN1))
    println(LibraryUtils.validateISBN(badISBN2))
    println(LibraryUtils.validateISBN(badISBN3))
    println(LibraryUtils.validateISBN(badISBN4))

    // Library Config TESTS

    println("-----Library Config TESTS-----")

    println(LibraryConfig.categories)
    println(LibraryConfig.maxRenewalTimes)
    println(LibraryConfig.lateFeeCap)

}