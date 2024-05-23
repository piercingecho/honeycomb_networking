package honeycombData;

public class AllRecommender implements RecommenderStrategy
{

	@Override
	public boolean recommend(JobPosting j, Person p)
	{
		return true;
	}

}
