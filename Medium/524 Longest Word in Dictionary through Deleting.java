/*
This question is from https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/
Difficulty: medium

Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

Example 1:
Input:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

Output:
"apple"
Example 2:
Input:
s = "abpcplea", d = ["a","b","c"]

Output:
"a"
Note:
All the strings in the input will only contain lower-case letters.
The size of the dictionary won't exceed 1,000.
The length of all the strings in the input won't exceed 1,000.
*/
// T:O(N * K), K: the length of s, S:O(N), 26 ms
class Solution {
    public String findLongestWord(String s, List<String> d) {
        int max = 0;
        PriorityQueue<String> pq  = new PriorityQueue();
        pq.add("");
        for(String str: d){
            if(subseq(s, str)){
                int len = str.length();
                if(len == max){
                    pq.add(str);
                }else if(len > max){
                    max = len;
                    pq.clear();
                    pq.add(str);
                }
            }
        }

        return pq.peek();
    }

    private boolean subseq(String a, String b){
        int i = 0;
        int j = 0;
        int lenA = a.length();
        int lenB = b.length();
        while(i < lenA){
            if(a.charAt(i) == b.charAt(j)){
                j++;
                if(j == lenB) return true;
            }
            i++;
        }

        return false;
    }
}

// without extra space.
public class Solution {
    public boolean isSubsequence(String x, String y) {
        int j = 0;
        for (int i = 0; i < y.length() && j < x.length(); i++)
            if (x.charAt(j) == y.charAt(i))
                j++;
        return j == x.length();
    }
    public String findLongestWord(String s, List < String > d) {
        String max_str = "";
        for (String str: d) {
            if (isSubsequence(str, s)) {
                if (str.length() > max_str.length() || (str.length() == max_str.length() && str.compareTo(max_str) < 0))
                    max_str = str;
            }
        }
        return max_str;
    }
}