package honeycombData;

public class IDGeneratorSingleton
{
	private static IDGeneratorSingleton generator;
	private IDGeneratorSingleton()
	{
		//this.current_id_num = 0;
	}
	public String getNextID()
	{
		return Storage.getNextID();
		//generator.current_id_num++; 
		//return Integer.toString(generator.current_id_num);
	}
	
	public static IDGeneratorSingleton getInstance()
	{
		if(generator == null)
		{
			generator = new IDGeneratorSingleton();
		}
		return generator;
	}
}
