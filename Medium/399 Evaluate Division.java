/*
This question is from https://leetcode.com/problems/evaluate-division/
Difficulty: medium

Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].


The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
*/

// T:O(N * q), S:O(N),
class Solution {

    HashMap<String, HashMap<String, Double>> graph;

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        graph = getGraph(equations, values);

        double[] ans = new double[queries.length];

        for(int i = 0; i < queries.length; i++){
            HashSet<String> visited = new HashSet();
            ans[i] = dfs(queries[i][0], queries[i][i], visited);
        }

        return ans;
    }


    private double dfs(String a, String b, HashSet<String> visited){

        if(!graph.containsKey(a)){
            return -1.0
        }

        if(graph.get(a).containsKey(b)){
            return graph.get(a).get(b);
        }

        visited.add(a);

        for(Map.Entry<String, Double> neighbor: graph.get(a).entrySet()){

            if(!visited.contains(neighbor.getKey())){
                double val = dfs(neighbor.getKey(), b, visited);
                if(val != -1.0) return val * neighbor.getValue();
            }
        }

        return -1.0;
    }


    private HashMap<String, HashMap<String, Double>> getGraph(String[][] equations, double[] values){
        HashMap<String, HashMap<String, Double>> g = new HashMap();

        for(int i = 0; i < values.length; i++){
            String a = equations[i][0];
            String b = equations[i][1];

            // a -> b
            HashMap<String, Double> pathAB = g.getOrDefault(a, new HashMap<String, Double>());
            pathAB.put(b, values[i]);
            g.put(a, pathAB);
            // b -> a
            HashMap<String, Double> pathBA = g.getOrDefault(b, new HashMap<String, Double>());
            pathBA.put(a, 1 / values[i]);
            g.put(b, pathBA);
        }

        return g;
    }
}