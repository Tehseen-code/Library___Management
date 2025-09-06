package org.example

import java.time.LocalDate

sealed class TransactionType {
    object Borrow: TransactionType()
    object Return: TransactionType()
    data class Renew(val newDueDate: LocalDate): TransactionType()
}