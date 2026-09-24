import java.util.*;
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicate fixed elements
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            int left = i + 1;
            int right = nums.length - 1; // Fixed: start at last element
            
            while (left < right) { // Fixed: condition should be left < right
                int sum = nums[i] + nums[left] + nums[right];
                
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // Skip duplicates for left and right pointers
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return result;
    }
}
///There are three main logical bugs in this implementation: 
// Incorrect Initial Right Pointer (right initial value):
// Issue: int right = nums.length - 2; starts the right pointer at the second-to-last element, 
// completely ignoring the last element (nums.length - 1).
// Fix: Change it to int right = nums.length - 1;.
// Inverted Loop Condition (while (left > right)):
// Issue: The condition while (left > right) will evaluate to false immediately because left starts at i + 1 and right starts at the end of the array (so left < right). The inner search loop never executes.
// Fix: Change it to while (left < right).
// Incomplete Duplicate Handling:
// Issue: Inside the if (sum == 0) block, the inner while loops skip adjacent duplicate values, but they increment/decrement left and right while comparing. After these loops finish, your extra left++; and right--; step past one element too many, which can skip valid unique triplets.
// Fix: Adjust the pointer movements so you check for duplicate values smoothly.

        
    