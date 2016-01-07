
import java.io.*;
public class CharacterFrequency {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//Inputs the text file as a bufferedreader
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/letter1.txt")));
		//Stores the file into a string, letter1
		String letter1 = "";
		//16 is the number of lines in the document
		for(int i =0; i<16; i++)
		{
			letter1+=br.readLine();
			
		}
		
		//stores aphabet for later when printing final values
		char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		String letters[]= new String[26];
		for(int i=0; i<26; i++)
		{
			letters[i]="" + alphabet[i];
		}
		FrequencyBag<String> fb = new FrequencyBag<String>();
		letter1=letter1.toLowerCase();
		//Gets rid of spaces, commas etc in string
		letter1=letter1.replaceAll("[^a-z]", "");
		//Adds the letters in the string to the bag, one by one
		for(int i =0; i<letter1.length(); i++)
		{
			fb.add(letter1.substring(i, i+1));
			//System.out.println(letter1.substring(i, i+1)+ " " + fb.getFrequencyOf(letter1.substring(i, i+1)));
		}
		//System.out.println(letter1);
		//Prints the final output
		System.out.println("Character: Frequency");
		System.out.println("====================");
		for(int i =0; i<26; i++)
		{
			System.out.println(letters[i] + ": "+ fb.getFrequencyOf(letters[i]));
		}
		
	}

}
