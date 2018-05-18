/*
This question is from https://leetcode.com/problems/most-common-word/description/
DIfficulty: easy
Tag: A

Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.

Example:
Input:
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]
Output: "ball"
Explanation:
"hit" occurs 3 times, but it is a banned word.
"ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
Note that words in the paragraph are not case sensitive,
that punctuation is ignored (even if adjacent to words, such as "ball,"),
and that "hit" isn't the answer even though it occurs more because it is banned.


Note:

1 <= paragraph.length <= 1000.
1 <= banned.length <= 100.
1 <= banned[i].length <= 10.
The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
Different words in paragraph are always separated by a space.
There are no hyphens or hyphenated words.
Words only consist of letters, never apostrophes or other punctuation symbols.

*/
// T:O(P+B), M:O(P+B), 32ms
class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        // banMap store all banned word
        Set<String> banMap = new HashSet();
        for(String word: banned) banMap.add(word);

        // wordMap store word counts in the paragraph
        // String: word, Integer: count
        HashMap<String, Integer> wordMap = new HashMap();

        // get string array from paragraph
        String[] strArr = paragraph.split("[\\p{Punct}\\s]+");
        // max count word
        String maxWord = "";
        // max count
        int maxCount = 0;

        // go through all words in the paragraph
        for(String wordOriginal: strArr){
            String word = wordOriginal.toLowerCase();
            // skip the banned word
            if(banMap.contains(word)) continue;

            // check the word count
            if(wordMap.containsKey(word)){
                int count = wordMap.get(word) + 1;
                wordMap.put(word, count);
                // check if this word is the max word
                if(count > maxCount){
                    maxCount = count;
                    maxWord = word;
                }
            }else{
                wordMap.put(word, 1);
                if(1 > maxCount){
                    maxCount = 1;
                    maxWord = word;
                }
            }
        }
        return maxWord;
    }
}

// solution
// Time Complexity: O(P + B), where P is the size of paragraph and B is the size of banned.
//Space Complexity: O(P + B), to store the count and the banned set.
class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        paragraph += ".";

        Set<String> banset = new HashSet();
        for (String word: banned) banset.add(word);
        Map<String, Integer> count = new HashMap();

        String ans = "";
        int ansfreq = 0;

        StringBuilder word = new StringBuilder();
        for (char c: paragraph.toCharArray()) {
            if (Character.isLetter(c)) {
                word.append(Character.toLowerCase(c));
            } else if (word.length() > 0) {
                String finalword = word.toString();
                if (!banset.contains(finalword)) {
                    count.put(finalword, count.getOrDefault(finalword, 0) + 1);
                    if (count.get(finalword) > ansfreq) {
                        ans = finalword;
                        ansfreq = count.get(finalword);
                    }
                }
                word = new StringBuilder();
            }
        }

        return ans;
    }
}