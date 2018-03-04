package com.onemap.api;

public class InvalidRequestException extends OneMapServerException {

    private static final long serialVersionUID = 3341653614035548951L;

    public InvalidRequestException(String subErrorCode) {
        super(ErrorType.invalid_request.toString(), subErrorCode);
    }

    @Override
	public String getErrorType() {
		return ErrorType.invalid_request.toString();
	}

	@Override
	public int getHttpErrorCode() {
		return ErrorType.invalid_request.getHttpErrorCode();
	}

}
