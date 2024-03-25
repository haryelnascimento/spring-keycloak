package io.imob.core.exception;

public class S3ProblemSendFileException extends RuntimeException {

    public S3ProblemSendFileException() {
    }

    public S3ProblemSendFileException(String message) {
        super(message);
    }

    public S3ProblemSendFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public S3ProblemSendFileException(Throwable cause) {
        super(cause);
    }
}
