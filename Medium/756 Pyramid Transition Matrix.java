/*
This question is from https://leetcode.com/problems/pyramid-transition-matrix/
Difficulty: medium

We are stacking blocks to form a pyramid. Each block has a color which is a one letter string.

We are allowed to place any color block C on top of two adjacent blocks of colors A and B, if and only if ABC is an allowed triple.

We start with a bottom row of bottom, represented as a single string. We also start with a list of allowed triples allowed. Each allowed triple is represented as a string of length 3.

Return true if we can build the pyramid all the way to the top, otherwise false.

Example 1:

Input: bottom = "BCD", allowed = ["BCG", "CDE", "GEA", "FFF"]
Output: true
Explanation:
We can stack the pyramid like this:
    A
   / \
  G   E
 / \ / \
B   C   D

We are allowed to place G on top of B and C because BCG is an allowed triple.  Similarly, we can place E on top of C and D, then A on top of G and E.


Example 2:

Input: bottom = "AABA", allowed = ["AAA", "AAB", "ABA", "ABB", "BAC"]
Output: false
Explanation:
We can't stack the pyramid to the top.
Note that there could be allowed triples (A, B, C) and (A, B, D) with C != D.


Note:

bottom will be a string with length in range [2, 8].
allowed will have length in range [0, 200].
Letters in all strings will be chosen from the set {'A', 'B', 'C', 'D', 'E', 'F', 'G'}.
*/
// tricky question, T:O(?), S:O(N), 34 ms
class Solution {
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        HashMap<String, ArrayList<String>> map = new HashMap();

        for(String s: allowed){
            String key = s.substring(0,2);
            String val = s.substring(2);

            ArrayList list = map.getOrDefault(key, new ArrayList());
            list.add(val);
            map.put(key, list);

        }

        Node root = new Node(bottom);
        LinkedList<Node> q = new LinkedList();
        q.add(root);

        while(q.size() > 0){
            Node node = q.poll();

            if(node.currStr.length() == 0){
                node.currStr = node.nextStr;
                node.nextStr = "";
            }

            if(node.currStr.length() == 1){
                return true;
            }

            String btm = node.currStr.substring(0,2);

            if(!map.containsKey(btm)) continue;

            for(String s : map.get(btm)){
                Node newNode = new Node(node);
                newNode.currStr = node.currStr.substring(1);
                if(newNode.currStr.length() == 1) newNode.currStr = "";

                newNode.nextStr += s;
                q.addFirst(newNode);
            }
        }

        return false;
    }
}

class Node{
    String currStr;
    String nextStr;

    Node(String s){
        this.currStr = s;
        this.nextStr = "";
    }

    Node(Node node){
        this.currStr = node.currStr;
        this.nextStr = node.nextStr;
    }
}