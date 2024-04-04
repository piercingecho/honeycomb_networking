package honeycombData;

public interface RecommenderStrategy
{
	public boolean recommend(JobPosting j, Person p) throws RoleNotAllowedException;
}