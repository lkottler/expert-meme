import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * This class contains some utility helper methods
 * 
 * @author sapan (sapan@cs.wisc.edu)
 */
public class WordProcessor {
	
	/**
	 * Gets a Stream of words from the filepath.
	 * 
	 * The Stream should only contain trimmed, non-empty and UPPERCASE words.
	 * 
	 * @see <a href="http://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html">java8 stream blog</a>
	 * 
	 * @param filepath file path to the dictionary file
	 * @return Stream<String> stream of words read from the filepath
	 * @throws IOException exception resulting from accessing the filepath
	 */
	public static Stream<String> getWordStream(String filepath) throws IOException {
		Stream<String> wordStream = 
				Files.lines(Paths.get(filepath))
				.filter(x -> x!=null && !x.matches("\\S")) //this way is more efficient by filtering before trimming
				.map(String::trim)
				// .filter(x -> x!=null && !x.equals(""));
				.map(String::toUpperCase);
		return wordStream;
		/**
		 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html">java.nio.file.Files</a>
		 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/nio/file/Paths.html">java.nio.file.Paths</a>
		 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html">java.nio.file.Path</a>
		 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html">java.util.stream.Stream</a>
		 * 
		 * class Files has a method lines() which accepts an interface Path object and 
		 * produces a Stream<String> object via which one can read all the lines from a file as a Stream.
		 * 
		 * class Paths has a method get() which accepts one or more strings (filepath),  
		 * joins them if required and produces a interface Path object
		 * 
		 * Combining these two methods:
		 *     Files.lines(Paths.get(<string filepath>))
		 *     produces
		 *         a Stream of lines read from the filepath
		 * 
		 * Once this Stream of lines is available, you can use the powerful operations available for Stream objects to combine 
		 * multiple pre-processing operations of each line in a single statement.
		 * 
		 * Few of these features:
		 * 		1. map( )      [changes a line to the result of the applied function. Mathematically, line = operation(line)]
		 * 			-  trim all the lines
		 * 			-  convert all the lines to UpperCase
		 * 			-  example takes each of the lines one by one and apply the function toString on them as line.toString() 
		 * 			   and returns the Stream:
		 * 			        streamOfLines = streamOfLines.map(String::toString) 
		 * 
		 * 		2. filter( )   [keeps only lines which satisfy the provided condition]  
		 *      	-  can be used to only keep non-empty lines and drop empty lines
		 *      	-  example below removes all the lines from the Stream which do not equal the string "apple" 
		 *                 and returns the Stream:
		 *      			streamOfLines = streamOfLines.filter(x -> x != "apple");
		 *      			 
		 * 		3. collect( )  [collects all the lines into a java.util.List object]
		 * 			-  can be used in the function which will invoke this method to convert Stream<String> of lines to List<String> of lines
		 * 			-  example below collects all the elements of the Stream into a List and returns the List:
		 * 				List<String> listOfLines = streamOfLines.collect(Collectors::toList); 
		 * 
		 * Note: since map and filter return the updated Stream objects, they can chained together as:
		 * 		streamOfLines.map(...).filter(a -> ...).map(...) and so on
		 */
	}
	
	/**
	 * Adjacency between word1 and word2 is defined by:
	 * if the difference between word1 and word2 is of
	 * 	1 char replacement
	 *  1 char addition
	 *  1 char deletion
	 * then 
	 *  word1 and word2 are adjacent
	 * else
	 *  word1 and word2 are not adjacent
	 *  
	 * Note: if word1 is equal to word2, they are not adjacent
	 * 
	 * @param word1 first word
	 * @param word2 second word
	 * @return true if word1 and word2 are adjacent else false
	 */
	
	public static boolean isAdjacent(String word1, String word2) {
		/* Forms of adjacency
		 * 1. Substitution: One letter is substituted for a different.
		 * 2. Addition: There is an extra letter.
		 * 3. Subtaction: There is one less letter.
		 */
		
		int mistakesAllowed = 1;
		int currMistakes = 0;
		
		// Case: equality
		if (word1.equals(word2))
			return true;
		
		// Case: substitution check
		if (word1.length() == word2.length())
			for (int i = 0; i < word1.length(); i++){
				if (word1.charAt(i) != word2.charAt(i)){
						currMistakes++;
				}
			}
		
		// Case: Addition / Subtraction
		int diff = word1.length() - word2.length();
		if ((diff <= mistakesAllowed && diff > 0) || (diff >= mistakesAllowed && diff < 0)){ //check bounds
			String largerWord = (diff > 0) ? word1 : word2;
			String smallerWord = (diff > 0) ? word2 : word1;
			int tempMistakes = 0;
			
			for (int i = 0; i < smallerWord.length(); i++){
				if (smallerWord.charAt(i) != largerWord.charAt(i-tempMistakes)){
					tempMistakes++;
				}	
			}
			currMistakes += tempMistakes;
		}
		
		return (currMistakes < mistakesAllowed) ? true : false;
		
		/* Replaced Addition / Subtraction AI-esk guessing machine to calculate adjacency.
		 * Scrapped due to complexity being unnecessary and likely irrelevant to this project.
		 * 
		int lengthDifference = word1.length() - word2.length();
		if ((lengthDifference <= mistakesAllowed && lengthDifference > 0)
			|| (lengthDifference >= mistakesAllowed*-1 && lengthDifference < 0)){ //if they are within bounds
			String largerWord = (lengthDifference > 0) ? word1 : word2;
			String smallerWord = (lengthDifference > 0) ? word2 : word1;
			
			boolean fixOrdering = false;
			int smallFix = 0, largeFix = 0;
			for (int i = 0; i < smallerWord.length(); i++){
				if (fixOrdering){ //This will make a guess whether or not the word is offset forwards or backwards at the current found error.
					int forwardMistakes = 0, backwardMistakes = 0;
					for (int j = i + 1; j < smallerWord.length() - smallFix - 1; j++){
						if (smallerWord.charAt(j - smallFix - 1) != largerWord.charAt(j - largeFix)){
							forwardMistakes++;
						}
						if (smallerWord.charAt(j - smallFix) != largerWord.charAt(j - largeFix - 1)){
							backwardMistakes++;
						}
					}
					if (forwardMistakes >= backwardMistakes)
						smallFix++;
					else { largeFix++;}
					fixOrdering = false;
				}
				if (smallerWord.charAt(i-smallFix) != largerWord.charAt(i-largeFix)){
					currMistakes++;
					fixOrdering = true;
				}	
			}
		}
		*/
	}
	
}
