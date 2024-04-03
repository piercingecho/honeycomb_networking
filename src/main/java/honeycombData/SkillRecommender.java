package honeycombData;
import java.util.ArrayList;

public class SkillRecommender implements RecommenderStrategy
{

	@Override
	public boolean recommend(JobPosting j, Person p)
	{
		ArrayList<String> qualifications;
		ArrayList<String> skills;
		//Returns true
		//if the person is following one of JobPosting's contributors.
		try
		{
		qualifications = j.getInternalLinks("qualification");
		skills = p.getInternalLinks("skill");
		}
		catch(Exception e)
		{
			qualifications = new ArrayList<String>();
			skills = new ArrayList<>();
		}
		
		
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