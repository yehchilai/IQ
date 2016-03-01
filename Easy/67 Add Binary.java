/*
This question is from https://leetcode.com/problems/add-binary/
Difficulty: easy

Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".

Time Complexity: O()
*/
// only available to 64 bits
public class Solution {
    public String addBinary(String a, String b) {
        long aNum = 0;
        long bNum = 0;
        char[] aArr = a.toCharArray();
        char[] bArr = b.toCharArray();
        for(int i = 0; i< aArr.length; i++){
        	if(aArr[i] == '1'){
        		aNum = aNum << 1;
        		aNum++;
        	}else{
        		aNum = aNum << 1;
        	}
        }

        for(int i = 0; i < bArr.length; i++){
        	if(bArr[i] == '1'){
        		bNum = bNum << 1;
        		bNum++;
        	}else{
        		bNum = bNum << 1;
        	}
        }
        return Long.toBinaryString(aNum + bNum);
    }
}
// universal : 4ms
    "10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101"
"110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011"
--------------------------------------------------------------------------------------------------------
"110111101100010011000101110110100000011101000101011001000011011000001100011110011010010011000000000"
    "11101100010011000101110110100000011101000101011001000011011000001100011110011010010011000000000"

     "01100101"
"0001110101100"
----------------
  "10000010001"
  "00100010001"
public class Solution {
    public String addBinary(String a, String b) {
        char[] aArr = a.toCharArray();
        char[] bArr = b.toCharArray();
        int longString = Math.max(aArr.length, bArr.length);
        StringBuilder result = new StringBuilder();
        int i = aArr.length - 1;
        int j = bArr.length - 1;
        longString--;
        int next = 0;
        while(i >= 0 || j >= 0){
        	if(i >= 0 && j >= 0){
        		int tmp = (aArr[i] - '0') + (bArr[j] - '0') + next;
        		next = 0;
        		if(tmp >= 2) {
        			tmp = tmp -2;
        			next = 1;
        		}
        		result.append((char)(tmp + '0'));
        	}else if (i >= 0){
        		int tmp = (aArr[i] - '0') + next;
        		next = 0;
        		if(tmp >= 2) {
        			tmp = tmp -2;
        			next = 1;
        		}
        		result.append((char)(tmp + '0'));
        	}else if(j >= 0){
        		int tmp = (bArr[j] - '0') + next;
        		next = 0;
        		if(tmp >= 2) {
        			tmp = tmp -2;
        			next = 1;
        		}
        		result.append((char)(tmp + '0'));
        	}
        	longString--;
        	i--;
        	j--;
        }
        if(next == 1){
        	return result.append(1).reverse().toString();
        }else{
        	return result.reverse().toString();
        }
    }
}

// Alternitive : https://leetcode.com/discuss/67571/my-simple-4ms-java-solution-clean-and-consice
public class Solution{
	public String addBinary(String a, String b) {
    int aLength = a.length();
    int bLength = b.length();
    StringBuilder sb = new StringBuilder();
    int carry = 0;
    while(Math.max(aLength, bLength) > 0) {
      int aNum = aLength > 0 ? (a.charAt(aLength---1) - '0') : 0;
      int bNum = bLength > 0 ? (b.charAt(bLength---1) - '0') : 0;
      int cNum = aNum + bNum + carry;
      sb.append(cNum%2);
      carry = cNum / 2;
    }
    return (carry == 1)?sb.append(1).reverse().toString():sb.reverse().toString();
  }
}