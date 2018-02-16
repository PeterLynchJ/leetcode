package LeetCodeRepo.leetcode.LeetCode;

import java.util.*;

public class LeetCode40_CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length < 1) {
            return res;
        }
        List<Integer> tmp = new ArrayList<>();
        Arrays.sort(candidates);
        findCombination(candidates, target, res, tmp, 0);
        return res;
    }

    void findCombination(int[] candidates, int target, List<List<Integer>> res, List<Integer> tmp, int ptr) {
        // target < 0 means no match
        if (target < 0) {
            return;
        }
        // target = 0 means a match
        if (target == 0) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        // looping through the candidates array
        for (int i = ptr; i < candidates.length && candidates[i] <= target; i++) {
            tmp.add(candidates[i]);
            findCombination(candidates, target - candidates[i], res, tmp, i + 1);
            tmp.remove(tmp.size() - 1);
            // check if next spot is the same as current, eg. 1 1 2 5 => the 2nd 1 will result in another 1, 2, 5 thus we want to skip
            while (i + 1 < candidates.length && candidates[i] == candidates[i + 1]) {
                i++;
            }
        }
    }

    public static void main(String[] args) {
        LeetCode40_CombinationSum2 test = new LeetCode40_CombinationSum2();
        List<List<Integer>> res = test.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8);
        for(List<Integer> l : res) {
            System.out.print("[");
            for(Integer i : l) {
                System.out.print(i + ",");
            }
            System.out.println("]");
        }
    }
}
