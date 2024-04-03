package RESTAPI;

import honeycombData.Skill;

public record RSkillResp
(
		String request,
		boolean successful,
		String message,
		RSkill data
	) {};