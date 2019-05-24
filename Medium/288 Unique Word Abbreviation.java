/*
This question is from https://leetcode.com/problems/unique-word-abbreviation/
Difficulty: medium

An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
     ↓
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
     ↓   ↓    ↓    ↓  ↓
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
     ↓   ↓    ↓
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example:

Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true
*/
// T:O(N), S:O(N), 97 ms
class ValidWordAbbr {

    HashMap<String, Boolean> map;
    HashSet<String> set;

    public ValidWordAbbr(String[] dictionary) {

        map = new HashMap();
        set = new HashSet();

        for(String str: dictionary){
             set.add(str);
        }

        for(String str : set){
            String strAbbr = abbreviate(str);
            map.put(strAbbr, !map.containsKey(strAbbr));
        }

    }

    public boolean isUnique(String word) {
        String abbr = abbreviate(word);
        Boolean hasAbbr = map.get(abbr);
        // System.out.println(hasAbbr);
        return hasAbbr == null || (hasAbbr && set.contains(word));
    }

    private String abbreviate(String str){
        int len = str.length();
        if(len <= 2) return str;

        return str.charAt(0) + Integer.toString(len - 2) + str.charAt(len - 1);
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */