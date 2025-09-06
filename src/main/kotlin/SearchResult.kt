package org.example

data class SearchResult(
    val items: List<LibraryItem>,
    val totalFound: Int,
    val searchCriteria: String
)