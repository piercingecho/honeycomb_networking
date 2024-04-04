package honeycombData;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestRecommender
{

	@BeforeEach
	void setUp() throws Exception
	{
	}

	@Test
	void testAllRecommender()
	{
		//just returns true lol
		Person p = new Person("","");
		JobPosting j = new JobPosting("","");
		RecommenderStrategy r = new AllRecommender();
		assertEquals(true, r.recommend(j, p));
		
	}
	
	@Test
	void testSkillRecommender() throws RoleNotAllowedException
	{
		Person p = new Person("","");
		JobPosting j = new JobPosting("","");
		JobPosting noquals = new JobPosting("","");
		Skill s1 = new Skill("skill1", "skill");
		Skill s2 = new Skill("skill2", "skill");
		Skill s3 = new Skill("extra skill", "skill");
		
		j.addInternalLink(s1, "qualification");
		j.addInternalLink(s2, "qualification");

		
		RecommenderStrategy r = new SkillRecommender();
		assertEquals(true, r.recommend(noquals, p));
		assertEquals(false, r.recommend(j, p));
		
		
		p.addInternalLink(s2, "skill");
		
		assertEquals(false, r.recommend(j, p));

		p.addInternalLink(s1, "skill");
		
		assertEquals(true, r.recommend(j, p));
		
		p.addInternalLink(s3, "skill");
		
		assertEquals(true, r.recommend(j, p));
		
	}
	
	@Test
	void testFollowing() throws RoleNotAllowedException
	{
		Person noFollowing = new Person("","");
		Person someFollowing = new Person("","");
		Person personFollowing = new Person("","");
		Person contributorPerson = new Person("","");
		Company c = new Company("","");
		Company c2 = new Company("","");
		Company c3 = new Company("","");
		JobPosting j = new JobPosting("","");
		
		RecommenderStrategy r = new FollowingRecommender();
		
		j.addInternalLink(c, "contributor");
		j.addInternalLink(c2, "contributor");
		j.addInternalLink(contributorPerson, "contributor");
		
		someFollowing.follow(c3);
		personFollowing.follow(contributorPerson);
		
		assertEquals(false, r.recommend(j, noFollowing));
		
		assertEquals(false, r.recommend(j, someFollowing));
		
		someFollowing.follow(c2);
		
		assertEquals(true, r.recommend(j, someFollowing));
		
		assertEquals(true, r.recommend(j, personFollowing));

	}

}
