/*
This question is from https://leetcode.com/problems/shortest-word-distance/
Difficulty: easy

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “coding”, word2 = “practice”
Output: 3
Input: word1 = "makes", word2 = "coding"
Output: 1
Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.


*/
// T:O(N), S:O(1), 2 ms
class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {

        if(words.length == 0) return -1;
        int index1 = -1;
        int index2 = -1;
        int min = words.length;

        for(int i = 0; i < words.length; i++){
            if(words[i].equals(word1)) index1 = i;
            if(words[i].equals(word2)) index2 = i;

            if(index1 != -1 && index2 != -1){
                min = Math.min(min, Math.abs(index1 - index2));
            }
        }

        return min;
    }
}


// T:O(N), S:O(N), 6 ms
class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {

        if(words.length == 0) return -1;

        HashMap<String, List<Integer>> map = new HashMap();

        for(int i = 0; i < words.length; i++){
            List<Integer> list = map.getOrDefault(words[i], new LinkedList());
            list.add(i);
            map.put(words[i], list);
        }

        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);

        int min = Integer.MAX_VALUE;

        for(int n1: list1){
            for(int n2: list2){
                min = Math.min(min, Math.abs(n1 - n2));
            }
        }

        return min;
    }
}