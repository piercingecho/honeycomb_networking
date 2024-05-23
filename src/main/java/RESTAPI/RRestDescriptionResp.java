package RESTAPI;

import java.util.ArrayList;

public record RRestDescriptionResp(

String request,
boolean successful,
String message,
ArrayList<RRestDescription> data)
{}
