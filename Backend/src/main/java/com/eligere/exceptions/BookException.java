package com.eligere.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BookException extends Exception{
	public BookException(String msg) {
		super(msg);
	}
}
