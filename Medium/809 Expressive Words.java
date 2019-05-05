/*
This question is from https://leetcode.com/problems/expressive-words/
Difficulty: medium

Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  In these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".

For some given string S, a query word is stretchy if it can be made to be equal to S by any number of applications of the following extension operation: choose a group consisting of characters c, and add some number of characters c to the group so that the size of the group is 3 or more.

For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has size less than 3.  Also, we could do another extension like "ll" -> "lllll" to get "helllllooo".  If S = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = S.

Given a list of query words, return the number of words that are stretchy.



Example:
Input:
S = "heeellooo"
words = ["hello", "hi", "helo"]
Output: 1
Explanation:
We can extend "e" and "o" in the word "hello" to get "heeellooo".
We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.


Notes:

0 <= len(S) <= 100.
0 <= len(words) <= 100.
0 <= len(words[i]) <= 100.
S and all words in words consist only of lowercase letters

*/
// T:O(N*K), N: length of wourds, K: largest String, S:O(N), 3ms
class Solution {
    public int expressiveWords(String S, String[] words) {
        if(words == null || words.length == 0) return 0;
        int result = 0;
        int len = S.length();
        EncodeStr target = new EncodeStr(S);

        for(int i = 0; i < words.length; i++){
            EncodeStr source = new EncodeStr(words[i]);
            if(isExtend(source, target)){
                // System.out.println(words[i]+"\n"+S);
                result++;
            }
        }

        return result;
    }

    public boolean isExtend(EncodeStr source, EncodeStr target){
        if(!source.key.equals(target.key)) return false;

        for(int i = 0; i < source.count.size(); i++){
            int sourceC = source.count.get(i);
            int targetC = target.count.get(i);

            if(sourceC > targetC || (targetC < 3 && targetC != sourceC)) return false;
        }

        return true;
    }
}

class EncodeStr{
    String key;
    List<Integer> count;

    EncodeStr(String s){
        StringBuilder sb = new StringBuilder();
        char[] sArr = s.toCharArray();
        count = new LinkedList();
        int prev = -1;
        for(int i = 0; i < sArr.length; i++){
            if(i+1 == sArr.length || sArr[i] != sArr[i+1]){
                sb.append(sArr[i]);
                count.add(i - prev);
                prev = i;
            }
        }

        key = sb.toString();
    }
}