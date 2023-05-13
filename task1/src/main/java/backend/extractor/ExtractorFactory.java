package backend.extractor;

public class ExtractorFactory {
    private ExtractorFactory() {
    }
    public static Extractor<?> createExtractor(ExtractorType type) {
        return type.getExtractor();
    }
}
