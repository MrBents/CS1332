import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.HashMap;


/**
 * Your implementations of various graph algorithms.
 *
 * @author Max Bentata
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Perform breadth first search on the given graph, starting at the start
     * Vertex.  You will return a List of the vertices in the order that
     * you visited them.  Make sure to include the starting vertex at the
     * beginning of the list.
     *
     * When exploring a Vertex, make sure you explore in the order that the
     * adjacency list returns the neighbors to you.  Failure to do so may
     * cause you to lose points.
     *
     * You may import/use {@code java.util.Queue}, {@code java.util.Set},
     * {@code java.util.Map}, {@code java.util.List}, and any classes
     * that implement the aforementioned interfaces.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph you are traversing
     * @param <T> the data type representing the vertices in the graph.
     * @return a List of vertices in the order that you visited them
     */
    public static <T> List<Vertex<T>> breadthFirstSearch(Vertex<T> start,
            Graph<T> graph) {
                if (start == null || graph == null) {
                    throw new IllegalArgumentException("Inputs cannot be null");
                }
                LinkedList<Vertex<T>> list = new LinkedList<Vertex<T>>();
                list.add(start);
                HashSet<Vertex<T>> set = new HashSet<Vertex<T>>();
                set.add(start);
                bfs(start, graph, list, set);
                return list;

    }

    /**
    * @param cur the Vertex to be analyzed
    * @param g the Graph you are traversing
    * @param <T> the data type representing the vertices in the graph.
    * @param list the list to add the vetices as they're traversed
    * @param visited the set that keeps track of visited nodes
    * @return a List of vertices in the order that you visited them
    */
    private static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> g, LinkedList<Vertex<T>> list, HashSet<Vertex<T>> visited) {
        Map<Vertex<T>, List<VertexDistancePair<T>>> amap = g.getAdjacencyList();
        List<VertexDistancePair<T>> alist = amap.get(start);
        VertexDistancePair<T> cur;
        T curData;
        LinkedList<LinkedList<Vertex<T>>> temp = new LinkedList<LinkedList<Vertex<T>>>();
        LinkedList<Vertex<T>> tempo = new LinkedList<Vertex<T>>();

        for (int i = 0; i < alist.size(); i++) {
            cur = alist.get(i);

            if (!(visited.contains(cur.getVertex()))) {
                tempo.add(cur.getVertex());
                list.add(cur.getVertex());
                visited.add(cur.getVertex());
            }
            temp.add(tempo);
        }

        while(temp.get(0).size() > 0){
            tempo = new LinkedList<Vertex<T>>();
            for (int x = 0; x < temp.get(0).size(); x++) {
                // for (int i = 0; i < temp.size(); i++) {
                    alist = amap.get(temp.get(0).get(x));
                    for (int n = 0; n < alist.size(); n++) {
                        cur = alist.get(n);

                        if (!(visited.contains(cur.getVertex()))) {
                            tempo.add(cur.getVertex());
                            list.add(cur.getVertex());
                            visited.add(cur.getVertex());
                        }
                    }
                // }
            }
            temp.remove(0);
            temp.add(tempo);
        }

        return list;

    }

    /**
     * Perform depth first search on the given graph, starting at the start
     * Vertex.  You will return a List of the vertices in the order that
     * you visited them.  Make sure to include the starting vertex at the
     * beginning of the list.
     *
     * When exploring a Vertex, make sure you explore in the order that the
     * adjacency list returns the neighbors to you.  Failure to do so may
     * cause you to lose points.
     *
     * You MUST implement this method recursively.
     * Do not use any data structure as a stack to avoid recursion.
     * Implementing it any other way WILL cause you to lose points!
     *
     * You may import/use {@code java.util.Set}, {@code java.util.Map},
     * {@code java.util.List}, and any classes that implement the
     * aforementioned interfaces.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph you are traversing
     * @param <T> the data type representing the vertices in the graph.
     * @return a List of vertices in the order that you visited them
     */
    public static <T> List<Vertex<T>> depthFirstSearch(Vertex<T> start,
            Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("Inputs cannot be null");
        }
        LinkedList<Vertex<T>> list = new LinkedList<Vertex<T>>();
        list.add(start);
        HashSet<Vertex<T>> set = new HashSet<Vertex<T>>();
        set.add(start);
        dfs(start, graph, list, set);
        return list;

    }

    /**
    * @param cur the Vertex to be analyzed
    * @param g the Graph you are traversing
    * @param <T> the data type representing the vertices in the graph.
    * @param list the list to add the vetices as they're traversed
    * @param visited the set that keeps track of visited nodes
    * @return a List of vertices in the order that you visited them
    */
    private static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> g, LinkedList<Vertex<T>> list, HashSet<Vertex<T>> visited) {
        Map<Vertex<T>, List<VertexDistancePair<T>>> amap = g.getAdjacencyList();
        List<VertexDistancePair<T>> alist = amap.get(start);
        VertexDistancePair<T> cur;
        T curData;

        for (int i = 0; i < alist.size(); i++) {
            cur = alist.get(i);

            if (!(visited.contains(cur.getVertex()))) {
                list.add(cur.getVertex());
                visited.add(cur.getVertex());
                dfs(cur.getVertex(), g, list, visited);
            }

        }

        return list;

    }

    /**
     * Find the shortest distance between the start vertex and all other
     * vertices given a weighted graph where the edges only have positive
     * weights.
     *
     * Return a map of the shortest distances such that the key of each entry is
     * a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing infinity)
     * if no path exists. You may assume that going from a vertex to itself
     * has a distance of 0.
     *
     * There are guaranteed to be no negative edge weights in the graph.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and any class that implements the aforementioned
     * interface.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph you are searching
     * @param <T> the data type representing the vertices in the graph.
     * @return a map of the shortest distances from start to every other node
     *         in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
            Graph<T> graph) {

        if (graph == null || start == null) {
            throw new java.lang.IllegalArgumentException(
                    "Inputs cannot be null");
        }
        Map<Vertex<T>, List<VertexDistancePair<T>>> alist =
                graph.getAdjacencyList();
        if (!alist.containsKey(start)) {
            throw new java.lang.IllegalArgumentException(
                    "Start has to be in the graph");
        }

        Map<Vertex<T>, Integer> map = new HashMap<>();
        for (Vertex<T> j : alist.keySet()) {
            map.put(j, Integer.MAX_VALUE);
        }
        return dijk(start, alist, map, 0);

    }

    private static <T> Map<Vertex<T>, Integer> dijk(Vertex<T> cur,
                                              Map<Vertex<T>,
                                              List<VertexDistancePair<T>>> alist,
                                              Map<Vertex<T>, Integer> map,
                                              int dist) {

        if (map.get(cur).compareTo(dist) > 0) {

            map.replace(cur, dist);

            List<VertexDistancePair<T>> adj = alist.get(cur);
            for (int i = 0; i < adj.size(); i++) {

                map = dijk(adj.get(i).getVertex(), alist, map,
                            dist + adj.get(i).getDistance());

            }
        }
        return map;
    }

    /**
     * Run Prim's algorithm on the given graph and return the minimum spanning
     * tree in the form of a set of Edges.  If the graph is disconnected, and
     * therefore there is no valid MST, return null.
     *
     * When exploring a Vertex, make sure you explore in the order that the
     * adjacency list returns the neighbors to you.  Failure to do so may
     * cause you to lose points.
     *
     * You may assume that for a given starting vertex, there will only be
     * one valid MST that can be formed. In addition, only an undirected graph
     * will be passed in.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, and any class that implements the aforementioned
     * interface.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph you are creating the MST for
     * @param <T> the data type representing the vertices in the graph.
     * @return the MST of the graph; null if no valid MST exists.
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        return new HashSet<Edge<T>>();
    }

}
