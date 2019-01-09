 /* The <code>RipoffRental</code> class creates three instances of the Bookshelf object and provides an interface for a
 * user to manipulate the list.
 *
 *
 * @author Spencer Nisonoff
 *    e-mail: spencer.nisonoff@stonybrook.edu
 *    Stony Brook ID: 111614611
 *    Assignment number: 1
 *    Course: CSE 214
 *    Recitation Number: 14
 **/
package bm;



import java.util.InputMismatchException;
import java.util.Scanner;
import ShelfExceptions.*;
import TextMenuExceptions.*;

public class RipoffRental {

    private static Bookshelf shelfA = new Bookshelf();
    private static Bookshelf shelfB = new Bookshelf();
    private static Bookshelf shelfC = new Bookshelf();

    public static void main(String[] args){

        try {
            Scanner stdin = new Scanner(System.in);
            System.out.println("Welcome to Jack's aMAzin' Textbook Rentals, highest price guaranteed!");
            System.out.println("(current shelf is shelf A)");
            System.out.println();
            displayUIRequirements("A");

        }catch (FullShelfException e) {
            System.out.println("The shelf is full.");
        } catch (CloneNotSupportedException e) {
            System.out.println("The clone is not supported.");
        }
    }

    /** displayUIReguirements
     *
     * This method displays the options for the user to choose from and essentially starts and stops the program.
     *
     * @param shelfName "A" if shelfA is being looked at, "B" is shelfB is being looked at, otherwise it assumes shelfC is being looked at
     * @throws FullShelfException if the shelf is full.
     * @throws CloneNotSupportedException if the <CODE>clone</CODE> is not supported
     */
    private static void displayUIRequirements(String shelfName) throws FullShelfException, CloneNotSupportedException {
        //IMPORTANT NOTE: In the current program, it is assumed that if shelfName isn't "a" or "b" then it is "c".
        //If another shelf is added, user select shelf will need to be change as well as all of the else statements.
        boolean userQuits = false;
        Scanner stdin = new Scanner(System.in);
        System.out.println("Menu:");
        System.out.println("   A) Add Book");
        System.out.println("   S) Swap Book");
        System.out.println("   L) Loan Book");
        System.out.println("   R) Remove Book");
        System.out.println("   D) Duplicate Book");
        System.out.println("   C) Change Shelf");
        System.out.println("   O) Overwrite shelf with clone of current shelf");
        System.out.println("   E) Check if two shelves are equal");
        System.out.println("   P) Print current bookshelf");
        System.out.println("   Q) Quit");
        while(!userQuits) {
            try {
                System.out.println("\n\n");
                System.out.print("Please select an option: ");
                String input = stdin.next().toUpperCase();

                switch(input){
                    case "A" : addBook(shelfName); break;
                    case "S" : swapBook(shelfName); break;
                    case "L" : loanBook(shelfName); break;
                    case "R" : removeBook(shelfName); break;
                    case "D" : duplicateBook(shelfName); break;
                    case "C" : System.out.print("Please select shelf to look at: ");
                        shelfName = userSelectShelf(); break;
                    case "O" : overwriteShelf(shelfName); break;
                    case "E" : checkIfShelvesAreEqual(shelfName); break;
                    case "P" : printCurrentShelf(shelfName); break;
                    case "Q" : System.out.println("Goodbye!"); userQuits = true; break;
                    default : System.out.println("Invalid option. Try again.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Illegal Argument. Try again.");
                displayUIRequirements(shelfName);
            } catch (FullShelfException e) {
                System.out.println("Full shelf. Try again.");
                displayUIRequirements(shelfName);
            } catch(CloneNotSupportedException e) {
                System.out.println("Clone not supported. Try again.");
                displayUIRequirements(shelfName);
            }
        }
    }

    /** userSelectShelf
     *
     * This method ensures that the user can only choose "a", "b", or "c".
     *
     * @return "A" if user enters "a", "B" if user enters "b", or "C" if user enters "c".
     */
    private static String userSelectShelf() {
        String shelfName;
        Scanner stdin = new Scanner(System.in);

        String newShelfName = stdin.next().toUpperCase();
        System.out.println();
        switch(newShelfName){
            case "A" : shelfName = newShelfName;
                System.out.println("Shelf A Selected.");
                return "A";
            case "B" : shelfName = newShelfName;
                System.out.println("Shelf B Selected.");
                return "B";
            case "C" : shelfName = newShelfName;
                System.out.println("Shelf C Selected.");
                return "C";
            default : System.out.println("Invalid option. You must enter 'A' if you want to select Shelf A, " +
                    "'B' if you want to select Shelf B, or 'C' if you want to select Shelf C. Try again.\n");
                System.out.print("Enter a shelf: ");
                return userSelectShelf();
        }
    }

    /** callForCondition
     *
     * This method prompts the user to enter a numerical value representing the condition, ensuring that the user can
     * only enter a value from 1-5
     *
     * @return the numberical value of the condition entered by the user if the user enters a value from 1-5
     */
    private static int callForCondition() {
        Scanner stdin = new Scanner(System.in);

        while(true) { //intentional infinite loop
            try {
                System.out.print("Please enter condition (1-5): ");
                int condition = stdin.nextInt();
                if (condition < 1 || condition > 5) {
                    System.out.println("You did not enter a number from 1-5. Try again!");
                } else {
                    return condition;
                }
            }
            catch(InputMismatchException e) {
                System.out.println("You did not enter a number from 1-5. Try again.");
                return callForCondition();
            }
        }
    }

    /**
     * This method allows the user to add a book to a shelf, provided that there is enough room for the book.
     *
     * @param shelfName the name of the shelf that the book will be added to.
     * @throws FullShelfException if the shelf is full.
     */
    private static void addBook(String shelfName) throws FullShelfException{
        Scanner stdin = new Scanner(System.in);
        System.out.print("Please enter a title: ");
        String title = stdin.nextLine();
        System.out.print("Please enter an author: ");
        String author = stdin.nextLine();
        int condition = callForCondition();
        int position = 1;
        boolean indexImpossible = true; //will be false if position is greater than 0
        while(indexImpossible) {
            try {
                System.out.print("Please enter position on shelf: ");
                position = stdin.nextInt();
                if (position > 0) {
                    indexImpossible = false;
                } else {
                    System.out.println("The position must be greater than 0. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Positions must be entered as numbers. Try again from the beginning.");
                addBook(shelfName); return;
            }
        }
        try {
            switch(shelfName.toUpperCase()) {
                case "A" : shelfA.addBook(position - 1, new Book(title, author, condition)); break;
                case "B" : shelfB.addBook(position - 1, new Book(title, author, condition)); break;
                case "C" : shelfC.addBook(position - 1, new Book(title, author, condition)); break;
            }
            System.out.println("Book added!");
        } catch (FullShelfException e) {
            System.out.println("The shelf is currently full. You may remove books if you want to add something " +
                    "else to it.");
        } catch (IllegalArgumentException e) {
            System.out.print("You entered a position that currently doesn't exist. Try again from the beginning.");
            addBook(shelfName);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You cannot add a book here yet. " +
                    "Try adding some (one) at the indexes (index) below it.");
            addBook(shelfName);
        }
    }

    /**
     * This method will be called when the user wants to swap books in the current shelf being looked at.
     *
     * @param shelfName respresents the current shelf being looked at.
     */
    private static void swapBook(String shelfName){
        Scanner stdin = new Scanner(System.in);
        int index1 = - 1;
        int index2 = -1;
        boolean indexImpossibleForIndex1 = true; //will be false if index is greater than 0
        try {
            while (indexImpossibleForIndex1) {
                System.out.print("Please enter an index: ");
                index1 = stdin.nextInt() - 1;
                if (index1 > -1) {
                    indexImpossibleForIndex1 = false;
                } else {
                    System.out.println("Invalid position. Try again.");
                }
            }
            boolean indexImpossibleForIndex2 = true; //will be false if index is greater than 0
            while (indexImpossibleForIndex2) {
                System.out.print("Please enter another index: ");
                index2 = stdin.nextInt() - 1;
                if (index2 > -1) {
                    indexImpossibleForIndex2 = false;
                } else {
                    System.out.println("Invalid position. Try again.");
                }
            }
        } catch(InputMismatchException e) {
            System.out.println("Positions must be entered as numbers. Let's try again from the beginning");
            swapBook(shelfName); return;
        }
        try {
            switch (shelfName) {
                case "A":
                    shelfA.swapBooks(index1, index2);
                    System.out.println(shelfA.getBook(index1).getTitle() + " has been swapped with "
                            + shelfA.getBook(index2).getTitle());
                    break;
                case "B":
                    shelfB.swapBooks(index1, index2);
                    System.out.println(shelfB.getBook(index1).getTitle() + " has been swapped with "
                            + shelfB.getBook(index2).getTitle());
                    break;
                case "C":
                    shelfC.swapBooks(index1, index2);
                    System.out.println(shelfC.getBook(index1).getTitle() + " has been swapped with "
                            + shelfC.getBook(index2).getTitle());
                    break;
                default:
                    System.out.println("Something went wrong. Let's fix it!");
                    boolean invalidOption = true;
                    while (invalidOption) {
                        shelfName = userSelectShelf();
                        invalidOption = false;
                    }
            }
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("At least one of these indexes does not contain a book. You should add more books.");
        }
    }

    /**
     * This methods will be called when the user wants to loan a book to another person.
     *
     * @param shelfName represents the current shelf being looked at.
     * <dt><b>Precondition</b></dt>
     *     The <CODE>Book</CODE> at the specified index may or may not have initialize its <CODE>borrower</CODE> instance variable.
     * <dt><b>Postcondition</b></dt>
     *     The <CODE>Book</CODE> at the specified index's <CODE>borrower</CODE> instance variable is set to what the
     *     user types as the recipient.
     */
    private static void loanBook(String shelfName) {
        Scanner stdin = new Scanner(System.in);
        boolean isEmpty;
        switch(shelfName) {
            case "A" : isEmpty = shelfA.numBooks() == 0; break;
            case "B" : isEmpty = shelfB.numBooks() == 0; break;
            case "C" : isEmpty = shelfC.numBooks() == 0; break;
            default: isEmpty = false;
        }
        if(isEmpty) { System.out.println("There are no books in this shelf. You should add some."); }
        while(!isEmpty) {
            try {
                System.out.print("Please enter an index: ");
                int index = stdin.nextInt() - 1;
                System.out.print("Please enter a recipient: ");
                String recipient = stdin.next() + " " + stdin.nextLine();
                System.out.println();
                int condition = callForCondition();
                System.out.println();
                switch (shelfName) {
                    case "A":
;                        shelfA.getBook(index).setBorrower(recipient);
                        System.out.println(shelfA.getBook(index).getTitle() + " has loaned to " + recipient);
                        return;
                    case "B":
                        shelfB.getBook(index).setBorrower(recipient);
                        System.out.println(shelfB.getBook(index).getTitle() + " has loaned to " + recipient);
                        return;
                    case "C":
                        shelfC.getBook(index).setBorrower(recipient);
                        System.out.println(shelfC.getBook(index).getTitle() + " has loaned to " + recipient);
                        return;
                    default:
                        System.out.println("Something went wrong. Let's fix it!");
                        shelfName = userSelectShelf();
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("There currently is no book in the position you entered. You should add it.");
                return;
            } catch (NullPointerException e) { //do not remove
                System.out.println("There currently is no book in the position you entered. You should add it.");
                return;
            }
        }
    }

    /** removeBook
     *
     * This method is called when the user wants to remove a book from the current shelf being looked at.
     *
     * @param shelfName represents the current shelf being looked at.
     */
    private static void removeBook(String shelfName) {
        Scanner stdin = new Scanner(System.in);
        boolean isEmpty;
        switch(shelfName) {
            case "A" : isEmpty = shelfA.numBooks() == 0; break;
            case "B" : isEmpty = shelfB.numBooks() == 0; break;
            case "C" : isEmpty = shelfC.numBooks() == 0; break;
            default: isEmpty = false;
        }
        if(isEmpty) { System.out.println("There are no books to be removed in this shelf. You should add some."); }
        while (!isEmpty) {
            try {
                System.out.print("Please enter an index: ");
                int index = stdin.nextInt() - 1;
                System.out.println();
                switch(shelfName){
                    case "A" : System.out.println(shelfA.removeBook(index).getTitle() + " has been removed."); break;
                    case "B" : System.out.println(shelfB.removeBook(index).getTitle() + " has been removed."); break;
                    case "C" : System.out.println(shelfC.removeBook(index).getTitle() + " has been removed."); break;
                    default : System.out.println("Something went wrong. Let's fix it!"); shelfName = userSelectShelf();
                }
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("There currently is no book in the position you entered. You should add it.");
                return;
            } catch (InputMismatchException e){
                System.out.println("Indexes are always numbers in this program. Try again.\n");
                removeBook(shelfName); return;
            } catch (EmptyShelfException e) {
                System.out.print("This shelf is currently empty; there are no books to be removed.");
                break;
            }
        }
    }

    /**
     * This method is called if the user wants to duplicate a book.
     *
     * @param shelfName represents the current shelf being looked at.
     * @throws CloneNotSupportedException only thrown because cloneable is implemented by the <CODE>Book</CODE> class.
     * @throws FullShelfException if the shelf is full and no more elements can be added.
     */
    private static void duplicateBook(String shelfName) throws CloneNotSupportedException, FullShelfException {
        Scanner stdin = new Scanner(System.in);
        boolean isEmpty;
        switch(shelfName) {
            case "A" : isEmpty = shelfA.numBooks() == 0; break;
            case "B" : isEmpty = shelfB.numBooks() == 0; break;
            case "C" : isEmpty = shelfC.numBooks() == 0; break;
            default: isEmpty = false;
        }
        if(isEmpty) {
            System.out.println("There are no books to be duplicated in this shelf. You should add some.");
            return;
        }
        try {
            System.out.print("Please enter a source index: ");
            int index1 = stdin.nextInt() - 1;

            System.out.print("Please enter a destination index: ");
            int index2 = stdin.nextInt() - 1;

            switch (shelfName) {
                case "A":
                    shelfA.addBook(index2, shelfA.getBook(index1).clone());
                    System.out.println("A new copy of " + shelfA.getBook(index2).getTitle() + " is in index "
                            + (index2 + 1) + ".");
                    break;
                case "B":
                    shelfB.addBook(index2, shelfB.getBook(index1).clone());
                    System.out.println("A new copy of " + shelfB.getBook(index2).getTitle() + " is in index "
                            + (index2 + 1) + ".");
                    break;
                case "C":
                    shelfC.addBook(index2, shelfC.getBook(index1).clone());
                    System.out.println("A new copy of " + shelfC.getBook(index2).getTitle() + " is in index "
                            + (index2 + 1) + ".");
                    break;
                default:
                    System.out.println("Something wrong happened...");
            }
        } catch(InputMismatchException e) {
            System.out.println("Indexes in this program are always non-decimal numbers. " +
                    "Please start over and try again.\n");
            duplicateBook(shelfName);
        } catch (NullPointerException e) {
            System.out.print("One of the indexes you entered does not have a book. You should add it.");
        } catch(CloneNotSupportedException e) {
            System.out.println("CLONE NOT SUPPORTED!");
        } catch(FullShelfException e) {
            System.out.println("The shelf you are using is currently full. " +
                    "Please try again after you have removed a book from the shelf.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You cannot add a book here yet. " +
                    "Try adding some (one) at the indexes (index) below it.");
        }

    }

    /** overwriteBook
     *
     * This method is called if the user wants to overwrite a shelf with another shelf?
     *
     * @param shelfName represents the current shelf being looked at.
     */
    private static void overwriteShelf(String shelfName) {
        System.out.print("Please select shelf to overwrite with the current shelf: ");
        String otherShelf = userSelectShelf();
        //The nested switch algorithm below will print "Same shelf" if the shelves are the same. Otherwise it will
        //overwrite a shelf with another shelf.
        switch(shelfName) {
            case "A" :
            switch(otherShelf) {
                case "A" : System.out.println("Same shelf!"); break;
                case "B" : shelfB = shelfA;
                    System.out.println("Shelf B overwritten with a copy of Shelf A."); break;
                case "C" : shelfC = shelfA;
                    System.out.println("Shelf B overwritten with a copy of Shelf A."); break;
                default : System.out.println("Invalid option. Try again."); overwriteShelf(shelfName);
            } break;
            case "B" :
            switch(otherShelf) {
                case "A" : shelfA = shelfB;
                System.out.println("Shelf A overwritten with a copy of Shelf B."); break;
                case "B" : System.out.println("Same shelf!"); break;
                case "C" : shelfC = shelfB;
                    System.out.println("Shelf C overwritten with a copy of Shelf B."); break;
                default : System.out.println("Invalid option. Try again."); overwriteShelf(shelfName);
            } break;
            case "C" :
            switch(otherShelf) {
                case "A" : shelfA = shelfC;
                    System.out.println("Shelf A overwritten with a copy of Shelf C."); break;
                case "B" : shelfB = shelfC;
                    System.out.println("Shelf B overwritten with a copy of Shelf C"); break;
                case "C" : System.out.println("Same shelf!"); break;
                default : System.out.println("Invalid option. Try again."); overwriteShelf(shelfName);
            } break;
            default: System.out.println("Something went wrong. " +
                    "It appears that you are not looking at any shelf. Let's fix that!");
                shelfName = userSelectShelf();
        }
    }

    /** checkIfShelvesAreEqual
     *
     * This method is used to check if two shelves are equal.
     *
     * @param shelfName represents the current shelf being used.
     */
    private static void checkIfShelvesAreEqual(String shelfName) {
        System.out.print("Please select a shelf: ");
        String firstShelfName = userSelectShelf().toUpperCase();
        Bookshelf firstShelf = shelfA; Bookshelf secondShelf = shelfA;
        switch(firstShelfName){
            case "A" : firstShelf = shelfA; break;
            case "B" : firstShelf = shelfB; break;
            case "C" : firstShelf = shelfC; break;
            default : System.out.println("Something went wrong. Let's fix it.");
                shelfName = userSelectShelf();
                checkIfShelvesAreEqual(shelfName); return;
        }
        System.out.print("Please select another shelf: ");
        String secondShelfName = userSelectShelf().toUpperCase();
        switch(secondShelfName){
            case "A" : secondShelf = shelfA; break;
            case "B" : secondShelf = shelfB; break;
            case "C" : secondShelf = shelfC; break;
            default : System.out.println("Something went wrong. Let's fix it.");
                shelfName = userSelectShelf();
                System.out.println("Now let's try again from the beginning.");
                checkIfShelvesAreEqual(shelfName); return;
        }
        System.out.println();
         if(firstShelf.equals(secondShelf)){
            System.out.print("The shelves are equal.");
        } else{
            System.out.print("The shelves are not equal.");
        }
    }

    /** printCurrentShelf
     *
     * This method prints each <CODE>Book</CODE> in the given shelf.
     *
     * @param shelfName represents the current shelf being used.
     */
    private static void printCurrentShelf(String shelfName){
        switch(shelfName){
            case "A" : System.out.print("Shelf A:\n" + shelfA); break;
            case "B" : System.out.print("Shelf B:\n" + shelfB); break;
            case "C" : System.out.print("Shelf C:\n" + shelfC); break;
            default : System.out.println("Something wrong happened. Let's fix it!");
                shelfName = userSelectShelf();
                printCurrentShelf(shelfName);
        }
    }
}
