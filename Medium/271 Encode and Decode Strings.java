/*
This question is from https://leetcode.com/problems/encode-and-decode-strings/
Difficulty: medium

Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
Machine 2 (receiver) has the function:
vector<string> decode(string s) {
  //... your code
  return strs;
}
So Machine 1 does:

string encoded_string = encode(strs);
and Machine 2 does:

vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.



Note:

The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
*/

// T:O(N), S:O(N), 25 ms
public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder header = new StringBuilder();
        String carrier = "";

        if(strs == null || strs.size() == 0) return "";

        for(int i = 0; i < strs.size(); i++){
            String str = strs.get(i);
            header.append(str.length());
            if(i < strs.size() - 1) header.append(',');
            carrier += str;
        }
        return header.toString()+":"+carrier;
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        // System.out.println(s);
        List<String> ans = new LinkedList();
        if(s.equals("")) return ans;
        int index = s.indexOf(':');
        // System.out.println(index);
        String header = s.substring(0,index);
        String carrier = s.substring(index + 1);
        // System.out.println(header);
        // System.out.println(carrier);
        String[] sizes = header.split(",");
        // System.out.println(sizes.length);

        int start = 0;
        int end = 0;

        for(int i = 0; i < sizes.length; i++){
            end = end + Integer.valueOf(sizes[i]);
            // System.out.println("end: "+end);
            if(i < sizes.length - 1){
                String str = carrier.substring(start, end);
                // System.out.println(str);
                ans.add(str);
                start = end;
            }else{
                String str = carrier.substring(start);
                // System.out.println(str);
                ans.add(str);
            }
        }

        return ans;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));