package honeycombData;
import java.util.ArrayList;

public class FollowingRecommender implements RecommenderStrategy
{

	@Override
	public boolean recommend(JobPosting j, Person p)
	{
		ArrayList<String> contributors;
		ArrayList<String> following;
		//Returns true
		//if the person is following one of JobPosting's contributors.
		try
		{
		contributors = j.getInternalLinks("contributor");
		following = p.getInternalLinks("following");
		}
		catch(Exception e)
		{
			contributors = new ArrayList<String>();
			following = new ArrayList<>();
		}
		
		
		for(int i=0; i<contributors.size(); i++)
		{
			String contributorId = contributors.get(i);
			if(following.contains(contributorId))
			{
				return true;
			}
			
		}
		
		return false;
	}

}