package RESTAPI;

import honeycombData.Company;

public record RCompanyResp(
	String request,
	boolean successful,
	String message,
	RCompany data
) {};

