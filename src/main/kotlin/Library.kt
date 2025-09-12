package org.example

class Library {
    private val itemsById = HashMap<String, LibraryItem>()
    private val itemsByCategory = HashMap<String, MutableList<LibraryItem>>()
    private val members = HashMap<String, Member>()
    private val borrowedItems = HashMap<String, MutableList<String>>()

    fun addItem(item: LibraryItem) {
        itemsById[item.id] = item
        val category = item.getItemType()
        if (itemsByCategory.containsKey(category)) {
            itemsByCategory[category]?.add(item)
        } else {
            itemsByCategory[category] = mutableListOf(item)
        }
        println("${item.title} (${item.getItemType()}) has been added to the library.")
    } // This is the correct position for the closing brace.


    fun registerMember(member: Member) {
        members[member.getMemberId()] = member
        println("Member ${member.getName()} has been registered with ID ${member.getMemberId()}.")
    }

    fun borrowItem(memberId: String, itemId: String) {
        val member = members[memberId]
        val item = itemsById[itemId]

        if (member == null) {
            println("Error: Member with ID $memberId not found.")
            return
        }

        if (item == null) {
            println("Error: Item with ID $itemId not found.")
            return
        }

        if (!item.isAvailable) {
            println("Error: '${item.title}' is currently not available.")
            return
        }

        item.isAvailable = false

        if (borrowedItems.containsKey(memberId)) {
            borrowedItems[memberId]?.add(itemId)
        } else {
            borrowedItems[memberId] = mutableListOf(itemId)
        }

        println("${member.getName()} has successfully borrowed '${item.title}'.")
    }

    fun returnItem(memberId: String, itemId: String, daysLate: Int) {
        val member = members[memberId]
        val item = itemsById[itemId]

        if (member == null || item == null) {
            println("Error: Invalid member or item ID.")
            return
        }

        if (borrowedItems[memberId]?.remove(itemId) == true) {
            item.isAvailable = true
            val lateFee = item.calculateLateFee(daysLate)
            println("${member.getName()} has returned '${item.title}'. Late fee: $$lateFee.")
        } else {
            println("Error: Item with ID $itemId was not borrowed by member $memberId.")
        }
    }

    fun searchItemById(itemId: String): LibraryItem? {
        return itemsById[itemId]
    }

    fun searchItemsByCategory(category: String): List<LibraryItem>? {
        return itemsByCategory[category]
    }

    fun displayAllItems() {
        if (itemsById.isEmpty()) {
            println("The library inventory is empty.")
            return
        }
        println(" Library Inventory ")
        itemsById.values.forEach { item ->
            println(item.displayInfo())
        }
    }
}