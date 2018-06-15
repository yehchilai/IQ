/*
This question is from https://leetcode.com/problems/custom-sort-string/description/
Difficulty: medium

S and T are strings composed of lowercase letters. In S, no letter occurs more than once.

S was sorted in some custom order previously. We want to permute the characters of T so that they match the order that S was sorted. More specifically, if x occurs before y in S, then x should occur before y in the returned string.

Return any permutation of T (as a string) that satisfies this property.

Example :
Input:
S = "cba"
T = "abcd"
Output: "cbad"
Explanation:
"a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a".
Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.


Note:

S has length at most 26, and no character is repeated in S.
T has length at most 200.
S and T consist of lowercase letters only.

*/
// T:O(S+T), S:O(1), 4ms
class Solution {

    Map<Character, Integer> order;

    public String customSortString(String S, String T) {
        // characters in T
        int[] counts = new int[26];

        // character array of T
        char[] arrT = T.toCharArray();

        // count characters in T
        for(char c: arrT){
            counts[c - 'a'] += 1;
        }

        // answer string builder
        StringBuilder sb = new StringBuilder();

        // go through S to build the answer string
        for(char c: S.toCharArray()){
            for(int i = 0 ; i < counts[c - 'a']; i++){
                sb.append(c);
            }
            counts[c - 'a'] = 0;
        }

        // attach the rest of characters in the character arrya of T
        for(int i = 0; i < counts.length; i++){
            for(int j = 0; j < counts[i]; j++){
                char c = (char)(i + 'a');
                //System.out.println(i +", "+ c);
                sb.append(c);
            }
        }
        /*
		// Write all remaining characters that don't occur in S.
        // That information is specified by 'count'.
        for (char c = 'a'; c <= 'z'; ++c)
            for (int i = 0; i < count[c - 'a']; ++i)
                ans.append(c);
        */

        return sb.toString();
    }

}


// cannot compile, T:O(S+TlogT), S:O(S)
class Solution {

    Map<Character, Integer> order;

    public String customSortString(String S, String T) {
        // key: char, value: index
        order = new HashMap();
        // String length of S
        int lenS = S.length();
        // go through S string.
        for(int i = 0; i < lenS; i++){
            order.put(S.charAt(i), i);
        }

        // sort string T
        char[] arrT = T.toCharArray();
        Arrays.sort(arrT, new Comparator<Character>(){
            public int compare(Character a, Character b){
                if(order.get(a) < order.get(b)) return -1;
                if(order.get(a) > order.get(b)) return 1;
                return 0;
            }
        });

        return new String(arrT);
    }

}