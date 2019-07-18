/*
This question is from https://leetcode.com/problems/implement-strstr/
DIfficulty: easy

Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
*/

// O(m+n) KMP pattern matching
// https://www.youtube.com/watch?v=GTJr8OvyEVQ
class Solution {
    public int strStr(String haystack, String needle) {
        if(haystack == null || needle == null || needle.length() > haystack.length()) return -1;

        int[] parr = kmpPreprocess(needle);
        int i = 0, j = 0;
        while(i < haystack.length() && j < needle.length()) {
            if(haystack.charAt(i) == needle.charAt(j)) {
                i++; j++;
            } else if(j > 0) {
                j = parr[j - 1];
            } else {
                i++;
            }
        }
        return j == needle.length() ? i - j : -1;
    }

    private int[] kmpPreprocess(String pattern) {
        int i = 1, j = 0;
        int[] res = new int[pattern.length()];
        while(i < pattern.length()) {
            if(pattern.charAt(i) == pattern.charAt(j)) {
                res[i] = j+1;
                i++; j++;
            } else if (j > 0) {
                j = res[j-1];
            } else {
                res[i] = 0;
                i++;
            }
        }
        return res;
    }
}

// T:O(M*N), S:O(1), 9 ms (13%)
class Solution {
    public int strStr(String haystack, String needle) {
        if(needle == null || needle.length() == 0) return 0;

        int len = haystack.length();
        int lenNeedle = needle.length();

        for(int i = 0; i <= len - lenNeedle;i++){
            char c = haystack.charAt(i);

            if(c == needle.charAt(0)) {
                String str = haystack.substring(i, i + lenNeedle);
                System.out.println(haystack + ": " + str);
                if(str.equals(needle)) return i;
            }
        }

        return -1;
    }

}

// (3 ms/ 33.37%) from https://leetcode.com/discuss/46047/elegant-java-solution
public class Solution {
    public int strStr(String haystack, String needle) {
        for(int i = 0 ; ; i++){
        	for(int j = 0; ; j++){
        		if(j >= needle.length()) return i;
        		if((i+j) >= haystack.length()) return -1;
        		if(haystack.charAt(i+j) != needle.charAt(j)) break;
        	}
        }
    }
}

// wrong answer
public class Solution {
    public int strStr(String haystack, String needle) {
        if(haystack.length() < needle.length()) return -1;
        if(needle.length() == 0) return 0;

        int i = 0;
        int j = 0;
        int index = -1;
        while(i < haystack.length()){
        	if(haystack.charAt(i) == needle.charAt(j)){
        		if(j == 0) index = i;
        		j++;
        		if(j >= needle.length()) return index;
        	}else{
        		j = 0;
        		index = -1;
        	}

        	i++;
        }
        return index;
    }
}