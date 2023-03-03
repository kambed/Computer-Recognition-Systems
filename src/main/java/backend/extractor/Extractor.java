package backend.extractor;

import java.util.Map;

public interface Extractor<T> {
    T extract(Map<String, String> value);
}
