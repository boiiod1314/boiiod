package com.boiiod.exception;

import com.boiiod.status.StatusCode;

public class BoiiodException extends Exception{

	private static final long serialVersionUID = -4010787523984781561L;

	private int code;

	public BoiiodException(StatusCode code) {
		super(code.getMsg());
		this.code = code.getCode();
	}

	public int getCode() {
		return code;
	}

}
