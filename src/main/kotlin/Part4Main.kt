//package org.example



import com.example.smartlibrary.calculateCompoundLateFee
import com.example.smartlibrary.findAllSubcategories
import com.example.smartlibrary.recursiveBinarySearch

fun main() {
    println("=== Recursion Tests ===")

    // 1. Late fee
    val fee = calculateCompoundLateFee(5.0, 7)
    println("Compound late fee for 7 days: $fee")

    // 2. Subcategories
    val hierarchy = mapOf(
        "Fiction" to listOf("Mystery", "Romance"),
        "Mystery" to listOf("Detective", "Thriller"),
        "Romance" to listOf("Historical Romance")
    )
    println("Subcategories of Fiction: ${findAllSubcategories("Fiction", hierarchy)}")

    // 3. Binary search
    val sortedList = listOf(1, 3, 5, 7, 9, 11)
    println("Index of 7: ${recursiveBinarySearch(sortedList, 7)}")
    println("Index of 2: ${recursiveBinarySearch(sortedList, 2)}")
}