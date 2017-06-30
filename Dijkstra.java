import java.util.*;

public class Dijkstra {
    public static class Node implements Comparable<Node>{
        int index;
        int dist;
               Node(int index,int dist){
                   this.index=index;
                   this.dist=dist;
               }

        @Override
        public int compareTo(Node t) {
           if(this.dist>t.dist){
             return 1;
           }
           else if(this.dist<t.dist){
            return -1;
           }
           else
               return 0;
        }
       
        
    }
    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        int dist[] = new int[adj.length];
        int next[] = new int[adj.length];
        PriorityQueue<Node> Queue = new PriorityQueue<Node>();
        for(int i =0;i<adj.length;i++){
            dist[i]=Integer.MAX_VALUE; 
        }
        Queue.add(new Node(s,dist[s]));
        dist[s]=0;
        while(!Queue.isEmpty()){
            Node u =Queue.remove();
            for(int v :adj[u.index]){
                if(dist[v]>dist[u.index]+cost[u.index].get(adj[u.index].indexOf(v))){
                    dist[v]=dist[u.index]+cost[u.index].get(adj[u.index].indexOf(v));
                    Queue.add(new Node(v,dist[v]));
                }
                
            }
        }
        if(dist[t]==Integer.MAX_VALUE)
        return -1;
        
      return dist [t];  
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}

private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
       int dist[]= new int[adj.length];
       int next[]= new int[adj.length];
       for(int i =0 ; i<adj.length;i++)
           dist[i]=Integer.MAX_VALUE;
       
       dist[0]=0;
       for(int i =0 ; i <adj.length;i++){
            for(int u=0 ;u<adj.length;u++){
                for(int v:adj[u]){
                    if(dist[v]>dist[u]+cost[u].get(adj[u].indexOf(v))){
                        dist[v]=dist[u]+cost[u].get(adj[u].indexOf(v));
                        if( i==adj.length-1)
                            return 1;
                    }
                }
            }
       }
          
        return 0;
    }