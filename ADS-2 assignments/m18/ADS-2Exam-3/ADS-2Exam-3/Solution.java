import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

public class Solution {

	// Don't modify this method.
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String cases = scan.nextLine();

		switch (cases) {
		case "loadDictionary":
			// input000.txt and output000.txt
			BinarySearchST<String, Integer> hash = loadDictionary("/Files/t9.csv");
			while (scan.hasNextLine()) {
				String key = scan.nextLine();
				System.out.println(hash.get(key));
			}
			break;

		case "getAllPrefixes":
			// input001.txt and output001.txt
			T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
			while (scan.hasNextLine()) {
				String prefix = scan.nextLine();
				for (String each : t9.getAllWords(prefix)) {
					System.out.println(each);
				}
			}
			break;

		case "potentialWords":
			// input002.txt and output002.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			int count = 0;
			while (scan.hasNextLine()) {
				String t9Signature = scan.nextLine();
				for (String each : t9.potentialWords(t9Signature)) {
					count++;
					System.out.println(each);
				}
			}
			if (count == 0) {
				System.out.println("No valid words found.");
			}
			break;

		case "topK":
			// input003.txt and output003.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			Bag<String> bag = new Bag<String>();
			int k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				bag.add(line);
			}
			for (String each : t9.getSuggestions(bag, k)) {
				System.out.println(each);
			}

			break;

		case "t9Signature":
			// input004.txt and output004.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			bag = new Bag<String>();
			k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				for (String each : t9.t9(line, k)) {
					System.out.println(each);
				}
			}
			break;

		default:
			break;

		}
	}

	// Don't modify this method.
	public static String[] toReadFile(String file) {
		In in = new In(file);
		return in.readAllStrings();
	}

	public static BinarySearchST<String, Integer> loadDictionary(String file) {
		BinarySearchST<String, Integer>  st = new BinarySearchST<String, Integer>();
		// your code goes here
		for (String word : toReadFile(file)) {
			word = word.toLowerCase();
			if (st.contains(word)) {
				st.put(word, st.get(word) + 1);
			} else {
				st.put(word, 1);
			}
		}
		return st;
	}

}

class T9 {
	private TST<Integer> tst;
	public T9(BinarySearchST<String, Integer> st) {
		// your code goes here
		tst = new TST<Integer>();
		for (String word : st.keys()) {
			tst.put(word, st.get(word));
		}

	}

	// get all the prefixes that match with given prefix.
	public Iterable<String> getAllWords(String prefix) {
		// your code goes here
		return tst.keysWithPrefix(prefix);
	}

	public Iterable<String> potentialWords(String t9Signature) {
		// your code goes here
		ArrayList<String> arraylist = new ArrayList<String>();
		//HashMap<String, ArrayList<String>> charMap =
		     new HashMap<String, ArrayList<String>>();
		for (String word : tst.keys()) {
			String[] characterArray = word.split("");
			String num = "";
			for(String c:characterArray) {
				if(c.equals("a")||c.equals("b")||c.equals("c")) {
					num = num + "2";
				}
				if(c.equals("d")||c.equals("e")||c.equals("f")) {
					num = num + "3";
				}
				if(c.equals("g")||c.equals("h")||c.equals("i")) {
					num = num + "4";
				}
				if(c.equals("j")||c.equals("k")||c.equals("l")) {
					num = num + "5";
				}
				if(c.equals("m")||c.equals("n")||c.equals("o")) {
					num = num + "6";
				}
				if(c.equals("p")||c.equals("q")||c.equals("r")||c.equals("s")) {
					num = num + "7";
				}
				if(c.equals("t")||c.equals("u")||c.equals("v")) {
					num = num + "8";
				}
				if(c.equals("w")||c.equals("x")||c.equals("y")||c.equals("z")) {
					num = num + "9";
				}

			}
			if(num.equals(t9Signature)) {
				arraylist.add(word);
			}
		}
		// HashMap<String, ArrayList<String>> charMap =
		//     new HashMap<String, ArrayList<String>>();
		// for (String word : tst.keys()) {
		// 	String[] characterArray = word.split("");
		// 	for(String c:characterArray) {

		// 	}
		// }
		return arraylist;
	}

	// return all possibilities(words), find top k with highest frequency.
	public Iterable<String> getSuggestions(Iterable<String> words, int k) {
		// your code goes here
		HashMap<Integer, String> wordsMap = new HashMap<Integer, String>();
		for (String word : words) {
			for (String preWord : getAllWords(word)) {
				int freqValue = tst.get(preWord);
				if (wordsMap.containsKey(freqValue)) {
					continue;
				} else {
					wordsMap.put(freqValue, preWord);
				}
			}
		}
		Object[] keys = wordsMap.keySet().toArray();
		Arrays.sort(keys);
		ArrayList<String> arraylist = new ArrayList<String>();
		for (int i = keys.length - 1; i > keys.length - 1 - k; i--) {
			arraylist.add(wordsMap.get(keys[i]));
		}
		Collections.sort(arraylist);
		return arraylist;
	}

	// final output
	// Don't modify this method.
	public Iterable<String> t9(String t9Signature, int k) {
		return getSuggestions(potentialWords(t9Signature), k);
	}
}
