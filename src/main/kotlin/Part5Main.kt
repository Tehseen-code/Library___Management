package org.example
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

fun main() {
    val borrowItem1: Transaction = Transaction(
        memberId = "M1",
        itemId = "I1",
        type = TransactionType.Borrow,
        date = LocalDate.now()
    )
    val borrowItem2: Transaction = Transaction(
        memberId = "M1",
        itemId = "I2",
        type = TransactionType.Borrow,
        date = LocalDate.now()
    )
    val returnItem1: Transaction = Transaction(
        memberId = "M1",
        itemId = "I1",
        type = TransactionType.Return,
        date = LocalDate.now()
    )
    val renewItem2: Transaction = Transaction(
        memberId = "M1",
        itemId = "I1",
        type = TransactionType.Renew(newDueDate = LocalDate.of(2025, 9, 10)),
        date = LocalDate.now()
    )

    println(LocalDate.now())

    println(LocalDate.of(2025, 9, 10))

    println(LocalTime.now())

    println(LocalDateTime.now())

    println("B:" + Timestamp.valueOf(LocalDateTime.now()))

//    val n1 = 1
//    val n2 = 'a'
//    val number = Regex("0..9")
//    if(number.any(n1))

}