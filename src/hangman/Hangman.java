package hangman;

import java.util.Arrays;

class Hangman 
{
	private final String wordToGuess;
	private int livesLeft;
	private String gameString;
	private boolean[] isRevealedIndexes;
	
	Hangman(int lives, String wordToGuess)
	{
		this.wordToGuess = wordToGuess;
		this.livesLeft = lives;
		
		// Holds information if char at given index is already guessed or no.
		this.isRevealedIndexes = new boolean[wordToGuess.length()];
		
		reCreateGameString();
	}
	
	int livesLeft()
	{
		return livesLeft;
	}
	
	boolean isAlive()
	{
		return livesLeft() > 0;
	}
	
	void makeGuess(String userInput)
	{
		int inputLength = userInput.length();
		if (inputLength == wordToGuess.length() || inputLength == 1)
		{
			for (char character: userInput.toCharArray())
			{
				if (!revealCharIfPresent(character))
				{
					livesLeft--;
				}
			}
			reCreateGameString();
		}
		else
		{
			lose();
			revealKeyword();
			
		}
	}
	
	private void lose()
	{
		livesLeft = 0;
	}
	
	private void revealKeyword()
	{
		Arrays.fill(isRevealedIndexes, true);
		reCreateGameString();
		System.out.println(gameString());
	}
	
	
	private boolean revealCharIfPresent(char character)
	{
		boolean isCharPresentInKeyword = false;
		
		int occourenceIndex = wordToGuess.indexOf(character);
		while (occourenceIndex >= 0)
		{
			isRevealedIndexes[occourenceIndex] = true;
			occourenceIndex = wordToGuess.indexOf(character, occourenceIndex + 1);
			
			isCharPresentInKeyword = true;
		}
		
		return isCharPresentInKeyword;
	}
	
	boolean keywordComplete()
	{
		return gameString.equals(wordToGuess);
	}
	
	String gameString()
	{
		return gameString;
	}
	
	private void reCreateGameString()
	{
		StringBuilder gameStringBuilder = new StringBuilder();
		
		for (int i = 0; i < wordToGuess.length(); i++)
		{
			char toAppend = '-';
			
			if (isRevealedIndexes[i] == true)
			{
				toAppend = wordToGuess.charAt(i);
			}
				
			gameStringBuilder.append(toAppend);
		}
		
		gameString = gameStringBuilder.toString();
	}
}