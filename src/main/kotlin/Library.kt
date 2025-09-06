package org.example

import java.time.Clock


class Library {

    private val itemsById = HashMap<String, LibraryItem>()
    private val itemsByCategory = HashMap<String, MutableList<LibraryItem>>()
    private val members = HashMap<String, Member>()
    private val borrowedItems = HashMap<String, MutableList<String>>()


    // part 3

    // Filter available books by author
    fun findBooksByAuthor(author: String): List<Book> {
        // Use filter and map operations

        return itemsById.values.filterIsInstance<Book>().filter { it.author == author }.filter { it.isAvailable }
            .toList()
    }

    // Find all items of a specific type that meet criteria
    fun <T : LibraryItem> findItemsBy(
        type: Class<T>, predicate: (T) -> Boolean
    ): List<T> {
        // Use higher-order functions
        return itemsById.values.filter { type.isInstance(it) }.map { type.cast(it) }.filter(predicate).toList()
    }

    // Calculate statistics using functional operations
    fun getLibraryStatistics(): Map<String, Any> {
        // Use map, filter, reduce, groupBy operations
        // Return statistics like:
        // - Total items by type
        // - Average pages for books
        // - Most popular genre for DVDs
        // - Percentage of available items

        val totalItems = itemsById.values // items available in Library are here

        // first begin with the total items with type where?
        val totalitemType = totalItems.groupBy { it.getItemType() }.mapValues { (_, items) -> items.size }


        val averagePages = totalItems.filterIsInstance<Book>().map { it.pages }.ifEmpty { listOf(0) }.average()
        val mostPopularGenre =
            totalItems.filterIsInstance<DVD>().groupBy { it.genre }.maxByOrNull { (_, dvds) -> dvds.size }?.key ?: "N/A"
        val percentageAvailable =
            totalItems.filter { it.isAvailable }.size.toDouble() / totalItems.size.toDouble() * 100.0
        return mapOf(
            "totalItemsByType" to totalitemType,
            "averagePages" to averagePages,
            "mostPopularGenre" to mostPopularGenre,
            "percentageAvailable" to percentageAvailable
        )

    }

    // Process overdue items with custom action
    fun processOverdueItems(action: (LibraryItem, Member, Int) -> Unit) {
        // Use forEach with lambda
        val currentDate = Clock.systemDefaultZone().instant()
        borrowedItems.forEach { (memberId, itemIds) ->
            val member = members[memberId] ?: return@forEach // skip -> if the member is not found!
            itemIds.forEach { itemId ->
                val item = itemsById[itemId] ?: return@forEach // if item is not found, skip

                val overdueDays = 3  // just assume the overdue days here
                // the lambda action
                    action(item, member, overdueDays)

                }
            }

        }

    }
