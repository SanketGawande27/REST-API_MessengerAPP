package org.sanket.messengerAPI.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {

	private String errorMessage;
	private int errorCode;
	private String decumentation;

	public ErrorMessage() {

	}

	public ErrorMessage(String errorMessage, int errorCode, String decumentation) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.decumentation = decumentation;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getDecumentation() {
		return decumentation;
	}

	public void setDecumentation(String decumentation) {
		this.decumentation = decumentation;
	}

}
