package RESTAPI;

public record RGetString
(
	String request,
	boolean successful,
	String message,
	String data
) {};