package RESTAPI;

public record RJobPostingResp(
	String request,
	boolean successful,
	String message,
	RJobPosting data
) implements Response
{};
