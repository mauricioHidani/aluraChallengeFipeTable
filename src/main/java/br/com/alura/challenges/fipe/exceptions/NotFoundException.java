package br.com.alura.challenges.fipe.exceptions;

public class NotFoundException extends RuntimeException {
	public NotFoundException(String message) {
		super(message);
	}
}