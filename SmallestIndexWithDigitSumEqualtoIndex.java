import java.util.*;
public class SmallestIndexWithDigitSumEqualtoIndex {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < nums.length; i++){
            nums[i] = sc.nextInt();
        }
        int result = -1;
        for (int i = 0; i < nums.length; i++){
            int digitSum = 0;
            String str = String.valueOf(nums[i]);
            for (char c : str.toCharArray()){
                digitSum += c - '0';
            }
            if (digitSum == i){
                result = i;
                break;
            }
        }
        System.out.println(result);
        sc.close();
    }
}
