package lab09;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static lab09.HTMLMain.validateURL;
import static org.junit.jupiter.api.Assertions.*;

class HTMLMainTest {

    /**
     * Checks whether the URL is being validated/checked for exceptions.
     */
    @Test
    void validateURLTest() {
        // Invalid URL syntax
        assertFalse(validateURL("https:\\\\www.bucknell.edu"));
        // "Malformed" URL
        assertFalse(validateURL("asdfknelledu"));
        // Valid URL but host DNE
        assertFalse(validateURL("https://www.bucknell.ed"));
        // Valid URL and Host but page DNE
        assertFalse(validateURL("https://www.bucknell.edu/blah"));
    }

}