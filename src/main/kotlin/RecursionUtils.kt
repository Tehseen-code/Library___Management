

package com.example.smartlibrary

// 1. Recursive compound late fee
fun calculateCompoundLateFee(baseFee: Double, days: Int): Double {
    return if (days == 0) baseFee
    else calculateCompoundLateFee(baseFee * 1.05, days - 1)
}

// 2. Recursive subcategory finder
fun findAllSubcategories(
    category: String,
    categoryHierarchy: Map<String, List<String>>
): List<String> {
    val directSubs = categoryHierarchy[category] ?: emptyList()
    return directSubs + directSubs.flatMap { sub -> findAllSubcategories(sub, categoryHierarchy) }
}

// 3. Recursive binary search
fun <T : Comparable<T>> recursiveBinarySearch(
    list: List<T>, target: T,
    low: Int = 0, high: Int = list.size - 1
): Int {
    if (low > high) return -1
    val mid = (low + high) / 2
    return when {
        list[mid] == target -> mid
        list[mid] > target -> recursiveBinarySearch(list, target, low, mid - 1)
        else -> recursiveBinarySearch(list, target, mid + 1, high)
    }
}