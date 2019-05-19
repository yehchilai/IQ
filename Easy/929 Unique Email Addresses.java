/*
This question is from https://leetcode.com/problems/unique-email-addresses/
Difficulty: easy

Every email consists of a local name and a domain name, separated by the @ sign.

For example, in alice@leetcode.com, alice is the local name, and leetcode.com is the domain name.

Besides lowercase letters, these emails may contain '.'s or '+'s.

If you add periods ('.') between some characters in the local name part of an email address, mail sent there will be forwarded to the same address without dots in the local name.  For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.  (Note that this rule does not apply for domain names.)

If you add a plus ('+') in the local name, everything after the first plus sign will be ignored. This allows certain emails to be filtered, for example m.y+name@email.com will be forwarded to my@email.com.  (Again, this rule does not apply for domain names.)

It is possible to use both of these rules at the same time.

Given a list of emails, we send one email to each address in the list.  How many different addresses actually receive mails?



Example 1:

Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
Output: 2
Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails


Note:

1 <= emails[i].length <= 100
1 <= emails.length <= 100
Each emails[i] contains exactly one '@' character.
All local and domain names are non-empty.
Local names do not start with a '+' character.

*/
// T:O(N*K), K: the length of string, S:O(N), 11 ms
class Solution {
    public int numUniqueEmails(String[] emails) {
        HashSet<String> set = new HashSet();

        for(String email : emails){
            String[] arr = email.split("@");
            String localname = arr[0];
            int len = localname.length();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < len; i++){
                char c = localname.charAt(i);

                if(c == '.') continue;
                if(c == '+') break;

                sb.append(c);
            }
            //System.out.println(sb.toString()+"@"+ arr[1]);
            set.add(sb.toString() +"@"+ arr[1]);
        }
        // System.out.println("=====");
        return set.size();
    }
}

// Optional
class Solution {
    public int numUniqueEmails(String[] emails) {
        Set<String> seen = new HashSet();
        for (String email: emails) {
            int i = email.indexOf('@');
            String local = email.substring(0, i);
            String rest = email.substring(i);
            if (local.contains("+")) {
                local = local.substring(0, local.indexOf('+'));
            }
            local = local.replaceAll(".", "");
            seen.add(local + rest);
        }

        return seen.size();
    }
}