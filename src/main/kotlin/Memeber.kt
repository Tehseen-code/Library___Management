package org.example

class Member(
    private val memberId: String,
    private var name: String,
    private var email: String
) {
    private val borrowedItems = mutableListOf<LibraryItem>()


    fun getMemberId(): String {
        return memberId
    }

    fun getName(): String {
        return name
    }

    fun getEmail(): String {
        return email
    }


    fun setName(newName: String) {
        if (newName.isNotBlank()) {
            this.name = newName
        } else {
            println("Name cannot be empty.")
        }
    }

    fun setEmail(newEmail: String) {
        if (newEmail.contains("@") && newEmail.contains(".")) {
            this.email = newEmail
        } else {
            println("Invalid email format.")
        }
    }


    fun borrowItem(item: LibraryItem) {
        if (item.isAvailable) {
            item.isAvailable = false
            borrowedItems.add(item)
            println("$name has borrowed '${item.title}'.")
        } else {
            println("'${item.title}' is currently unavailable.")
        }
    }

    fun returnItem(item: LibraryItem, daysLate: Int) {
        if (borrowedItems.remove(item)) {
            item.isAvailable = true
            val lateFee = item.calculateLateFee(daysLate)
            println("$name has returned '${item.title}'. Late fee is $$lateFee.")
        } else {
            println("$name did not borrow '${item.title}'.")
        }
    }


    fun calculateTotalLateFees(itemsWithDaysLate: Map<LibraryItem, Int>): Double {
        var totalFee = 0.0
        for ((item, daysLate) in itemsWithDaysLate) {
            totalFee += item.calculateLateFee(daysLate)
        }
        return totalFee
    }


    fun displayBorrowedItems() {
        if (borrowedItems.isEmpty()) {
            println("$name has no items borrowed.")
        } else {
            println("$name's borrowed items:")
            borrowedItems.forEach {
                println("- ${it.displayInfo()}")
            }
        }
    }
}