package client;

public class ClientUI
{
	public static void main(String[] args)
	{
		SchiffClient client = new SchiffClient();
		if (client.verbinden("localhost", 5000))
		{
			
		}
	}
}
