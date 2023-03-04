package backend.process.exception;

public class NoDataFoundException extends Exception {
    public NoDataFoundException(String noDataFound) {
        super(noDataFound);
    }
}
