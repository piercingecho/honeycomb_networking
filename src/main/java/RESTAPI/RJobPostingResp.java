package RESTAPI;

import honeycombData.JobPosting;

public record RJobPostingResp(
	String request,
	boolean successful,
	String message,
	RJobPosting data
) {};
