/*
This question is from https://leetcode.com/problems/alien-dictionary/
Difficulty: hard

There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:

Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"
Example 2:

Input:
[
  "z",
  "x"
]

Output: "zx"
Example 3:

Input:
[
  "z",
  "x",
  "z"
]

Output: ""

Explanation: The order is invalid, so return "".
Note:

You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
*/
// (https://en.wikipedia.org/wiki/Topological_sorting)
// T:O(N) , N: all characters in the words, S:O(26), 4 ms
class Solution {
    public String alienOrder(String[] words) {
        // char : chars after key char
        HashMap<Character, Set<Character>> map = new HashMap();
        // char : number of chars before this key char
        HashMap<Character, Integer> degree = new HashMap();
        String ans = "";
        if(words == null || words.length == 0) return ans;

        for(String word: words){
            for(char c: word.toCharArray()) degree.put(c, 0);
        }

        // generate graph
        for(int i = 0 ; i < words.length - 1; i++){
            char[] w1 = words[i].toCharArray();
            char[] w2 = words[i + 1].toCharArray();

            int len = Math.min(w1.length, w2.length);

            for(int j = 0; j < len; j++){
                char c1 = w1[j];
                char c2 = w2[j];

                if(c1 != c2){
                    Set<Character> set = map.getOrDefault(c1, new HashSet<Character>());
                    if(!set.contains(c2)){
                        set.add(c2);
                        // update the number of chars before c2
                        degree.put(c2, degree.get(c2) + 1);
                    }
                    // update c1
                    map.put(c1, set);
                    // Only check the first difference
                    break;
                }
            }
        }

        // generate q
        LinkedList<Character> q = new LinkedList();
        for(char c: degree.keySet()){
            if(degree.get(c) == 0) q.add(c);
        }

        while(q.size() > 0){
            char current = q.poll();
            ans += current;

            if(map.containsKey(current)){
                for(char next: map.get(current)){
                    degree.put(next, degree.get(next) - 1);
                    if(degree.get(next) == 0) q.add(next);

                }
            }

        }
        // System.out.println(ans);
        if(ans.length() != degree.size()) return "";

        return ans;
    }
}

// wrong anwser
class Solution {
    public String alienOrder(String[] words) {
        StringBuilder sb = new StringBuilder();
        HashSet<Character> visited = new HashSet();
        HashSet<String> valid = new HashSet();
        String last = null;
        valid.add(last);
        int index = 0;
        while(true){
            boolean goNext = false;

            for(int i = 0; i < words.length; i++){
                if(index < words[i].length()){
                    goNext = true;
                    char c = words[i].charAt(index);
                    // System.out.println(c);
                    String prefix = words[i].substring(0, index + 1);
                    if(valid.contains(prefix) && !prefix.equals(last) ){
                        // System.out.println("invalid: " + prefix +","+last);
                        return "";
                    }else{
                        valid.add(prefix);
                        last = prefix;
                    }

                    if(!visited.contains(c)){
                        visited.add(c);
                        sb.append(c);
                    }
                }
            }
            // System.out.println("=====");
            if(!goNext) break;
            index++;
        }

        return sb.toString();
    }
}