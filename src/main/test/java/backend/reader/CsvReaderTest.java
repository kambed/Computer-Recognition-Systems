package backend.reader;

import backend.model.Article;
import backend.model.Root;
import backend.model.TextContent;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class CsvReaderTest {

    @Test
    void FileDataObjectMappingTest() throws URISyntaxException {
        Map<String, List<String>> dic = CsvReader.readDictionary(
                getFilePath("dictionary.csv")
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

    private String getFilePath(String resourceName) throws URISyntaxException {
        return Paths.get(
                Objects.requireNonNull(
                        getClass().getClassLoader().getResource(resourceName)
                ).toURI()
        ).toString();
    }
}
