package frontend.model;

import backend.Rounder;
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
        this.t1 = Rounder.round(summary.getT1());
        this.t2 = Rounder.round(summary.getT2());
        this.t3 = Rounder.round(summary.getT3());
        this.t4 = Rounder.round(summary.getT4());
        this.t5 = Rounder.round(summary.getT5());
        this.t6 = Rounder.round(summary.getT6());
        this.t7 = Rounder.round(summary.getT7());
        this.t8 = Rounder.round(summary.getT8());
        this.t9 = Rounder.round(summary.getT9());
        this.t10 = Rounder.round(summary.getT10());
        this.t11 = Rounder.round(summary.getT11());
        this.finalDegreeOfTruth = Rounder.round(summary.getFinalDegreeOfTruth());
        this.selected = false;
    }

    @Override
    public String toString() {
        return "Summary: \n" + getSummaryString() + "\n" +
                "TAvg: " + getFinalDegreeOfTruth() + "\n" +
                "T1: " + getT1() + "\n" +
                "T2: " + getT2() + "\n" +
                "T3: " + getT3() + "\n" +
                "T4: " + getT4() + "\n" +
                "T5: " + getT5() + "\n" +
                "T6: " + getT6() + "\n" +
                "T7: " + getT7() + "\n" +
                "T8: " + getT8() + "\n" +
                "T9: " + getT9() + "\n" +
                "T10: " + getT10() + "\n" +
                "T11: " + getT11() + "\n" +
                "\n";
    }

    public String toHtmlTableRow() {
        return "<tr>" +
                CELL_PATTERN.formatted(getSummaryString()) +
                CELL_PATTERN.formatted(getFinalDegreeOfTruth()) +
                CELL_PATTERN.formatted(getT1()) +
                CELL_PATTERN.formatted(getT2()) +
                CELL_PATTERN.formatted(getT3()) +
                CELL_PATTERN.formatted(getT4()) +
                CELL_PATTERN.formatted(getT5()) +
                CELL_PATTERN.formatted(getT6()) +
                CELL_PATTERN.formatted(getT7()) +
                CELL_PATTERN.formatted(getT8()) +
                CELL_PATTERN.formatted(getT9()) +
                CELL_PATTERN.formatted(getT10()) +
                CELL_PATTERN.formatted(getT11()) +
                "</tr>";
    }
}
