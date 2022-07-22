/* *
 * @version date ( in_ISO_8601 format : 2015 - 11 - 16 )
 * @author Shiva Acharya
 */

public class Library {

    /** Books in the library. */
    private Book[] books;

    /** Number of copies for each book. */
    private int[] copies;

    /** Number of copies currently checked out for each book. */
    private int[] checkedOut;

    /** Number of unique books in the library. */
    private int numBooks;

    /** Construct a new empty Library. */
    public Library() {
        // We'll assume we never have more than 400 books.
        int librarySize = 400;
        books = new Book[librarySize];
        copies = new int[librarySize];
        checkedOut = new int[librarySize];
        numBooks = 0;
    }

    /**
     * Get the number of total copies of all books that exist in the library.
     * 
     * @return Total number of copies in the library.
     */
    public int totalCopies() {
        int count = 0;
        for (int i = 0; i < books.length; i++) {
            count += copies[i];
        }
        return count + totalCheckedOut();
    }

    /**
     * Get the number of copies currently checked out.
     * 
     * @return Total number of copies checked out.
     */
    public int totalCheckedOut() {
        int count = 0;
        for (int i = 0; i < books.length; i++) {
            count += checkedOut[i];
        }
        return count;
    }

    /**
     * Get a String representing the status of the library.
     * 
     * @return Status string.
     */
    public String statusString() {
        int bookSize = getUniqueBooks();
        bookSize = (bookSize == 0) ? 0 : bookSize--;
        String status = "Total unique books: " + bookSize + "\n" + "Total number of copies: " + totalCopies() + "\n"
                + "Total checked out: " + totalCheckedOut();
        return status;
    }

    /**
     * Add all the books in the array to the library. Adds one copy of each
     * book.
     * 
     * @param newBooks
     *            Books to add.
     */
    public void addBooks(Book[] newBooks) {
        int count = 0;
        int bookSize = getUniqueBooks();
        bookSize = (bookSize == 0) ? 0 : bookSize--;

        int newBooksSize = newBooks.length;
        boolean found = false;

        for (int i = 0; i < newBooksSize; i++) {
            for (int j = 0; j < bookSize; j++) {
                if (books[j] == newBooks[i]) {
                    found = true;
                    copies[i] = copies[i] + 1;

                }

            }
            if (!found) {
                books[bookSize] = newBooks[i];
                copies[bookSize] = copies[bookSize] + 1;
                bookSize++;

                count = copies[i];
            }
            found = false;

        }
    }

    /**
     * Add a single book the library.
     *
     * If the book is already present, adds another copy. If the book is new,
     * add it after the existing books.
     * 
     * @param b
     *            Book to add.
     */
    public void addBook(Book b) {
        int bookSize = getUniqueBooks();
        boolean found = false;
        for (int j = 0; j < bookSize; j++) {
            if (books[j] == b) {
                found = true;
                copies[j] = copies[j] + 1;
            }

        }
        if (!found) {
            books[bookSize] = b;
            copies[bookSize] = copies[bookSize] + 1;
            bookSize++;
        }
    }

    /**
     * Checks out a book from the library if possible.
     * 
     * @param b
     *            Book to check out.
     * @return String denoting success or failure.
     */
    public String checkOut(Book b) {

        int bookIndex = 0;
        for (int i = 0; i < books.length; i++) {
            if (books[i] == b) {
                if (copies[i] == 0) {
                    bookIndex = -1;
                    break;
                }
                bookIndex = i;
                break;
            }
        }
        if (bookIndex == 0)
            return "Book not found.";
        if (bookIndex == -1)
            return "All out of copies.";
        checkedOut[bookIndex] = checkedOut[bookIndex] + 1;
        copies[bookIndex] = copies[bookIndex] - 1;
        return "Checked out!";
    }

    /**
     * Checks in a book to the library if possible.
     * 
     * @param b
     *            Book to check in.
     * @return String denoting success or failure.
     */
    public String checkIn(Book b) {
        int bookIndex = 0;
        for (int i = 0; i < books.length; i++) {
            if (books[i] == b) {
                if (copies[i] == 0) {
                    bookIndex = i;
                    break;
                }
                bookIndex = -1;
                break;
            }
        }
        if (bookIndex == 0)
            return "Book not found.";
        if (bookIndex == -1)
            return "All of our copies are already checked in.";
        checkedOut[bookIndex] = checkedOut[bookIndex] - 1;
        copies[bookIndex] = copies[bookIndex] + 1;
        return "Checked in!";
    }

    /**
     * Get string representation of entire library collection and status.
     * 
     * @return String representation of library.
     */
    public String toString() {
        int bookSize = getUniqueBooks(), totalCopies = 0;
        String allBooks = "";
        for (int i = 0; i < bookSize; i++) {
            totalCopies = copies[i] + checkedOut[i];
            allBooks = (i == 0) ? allBooks : allBooks + "\n";
            allBooks = allBooks + i + ". " + books[i].getTitle() + ". " + books[i].getAuthor() + ". : " + copies[i]
                    + "/" + totalCopies;

        }
        String Status = statusString();
        allBooks = allBooks + "\n\n" + Status;
        return allBooks;
    }

    /**
     * Get number of unique books that exist for a given author.
     * 
     * @param a
     *            The author to check.
     * @return Number of books by the author.
     */
    public int numBooksByAuthor(Author a) {
        int booksize = getUniqueBooks();
        booksize = (booksize == 0) ? 0 : booksize--;
        int count = 0;

        for (int i = 0; i < booksize; i++) {
            Author b = books[i].getAuthor();
            if (b.isSame(a)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns a String that lists the unique books which exist for a given
     * author, in standard book format, with a newline after each. If no books
     * are found for the author, returns string that says so.
     * 
     * @param a
     *            The author in question.
     * @return String listing books by the author.
     */
    public String booksByAuthor(Author a) {
        int booksize = getUniqueBooks();
        booksize = (booksize == 0) ? 0 : booksize--;
        String book = "";
        int count = 0;

        for (int i = 0; i < booksize; i++) {
            Author b = books[i].getAuthor();
            if (b.isSame(a)) {

                book = book + books[i].getTitle() + ". " + a + ".\n";
                count++;
            }
        }
        if (count == 0)
            return "No books by " + a + ".";
        return book;
    }

    /**
     * Returns string that lists the unique books with contain the given string
     * within their titles, without regard for case, with a newline after each.
     * If no books are found containing the string, returns string that says so.
     * 
     * @param s
     *            The string to look for in the titles.
     * @return String listing books that contain given string in titles.
     */
    public String booksByTitle(String s) {
        int booksize = getUniqueBooks();
        booksize = (booksize == 0) ? 0 : booksize--;
        int count = 0, acount = 0;
        String book = "";

        for (int i = 0; i < booksize; i++) {
            String[] splited = books[i].getTitle().split(" ");
            for (int j = 0; j < splited.length; j++) {
                if (splited[j].toLowerCase().equals(s.toLowerCase())) {
                    count++;
                    acount++;
                }
            }
            if (count > 0)
                book = book + books[i].getTitle() + ". " + books[i].getAuthor() + ".\n";
            count = 0;
        }
        if (acount == 0)
            return "No books with \"" + s + "\" in the title.";
        return book;

    }

    /**
     * Deletes book entirely from the library.
     * 
     * @param b
     *            Book to remove.
     * @return String denoting success or failure.
     */
    public String deleteBook(Book b) {

        int booksize = getUniqueBooks();
        int index = -1;

        for (int i = 0; i < booksize; i++) {
            if (books[i] == b) {
                index = i;
                break;
            }
        }
        if (index == -1)
            return "Book not found.";
        for (int i = index; i < booksize; i++) {
            books[i] = books[i + 1];
            copies[i] = copies[i + 1];
            checkedOut[i] = checkedOut[i + 1];
        }

        return "Book removed.";
    }

    int getUniqueBooks() {
        int bookSize = 0;

        for (int i = 0; i < books.length; i++) {
            if (books[i] != null) {
                bookSize++;
            }
        }

        return bookSize;
    }

}