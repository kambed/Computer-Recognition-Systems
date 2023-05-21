package backend.lingustic.summary;

import backend.lingustic.LabeledFuzzySet;
import backend.lingustic.Subject;
import backend.lingustic.quantifier.AbsoluteQuantifier;

import java.util.List;

public abstract class SingleSubjectSummary extends Summary {
    protected AbsoluteQuantifier quantifier;
    protected Subject subject;
    protected LabeledFuzzySet summarizer;
    public abstract double calculateFinalDegreeOfTruth(List<Double> weights);
    protected abstract double calculateT1();
    protected abstract double calculateT2();
    protected abstract double calculateT3();
    protected abstract double calculateT4();
    protected abstract double calculateT5();
    protected abstract double calculateT6();
    protected abstract double calculateT7();
    protected abstract double calculateT8();
    protected abstract double calculateT9();
    protected abstract double calculateT10();
    protected abstract double calculateT11();
}
