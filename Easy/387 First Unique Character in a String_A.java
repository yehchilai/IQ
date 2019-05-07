/*
This question is from https://leetcode.com/problems/first-unique-character-in-a-string/
Difficulty: Easy

Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
Note: You may assume the string contain only lowercase letters.


*/

// LinkedHashMap, T:O(n), M:(n), 67ms
class Solution {
    public int firstUniqChar(String s) {
        // linked hash table
        // throw new Exception()
        // store character and occurrence times.
        Map<Character, Integer> map = new LinkedHashMap();
        // store visited character
        Set<Character> set = new HashSet();
        // character index
        int index = 0;
        for(char c : s.toCharArray()){
            if(set.contains(c)){
                map.remove(c);
            }else{
                map.put(c, index);
                set.add(c);
            }
            index++;
        }
        if(map.isEmpty()) return -1;
        Map.Entry<Character, Integer> pair = map.entrySet().iterator().next();
        return pair.getValue();
    }
}

class Solution {
    public int firstUniqChar(String s) {
        LinkedHashMap<Character, Integer> map = new LinkedHashMap();
        HashMap<Character, Integer> mapIndex = new HashMap();
        int len = s.length();

        for(int i = 0; i < len; i++){
            char c = s.charAt(i);
            int count = map.getOrDefault(c, 0);
            if(count == 0){
                mapIndex.put(c, i);
            }
            map.put(c, count + 1);
        }

        for(Map.Entry<Character, Integer> entry: map.entrySet()){
            if(entry.getValue() == 1) return mapIndex.get(entry.getKey());
        }

        return -1;
    }
}

// HashMap, T:O(n), M:(n), 95ms
public class Solution {
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int sLen = s.length();
        for(int i = 0; i < sLen; i++){
            char c = s.charAt(i);
            if(!map.containsKey(s.charAt(i))){
                map.put(c, i);
            }else{
                map.put(c, -1);
            }
        }
        int min = Integer.MAX_VALUE;
        for(Map.Entry<Character, Integer> e : map.entrySet()){
            if(e.getValue() < min && e.getValue() > -1){
                min = e.getValue();
            }
        }

        if(min == Integer.MAX_VALUE){
            return -1;
        }else{
            return min;
        }
    }
}

// int array int[26], T:O(n), M:(26), 23ms
public class Solution {
    public int firstUniqChar(String s) {
        int[] index = new int[26];
        int sLen = s.length();
        for(int i = 0; i < sLen ; i++){
            char c = s.charAt(i);
            if(index[c-'a'] != 0){
                index[c-'a'] = Integer.MAX_VALUE;
            }else{
                index[c-'a'] = i + 1;
            }
        }

        int result = Integer.MAX_VALUE;
        for(int i = 0; i< index.length; i++){
            if(index[i] != 0)
                result = Math.min(result, index[i]);
        }

        if(result == Integer.MAX_VALUE){
            return -1;
        }else{
            return result -1 ;
        }
    }
}
