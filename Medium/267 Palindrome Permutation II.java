/**
This question is from https://leetcode.com/problems/palindrome-permutation-ii/
Difficulty: medium

Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

Example 1:

Input: "aabb"
Output: ["abba", "baab"]
Example 2:

Input: "abc"
Output: []
*/

// T:O((N/2)!), S:O(N), 3 ms (51.17%)
class Solution {

    HashMap<Character, Integer> map;
    List<String> ans;

    public List<String> generatePalindromes(String s) {
        ans = new LinkedList();
        if(s == null) return ans;

        int len = s.length();
        map = new HashMap();

        char[] arr = s.toCharArray();

        for(char c : arr){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        HashMap<Character, Integer> visited = new HashMap();

        int odd = 0;
        String center = "";

        for(Map.Entry<Character, Integer> entry: map.entrySet()){
            char key = entry.getKey();
            int val = entry.getValue();
            // System.out.println(key+": "+val);
            visited.put(key, 0);
            if(val % 2 == 1){
                odd++;
                center = Character.toString(key);
                map.put(key, (val - 1)/2);
            }else{
                map.put(key, val/2);
            }
        }

        if(odd <= 1){
            StringBuilder sb = new StringBuilder();
            len = len % 2 == 1 ? (len - 1) / 2 : (len / 2);
            // System.out.println("length: "+len);
            backtracking(sb, visited, center, len);
        }

        return ans;
    }


    private void backtracking(StringBuilder sb, HashMap<Character, Integer> visited, String center, int length){
       // System.out.println("current sb: "+sb.toString()+", length: "+ length);
       if(sb.length() == length){
           String result = sb.toString() + center + sb.reverse().toString();
           sb.reverse();
           ans.add(result);
           return;
       }

       for(char c : map.keySet()){
           if(map.get(c) != visited.get(c)){
               visited.put(c, visited.get(c) + 1);
               sb.append(c);
               // System.out.println(c+": "+sb.toString());
               backtracking(sb, visited, center, length);
               sb.deleteCharAt(sb.length() - 1);
               visited.put(c, visited.get(c) - 1);
           }
       }
    }
}