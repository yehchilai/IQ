/*
This question is from https://leetcode.com/problems/restore-ip-addresses/
Difficulty: medium

Given a string containing only digits, restore it by returning all possible valid IP address combinations.

Example:

Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]
*/
// T:O(27), S:O(19), 3 ms (https://leetcode.com/articles/restore-ip-addresses/)
class Solution {

    LinkedList<String> sections;
    LinkedList<String> ans;
    String s;
    int len;

    public List<String> restoreIpAddresses(String s) {
        ans = new LinkedList();
        sections = new LinkedList();
        this.s = s;
        len = s.length();
        if(len < 4 || len > 12) return ans;

        dfs(0, 4);

        return ans;
    }


    private void dfs(int index, int dots){
        if(dots < 0 || index >= len) return;

        dots--;

        for(int i = 1; i <= 3 ; i++){

            int next = Math.min(index + i, len);
            String section = s.substring(index, next);

            if(isValid(section)){
                sections.add(section);
                if(dots == 0 && next == len){
                    ans.add(String.join(".", sections));
                }else{
                    dfs(index + i, dots);
                }
                sections.removeLast();
            }

            if(next == len) break;
        }
    }

    private boolean isValid(String section){
        int length = section.length();
        if(length >= 2 && section.charAt(0) == '0') return false;

        if(Integer.valueOf(section) <= 255) return true;
        return false;
    }
}