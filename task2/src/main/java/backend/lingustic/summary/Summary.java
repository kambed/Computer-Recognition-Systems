package backend.lingustic.summary;

import lombok.Getter;

@Getter
public abstract class Summary {
    protected String summaryString;
    protected Double finalDegreeOfTruth;
    protected Double t1;
    protected Double t2;
    protected Double t3;
    protected Double t4;
    protected Double t5;
    protected Double t6;
    protected Double t7;
    protected Double t8;
    protected Double t9;
    protected Double t10;
    protected Double t11;

    protected Summary(String summaryString) {
        this.summaryString = summaryString;
    }
}
