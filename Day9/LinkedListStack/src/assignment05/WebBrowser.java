package assignment05;

import java.net.URL;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class WebBrowser {
    private LinkedListStack<URL> backStack;
    private LinkedListStack<URL> forwardStack;
    URL currentURL;

    public WebBrowser(){
        backStack = new LinkedListStack<URL>();
        forwardStack = new LinkedListStack<URL>();
        currentURL = null;
    }

    public WebBrowser(SinglyLinkedList<URL> history){
        this();
        // Use a temporary stack to reverse the order
        LinkedListStack<URL> tempStack = new LinkedListStack<>();
        if(!history.isEmpty()) {
            Iterator<URL> itr = history.iterator();
            if (itr.hasNext()){
                currentURL = itr.next(); // Set the first URL as current URL
                while (itr.hasNext()) {
                    tempStack.push(itr.next()); // Push remaining to temp stack
                }
            }
            // Now transfer from temporary stack to back stack to get correct order
            while(!tempStack.isEmpty()){
                backStack.push(tempStack.pop());
            }
        }
    }

    public void visit(URL webpage){
        if (currentURL != null){
            backStack.push(currentURL); // Push current URL to back stack
        }
        currentURL = webpage; // Update current URL
        forwardStack.clear(); // Clear forward URL
    }

    public URL back() throws NoSuchElementException{
        if (backStack.isEmpty()){
            throw new NoSuchElementException("No previous URL.");
        }
        forwardStack.push(currentURL); // Push current URL to forward stack
        currentURL = backStack.pop(); // Pop from back stack and set as current
        return currentURL;
    }

    public URL forward() throws NoSuchElementException{
        if (forwardStack.isEmpty()){
            throw new NoSuchElementException("No next URL.");
        }
        backStack.push(currentURL); // Push current URL to back stack
        currentURL = forwardStack.pop(); // Pop from forward stack and set as current
        return currentURL;
    }

    public SinglyLinkedList<URL> history(){
        SinglyLinkedList<URL> historyList = new SinglyLinkedList<>();
        LinkedListStack<URL> tempStack = new LinkedListStack<>();

        // Reverse the order of URLs from back stack to temporary stack
        while(!backStack.isEmpty()){
            tempStack.push(backStack.pop());
        }

        // Restore back stack and build history list
        while (!tempStack.isEmpty()){
            URL url = tempStack.pop();
            backStack.push(url);
            historyList.insertFirst(url); // Inserting at the beginning to maintain order
        }

        // Add current URL at the beginning if it exists
        if (currentURL != null){
            historyList.insertFirst(currentURL);
        }

        return historyList;
    }
}
