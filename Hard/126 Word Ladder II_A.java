/*
This question is from https://leetcode.com/problems/word-ladder-ii/description/
Difficulty: hard

Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return an empty list if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: []

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

*/

// T: O(), S: O(N),  Time Limit Exceeded
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // get a dictionary of wordList
        HashSet<String> set = new HashSet(wordList);
        // qeue to hold possible path
        Deque<Path> q = new LinkedList();
        // result lists
        LinkedList<List<String>> result = new LinkedList();
        // insert the begin word
        q.add(new Path(beginWord, beginWord));
        // Minum sequence
        int min = Integer.MAX_VALUE;

        // word length
        int len = beginWord.length();

        while(q.size() > 0){
            Path p = q.poll();
            String currentWord = p.list.getLast();
            // System.out.println("q: "+currentWord);
            // for(int i = 0;i<p.list.size();i++){
            //     System.out.print(p.list.get(i)+" ");
            // }
            // System.out.println();
            // update charactors for a given word
            for(int i = 0; i < len; i++){
                if(currentWord.equals(endWord)) break;
                for(char c ='a' ; c < 'z'; c++){
                    StringBuilder sb = new StringBuilder(currentWord);
                    sb.setCharAt(i, c);
                    String updatedWord = sb.toString();
                    // check if the updated word is in the dictionary
                    if(set.contains(updatedWord) && !p.visited.contains(updatedWord)){
                        // System.out.println(updatedWord);
                        // check if the current word is the endWord
                        if(updatedWord.equals(endWord)){
                            if(min >= p.list.size() + 1){
                                LinkedList<String> list = new LinkedList<String>(p.list);
                                list.add(updatedWord);
                                result.add(list);
                                min = p.list.size() + 1;
                            }
                            break;
                        }else if(min == Integer.MAX_VALUE){
                            Path newPath = new Path(new LinkedList<String>(p.list), new HashSet<String>(p.visited));
                            newPath.list.add(updatedWord);
                            newPath.visited.add(updatedWord);
                            q.add(newPath);
                        }
                    }
                }
            }
        }
        return result;
    }

    class Path{
        // the path of the shortest transformation sequences
        LinkedList<String> list;
        // visited words
        HashSet<String> visited;

        public Path(String s, String v){
            list = new LinkedList();
            visited = new HashSet();
            list.add(s);
            visited.add(v);
        }

        public Path(LinkedList<String> list, HashSet<String> set){
            this.list = list;
            this.visited = set;
        }
    }
}

// DFS nad BFS, 210 ms
class Solution {
    public List<List<String>> findLadders(String start, String end, List<String> wordList) {
       HashSet<String> dict = new HashSet<String>(wordList);
       List<List<String>> res = new ArrayList<List<String>>();
       HashMap<String, ArrayList<String>> nodeNeighbors = new HashMap<String, ArrayList<String>>();// Neighbors for every node
       HashMap<String, Integer> distance = new HashMap<String, Integer>();// Distance of every node from the start node
       ArrayList<String> solution = new ArrayList<String>();

       dict.add(start);
       bfs(start, end, dict, nodeNeighbors, distance);
       dfs(start, end, dict, nodeNeighbors, distance, solution, res);
       return res;
    }

    // BFS: Trace every node's distance from the start node (level by level).
    private void bfs(String start, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance) {
      for (String str : dict)
          nodeNeighbors.put(str, new ArrayList<String>());

      Queue<String> queue = new LinkedList<String>();
      queue.offer(start);
      distance.put(start, 0);

      while (!queue.isEmpty()) {
          int count = queue.size();
          boolean foundEnd = false;
          for (int i = 0; i < count; i++) {
              String cur = queue.poll();
              int curDistance = distance.get(cur);
              ArrayList<String> neighbors = getNeighbors(cur, dict);

              for (String neighbor : neighbors) {
                  nodeNeighbors.get(cur).add(neighbor);
                  if (!distance.containsKey(neighbor)) {// Check if visited
                      distance.put(neighbor, curDistance + 1);
                      if (end.equals(neighbor))// Found the shortest path
                          foundEnd = true;
                      else
                          queue.offer(neighbor);
                      }
                  }
              }

              if (foundEnd)
                  break;
          }
      }

    // Find all next level nodes.
    private ArrayList<String> getNeighbors(String node, Set<String> dict) {
      ArrayList<String> res = new ArrayList<String>();
      char chs[] = node.toCharArray();

      for (char ch ='a'; ch <= 'z'; ch++) {
          for (int i = 0; i < chs.length; i++) {
              if (chs[i] == ch) continue;
              char old_ch = chs[i];
              chs[i] = ch;
              if (dict.contains(String.valueOf(chs))) {
                  res.add(String.valueOf(chs));
              }
              chs[i] = old_ch;
          }

      }
      return res;
    }

    // DFS: output all paths with the shortest distance.
    private void dfs(String cur, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance, ArrayList<String> solution, List<List<String>> res) {
        solution.add(cur);
        if (end.equals(cur)) {
           res.add(new ArrayList<String>(solution));
        } else {
           for (String next : nodeNeighbors.get(cur)) {
                if (distance.get(next) == distance.get(cur) + 1) {
                     dfs(next, end, dict, nodeNeighbors, distance, solution, res);
                }
            }
        }
       solution.remove(solution.size() - 1);
    }
}