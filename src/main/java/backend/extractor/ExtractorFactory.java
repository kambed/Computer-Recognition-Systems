package backend.extractor;

public class ExtractorFactory {
    public static Extractor<?> createExtractor(ExtractorType type) {
        return type.getExtractor();
    }
}
