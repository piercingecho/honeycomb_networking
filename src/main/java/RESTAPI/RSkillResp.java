package RESTAPI;

public record RSkillResp
(
		String request,
		boolean successful,
		String message,
		RSkill data
	) implements Response
{};