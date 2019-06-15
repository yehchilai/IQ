/*
This question is from https://leetcode.com/problems/word-search-ii/
Difficulty: hard

Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.



Example:

Input:
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]


Note:

All inputs are consist of lowercase letters a-z.
The values of words are distinct.
*/
// backtracking + Trie, T:O(len * row * col), len is the max length of words, S:O(row * col), 27 ms
class Solution {

    int[][] directions;
    char[][] board;
    int row;
    int col;
    TrieNode root;
    HashSet<String> ans;

    public List<String> findWords(char[][] board, String[] words) {

        ans = new HashSet();

        if(board == null || board.length == 0 || words == null) new LinkedList(ans);

        directions = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
        this.board = board;
        row = board.length;
        col = board[0].length;

        root = new TrieNode();

        for(String word: words){
            addNode(root, word);
        }

        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                boolean[][] visited = new boolean[row][col];

                if(root.children.containsKey(board[r][c])){
                    // System.out.println("start, r: "+ r +", c: "+c);
                    TrieNode node = root.children.get(board[r][c]);
                    if(root.isWord){
                        ans.add(root.word);
                    }else{
                        dfs(r, c, node, visited);
                    }

                }
            }
        }

        return new LinkedList(ans);
    }

    private void addNode(TrieNode root, String word){
        int len = word.length();
        TrieNode node = root;

        for(int i = 0; i < len; i++){
            char c = word.charAt(i);

            if(!node.children.containsKey(c)){
                // System.out.println("add: "+ c );
                node.children.put(c, new TrieNode());
            }


            node = node.children.get(c);
        }

        node.isWord = true;
        node.word = word;
    }

    private void dfs(int r, int c, TrieNode node, boolean[][] visited){

        visited[r][c] = true;

        if(node.isWord) {
            // System.out.println("node.isWord: "+node.isWord+", c: "+board[r][c]);
            ans.add(node.word);
        }
        // System.out.println("children: "+ node.children.keySet().toString());
        for(int i = 0 ; i < directions.length; i++){
            int newR = r + directions[i][0];
            int newC = c + directions[i][1];
            // System.out.println("newR: "+ newR +", newC: "+newC);
            if(newR >= 0 && newR < row && newC >= 0 && newC < col
               && visited[newR][newC] == false
               && node.children.containsKey(board[newR][newC])){
                if(node.children.containsKey(board[newR][newC])){
                    // System.out.println("next: "+ board[newR][newC]);
                    dfs(newR, newC, node.children.get(board[newR][newC]), visited);
                }

            }
        }

        visited[r][c] = false;

    }
}

class TrieNode{

    String word;
    boolean isWord;
    HashMap<Character, TrieNode> children;

    public TrieNode(){
        children = new HashMap();
        isWord = false;
        word = "";
    }
}

// backtracking, T:O(w * row * col), S:O(row * col), 564 ms
class Solution {

    int[][] directions;
    char[][] board;
    int row;
    int col;

    public List<String> findWords(char[][] board, String[] words) {

        List<String> ans = new LinkedList();

        if(board == null || board.length == 0 || words == null) return ans;

        directions = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
        this.board = board;
        row = board.length;
        col = board[0].length;

        for(String word: words){
            if(find(word)) ans.add(word);
        }

        return ans;
    }

    private boolean find(String word){
        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                if(word.charAt(0) == board[r][c]){
                    boolean[][] visited = new boolean[row][col];
                    if(dfs(r, c, word, 0, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(int r, int c, String word, int index, boolean[][] visited){

        visited[r][c] = true;

        if(index == word.length() - 1) return true;

        for(int i = 0 ; i < directions.length; i++){
            int newR = r + directions[i][0];
            int newC = c + directions[i][1];

            if(newR >= 0 && newR < row && newC >= 0 && newC < col
               && visited[newR][newC] == false && word.charAt(index + 1) == board[newR][newC]){
                if(dfs(newR, newC, word, index + 1, visited)) return true;
            }
        }

        visited[r][c] = false;

        return false;
    }
}