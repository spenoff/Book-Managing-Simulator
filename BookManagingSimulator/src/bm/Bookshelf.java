package bm;

/**
 * The <code>Book</code> class implements an ArrayList-like data structure with maximum size limited by the final int
 * CAPACITY.
 *
 *
 * @author Spencer Nisonoff
 **/

import ShelfExceptions.*;
import TextMenuExceptions.*;


public class Bookshelf implements Cloneable{
    private Book[] books;
    private int count;
    final int CAPACITY = 20; //set to 20 or 21

    /** Bookshelf
     * Default constructor for the <CODE>Bookshelf</CODE> object
     */
    public Bookshelf() {
        count = 0;
        books = new Book[CAPACITY];
    }

    /** numBooks
     * Returns the number of <CODE>Book</CODE>s' in the array <CODE>books</CODE>.
     * @return the number of <CODE>Book</CODE>s' in the array <CODE>books</CODE>.
     */
    public int numBooks() {
        return count;
    }

    /** getBook
     * Getter method for any <CODE>Book</CODE> in the array <CODE>books</CODE>.
     * @param index the in the array <CODE>books</CODE>.
     * @return the <CODE>Book</CODE> in the specified index of the array <CODE>books</CODE>.
     * @throws ArrayIndexOutOfBoundsException if the index is invalid.
     */
    public Book getBook(int index) throws ArrayIndexOutOfBoundsException {
        return books[index];
    }

    /** removeBook
     *
     * Removes the given book and moves all books to the right of it leftwards by one index
     *
     * @param index position of book to remove
     * @return the book removed
     * @throws ArrayIndexOutOfBoundsException if the index is invalid
     * @throws EmptyShelfException            if there is no book on the shelf
     */
    public Book removeBook(int index) throws ArrayIndexOutOfBoundsException, EmptyShelfException {

        Book b = books[index];
        if(books[index] == null){
            throw (new ArrayIndexOutOfBoundsException());
        }

        if (count == 0) //if there are no books on the shelf
        {
            throw (new EmptyShelfException());
        }

        books[index] = null;

        return b;

    }

    /** addBook
     *
     * Adds a book at the given index, moving all books at or after the given index one index to the right.
     *
     * @param index the position to add the book
     * @param book  the book to be added
     * @throws IllegalArgumentException if the index is too high and would create a hole in the array
     * @throws FullShelfException       if more than 20 books are added to the shelf
     */
    public void addBook(int index, Book book) throws IllegalArgumentException, FullShelfException, IndexOutOfBoundsException {
        if (index > count + 1) { //would this create a hole?
            throw (new IllegalArgumentException());
        }
        count++;
        if (count >= CAPACITY) {
            throw (new FullShelfException());
        }
        if(index >= count){
            throw (new IndexOutOfBoundsException());
        }
        if(books[index] != null) {
            for(int i = count - 1; i > index-1; i--) {
                if(i > -1 && books[i] != null){
                    Book temporaryBook = books[i];
                    books[i+1] = temporaryBook;
                }
            }
        }
        books[index] = book;
    }

    /** swapBooks
     *
     * If the indices are valid, the two books are swapped.
     *
     * @param index1 position of first element to be swapped.
     * @param index2 position of seond element to be swapped.
     * @throws ArrayIndexOutOfBoundsException
     */
    public void swapBooks(int index1, int index2) throws ArrayIndexOutOfBoundsException
    {
        if(index1 < count && index2 < count) //if the indices are valid
        {
            Book t1 = books[index1];
            Book t2 = books[index2];

            books[index1] = t2;
            books[index2] = t1;
        }
        else{
            throw(new ArrayIndexOutOfBoundsException());
        }
    }

    /** clone
     *
     * This method creates a deep copy of the <CODE>Bookshelf</CODE>
     *
     * @return the deep clone.
     */
    public Bookshelf clone() {
        //create deep copy of Bookshelf object

        Bookshelf b = new Bookshelf();

        return b;
    }

    /** equals
     *
     * This method checks if two objects of type <CODE>Bookshelf</CODE> are equal.
     * @param o the object being compared to.
     * @return true if the objects are equal, false if they are not equal.
     */
    public boolean equals(Object o) {
        if(o instanceof Bookshelf) {
            if(((Bookshelf) o).numBooks() != count) { return false; }
            for(int i = 0; i < count; i++){
                if(((Bookshelf) o).getBook(i) != books[i]) { return false; }
            }
            return true;
        }
        return false;
    }

    /** toString
     *
     * This method prints the <CODE>title</CODE>, <CODE>author</CODE>, and <CODE>condition</CODE> of each
     * <CODE>Book</CODE> on separate lines.
     *
     * @return the lines of text that is printed
     */
    @Override
    public String toString() {
        String str = "";
        for(int i = 0; i < count; i++) {
            if(books[i] != null) { str += books[i].toString(); }
            if(i != count - 1) {
                str += "\n";
            }
        }
        return str;
    }
}
