/*
This question is from https://leetcode.com/problems/reorder-log-files/
Difficulty: easy

You have an array of logs.  Each log is a space delimited string of words.

For each log, the first word in each log is an alphanumeric identifier.  Then, either:

Each word after the identifier will consist only of lowercase letters, or;
Each word after the identifier will consist only of digits.
We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.

Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.

Return the final order of the logs.



Example 1:

Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]


Note:

0 <= logs.length <= 100
3 <= logs[i].length <= 100
logs[i] is guaranteed to have an identifier, and a word after the identifier.
*/
// T:O(NlogN), S:O(N), 3 ms
class Solution {
    public String[] reorderLogFiles(String[] logs) {
        List<String> letterLogs = new LinkedList();
        List<String> digitLogs = new LinkedList();

        for(int i = 0; i < logs.length; i++){
            int index = logs[i].indexOf(" ");
            // System.out.println(logs[i].charAt(index + 1));
            if(Character.isDigit(logs[i].charAt(index + 1))){
                digitLogs.add(logs[i]);
            }else{
                letterLogs.add(logs[i]);
            }
        }

        Collections.sort(letterLogs, new LetterComparator());

        for(String str: digitLogs){
            letterLogs.add(str);
        }

        return letterLogs.toArray(new String[letterLogs.size()]);
    }
}

class LetterComparator implements Comparator<String>{
    @Override
    public int compare(String a, String b){
        int indexA = a.indexOf(" ");
        String aID = a.substring(0, indexA);
        String aContent = a.substring(indexA + 1);

        int indexB = b.indexOf(" ");
        String bID = b.substring(0, indexB);
        String bContent = b.substring(indexB + 1);

        if(aContent.equals(bContent)){
            return aID.compareTo(bID);
        }else{
            return aContent.compareTo(bContent);
        }
    }
}