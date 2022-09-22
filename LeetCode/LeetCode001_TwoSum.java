package LeetCodeRepo.leetcode.LeetCode;

import java.util.Arrays;
import java.util.HashMap;

/** Easy
 * Given an array of integers nums and an integer target, return indices of the two numbers that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 * ...
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * ...
 * Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
 */
public class LeetCode001_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int res[] = new int[2];
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hm.get(target - nums[i]) != null) {
                res = new int[]{hm.get(target - nums[i]), i};
                return res;
            }
            hm.put(nums[i], i);
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode001_TwoSum driver = new LeetCode001_TwoSum();
        System.out.println(Arrays.toString(driver.twoSum(new int[]{3, 2, 4}, 6)));
    }
}
