package backend.model.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateAdapter extends XmlAdapter<String, Date> {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SS", Locale.ENGLISH);

    @Override
    public Date unmarshal(String dateString) throws Exception {
        return dateFormat.parse(dateString);
    }

    @Override
    public String marshal(Date date) {
        return dateFormat.format(date);
    }
}
