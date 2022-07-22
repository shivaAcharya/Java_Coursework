/* *
 * @version date ( in_ISO_8601 format : 2015 - 11 - 08 )
 * @author Shiva Acharya
 */
public class Book {
    private int publishedYear;
    private String title;
    private String ISBN;
    private Author author;

    public Book() {
        this.title = BookConstants.TITLE_NOT_SET;
        this.author = BookConstants.AUTHOR_NOT_SET;
        this.ISBN = BookConstants.ISBN_NOT_SET;
        this.publishedYear = BookConstants.YEAR_NOT_SET;
    }

    public Book(String title) {
        this.title = title;
        this.author = BookConstants.AUTHOR_NOT_SET;
        this.ISBN = BookConstants.ISBN_NOT_SET;
        this.publishedYear = BookConstants.YEAR_NOT_SET;
    }

    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
        this.ISBN = BookConstants.ISBN_NOT_SET;
        this.publishedYear = BookConstants.YEAR_NOT_SET;
    }

    public void setTitle(String title) {
        if (title.length() > 0) {
            this.title = title;

        }
    }

    public String getTitle() {
        return title;

    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Author getAuthor() {

        return author;
    }

    public void setYear(int publishedYear) {

        if (publishedYear > -2000 && publishedYear < 2020)
            this.publishedYear = publishedYear;

    }

    public int getYear() {
        return publishedYear;
    }

    public void setISBN(String ISBN) {
        if (ISBN.length() == 10 || ISBN.length() == 13) {
            this.ISBN = ISBN;
        }

    }

    public String getISBN() {
        return ISBN;
    }

    public boolean sameAuthor(Book other) {
        if (author == other.author) {
            return true;
        } else
            return false;
    }

    public boolean equals(Book other) {
        if (ISBN == other.ISBN) {
            return true;
        } else
            return false;
    }

    public String toString() {
        String bookInformation;
        if (author != BookConstants.AUTHOR_NOT_SET && publishedYear == BookConstants.YEAR_NOT_SET) {
            bookInformation = title + ". " + author + ".";
        } else if (author != BookConstants.AUTHOR_NOT_SET && publishedYear != BookConstants.YEAR_NOT_SET) {
            bookInformation = title + " (" + publishedYear + ")" + ". " + author + ".";

        } else {
            bookInformation = title + ".";
        }
        return bookInformation;
    }

}
