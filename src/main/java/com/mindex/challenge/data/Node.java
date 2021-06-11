package com.mindex.challenge.data;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Node{
	 
	 private String data = null;
	 
	 private List<Node> children = new ArrayList<>();
	 
	 private Node parent = null;
	 
	 private boolean visited = false;
	 
	 @JsonCreator
	 public Node(String data) {
		 this.data = data;
	 }
	 
	 public Node addChild(Node child) {
		 child.setParent(this);
		 this.children.add(child);
		 return child;
	 }
	 
	 public void addChildren(List<Node> children) {
		 children.forEach(each -> each.setParent(this));
		 this.children.addAll(children);
	 }
	 
	 public List<Node> getChildren() {
		 return children;
	 }
	 
	 public String getData() {
		 return data;
	 }
	 
	 public void setData(String data) {
		 this.data = data;
	 }
	 
	 private void setParent(Node parent) {
		 this.parent = parent;
	 }
	 
	 public Node getParent() {
		 return parent;
	 }

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	 
}