import java.util.Scanner;
import java.util.Arrays;
public final class Solution {
	private Solution() {

	}
	public static void main(final String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		String[] strings = new String[n];
		for(int i = 0; i < n;i++) {
			strings[i] = sc.nextLine();
		}
		LSD lsdobj = new LSD();
		lsdobj.sort(strings, strings[0].length());
		System.out.println(Arrays.toString(strings));
	}
}