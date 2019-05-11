/*
This question is from https://leetcode.com/problems/swap-adjacent-in-lr-string/
Difficulty: medium

In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR". Given the starting string start and the ending string end, return True if and only if there exists a sequence of moves to transform one string to the other.

Example:

Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
Output: True
Explanation:
We can transform start to end following these steps:
RXXLRXRXL ->
XRXLRXRXL ->
XRLXRXRXL ->
XRLXXRRXL ->
XRLXXRRLX
Note:

1 <= len(start) = len(end) <= 10000.
Both start and end will only consist of characters in {'L', 'R', 'X'}.
*/

// T:O(N), S:O(N), 8 ms
class Solution {
    public boolean canTransform(String start, String end) {
        if(start.length() != end.length()) return false;

        int len = start.length();

        ArrayList<CP> listS = new ArrayList();
        ArrayList<CP> listE = new ArrayList();

        for(int i = 0 ;i < len; i++){
            char c = start.charAt(i);
            if(c == 'R' || c == 'L')
                listS.add(new CP(c, i));
        }

        for(int i = 0 ;i < len; i++){
            char c = end.charAt(i);
            if(c == 'R' || c == 'L')
                listE.add(new CP(c, i));
        }

        if(listS.size() != listE.size()) return false;

        for(int i = 0; i < listS.size(); i++){
            CP s = listS.get(i);
            CP e = listE.get(i);
            if(s.c != e.c) return false;
            // System.out.println("s: "+ s.c + ", "+s.index+"; e: "+e.c+", "+e.index);
            if(s.c == 'R' && s.index > e.index) return false;
            if(s.c == 'L' && s.index < e.index) return false;
        }

        return true;
    }
}

class CP{
    char c;
    int index;

    CP(char c, int i){
        this.c = c;
        this.index = i;
    }
}