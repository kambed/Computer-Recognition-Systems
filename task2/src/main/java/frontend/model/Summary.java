package frontend.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Summary {
    private static final String CELL_PATTERN = "<td>%s</td>";
    private String summary;
    private Double t1;
    private Double t2;
    private Double t3;
    private boolean selected;

    public Summary(String summary, Double t1, Double t2, Double t3) {
        this.summary = summary;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.selected = false;
    }

    @Override
    public String toString() {
        return "Summary: \n" + summary + "\n" +
                "T1: " + t1 + "\n" +
                "T2: " + t2 + "\n" +
                "T3: " + t3 + "\n" +
                "\n";
    }

    public String toHtmlTableRow() {
        return "<tr>" +
                CELL_PATTERN.formatted(summary) +
                CELL_PATTERN.formatted(t1) +
                CELL_PATTERN.formatted(t2) +
                CELL_PATTERN.formatted(t3) +
                "</tr>";
    }
}
