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

// T:O(N * q), S:O(N), 1 ms
class Solution {

    HashMap<String, HashMap<String, Double>> graph;

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        graph = getGraph(equations, values);
        double[] ans = new double[queries.size()];

        for(int i = 0; i < queries.size(); i++){
            String top = queries.get(i).get(0);
            String bottom = queries.get(i).get(1);
            HashSet<String> set = new HashSet();
            ans[i] = dfs(top, bottom, set);
        }


        return ans;
    }

    private double dfs(String top, String bottom, HashSet<String> set){
        if(!graph.containsKey(top)) return -1;

        if(graph.get(top).containsKey(bottom))
            return graph.get(top).get(bottom);

        set.add(top);
        for(Map.Entry<String, Double> entry: graph.get(top).entrySet()){
            String newTop = entry.getKey();
            if(!set.contains(newTop)){
                double val = dfs(newTop, bottom, set);
                if( val != -1) return val * entry.getValue();
            }

        }

        return -1;
    }

    private HashMap<String, HashMap<String, Double>> getGraph(List<List<String>> equations, double[] values){

        HashMap<String, HashMap<String, Double>> g = new HashMap();

        for(int i = 0; i < equations.size(); i++){
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            // a->b
            HashMap<String, Double> ab = g.getOrDefault(a, new HashMap<String, Double>());
            ab.put(b, values[i]);
            g.put(a, ab);
            // b -> a
            HashMap<String, Double> ba = g.getOrDefault(b, new HashMap<String, Double>());
            ba.put(a, 1 / values[i]);
            g.put(b, ba);
        }

        return g;
    }
}