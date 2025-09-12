package org.example

class part1andpart2main {

    fun main() {

        val myLibrary = Library()


        println("--- Adding Books, DVDs, and Magazines to the Library ---")
        val book1 = Book("B001", "Learning OOP", "Muhammad Atif", "978-0-345-39180-3", 216)
        val dvd1 = DVD("D001", "Kotlin", "Tehseen ul hassan", 148, "Sci-Fi")
        val magazine1 = Magazine("M001", "National Geographic", 256, "National Geographic Society")
        val book2 = Book("B002", "Dune", "Frank Herbert", "978-0-441-17271-9", 412)

        myLibrary.addItem(book1)
        myLibrary.addItem(dvd1)
        myLibrary.addItem(magazine1)
        myLibrary.addItem(book2)

        println("\n Registering Members ")
        val member1 = Member("M001", "Alice", "alice@example.com")
        val member2 = Member("M002", "Bob", "bob@example.com")

        myLibrary.registerMember(member1)
        myLibrary.registerMember(member2)

        println("\n--- Current Library Inventory ---")
        myLibrary.displayAllItems()
        println()

        println("--- Member Alice borrows 'The Hitchhiker's Guide to the Galaxy' ---")
        myLibrary.borrowItem(member1.getMemberId(), book1.id)

        println("\n--- Member Bob tries to borrow the same book (which is now unavailable) ---")
        myLibrary.borrowItem(member2.getMemberId(), book1.id)

        println("\n--- Member Bob borrows 'Inception' DVD ---")
        myLibrary.borrowItem(member2.getMemberId(), dvd1.id)

        println("\n--- Current Library Inventory after borrowing ---")
        myLibrary.displayAllItems()
        println()

        println("--- Alice returns 'The Hitchhiker's Guide to the Galaxy' 5 days late ---")
        myLibrary.returnItem(member1.getMemberId(), book1.id, 5)

        println("\n--- Bob returns 'Inception' 10 days late ---")
        myLibrary.returnItem(member2.getMemberId(), dvd1.id, 10)

        println("\n--- Search for 'Dune' by its ID ---")
        val searchedBook = myLibrary.searchItemById("B002")
        println(searchedBook?.displayInfo() ?: "Item not found.")

        println("\n--- Search for all 'Books' by category ---")
        val searchedBooks = myLibrary.searchItemsByCategory("Book")
        searchedBooks?.forEach { item ->
            println(item.displayInfo())
        }

        println("\n Final Library Inventory after returns ")
        myLibrary.displayAllItems()
    }
}