/*
This question is from https://leetcode.com/problems/ransom-note/
Difficulty: easy

 Given  an  arbitrary  ransom  note  string  and  another  string  containing  letters from  all  the  magazines,  write  a  function  that  will  return  true  if  the  ransom   note  can  be  constructed  from  the  magazines ;  otherwise,  it  will  return  false.   

Each  letter  in  the  magazine  string  can  only  be  used  once  in  your  ransom  note.

Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true

*/
// T: O(m+n), M: O(m+n), 63ms
public class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> ransomNoteMap = getCharHistogram(ransomNote);
        HashMap<Character, Integer> magazineMap = getCharHistogram(magazine);
        
        for(Map.Entry<Character, Integer> e : ransomNoteMap.entrySet()){
            if(magazineMap.get(e.getKey()) == null) return false;
            if(e.getValue() > magazineMap.get(e.getKey()))
                return false;
        }
        return true;
     }
    
    public HashMap<Character, Integer> getCharHistogram(String str){
        HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
        for(char c : str.toCharArray()){
            if(!charMap.containsKey(c)){
                charMap.put(c, 1);
            }else{
                charMap.put(c, charMap.get(c) + 1);
            }
        }
        return charMap;
    }
}

// T: O(M+n), M: O(26 * 2), 20ms
public boolean canConstruct(String ransomNote, String magazine) {
    int[] map = new int[26];
    for(int i=0; i<magazine.length(); i++)
        map[magazine.charAt(i) - 'a']++;
    for(int i=0; i<ransomNote.length(); i++)
        map[ransomNote.charAt(i) - 'a']--;
    for(int num: map)
        if(num < 0)
            return false;
    return true;
}