package frontend.model;

import backend.lingustic.summary.Summary;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SummaryDto extends Summary {
    private static final String CELL_PATTERN = "<td>%s</td>";
    private boolean selected;

    public SummaryDto(Summary summary) {
        super(summary.getSummaryString());
        this.t1 = summary.getT1();
        this.t2 = summary.getT2();
        this.t3 = summary.getT3();
        this.t4 = summary.getT4();
        this.t5 = summary.getT5();
        this.t6 = summary.getT6();
        this.t7 = summary.getT7();
        this.t8 = summary.getT8();
        this.t9 = summary.getT9();
        this.t10 = summary.getT10();
        this.t11 = summary.getT11();
        this.finalDegreeOfTruth = summary.getFinalDegreeOfTruth();
        this.selected = false;
    }

    @Override
    public String toString() {
        return "Summary: \n" + getSummaryString() + "\n" +
                "T1: " + getT1() + "\n" +
                "T2: " + getT2() + "\n" +
                "T3: " + getT3() + "\n" +
                "\n";
    }

    public String toHtmlTableRow() {
        return "<tr>" +
                CELL_PATTERN.formatted(getSummaryString()) +
                CELL_PATTERN.formatted(getT1()) +
                CELL_PATTERN.formatted(getT2()) +
                CELL_PATTERN.formatted(getT3()) +
                "</tr>";
    }
}
