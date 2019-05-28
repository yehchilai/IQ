/*
This question is from https://leetcode.com/problems/word-abbreviation/
Difficulty: hard

Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.

Begin with the first character and then the number of characters abbreviated, which followed by the last character.
If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of only the first character until making the map from word to abbreviation become unique. In other words, a final abbreviation cannot map to more than one original words.
If the abbreviation doesn't make the word shorter, then keep it as original.
Example:
Input: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
Note:
Both n and the length of each word will not exceed 400.
The length of each word is greater than 1.
The words consist of lowercase English letters only.
The return answers should be in the same order as the original array.

*/
// T:O(ClogC), S:O(C), 45 ms
class Solution {


    public List<String> wordsAbbreviation(List<String> dict) {

        HashMap<String, List<Word>> group = new HashMap();
        String[] ans = new String[dict.size()];

        // arrange dict
        for(int i = 0; i < dict.size(); i++){
            String str = dict.get(i);
            String key = abbreviate(str, 0);
            // System.out.println("key: "+key);
            List<Word> list = group.getOrDefault(key, new LinkedList());
            list.add(new Word(str, i));
            group.put(key, list);
        }

        // check abbreviation
        for(List<Word> list: group.values()){
            Collections.sort(list, (a,b)-> a.str.compareTo(b.str));

            int[] prefix = new int[list.size()];
            for(int i = 1; i < list.size(); i++){
                int index = getPrefix(list.get(i - 1).str, list.get(i).str);
                // System.out.println(list.get(i - 1)+", "+list.get(i)+ ": "+index);
                prefix[i] = index;
                prefix[i - 1] = Math.max(prefix[i - 1], index);
            }

            for(int i = 0; i < list.size(); i++){
                // System.out.println(list.get(i)+": "+ prefix[i]);
                ans[list.get(i).index]= abbreviate(list.get(i).str, prefix[i]);
            }
        }

        return Arrays.asList(ans);
    }

    private int getPrefix(String a, String b){
        int len = Math.min(a.length(), b.length());

        int index = 0;
        while(index < len){
            if(a.charAt(index) != b.charAt(index)) break;
            index++;
        }

        return index;
    }

    private String abbreviate(String str, int prefix){
        int len = str.length();
        // System.out.println(str+": "+ prefix);
        if(len <= 3 || (len - 2 - prefix) <= 1) return str;

        return str.substring(0, 1 + prefix) + (len - 2 - prefix) + str.substring(len - 1);
    }
}

class Word{
    String str;
    int index;

    Word(String s, int i){
        str = s;
        index = i;
    }
}