package CheckRunner.exceptions;

public class OutputTypeNotFountException extends RuntimeException {
    private final String outputType;

    public OutputTypeNotFountException(String outputType) {
        this.outputType = outputType;
    }

    @Override
    public String getMessage() {
        return "Please select one of the options from the parameter name. "
                + outputType.toUpperCase() + " is incorrect";
    }
}
