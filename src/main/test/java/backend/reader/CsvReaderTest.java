package backend.reader;

import backend.helper.Helper;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CsvReaderTest {

    @Test
    void FileDataObjectMappingTest() throws URISyntaxException {
        Map<String, List<String>> dic = CsvReader.readDictionary(
                Helper.getFilePath(this, "dictionary.csv")
        ).orElse(null);

        assertNotNull(dic);
        assertEquals(2, dic.size());

        assertTrue(dic.containsKey("Japan"));
        assertTrue(dic.containsKey("France"));

        assertEquals(2, dic.get("Japan").size());
        assertEquals(1, dic.get("France").size());

        assertEquals("Tokyo", dic.get("Japan").get(0));
        assertEquals("Osaka", dic.get("Japan").get(1));
        assertEquals("Paris", dic.get("France").get(0));
    }
}
