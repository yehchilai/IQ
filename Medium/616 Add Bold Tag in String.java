/*
This question is from https://leetcode.com/problems/add-bold-tag-in-string/
Difficulty: medium

Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
Example 1:
Input:
s = "abcxyz123"
dict = ["abc","123"]
Output:
"<b>abc</b>xyz<b>123</b>"
Example 2:
Input:
s = "aaabbcc"
dict = ["aaa","aab","bc"]
Output:
"<b>aaabbc</b>c"
Note:
The given dict won't contain duplicates, and its length won't exceed 100.
All the strings in input have length in range [1, 1000].

*/
// T:O(N*K), S:O(N), 5 ms
class Solution {
    public String addBoldTag(String s, String[] dict) {
        int n = s.length();
        boolean[] mark = new boolean[n];

        for(int i = 0; i < dict.length; i++){
            int index = -1;
            index = s.indexOf(dict[i]);
            while(index >= 0){
                int len = dict[i].length();
                for(int m = index; m < index + len; m++){
                    mark[m] = true;
                }
                index++;
                index = s.indexOf(dict[i], index);
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++){
            if(mark[i] == false){
                sb.append(s.charAt(i));
                continue;
            }
            sb.append("<b>");
            int index = i;
            while(index < n && mark[index]){
                sb.append(s.charAt(index));
                index++;
            }
            sb.append("</b>");
            i = index - 1;
        }

        return sb.toString();
    }
}
// class Solution {
//     public String addBoldTag(String s, String[] dict) {
//         LinkedList<Pair> list  = new LinkedList();

//         for(int i = 0; i < dict.length; i++){
//             int index = -1;
//             index = s.indexOf(dict[i]);
//             while(index >= 0){

//                 insertPair(list, index, dict[i]);
//                 index++;
//                 index = s.indexOf(dict[i], index);
//             }
//         }

//         StringBuilder sb = new StringBuilder();
//         int index = 0;

//         for(int i = 0;i < list.size(); i++){
//             Pair p = list.get(i);
//             // System.out.println(p.x+", "+p.y);
//             while(index < p.x){
//                 sb.append(s.charAt(index));
//                 index++;
//             }
//             sb.append("<b>");
//             while(index < p.y){
//                 sb.append(s.charAt(index));
//                 index++;
//             }
//             sb.append("</b>");
//         }

//         int len = s.length();
//         for(int i = index; i< len; i++){
//             sb.append(s.charAt(i));
//         }

//         return sb.toString();
//     }

//     private void insertPair(LinkedList<Pair> list, int index, String word){
//         Pair pair = new Pair(index, index + word.length());
//         // System.out.println("insert: "+pair.x+", "+pair.y);
//         for(int i = list.size() - 1; i >= 0; i--){
//             Pair p = list.get(i);
//             // System.out.println("test: p.x "+p.x+" < "+pair.y+", p.y "+p.y+" < "+pair.x);
//             if(p.x < pair.y && p.y < pair.x){
//                 // skip
//             }else{
//                 // overlape
//                 // System.out.println("update");
//                 pair.x = Math.min(p.x, pair.x);
//                 pair.y = Math.max(p.y, pair.y);
//                 list.remove(i);
//             }
//         }

//         list.add(pair);
//     }
// }

// class Pair{
//     int x;
//     int y;

//     Pair(int a, int b){
//         x = a;
//         y = b;
//     }
// }