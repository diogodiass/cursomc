package com.diogoamorim.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StanderError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> list = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	public void addError(String fieldName, String messagem) {
		list.add(new FieldMessage(fieldName, messagem));
	}

}
