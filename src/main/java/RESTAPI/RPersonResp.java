package RESTAPI;

public record RPersonResp(		
		String request,
		boolean successful,
		String message,
		RPerson data
	) implements Response
{};