package RESTAPI;

public record RCompanyResp(
	String request,
	boolean successful,
	String message,
	RCompany data
) implements Response
{};

