package LeetCodeRepo.leetcode; /**
 * LeetCode 30 |Hard|
 * You are given a string, s, and a list of words, words, that are all of the same length.
 * Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
 * For example, given:
 * s: "barfoothefoobarman"
 * words: ["foo", "bar"]
 * You should return the indices: [0,9]. (order does not matter).
 *
 *
 * My hints: sliding window, bbb bb =>  |bb|b -> b|bb|
 * */
import java.util.*;
    // 160ms solution
    public class LeetCode30_SubStringAll {
/*    List<Integer> findSubstring(String s, String[] words) {
        if(words == null || s == null || words.length == 0 || s.length() < words[0].length()) {
            return new ArrayList<Integer>();
        }
        List<Integer> res = new ArrayList<Integer>();
        int wordLen = words[0].length(), arrLen = words.length, left = 0, right = arrLen * wordLen;
        HashMap<String, Integer> dic = new HashMap<String, Integer>(), used = new HashMap<String, Integer>();
        // put words in a map to reduce visit time
        for(String word : words ) {
            dic.put(word, dic.get(word) == null ?  1 : dic.get(word) + 1);
        }
        for(int i = 0; i < s.length() + 1 && right < s.length() + 1; i ++) {
            String tmp;
            //check each frame
            for(int j = left; j < right; j += wordLen) {
                tmp = s.substring(j, j + wordLen);
                if(dic.get(tmp) == null) {
                    break;
                }
                Object count = used.get(tmp);
                if(count == null) {
                    used.put(tmp, 1);
                    if(j + wordLen >= right) {
                        res.add(left);
                    }
                } else if((Integer)count == dic.get(tmp)) {
                    break;
                } else {
                    used.put(tmp, (Integer)count + 1);
                    if(j + wordLen >= right) {
                        res.add(left);
                    }
                }
            }
            left++;
            right++;
            used.clear();
        }
        return res;
    }*/

    // 30ms solution
    // continuing check for every 3 word, if meet the condition, count++. else move pointer, do count-- till condition met again.
    // there are word.length() different combinations: ababab , [ab]  => |ab|ab|ab| and a|ba|ba|b|  because ab|ab|ab| is redundant
    List<Integer> findSubstring(String s, String[] words) {
        int wordLen = words[0].length(), sLen = s.length();
        if(s == null || words == null || sLen < wordLen) {
            return new ArrayList<Integer>();
        }
        List<Integer> res = new ArrayList<Integer>();
        HashMap<String, Integer> dic = new HashMap<String, Integer>(), used = new HashMap<String, Integer>();
        int matchCount = 0, left = 0, arrLen = words.length, winSize = wordLen * arrLen;
        // put words in a map to reduce look up time
        for(String word : words) {
            dic.put(word, dic.getOrDefault(word, 0) + 1);
        }
        // every loop, continue until a mismatch is found
        for(int i = 0; i < wordLen; i++) {
            used.clear();
            matchCount = 0;
            left = i;
            // check every 3 word, move left ptr to remain word used not to exceed limit
            for(int j = wordLen + i; j <= sLen; j += wordLen) {
                String tmp = s.substring(j - wordLen, j);

                if(left + winSize > sLen) {
                    break;
                }
                if(dic.get(tmp) == null) {
                    matchCount = 0;
                    used.clear();
                    left = j;
                    continue;
                }
                if(used.getOrDefault(tmp, 0) < dic.get(tmp)) {
                    matchCount++;

                    used.put(tmp, used.getOrDefault(tmp, 0) + 1);
                } else {
                    //there is a count++ here, but later will do count--, it cancels out each other.
                    used.put(tmp, used.getOrDefault(tmp, 0) + 1);
                    String leftWord = s.substring(left, left + wordLen);
                    while(!leftWord.equals(tmp)) {
                        used.put(leftWord, used.get(leftWord) - 1);
                        left += wordLen;
                        leftWord = s.substring(left, left + wordLen);
                        matchCount --;
                        System.out.println("tmp:" + tmp + " leftword: " + leftWord + " count: " + matchCount + " left:" + left);
                    }
                    left += wordLen;
                    used.put(tmp, used.get(tmp) - 1);

                }
                if(matchCount == arrLen) {
                    res.add(left);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode30_SubStringAll test = new LeetCode30_SubStringAll();
        //List l1 = test.findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"});
        //List l1 = test.findSubstring("lingmindraboofooowingdingbarrwingmonkeypoundcake", new String[]{"fooo","barr","wing","ding","wing"});
        //List l1 = test.findSubstring("oooooo", new String[]{"oo","oo"});
        //List l1 = test.findSubstring("barfoofoobarthefoobarman", new String[]{"bar","foo","the"});
        List l1 = test.findSubstring("barfoothefoobarman", new String[]{"bar","foo"});
        System.out.println(l1.toString());
        //System.out.println(l2.toString());
    }
}

