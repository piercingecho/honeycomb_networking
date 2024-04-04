package RESTAPI;

import honeycombData.NewsArticle;

public record RNewsArticleResp(		
		String request,
		boolean successful,
		String message,
		RNewsArticle data
	) implements Response
{};