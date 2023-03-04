package backend.process.exception;

public class NoDataFountException extends Exception {
    public NoDataFountException(String noDataFound) {
        super(noDataFound);
    }
}
