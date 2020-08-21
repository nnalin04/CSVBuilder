package csvbuilder;

public class CSVBuilderException extends Exception {

    public enum ExceptionType {
        UNABLE_TO_PARSE, CENSUS_FILE_PROBLEM, NOT_A_CSV_TYPE_OR_HEADERS_INVALID
    }

    public ExceptionType type;

    public CSVBuilderException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
