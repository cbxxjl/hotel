import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author : cbxjl
 * @date : 2024/3/4 13:49
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()) {
            String line = in.nextLine();
            String[] strs = line.split(",");
            //Arrays.sort(strs, Collections.reverseOrder());
            Arrays.sort(strs);
            for(int i = 0; i < strs.length; i++) {
                System.out.print(strs[i]);
                if(i != strs.length - 1) {
                    System.out.print(",");
                }
            }
            System.out.println();
        }
    }
}
