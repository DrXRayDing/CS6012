package assignment05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;

public class WebBrowserTest {

    private WebBrowser webBrowser;
    private URL url1, url2, url3;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        webBrowser = new WebBrowser();
        url1 = new URL("http://example1.com");
        url2 = new URL("http://example2.com");
        url3 = new URL("http://example3.com");
    }

    @Test
    public void testURLEquality() throws MalformedURLException {
        URL link = new URL("https://a");
        Assertions.assertEquals("https://a", link.toString());  // true
        //Assertions.assertEquals("https://b", link.toString());  // false
        Assertions.assertEquals(new URL("https://a"), link);    // true
    }

    @Test
    public void testVisitAndGetCurrentWebpage() {
        webBrowser.visit(url1);
        Assertions.assertEquals(url1, webBrowser.currentURL);

        webBrowser.visit(url2);
        Assertions.assertEquals(url2, webBrowser.currentURL);
    }

    @Test
    public void testBackWithEmptyHistory() {
        Assertions.assertThrows(NoSuchElementException.class, webBrowser::back);
    }

    @Test
    public void testBackNavigation() {
        webBrowser.visit(url1);
        webBrowser.visit(url2);
        Assertions.assertEquals(url1, webBrowser.back());
    }

    @Test
    public void testForwardWithEmptyHistory() {
        Assertions.assertThrows(NoSuchElementException.class, webBrowser::forward);
    }

    @Test
    public void testForwardNavigation() {
        webBrowser.visit(url1);
        webBrowser.visit(url2);
        webBrowser.back();
        Assertions.assertEquals(url2, webBrowser.forward());
    }

    @Test
    public void testHistory() {
        webBrowser.visit(url1);
        webBrowser.visit(url2);
        webBrowser.visit(url3);
        webBrowser.back();
        SinglyLinkedList<URL> history = webBrowser.history();
        Assertions.assertFalse(history.isEmpty());
        Assertions.assertEquals(url2, history.getFirst());
    }

    @Test
    public void testConstructorWithHistory() throws MalformedURLException {
        WebBrowser w = new WebBrowser();
        w.visit(url1);
        w.visit(url2);
        w.visit(url3);
        SinglyLinkedList<URL> list = w.history();

        WebBrowser w2 = new WebBrowser(list);
        Assertions.assertEquals(url3, w2.currentURL);
        Assertions.assertEquals(url2, w2.back());
        Assertions.assertEquals(url1, w2.back());

    }
}
