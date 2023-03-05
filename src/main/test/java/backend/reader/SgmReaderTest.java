package backend.reader;

import backend.model.Article;
import backend.model.Root;
import backend.model.TextContent;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class SgmReaderTest {

    @Test
    void FileDataObjectMappingTest() throws URISyntaxException, ParseException {
        FileReader fr = ReaderFactory.createReader(FileType.SGM);
        Root r = fr.read(
            getFilePath("reut2-000.sgm")
        ).orElse(null);

        assertNotNull(r);
        assertEquals(2, r.getArticles().size());

        Article a = r.getArticles().get(0);
        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SS", Locale.ENGLISH);
        assertEquals(simpleDateFormat.parse("26-FEB-1987 15:01:01.79"), a.getDate());
        assertEquals(1, a.getTopics().size());
        assertEquals("cocoa", a.getTopics().get(0));
        assertEquals(3, a.getPlaces().size());
        assertEquals("el-salvador", a.getPlaces().get(0));
        assertEquals("usa", a.getPlaces().get(1));
        assertEquals("uruguay", a.getPlaces().get(2));
        assertTrue(a.getPeople().isEmpty());
        assertTrue(a.getOrgs().isEmpty());
        assertTrue(a.getExchanges().isEmpty());
        assertTrue(a.getCompanies().isEmpty());

        TextContent tc = a.getText();
        assertEquals("BAHIA COCOA REVIEW", tc.getTitle());
        assertEquals("    SALVADOR, Feb 26 - ", tc.getDateline());
        assertFalse(tc.getText().isEmpty());
    }

    @Test
    void FileDataObjectMappingShouldThrowWhenFileNotFound() {
        FileReader fr = ReaderFactory.createReader(FileType.SGM);
        assertThrows(NoSuchElementException.class,
                fr.read("sgasgsasg.sgm")::orElseThrow);
    }

    private String getFilePath(String resourceName) throws URISyntaxException {
        return Paths.get(
                Objects.requireNonNull(
                        getClass().getClassLoader().getResource(resourceName)
                ).toURI()
        ).toString();
    }
}