package RESTAPI;

import honeycombData.Person;

public record RPersonResp(		
		String request,
		boolean successful,
		String message,
		Person data
	) {};