package Daily.m2.d29;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1152 {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			StringTokenizer st =new StringTokenizer(br.readLine());
			int result=0;
			while(st.hasMoreTokens()) {
				st.nextToken();
				result++;
			}
			System.out.println(result);
		} catch (Exception e) {}
	}
}