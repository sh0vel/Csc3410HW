//Shovon Hossain
//Bhola csc3410
//Anagram program code
//9/20/2014

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Anagram {
	String word, sig;

	public Anagram(String word, String sig) {
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
		String wordsInFile[] = readFileAndStoreWords();
		String wordSignatures[] = createWordSignature(wordsInFile);
		Anagram[] anagrams = createAnagramsObject(wordsInFile, wordSignatures);
		writeFile(anagrams, wordSignatures);

	}

	/*
	 * The obtainInFileName method asks the user for the file path of the input
	 * file and the users input is thenused by the readFileAndStoreWords method
	 */
	public static String obtainInFileName() {
		Scanner kbInput = new Scanner(System.in);
		System.out.print("Enter file path name: ");
		String fileName = kbInput.next();
		return fileName;
	}

	/*
	 * The readFileAndStoreWords completes multiple tasks.It first creates an
	 * input stream for the file path obtained in the previous method.Then it
	 * checks if the file is empty, if so it will say so and terminate the
	 * program.If the file is not empty it will take each word store them in an
	 * array titled wordsInArray.It then copies every word in that array into
	 * tempArray ONLY IF that word is 12 characters or less.While it is coping
	 * the words into tempArray, it keeps track of the number of words copied.
	 * If the amountis greater than 50, the program will say so and terminate.
	 * If all conditions meet the data tempArray is moved back intowordsInList
	 * which will be the output of this method and wit will contain no more than
	 * 50 words and no word will havemore than 12 characters.
	 */
	public static String[] readFileAndStoreWords() {
		BufferedReader input;
		String line;
		String allWords = "";
		String wordsInFile[];
		int wordCount = 0;
		String tempArray[] = new String[50];
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
			wordsInFile = allWords.split(" ");
			int wordLength;
			for (int i = 0; i < wordsInFile.length; i++) {
				wordLength = wordsInFile[i].length();
				if (wordLength < 13) {
					wordCount++;
					if (wordCount > 50) {
						System.out
								.println("there are more than 50 words in the input file");
						System.exit(0);
					} else
						tempArray[i] = wordsInFile[i];
					{
						continue;
					}
				} else {
					continue;
				}
			}
		}
		wordsInFile = new String[wordCount];
		for (int i = 0; i < wordsInFile.length; i++) {
			wordsInFile[i] = tempArray[i];
		}
		return wordsInFile;
	}

	/*
	 * The createWordSignature method takes the array wordsInFile created in the
	 * previous method as its parameter. It then creates an array wordSignatures
	 * which the length of wordsInList. It then takes each word from wordsInList
	 * makes all chars lower case and removes all not alphabetical characters,
	 * the resulting characters is then ordered alphabetically using
	 * Arrays.sort() and then stored as a string in wordSignatures array into
	 * the space proportional to the original word taking from wordsInList
	 */

	public static String[] createWordSignature(String[] originalWordList) {
		String originalWords[] = originalWordList;
		char[] word;
		String wordSignatures[] = new String[originalWords.length];
		for (int i = 0; i < originalWords.length; i++) {
			word = originalWords[i].toLowerCase().replaceAll("[^a-zA-Z ]", "")
					.toCharArray();
			Arrays.sort(word);
			String signature = "";
			for (int j = 0; j < word.length; j++) {
				signature += word[j];
			}
			wordSignatures[i] = signature;
		}
		return wordSignatures;
	}

	/*
	 * The creatAnagramsObject takes the wordsInList and wordSignatures as
	 * parameters andcreates an anagram object. Each objects contains one of the
	 * words in wordsInList and its sigature from wordSignaturesand stores each
	 * object in an array called anagrams.this is by the writeFile method
	 */
	public static Anagram[] createAnagramsObject(String[] words, String[] sig) {
		Anagram[] anagrams = new Anagram[words.length];
		for (int i = 0; i < anagrams.length; i++) {
			anagrams[i] = new Anagram(words[i], sig[i]);
		}
		return anagrams;
	}

	/*
	 * the writeFile takes the anagrams array and wordSignatures array as
	 * parameters, it creates an outout stream thenit compares each signature in
	 * the wordSignatures array with the sig of anagram object of each object in
	 * theanagrams array. If for every match, it will add the word from the
	 * anagram object to a string. The string is then printed and stored in a
	 * file.If there is no match the word is printed by itself.
	 */
	public static PrintWriter writeFile(Anagram[] anagramsyo, String[] sig) {
		PrintWriter output = null;
		Set<String> signatureList = new HashSet<String>(Arrays.asList(sig));
		Object[] signatures = signatureList.toArray();
		String sign1;
		String sign2 = null;
		try {
			output = new PrintWriter(new FileWriter("/output.txt"));
			for (int i = 0; i < signatures.length; i++) {
				String words = "";
				sign1 = signatures[i].toString();
				for (int j = 0; j < anagramsyo.length; j++) {
					sign2 = anagramsyo[j].getSig();
					if (sign1.equals(sign2)) {
						words += anagramsyo[j].getWord() + " ";
					}
				}
				System.out.println(words);
				output.println(words);
			}
		} catch (IOException e) {
			System.out.println(e + "\nNeed permission to write in c drive");
			System.exit(1);
		} finally {
			System.out.println("\nOutput file created in  C drive");
			output.close();
		}
		return output;
	}
}