package lab09;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class HTMLScannerTest {
    @Test
    void scanForAllTags() throws FileNotFoundException {
        File testFile = new File("src/test/java/lab09/testHTML");
        InputStream testStream = new FileInputStream(testFile);
        HTMLScanner testScanner = new HTMLScanner(testStream);

        // Scans for tags and checks whether the number of tags is the same as expected
        testScanner.scanForAllTags();
        assertEquals(9, testScanner.getTotalTagCount());
        assertEquals(9, testScanner.getTotalUniqueTagCount());
    }
}