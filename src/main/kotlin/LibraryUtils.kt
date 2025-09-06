package org.example

import java.sql.Timestamp
import java.time.LocalDateTime

class LibraryUtils {
    companion object {
        const val MAX_BORROW_LIMIT = 5
        const val DEFAULT_BORROW_DAYS = 14

        fun generateItemId(type: String): String {
            // Generate unique ID based on type and timestamp
            var result: String = ""
                when (type) {
                    "Book" -> result = "B:" + Timestamp.valueOf(LocalDateTime.now())
                    "DVD" -> result = "D:" + Timestamp.valueOf(LocalDateTime.now())
                    "Magazine" -> result = "Mg:" + Timestamp.valueOf(LocalDateTime.now())
                }
            return result
        }

        fun validateISBN(isbn: String): Boolean {
            // Validate ISBN format
            // 978-1234567890
            val isbnArray: CharArray = isbn.toCharArray()

            if(isbn.length != 14) {
                return false
            }
            else if (isbnArray[3] != '-') {
                return false
            }

            else  {
                for(i in 0..2) {
                    if(!isbnArray[i].isDigit()) {
                        return false
                    }
                }

                for(i in 4..13) {
                    if(!isbnArray[i].isDigit()) {
                        return false
                    }
                }
                return true
            }
        }
    }
}