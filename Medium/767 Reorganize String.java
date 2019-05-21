/*
This qeustion is from https://leetcode.com/problems/reorganize-string/
Difficulty: medium

Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: ""
Note:

S will consist of lowercase letters and have length in range [1, 500].
*/
// T:O(N or logN), S:O(N), 2 ms
class Solution {
    public String reorganizeString(String S) {
        int n = S.length();
        if(n <= 1) return S;

        int[] letters = new int[26];

        for(int i = 0; i < n;i++){
            char c = S.charAt(i);
            letters[c - 'a']++;
            if(letters[c - 'a'] > (n + 1)/2) return "";
        }

        PriorityQueue<Letter> pq = new PriorityQueue(new LetterComparator());

        for(int i = 0; i < 26; i++){
            if(letters[i] != 0) pq.add(new Letter((char)(i+(int)'a'), letters[i]));
        }
        // System.out.println("char: "+pq.peek().c+", count: "+pq.peek().count);

        StringBuilder sb = new StringBuilder();

        while(pq.size() >= 2){
            Letter letter1 = pq.poll();
            Letter letter2 = pq.poll();

            sb.append(letter1.c);
            sb.append(letter2.c);

            letter1.count--;
            letter2.count--;

            if(letter1.count > 0) pq.add(letter1);
            if(letter2.count > 0) pq.add(letter2);
        }

        if(pq.size() > 0) sb.append(pq.poll().c);

        return sb.toString();
    }
}

class Letter{
    char c;
    int count;

    Letter(char c, int count){
        this.c = c;
        this.count = count;
    }
}

class LetterComparator implements Comparator<Letter>{
    @Override
    public int compare(Letter a, Letter b){
        return b.count - a.count;
    }
}