package CheckRunner.exceptions;

public class InputTypeNotFountException extends RuntimeException {
    private final String inputType;

    public InputTypeNotFountException(String outputType) {
        this.inputType = outputType;
    }

    @Override
    public String getMessage() {
        return "Please select one of the options from the parameter name. "
                + inputType.toUpperCase() + " is incorrect";
    }
}
