package com.micro.stockservice.util;

import java.util.Objects;

public class RestResponse<T> {
	private String status;
	private int code;
	private String message;
	private T result;

    public RestResponse() {}

    public RestResponse(String status, int code, String message, T result) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public String getStatus() {
		return status;
	}

	// TODO : Check if we can set the status without the code, if so access can be private
	public void setStatus(String status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	// TODO : Check if we can set the code without the status, if so access can be private
	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

	public void setStatusCode(Pair<Integer, String> pair) {
		this.setCode(pair.getKey());
		this.setStatus(pair.getValue());
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestResponse<?> that = (RestResponse<?>) o;
        return code == that.code &&
                Objects.equals(status, that.status) &&
                Objects.equals(message, that.message) &&
                Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, code, message, result);
    }
}
