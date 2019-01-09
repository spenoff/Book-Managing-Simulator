/**
 * The <code>Book</code> class implements representation of a book.
 *
 *
 * @author Spencer Nisonoff
 **/
package bm;


public class Book implements Cloneable{
    private String title;
    private String author;
    private String borrower;
    private int condition;

    /** Book
     * Constructor for the <CODE>Book</CODE> class that allows the us to set the variables for <CODE>title</CODE>,
     * <CODE>author</CODE>, and <CODE>condition</CODE>
     *
     * @param title the <CODE>title</CODE> of the <CODE>Book</CODE>.
     * @param author the <CODE>author</CODE> of the <CODE>Book</CODE>.
     * @param condition the <CODE>title</CODE> of the <CODE>Book</CODE>.
     */
    public Book(String title, String author, int condition){
        this.title = title;
        this.author = author;
        this.condition = condition;
        borrower = "";
    }

    /** Book
     *
     * Default constructor for <CODE>Book</CODE>
     */
    public Book(){
        title = "Textbook";
        author = "Jack Ma";
        condition = 5;
        borrower = "";
    }

    /** getTitle
     *
     * Returns the value of <CODE>title</CODE>
     * @return the <CODE>title</CODE> of the <CODE>Book</CODE>
     */
    public String getTitle(){
        return title;
    }

    /** getAuthor
     *
     * Returns the value of <CODE>author</CODE>
     * @return the <CODE>author</CODE> of the <CODE>Book</CODE>
     */
    public String getAuthor(){
        return author;
    }

    /** getBorrower
     *
     * Returns the value of <CODE>borrower</CODE> if it is not null
     * @return the <CODE>borrower</CODE> of the <CODE>Book</CODE>
     */
    public String getBorrower(){
        return borrower == null?null:borrower;
    }

    /**getCondition
     *
     * Returns the value of <CODE>condition</CODE>
     * @return the <CODE>borrower</CODE> of the <CODE>Book</CODE>
     */
    public int getCondition(){
        return condition;
    }

    /** setTitle
     *
     * Changes the value of instance variable title.
     * <dt><b>Precondition</b></dt>
     * There is some sort of value for the instance variable <CODE>title</CODE>.
     * <dt><b>Postcondition</b></dt>
     * The value of <CODE>title</CODE> changes to the variable of the parameter.
     * @param title the new <CODE>title</CODE>
     */
    public void setTitle(String title){
        this.title = title;
    }

    /** setAuthor
     *
     * Changes the value of instance variable author.
     * <dt><b>Precondition</b></dt>
     * There is some sort of value for the instance variable <CODE>author</CODE>.
     * <dt><b>Postcondition</b></dt>
     * The value of <CODE>author</CODE> changes to the variable of the parameter.
     * @param author the new <CODE>title</CODE>
     */
    public void setAuthor(String author){
        this.author = author;
    }

    /** setBorrower
     *
     * Changes the value of instance variable borrower.
     * <dt><b>Precondition</b></dt>
     * There may be some sort of value for the instance variable <CODE>borrower</CODE>.
     * <dt><b>Postcondition</b></dt>
     * The value of <CODE>borrower</CODE> changes to the variable of the parameter.
     * @param borrower the new value for this <CODE>borrower</CODE> if it has not already been set.
     */
    public void setBorrower(String borrower){
        this.borrower = borrower;
    }

    /** setCondition
     *
     * Sets the value of instance variable condition.
     * <dt><b>Precondition</b></dt>
     * There is some sort of value for the instance variable <CODE>condition</CODE>.
     * Otherwise it is null.
     * <dt><b>Postcondition</b></dt>
     * The value of <CODE>condition</CODE> changes to the variable of the parameter.
     *
     * @param condition the new value for this <CODE>condition</CODE>
     */
    public void setCondition(int condition) {
        this.condition = condition;
    }

    /** equals
     *
     * Checks if this <CODE>Book</CODE> is equal to another <CODE>Book</CODE>.
     *
     * @param obj the Book being compared to.
     * @return true if the books have the same <CODE>title</CODE>, <CODE>author</CODE>, and <CODE>condition</CODE>
     */
    public boolean equals(Object obj) {
        if(obj instanceof Book) {
            if(((Book) obj).getAuthor().equals(author)) {
                if(((Book) obj).getTitle().equals(title)) {
                    if(((Book) obj).getCondition() == condition) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /** clone
     *
     * This method creates a deep <CODE>clone</CODE> of the <CODE>book</CODE>, the borrower instance variable being ignored
     * @return the deep <CODE>clone</CODE>.
     * @throws CloneNotSupportedException only thrown because cloneable is implemented by the <CODE>Book</CODE> class
     */
    public Book clone() throws CloneNotSupportedException {
        Book b = new Book(this.title, this.author,this.condition);

        return b;

    }

    /** toString
     *
     * This method prints the <CODE>title</CODE>, <CODE>author</CODE>, and <CODE>condition</CODE> of the
     * <CODE>Book</CODE>
     *
     * @return the lines of text that is printed
     */
    @Override
    public String toString(){
        return "Title: " + title + ", Author: " + author + ", Condition: " + condition;
    }
}
