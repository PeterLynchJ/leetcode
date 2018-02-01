/**
 * 32. Longest Valid Parentheses
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 *
 *  keyword is substring, meaning ())() has () as longest valid parentheses.
 *  Planning to push on '(' and pop on ')', length is max of ')' index - the index left on the stack
 *
 *  Another clear o(1) space solution is scan twice from left to right and then right to left. because one of the scan contains max length
 *  DP slips my mind as well:
 *   ( ) (    ) ( (  )  )
 *   0 2 0   4  0 0 2   8    how dp array is working
 *     |_+_| |__+__|_+_|
 */
package LeetCodeRepo.leetcode.LeetCode;
import java.util.*;

public class LeetCode32_LongestValidParentheses {
    int longestValidParentheses(String s) {
        // edge cases
        if(s == null || s.length() < 2) {
            return 0;
        }
        /*
        //scan solution
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right >= left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return maxlength;*/
        /*
        //stack
        int maxLength = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
            } else if(s.charAt(i) == ')') {
                stack.pop();
                if(stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLength = Math.max(i - stack.peek(), maxLength);
                }
            }
        }
        return maxLength;*/

        //dp
        int dp[] = new int[s.length()];
        int maxLength = 0;
        //starting from 1 as 0 must not be a match
        for(int i = 1; i < s.length(); i++) {
            if(s.charAt(i) == ')') {
                //dealing with () case
                if(s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                }
                //dealing with (() ) case, make sure the mirror of ) is not out of bound, plus it's a matching (
                else if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    //need to add |)| in  ( |)| (())
                    dp[i] = dp[i-1] + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxLength = Math.max(maxLength, dp[i]);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        LeetCode32_LongestValidParentheses test = new LeetCode32_LongestValidParentheses();
        System.out.println(test.longestValidParentheses("") == 0);
        System.out.println(test.longestValidParentheses(null) == 0);
        System.out.println(test.longestValidParentheses("((()(())") == 6);
        System.out.println(test.longestValidParentheses("()(()") == 2);
        System.out.println(test.longestValidParentheses("((())") == 4);
        System.out.println(test.longestValidParentheses("((()))") == 6);
        System.out.println(test.longestValidParentheses("((())((((())))") == 8);
        System.out.println(test.longestValidParentheses(")()") == 2);
    }
}
