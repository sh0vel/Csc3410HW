//Shovon Hossain
//Bhola csc3410
//Anagram2 program code
//10/6/2017

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Anagram2 {

	String word, sig;

	public Anagram2(String word, String sig) {
		this.word = word;
		this.sig = sig;

	}

	public String getWord() {
		return word;
	}

	public String getSig() {
		return sig;
	}

	public static void main(String[] args) {
		ArrayList<String> wordsInFile = readFileAndStoreWords();
		ArrayList<String> wordSignatures = createWordSignatures(wordsInFile);
		ArrayList<Anagram2> anagrams = createAnagramObject(wordsInFile, wordSignatures);
		writeFile(anagrams, wordSignatures);

	}

	/*
	 * The obtainInFileName method asks the user for the file path of the input
	 * file and the users input is then used by the readFileAndStoreWords method
	 */
	public static String obtainInFileName() {
		Scanner kbInput = new Scanner(System.in);
		System.out.print("Enter file path name: ");
		String fileName = kbInput.next();
		return fileName;
	}
	/*The readFileAndStoreWords completes multiple tasks.
	 *It first creates an input stream for the file path obtained in the previous method.
	 *Then it checks if the file is empty, if so it will say so and terminate the program. 
	 *If the file is not empty it will take each word store them in an ArrayList titled wordsInFile.
	 *It then copies every word in that ArrayList into tempList ONLY IF that word is 12 characters or less.
	 *While it is coping the words into tempArray, it keeps track of the number of words copied. If the amount 
	 *is greater than 50, the program will say so and terminate. If all conditions meet the data tempList is moved back into
	 *wordsInFile which will be the output of this method and it will contain no more than 50 words and no word will have 
	 *more than 12 characters. */
	public static ArrayList<String> readFileAndStoreWords() {
		BufferedReader input;
		String line;
		String allWords = "";
		int wordCount = 0;
		ArrayList<String> wordsInFile = new ArrayList<String>();
		ArrayList<String> tempList = new ArrayList<String>();
		try {
			input = new BufferedReader(new FileReader(obtainInFileName()));
			if (input.readLine() == null) {
				System.out.print("Input file is empty");
				System.exit(0);
			}
			while ((line = input.readLine()) != null) {
				if (!line.isEmpty()) {
					allWords += line + " ";
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			for (String word : allWords.split(" ")) {
				wordsInFile.add(word);
			}
			int wordLength;
			for (int i = 0; i < wordsInFile.size(); i++) {
				wordLength = wordsInFile.get(i).length();
				if (wordLength < 13) {
					wordCount++;
					if (wordCount > 50) {
						System.out
								.println("there are more than 50 words in the input file");
						System.exit(0);
					} else
						tempList.add(wordsInFile.get(i));
					{
						continue;
					}
				} else {
					continue;
				}
			}
		}
		wordsInFile = tempList;
		return wordsInFile;
	}
	/*The createWordSignature method takes the list wordsInFile created in the previous method as its parameter.
	 * It then creates an list wordSignatures. It then takes each word from wordsInFile and makes 
	 * all chars lower case and removes all not alphabetical characters, the resulting characters is then ordered alphabetically 
	 * using Arrays.sort() and then stored in wordSignatures list into the space proportional to the original word
	 *  takin from wordsInFile */
	public static ArrayList<String> createWordSignatures(
			ArrayList<String> originalWordList) {
		ArrayList<String> originalWords = originalWordList;
		ArrayList<String> wordSignatures = new ArrayList();
		char[] word;
		for (int i = 0; i < originalWords.size(); i++) {
			word = originalWords.get(i).toLowerCase()
					.replaceAll("[^a-zA-Z ]", "").toCharArray();
			Arrays.sort(word);
			String signature = "";
			for (int j = 0; j < word.length; j++) {
				signature += word[j];
			}
			wordSignatures.add(signature);
		}

		return wordSignatures;
	}
	/*The creatAnagramsObject takes the wordsInList and wordSignatures as parameters and
	 *creates an anagram object. Each objects contains one of the words in wordsInList and its sigature from wordSignatures 
	 *and stores each object in a list called anagrams.
	 *this is used by the writeFile method */
	public static ArrayList<Anagram2> createAnagramObject(
			ArrayList<String> words, ArrayList<String> signatures) {
		ArrayList<Anagram2> anagrams = new ArrayList();
		for (int i = 0; i < words.size(); i++) {
			Anagram2 anagram = new Anagram2(words.get(i), signatures.get(i));
			anagrams.add(anagram);
		}

		return anagrams;

	}
	/*the writeFile takes the anagrams list and wordSignatures list as parameters, it creates an outout stream then
	 *it compares each signature in the wordSignatures array with the sig of anagram object of each object in the
	 *anagrams array. If for every match, it will add the word from the anagram object to a string. The string is then printed and stored in a file.
	 *If there is no match the word is printed by itself. */
	public static PrintWriter writeFile(ArrayList<Anagram2> anagramsyo,
			ArrayList<String> sig) {
		PrintWriter output = null;
		String sign1;
		String sign2 = null;
		Set<String> noDupes = new LinkedHashSet<String>(sig);
		sig.clear();
		sig.addAll(noDupes);

		try {
			output = new PrintWriter(new FileWriter("/output.txt"));
			for (int i = 0; i < sig.size(); i++) {
				String words = "";
				sign1 = sig.get(i);
				for (int j = 0; j < anagramsyo.size(); j++) {
					sign2 = anagramsyo.get(j).getSig();
					if (sign1.equals(sign2)) {
						words += anagramsyo.get(j).getWord() + " ";
					}
				}
				System.out.println(words);
				output.println(words);
			}
		} catch (IOException e) {
			System.out.println(e + "\nNeed permission to write in c drive");
			System.exit(1);
		} finally {
			System.out.println("\nOutput.txt file created in  C drive");
			output.close();
		}
		return output;
	}
}
