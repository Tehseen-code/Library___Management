package org.example

import java.time.LocalDate

data class Transaction(
    val memberId: String,
    val itemId: String,
    val type: TransactionType,
    val date: LocalDate,
    val dueDate: LocalDate? = null
)