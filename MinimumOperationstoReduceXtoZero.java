import java.util.*;
public class MinimumOperationstoReduceXtoZero {
    public int minOperations(int[] nums, int x) {
        int totalSum = Arrays.stream(nums).sum();
        int target = totalSum - x;
        if (target < 0) return -1;
        if (target == 0) return nums.length;

        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, -1);
        int currentSum = 0;
        int maxLength = -1;

        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            if (prefixSumMap.containsKey(currentSum - target)) {
                maxLength = Math.max(maxLength, i - prefixSumMap.get(currentSum - target));
            }
            prefixSumMap.putIfAbsent(currentSum, i);
        }

        return maxLength == -1 ? -1 : nums.length - maxLength;
    }
}