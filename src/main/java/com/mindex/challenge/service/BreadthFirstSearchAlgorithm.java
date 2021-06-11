package com.mindex.challenge.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.mindex.challenge.data.Node;

public class BreadthFirstSearchAlgorithm {

	private Queue<Node> queue;
	public BreadthFirstSearchAlgorithm() {
    	queue = new LinkedList<Node>();
    }
    public int bfs(Node node)
    {
    	int count = -1;
        queue.add(node);
        node.setVisited(true);
        while (!queue.isEmpty())
        { 
            Node element=queue.remove();
            count++;
            
            List<Node> neighbours=element.getChildren();
 
            for (int i = 0; i < neighbours.size(); i++) {
            	
                Node n=neighbours.get(i);
                if(n!=null && !n.isVisited())
                {
                    queue.add(n);
                    n.setVisited(true);
                }
                else {count++;}
            }
 
        }
        return count;
    }
 
}
