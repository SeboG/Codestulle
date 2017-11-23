package stull;

public class Stulle
{	
	public Stulle() {
		System.out.println("Ich bin eine Stulle aus Code.");
		System.out.println("Also bei mir laeuft der Code raus.");
	}
	public void schmieren(String streichkram) {
		System.out.println("Die Stulle wurde mit "+ streichkram + " beschmiert.");
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		Stulle stulli = new Stulle();
		stulli.schmieren("Mettwurst");
		
	}

}
