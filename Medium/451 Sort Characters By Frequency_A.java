/*
This question is from https://leetcode.com/problems/sort-characters-by-frequency/
Difficulty: medium

Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.

*/
// HashMap + PriorityQueue, T:O(N + logN), M:O(N), 46ms
public class Solution {
    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int strLen = s.length();
        for(int i = 0; i < strLen; i++){
            if(!map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), 1);
            }else{
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            }
        }

        PriorityQueue<StrNum> pq = new PriorityQueue<StrNum>(10, new StrNumComparator());

        for(Map.Entry<Character, Integer> e : map.entrySet()){
            StrNum sn = new StrNum(e.getKey(), e.getValue());
            pq.add(sn);
        }

        StringBuilder sb = new StringBuilder();
        while(pq.size() > 0){
            StrNum tmpSn = pq.poll();
            for(int i = 0; i < tmpSn.num; i++){
                sb.append(tmpSn.c);
            }
        }

        return sb.toString();
    }

    public class StrNum{
        public char c;
        public int num;
        public StrNum(char c, int num){
            this.c = c;
            this.num = num;
        }
    }

    public class StrNumComparator implements Comparator<StrNum>{
        @Override
        public int compare(StrNum a, StrNum b){
            if(a.num < b.num) return 1;
            if(a.num > b.num) return -1;
            return 0;
        }
    }
}
