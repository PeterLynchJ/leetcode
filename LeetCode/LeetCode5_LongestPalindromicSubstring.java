package LeetCodeRepo.leetcode.LeetCode;
/** Medium
* Given a string s, return the longest palindromic substring in s.
* Example 1:
*  Input: s = "babad"
*  Output: "bab"
* Explanation: "aba" is also a valid answer.
* Example 2:
*  Input: s = "cbbd"
*  Output: "bb"
*
* Hint: expand from middle
**/
public class LeetCode5_LongestPalindromicSubstring {
    private int start = 0, end = 0, maxLen = 1;

    enum Direction {
        left,
        right,
        mid
    }

    public String longestPalindrome(String s) {
        midExpand(s, s.length() / 2, Direction.mid);
        return s.substring(start, end + 1);
    }

    public void midExpand(String s, int mid, Direction d) {
        int left = mid - 1, right = mid + 1, pl = left, pr = right;
        // xxaaabcxx
        while (left > -1 && s.charAt(left) == s.charAt(mid)) {
            left --;
            pl --;
        }
        while (right < s.length() && s.charAt(right) == s.charAt(mid)) {
            right ++;
            pr ++;
        }
        while (left > -1 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left --;
            right ++;
        }
        // (right - 1) - (left + 1) + 1 = right - left - 1
        if (right - left - 1 > maxLen) {
            start = left + 1;
            end = right - 1;
            maxLen = end - start + 1;
        }

        // accaadef
        // if substring len greater than maxLen, it's worth another expand
        if ((pl + 1) * 2 > maxLen && (d == Direction.mid || d == Direction.left)) {
            midExpand(s, pl, Direction.left);
        }
        if ((s.length() - pr) * 2 > maxLen && (d == Direction.mid || d == Direction.right)) {
            midExpand(s, pr, Direction.right);
        }

    }

    public static void main(String[] args) {
        String testCase = "jglknendplocymmvwtoxvebkekzfdhykknufqdkntnqvgfbahsljkobhbxkvyictzkqjqydczuxjkgecdyhixdttxfqmgksrkyvopwprsgoszftuhawflzjyuyrujrxluhzjvbflxgcovilthvuihzttzithnsqbdxtafxrfrblulsakrahulwthhbjcslceewxfxtavljpimaqqlcbrdgtgjryjytgxljxtravwdlnrrauxplempnbfeusgtqzjtzshwieutxdytlrrqvyemlyzolhbkzhyfyttevqnfvmpqjngcnazmaagwihxrhmcibyfkccyrqwnzlzqeuenhwlzhbxqxerfifzncimwqsfatudjihtumrtjtggzleovihifxufvwqeimbxvzlxwcsknksogsbwwdlwulnetdysvsfkonggeedtshxqkgbhoscjgpiel";
        LeetCode5_LongestPalindromicSubstring driver = new LeetCode5_LongestPalindromicSubstring();
        System.out.println(driver.longestPalindrome(testCase));
    }
}
