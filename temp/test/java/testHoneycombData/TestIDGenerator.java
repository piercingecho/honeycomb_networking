package testHoneycombData;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.intThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import honeycombData.IDGeneratorSingleton;

class TestIDGenerator
{
	IDGeneratorSingleton generator;

	@BeforeEach
	void setUp() throws Exception
	{
		generator = IDGeneratorSingleton.getInstance();
	}

	@Test
	void testIncrements()
	{
		String firstId = generator.getNextID();
		String secondId = generator.getNextID();
		
		assertTrue(firstId.equals("1"));
		assertTrue(secondId.equals("2"));
	}

}
