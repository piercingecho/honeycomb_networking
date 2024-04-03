package RESTAPI;

public record PostResponse(String request, boolean successful, String message, Object data) {

}
