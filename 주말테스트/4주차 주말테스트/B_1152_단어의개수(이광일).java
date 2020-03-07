package algo0229;

import java.util.Scanner;

public class b_1152 {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);

        String str = sc.nextLine();

        String[] ans = str.split(" ");

        int i = 0;

        for(int k=0;k<ans.length;k++){
            if(ans[k].equals(""))
                continue;
            i++;
        }
        System.out.println(i);
    }
}