/**
 *  LeetCode 33  Search in Rotated Sorted Array
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 *
 */
package LeetCodeRepo.leetcode.LeetCode;

public class LeetCode33_SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return -1;
        }
        if(nums.length < 25) {
            for(int i = 0; i < nums.length; i++) {
                if(nums[i] == target) {
                    return i;
                }
            }
        } else {
            if(nums[0] > nums[1] && nums[1] > nums[2]) {
                return bSearchReverse(nums, 0, nums.length - 1, target);
            }
            else {
                int gap = findGap(nums, 0, nums.length - 1);
                if(gap == 0 && nums[nums.length - 1] > target) {
                    return bSearch(nums, 0, nums.length - 1, target);
                } else if(gap == 0) {
                    return -1;
                } else if(nums[gap] < target) {
                    return -1;
                } else {
                    int a = bSearch(nums, 0, gap, target);
                    int b = bSearch(nums, gap + 1, nums.length - 1, target);
                    if(a != -1) {
                        return a;
                    } if(b != -1){
                        return b;
                    }
                    return -1;
                }
            }
        }

        return -1;
    }
    int findGap(int[] nums, int start, int end) {
        int mid = (start + end) / 2;

        if(nums[mid] > nums[mid + 1] || mid == start || mid == end) {
            return mid;
        }
        if(nums[mid] > nums[nums.length - 1]) {
            return findGap(nums, mid + 1, end);
        } else {
            return findGap(nums, start, mid);
        }
    }
    int bSearchReverse(int[] nums, int start, int end, int target) {
        int mid = (start + end) / 2;
        if(nums[mid] == target) {
            return mid;
        }
        if(mid == start || mid == end) {
            return -1;
        }
        if(nums[mid] > target) {
            return bSearchReverse(nums, mid + 1, end, target);
        } else {
            return bSearchReverse(nums, start, mid, target);
        }
    }
    int bSearch(int[] nums, int start, int end, int target) {
        int mid = (start + end) / 2;
        if(nums[mid] == target) {
            return mid;
        }
        if(mid == start || mid == end) {
            return -1;
        }
        if(nums[mid] < target) {
            return bSearch(nums, mid + 1, end, target);
        } else {
            return bSearch(nums, start, mid, target);
        }
    }
    public static void main(String[] args) {
        LeetCode33_SearchInRotatedSortedArray test = new LeetCode33_SearchInRotatedSortedArray();
        int[] testArray = new int[]{188,193,194,200,201,208,212,217,221,228,232,233,234,237,239,244,249,250,253,258,263,264,267,271,272,277,279,282,284,287,289,293,295,299,0,3,6,11,13,18,20,21,24,25,26,29,32,36,41,42,47,50,53,54,56,58,60,61,63,67,72,73,75,77,78,79,81,83,84,87,90,92,94,95,110,112,119,120,123,130,135,138,146,149,159,165,166,170,171,173,174,177,183};
        int target = 244;
        int[] tt = new int[]{6,9,15,19,21,26,33,35,37,38,39,46,49,54,65,71,74,77,79,82,83,88,92,93,94,97,104,108,114,115,117,122,123,127,128,129,134,137,141,142,144,147,150,154,160,163,166,169,172,173,177,180,183,184,188,198,203,208,210,214,218,220,223,224,233,236,241,243,253,256,257,262,263};
        System.out.println(test.search(testArray, target));
    }
}
