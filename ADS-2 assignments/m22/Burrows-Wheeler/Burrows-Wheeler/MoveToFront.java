import java.util.ArrayList;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
public class MoveToFront {
	private static final int R = 256;
	public static void encode() {

		String s = BinaryStdIn.readString();
		char[] charinput = s.toCharArray();
		ArrayList<Integer> charList = new ArrayList<Integer>();
		for (int i = 0; i < R; i++) {
			charList.add(i);
		}
		for (int i = 0; i < charinput.length; i++) {
			int index = charList.indexOf((int) charinput[i]);
			BinaryStdOut.write((char) index, 8);
			int encodeElement = charList.remove(index);
			charList.add(0, encodeElement);
		}
		BinaryStdOut.close();
	}

	public static void decode() {
		String s = BinaryStdIn.readString();
		char[] charinput = s.toCharArray();
		ArrayList<Integer> charList = new ArrayList<Integer>();
		for (int i = 0; i < R; i++) {
			charList.add(i);
		}
		for (int i = 0; i < charinput.length; i++) {
			int encodeElement = charList.remove((int) charinput[i]);
			charList.add(0, encodeElement);
			BinaryStdOut.write((char) encodeElement, 8);
		}
		BinaryStdOut.close();
	}

	public static void main(String[] args) {
		if (args[0].equals("-")) {
			encode();
		} else if (args[0].equals("+")) {
			decode();
		}
	}
}
