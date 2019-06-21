/*
This question is from https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
Difficulty: hard

Given a string, find the length of the longest substring T that contains at most k distinct characters.

Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.
Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: T is "aa" which its length is 2.
*/
// T:O(N), S:O(N), 18 ms
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // char : index
        LinkedHashMap<Character, Integer> map = new LinkedHashMap();

        int start = 0;
        int N = s.length();
        if(N < k) return N;
        int ans = 0;

        for(int end = 0; end < N; end++){
            char c = s.charAt(end);
            if(map.containsKey(c)) map.remove(c);
            map.put(c, end);

            if(map.size() > k){
                Map.Entry<Character, Integer> first = map.entrySet().iterator().next();
                map.remove(first.getKey());
                start = first.getValue() + 1;
            }

            ans = Math.max(ans, end - start + 1);
        }

        return ans;
    }
}

// T:O(N), S:O(N), 14 ms
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // char: last index of the key char
        HashMap<Character, Integer> map = new HashMap();

        int start = 0;

        char[] arr = s.toCharArray();
        int ans = 0;
        if(arr.length < k) return arr.length;

        for(int end = 0; end < arr.length; end++){
            char c = arr[end];
            map.put(c, end);

            // 14 ms
            if(map.size() > k){
                int index = Collections.min(map.values());
                map.remove(arr[index]);
                start = index + 1;
            }else{
                ans = Math.max(ans, end - start + 1);
            }

            // 11 ms
            // if(map.size() <= k){
            //     // System.out.println("k=2: "+ c+", end: "+end+", start: "+start);
            //     ans = Math.max(ans, end - start + 1);
            // }else if(map.size() > k){
            //     int min = Integer.MAX_VALUE;
            //     char removeC = '0';
            //     for(Map.Entry<Character, Integer> entry: map.entrySet()){
            //         if(entry.getValue() < min){
            //             min = entry.getValue();
            //             removeC = entry.getKey();
            //         }
            //     }

            //     start = min + 1;
            //     map.remove(removeC);
            // }
        }
        // System.out.println("====");
        return ans;
    }
}