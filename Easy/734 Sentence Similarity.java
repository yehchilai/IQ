/*
This question is from https://leetcode.com/problems/sentence-similarity/
DIfficulty: easy

Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

For example, "great acting skills" and "fine drama talent" are similar, if the similar word pairs are pairs = [["great", "fine"], ["acting","drama"], ["skills","talent"]].

Note that the similarity relation is not transitive. For example, if "great" and "fine" are similar, and "fine" and "good" are similar, "great" and "good" are not necessarily similar.

However, similarity is symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

Note:

The length of words1 and words2 will not exceed 1000.
The length of pairs will not exceed 2000.
The length of each pairs[i] will be 2.
The length of each words[i] and pairs[i][j] will be in the range [1, 20].
*/
// T:O(N or M), N: length of words, M: length of pairs, S:O(M), 2 ms
class Solution {
    public boolean areSentencesSimilar(String[] words1, String[] words2, List<List<String>> pairs) {
        HashMap<String, List<String>> map = new HashMap();
        if(words1.length != words2.length) return false;
        for(List<String> pair: pairs){
            String a = pair.get(0);
            String b = pair.get(1);

            List<String> listAB = map.getOrDefault(a, new ArrayList<String>());
            listAB.add(b);
            map.put(a, listAB);

            List<String> listBA = map.getOrDefault(b, new ArrayList<String>());
            listBA.add(a);
            map.put(b, listBA);
        }

        for(int i = 0; i < words1.length; i++){
            String a = words1[i];
            String b = words2[i];

            if(a.equals(b)) continue;
            if(!map.containsKey(a)) return false;
            if(!check(map.get(a), b)) return false;

        }

        return true;
    }

    private boolean check(List<String> list, String b){
        for(String str: list){
            if(str.equals(b)) return true;
        }
        return false;
    }
}