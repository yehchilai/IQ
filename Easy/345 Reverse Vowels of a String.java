/*
This question is from https://leetcode.com/problems/reverse-vowels-of-a-string/
Difficulty: easy

Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y".
*/
// T:O(N), S:O(N), 8ms
class Solution {
    public String reverseVowels(String s) {
        HashSet<Character> set = new HashSet();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');

        int lo = 0;
        int hi = s.length() - 1;

        char[] sArr = s.toCharArray();

        while(lo < hi){
            char cLo = sArr[lo];
            char cHi = sArr[hi];
            if(!set.contains(Character.toLowerCase(cLo))) lo++;
            if(!set.contains(Character.toLowerCase(cHi))) hi--;
            if(set.contains(Character.toLowerCase(cLo)) &&
               set.contains(Character.toLowerCase(cHi))){
                sArr[lo] = cHi;
                sArr[hi] = cLo;
                lo++;
                hi--;
            }
        }

        return new String(sArr);
    }
}


// LinkedList, T:O(N), M:O(N), Time Limit Exceeded
public class Solution {
    public String reverseVowels(String s) {
        LinkedList<Character> charList = new LinkedList<>();
        LinkedList<Integer> intList = new LinkedList<>();
        HashSet<Character> vowels = new HashSet<>();
        int strLength = s.length();

        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        for(int i = 0; i < strLength; i++){
            if(vowels.contains(Character.toLowerCase(s.charAt(i)))){
                charList.add(s.charAt(i));
                intList.add(i);
            }
        }

        StringBuilder sb = new StringBuilder(s);
        int listSize = charList.size();
        for(int i = 0; i < listSize; i++){
            sb.setCharAt(intList.get(i), charList.get(listSize - 1 - i));
        }

        return sb.toString();
    }
}


// Two pointer, T:O(N), M:O(1), 19ms
public class Solution {
    public String reverseVowels(String s) {
        HashSet<Character> vowels = new HashSet<>();
        int strLength = s.length();
        StringBuilder sb = new StringBuilder(s);
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        int index = 0;
        int indexReverse = strLength - 1;
        if(strLength <= 1) return s;
        while(index <= indexReverse){
            if(!vowels.contains(Character.toLowerCase(s.charAt(index)))){
                index++;
            }

            if(!vowels.contains(Character.toLowerCase(s.charAt(indexReverse)))){
                indexReverse--;
            }

            if(vowels.contains(Character.toLowerCase(s.charAt(index))) && vowels.contains(Character.toLowerCase(s.charAt(indexReverse)))){
                char c = s.charAt(index);
                sb.setCharAt(index, s.charAt(indexReverse));
                sb.setCharAt(indexReverse, c);
                index++;
                indexReverse--;
            }
        }


        return sb.toString();
    }
}
