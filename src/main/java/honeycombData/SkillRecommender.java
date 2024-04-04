package honeycombData;
import java.util.ArrayList;

public class SkillRecommender implements RecommenderStrategy 
{

	@Override
	public boolean recommend(JobPosting j, Person p) throws RoleNotAllowedException
	{
		ArrayList<String> qualifications;
		ArrayList<String> skills;
		//Returns true
		//if the person is following one of JobPosting's contributors.
		
		qualifications = j.getInternalLinks("qualification");
		skills = p.getInternalLinks("skill");
		
		
		for(int i=0; i<qualifications.size(); i++)
		{
			String qualificationId = qualifications.get(i);
			if(!skills.contains(qualificationId))
			{
				return false;
			}
			
		}
		
		return true;
	}

}