/*
This question is from https://leetcode.com/problems/longest-palindrome/
Difficulty: easy

Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:

Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.

*/
// T:O(N), S:O(52), 6 ms
class Solution {
    public int longestPalindrome(String s) {

        HashMap<Character, Integer> map = new HashMap();
        int len = s.length();

        for(int i = 0; i < len; i++){
            char c = s.charAt(i);
            int count = map.getOrDefault(c, 0);
            map.put(c, count + 1);
        }

        boolean containsOdd = false;
        int total = 0;

        for(Map.Entry<Character, Integer> entry: map.entrySet()){
            int val = entry.getValue();
            if(val % 2 == 0){
                total += val;
            }else{
                containsOdd = true;
                if(val - 1 > 0) total += (val - 1);
            }
        }

        if(containsOdd) total++;

        return total;
    }
}

// HashMap, T:O(n), M:O(n), 29ms
public class Solution {
    public int longestPalindrome(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int sLen = s.length();
        for(int i = 0; i < sLen ; i++){
            if(!map.containsKey(s.charAt(i))){
                map.put(s.charAt(i),1);
            }else{
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            }
        }

        int result = 0;
        boolean odd = false;
        for(Map.Entry<Character, Integer> e: map.entrySet()){
            if(e.getValue()%2 == 0){
                result = result + e.getValue();
            }else {
                result = result + e.getValue() - 1;
                odd = true;
            }
        }

        if(odd){
            return result + 1;
        }else{
            return result;
        }

    }
}

// fix array, T:O(n), M:(52), 10ms
public class Solution {
    public int longestPalindrome(String s) {
        int[] charArr = new int[52];
        int sLength = s.length();
        for(int i=0; i<sLength; i++){
            char c = s.charAt(i);
            if(c >= 'A' && c <= 'Z'){
                charArr[c - 'A']++;
            }else if(c >= 'a' && c <= 'z'){
                charArr[c - 'a' + 25]++;
            }
        }

        int result = 0;
        boolean odd = false;
        for(int i = 0 ; i< charArr.length; i++){
            if(charArr[i]%2 == 0){
                result = result + charArr[i];
            }else{
                odd = true;
                result = result + charArr[i] - 1;
            }
        }

        if(odd){
            return result + 1;
        }else{
            return result;
        }
    }
}
