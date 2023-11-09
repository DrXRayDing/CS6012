package assignment02;
import java.lang.reflect.GenericArrayType;
import java.util.GregorianCalendar;

public class LibraryBook extends Book{
    private String holder;
    private GregorianCalendar dueDate;
    public LibraryBook(long isbn, String author, String title) {
        super(isbn, author, title); //super must be the first line in the constructor
        this.holder = null; // initialize holder
        this.dueDate = null; // initialize dueDate
    }

    public String getHolder(){return this.holder;}

    public GregorianCalendar getDueDate(){return this.dueDate;}

    public void checkIn(){
        this.holder = null;
        this.dueDate = null;
    }

    public void checkOut(String holder, GregorianCalendar dueDate){
        this.holder = holder;
        this.dueDate = dueDate;
    }
}
