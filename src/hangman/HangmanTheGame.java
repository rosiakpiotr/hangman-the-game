package hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class HangmanTheGame
{

	public static void main(String[] args) throws IOException 
	{
		//args = new String[]{"2", "laptop"};
		
		int lives = Integer.parseInt(args[0]);
		String keyword = args[1].toUpperCase();
		
		Hangman hangman = new Hangman(lives, keyword.toUpperCase());
		
		while (!hangman.keywordComplete() && hangman.isAlive())
		{
			System.out.print(hangman.gameString());
			System.out.println(String.format("\tŻycia: %d\n\n", hangman.livesLeft()));
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String userInput = reader.readLine().toUpperCase();
			
			hangman.makeGuess(userInput);
		}
		
		if (hangman.isAlive())
		{
			System.out.println(String.format("Gratulacje! Odgadnięte hasło to %s", keyword));
		} else {
			System.out.println(String.format("Niestety wisielec zakończył swój żywot :( Poprawne rozwiązanie to %s", keyword));
		}
	}
}
