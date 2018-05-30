/*
This question is from https://leetcode.com/problems/word-ladder/description/
Difficulty: medium

Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
*/
// T:O(26*M*N), M is the dictionay, N is the length of word, S: O(N), 100ms
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // visited words in the wordList
        HashSet<String> visited = new HashSet();
        // word dictionary
        HashSet<String> dict = new HashSet();
        // queue to restore possible words
        LinkedList<String> q = new LinkedList();
        // total sequence number
        int sequence = 1;

        //setupt dictionary
        for(int i=0; i< wordList.size(); i++){
            dict.add(wordList.get(i));
        }

        q.add(beginWord);
        visited.add(beginWord);

        while(q.size() > 0){
            //System.out.println("===== "+sequence+" ====== size: "+q.size());
            // q size need to store seperatly becuae the size is changing.
            int qSize = q.size();
            // poll all strings which contain the same sequence
            for(int k = 0; k < qSize; k++){
                String str = q.poll();
                //System.out.println("poll: "+str);
                int len = str.length();

                char[] strArr = str.toCharArray();
                // go through each charactors in the string
                for(int i = 0; i< len; i++){
                    char tmp = strArr[i];
                    // replace the charactors to possible charactors ('a' to 'z')
                    for(char j = 'a'; j <= 'z' ; j++){
                        strArr[i] = j;
                        String newStr = new String(strArr);
                        //System.out.println(newStr+" ");
                        if(dict.contains(newStr) && !visited.contains(newStr)){
                            //System.out.println("+++"+ newStr);
                            // if this new string is the endWord, return the sequence number
                            if(newStr.equals(endWord)) return sequence + 1;
                            // update visited map and add this new string into q.
                            visited.add(newStr);
                            q.add(newStr);
                        }
                    }
                    // restore the modified charactor
                    strArr[i] = tmp;
                }
            }

            sequence++;
        }
        return 0;
    }
}