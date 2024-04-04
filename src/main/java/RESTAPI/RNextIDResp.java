package RESTAPI;

public record RNextIDResp(String request,
		boolean successful,
		String message,
		RNextID data
	) implements Response {

}
