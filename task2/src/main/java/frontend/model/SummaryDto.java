package frontend.model;

import backend.Rounder;
import backend.lingustic.summary.Summary;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SummaryDto {
    private static final String CELL_PATTERN = "<td>%s</td>";
    private String summaryString;
    private ComparableMeasureString t1;
    private ComparableMeasureString t2;
    private ComparableMeasureString t3;
    private ComparableMeasureString t4;
    private ComparableMeasureString t5;
    private ComparableMeasureString t6;
    private ComparableMeasureString t7;
    private ComparableMeasureString t8;
    private ComparableMeasureString t9;
    private ComparableMeasureString t10;
    private ComparableMeasureString t11;
    private ComparableMeasureString finalDegreeOfTruth;
    private boolean selected;

    public SummaryDto(Summary summary) {
        this.summaryString = summary.getSummaryString();
        this.t1 = Rounder.roundSummary(summary.getT1());
        this.t2 = Rounder.roundSummary(summary.getT2());
        this.t3 = Rounder.roundSummary(summary.getT3());
        this.t4 = Rounder.roundSummary(summary.getT4());
        this.t5 = Rounder.roundSummary(summary.getT5());
        this.t6 = Rounder.roundSummary(summary.getT6());
        this.t7 = Rounder.roundSummary(summary.getT7());
        this.t8 = Rounder.roundSummary(summary.getT8());
        this.t9 = Rounder.roundSummary(summary.getT9());
        this.t10 = Rounder.roundSummary(summary.getT10());
        this.t11 = Rounder.roundSummary(summary.getT11());
        this.finalDegreeOfTruth = Rounder.roundSummary(summary.getFinalDegreeOfTruth());
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
