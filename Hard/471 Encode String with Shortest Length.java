/*
This question is from https://leetcode.com/problems/encode-string-with-shortest-length/
DIfficulty: hard

Given a non-empty string, encode the string such that its encoded length is the shortest.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.

Note:

k will be a positive integer and encoded string will not be empty or have extra space.
You may assume that the input string contains only lowercase English letters. The string's length is at most 160.
If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return any of them is fine.


Example 1:

Input: "aaa"
Output: "aaa"
Explanation: There is no way to encode it such that it is shorter than the input string, so we do not encode it.


Example 2:

Input: "aaaaa"
Output: "5[a]"
Explanation: "5[a]" is shorter than "aaaaa" by 1 character.


Example 3:

Input: "aaaaaaaaaa"
Output: "10[a]"
Explanation: "a9[a]" or "9[a]a" are also valid solutions, both of them have the same length = 5, which is the same as "10[a]".


Example 4:

Input: "aabcaabcd"
Output: "2[aabc]d"
Explanation: "aabc" occurs twice, so one answer can be "2[aabc]d".


Example 5:

Input: "abbbabbbcabbbabbbc"
Output: "2[2[abbb]c]"
Explanation: "abbbabbbc" occurs twice, but "abbbabbbc" can also be encoded to "2[abbb]c", so one answer can be "2[2[abbb]c]".

*/
// T:O(N^3), S:O(N^2), 196 ms
class Solution {
    public String encode(String s) {
        int N = s.length();
        String[][] dp = new String[N][N];

        for(int len = 0; len < N; len++){
            for(int i = 0; i < N - len; i++){
                int j = i + len;
                String subStr = s.substring(i, j + 1);
                dp[i][j] = subStr;
                if(len < 4) continue;

                for(int k = i; k < j ; k++){
                    if((dp[i][k] + dp[k+1][j]).length() < dp[i][j].length()){
                        dp[i][j] = dp[i][k] + dp[k+1][j];
                    }
                }

                int M = subStr.length();
                for(int index = 0; index < M; index++){
                    String duplicate = subStr.substring(0, index + 1);
                    if(duplicate != null
                       && M % duplicate.length() == 0
                       && subStr.replace(duplicate,"").length() == 0){

                        String encode = M / duplicate.length() + "["+dp[i][i+index]+"]";
                        if(encode.length() < dp[i][j].length()){
                            dp[i][j] = encode;
                        }
                    }
                }
            }
        }
        return dp[0][N - 1];
    }
}