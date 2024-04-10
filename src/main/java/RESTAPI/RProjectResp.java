package RESTAPI;

public record RProjectResp(
	String request,
	boolean successful,
	String message,
	RProject data
) implements Response
{};

