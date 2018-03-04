package com.onemap.api;

import com.onemap.api.OneMapServerException.ErrorType;

public class OneMapError {

    private String code;

    private String message;

    public OneMapError(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public static OneMapError valueOf(OneMapException cause) {
        return new OneMapError(cause.getErrorType() + "." + cause.getErrorCode(), cause.getMessage());
    }

    public OneMapException toAPIException() {
        OneMapException exception;
        String[] codeParts = code == null ? new String[0] : code.split("\\.");
        ErrorType type = ErrorType.valueOf(codeParts[0]);

        String code = codeParts != null && codeParts.length > 1 ? codeParts[1] : "UNKNOW";

        switch (type) {
            case forbidden:
                exception = new ForbiddenException(code);
                break;
            case internal_error:
                exception = new InternalErrorException(code);
                break;
            case invalid_authorization:
                exception = new InvalidAuthorizationException(code);
                break;
            case invalid_request:
                exception = new InvalidRequestException(code);
                break;
            default:
                exception = new InternalErrorException("UNKNOW");
                break;
        }
        exception.setMessage(this.getMessage());
        return exception;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
