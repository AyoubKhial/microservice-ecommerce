package com.micro.stockservice.util;

public class RestResponse<T> {
	private String status;
	private int code;
	private String message;
	private T result;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatusCode(Pair<Integer, String> pair) {
		this.setCode(pair.getKey());
		this.setStatus(pair.getValue());
	}
}
