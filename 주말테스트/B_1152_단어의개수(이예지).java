import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class boj_1152 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int cnt = 0;
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i)==' ' && i == str.length()-1)
				continue;
			else if(str.charAt(i) == ' ' && str.charAt(i+1)!=' ')
				cnt++;
			else if(i==0 && str.charAt(i)!=' ')
				cnt++;
		}
		System.out.println(cnt);

	}
}
