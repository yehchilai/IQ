/*
This question is from https://leetcode.com/problems/longest-palindromic-substring/description/
Difficulty: medium

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"


*/

// https://articles.leetcode.com/longest-palindromic-substring-part-i/
// Dynamic programming: T: O(N^2), S: O(N^2), 80ms
class Solution {
    public String longestPalindrome(String s) {
        // begining index of substring
        int index = 0;
        // substring length
        int maxLength = 0;
        // character array
        char[] sChar = s.toCharArray();
        // dynamic programming table
        boolean[][] table = new boolean[sChar.length][sChar.length];
        //System.out.println("Table: "+table.length);
        // fill true for singal charactor
        for(int i = 0; i < sChar.length; i++){
            table[i][i] = true;
            index = i;
            maxLength = 1;
        }

        // check if two adjacent characters are palindrome
        for(int i = 0; i < sChar.length - 1; i++){
            //System.out.print(i + ", ");
            if(sChar[i] == sChar[i+1]){
                table[i][i+1]=true;
                index = i;
                maxLength = 2;
            }
        }
        //System.out.println();

        // check other number of adjacent characters
        for(int len = 3; len <= sChar.length; len++){
            for(int i = 0; i < sChar.length - len + 1; i++){
                int j = i + len - 1;
                if(sChar[i] == sChar[j] && table[i+1][j-1]){
                    table[i][j] = true;
                    index = i;
                    maxLength = len;
                }
            }
        }

        if(s.length() == 2 && maxLength == 2){
            return s;
        }
        return s.substring(index, index+maxLength);
    }
}


// T: O(N^2), S:O(1)
class Solution{
	string expandAroundCenter(string s, int c1, int c2) {
	  int l = c1, r = c2;
	  int n = s.length();
	  while (l >= 0 && r <= n-1 && s[l] == s[r]) {
	    l--;
	    r++;
	  }
	  return s.substr(l+1, r-l-1);
	}

	string longestPalindromeSimple(string s) {
	  int n = s.length();
	  if (n == 0) return "";
	  string longest = s.substr(0, 1);  // a single char itself is a palindrome
	  for (int i = 0; i < n-1; i++) {
	    string p1 = expandAroundCenter(s, i, i);
	    if (p1.length() > longest.length())
	      longest = p1;

	    string p2 = expandAroundCenter(s, i, i+1);
	    if (p2.length() > longest.length())
	      longest = p2;
	  }
	  return longest;
}
}

// Manacher’s algorithm, T: O(N), S: O(N)
// Transform S into T.
// For example, S = "abba", T = "^#a#b#b#a#$".
// ^ and $ signs are sentinels appended to each end to avoid bounds checking
class Solution{
	string preProcess(string s) {
	  int n = s.length();
	  if (n == 0) return "^$";
	  string ret = "^";
	  for (int i = 0; i < n; i++)
	    ret += "#" + s.substr(i, 1);

	  ret += "#$";
	  return ret;
	}

	string longestPalindrome(string s) {
	  string T = preProcess(s);
	  int n = T.length();
	  int *P = new int[n];
	  int C = 0, R = 0;
	  for (int i = 1; i < n-1; i++) {
	    int i_mirror = 2*C-i; // equals to i' = C - (i-C)

	    P[i] = (R > i) ? min(R-i, P[i_mirror]) : 0;

	    // Attempt to expand palindrome centered at i
	    while (T[i + 1 + P[i]] == T[i - 1 - P[i]])
	      P[i]++;

	    // If palindrome centered at i expand past R,
	    // adjust center based on expanded palindrome.
	    if (i + P[i] > R) {
	      C = i;
	      R = i + P[i];
	    }
	  }

	  // Find the maximum element in P.
	  int maxLen = 0;
	  int centerIndex = 0;
	  for (int i = 1; i < n-1; i++) {
	    if (P[i] > maxLen) {
	      maxLen = P[i];
	      centerIndex = i;
	    }
	  }
	  delete[] P;

	  return s.substr((centerIndex - 1 - maxLen)/2, maxLen);
	}
}
