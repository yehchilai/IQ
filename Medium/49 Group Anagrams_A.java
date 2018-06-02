/*
Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:

All inputs will be in lowercase.
The order of your output does not matter.

*/

// use hashmap, T: O(N*KlogK), S: O(N*K), 34ms
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        // result list
        List<List<String>> result = new LinkedList();

        // edge case
        if(strs == null || strs.length == 0) return result;

        // group list by a word
        HashMap<String, List<String>> map = new HashMap();

        // go through the string array
        for(String str: strs){
            // sort the single string
            char[] cArr = str.toCharArray();
            Arrays.sort(cArr);
            // create a string key with sorted string
            String sortedStr = new String(cArr);
            // check if the string in the map
            List<String> list = map.getOrDefault(sortedStr, new LinkedList<String>());
            list.add(str);
            map.put(sortedStr, list);
        }
        // go through the map to get a list of an anagram group
        for(Map.Entry<String, List<String>> pair : map.entrySet()){
            result.add(pair.getValue());
        }

        return result;
    }
}

// use charactor count, T: O(N*K), S:O(N*K);
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) count[c - 'a']++;

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }
}
