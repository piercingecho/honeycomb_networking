package RESTAPI;

public record RSimpleMessageResp(
	String request,
	boolean successful,
	String message,
	RSimpleMessage data
) implements Response
{};

