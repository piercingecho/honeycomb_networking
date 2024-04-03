package RESTAPI;

import honeycombData.Company;

public record RProjectResp(
	String request,
	boolean successful,
	String message,
	RProject data
) {};

