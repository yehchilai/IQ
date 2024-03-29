/*
This question is from https://leetcode.com/problems/compare-version-numbers/
Difficulty: easy

Compare two version numbers version1 and version2.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 13.37

*/
// String manipulation, T:O(n or m), M:O(n+m), 1ms
public class Solution {
    public int compareVersion(String version1, String version2) {
        String[] ver1 = version1.split("\\.");
        String[] ver2 = version2.split("\\.");

        int index = Math.max(ver1.length, ver2.length);
        for(int i = 0; i < index ; i++){
            int ver1Int = (i < ver1.length)? Integer.valueOf(ver1[i]) : 0;
            int ver2Int = (i < ver2.length)? Integer.valueOf(ver2[i]) : 0;

            if(ver1Int > ver2Int){
                return 1;
            }else if(ver1Int < ver2Int){
                return -1;
            }
        }

        return 0;
    }
}

// T:O(N), S:O(N+M), 2 ms
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        System.out.println(Arrays.toString(v1));
        System.out.println(Arrays.toString(v2));
        int index = 0;

        while(index < v1.length || index < v2.length){
            if(index < v1.length && index < v2.length){
                if(Integer.valueOf(v1[index]) > Integer.valueOf(v2[index])){
                    return 1;
                }else if(Integer.valueOf(v1[index]) < Integer.valueOf(v2[index])){
                    return -1;
                }
            }else if(index < v1.length){
                if(Integer.valueOf(v1[index]) > 0) return 1;
            }else if(index < v2.length){
                if(Integer.valueOf(v2[index]) > 0) return -1;
            }
            index++;
        }
        return 0;
    }
}
