package com.main.user.service.payload;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

	
	String message;
	Date timestamp;
	String detail;
	/*
	 * public ExceptionResponse(String message, Date timestamp, String detail) {
	 * super(); this.message = message; this.timestamp = timestamp; this.detail =
	 * detail; }
	 */
	
	
}
