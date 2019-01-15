package com.zou.tools;

import javax.swing.tree.DefaultMutableTreeNode;

public class NodeObject {
	private String nodeString;
	private DefaultMutableTreeNode node;
	private Object userObject;
	
	public String getNodeString() {
		return nodeString;
	}
	public void setNodeString(String nodeString) {
		this.nodeString = nodeString;
	}
	public DefaultMutableTreeNode getNode() {
		return node;
	}
	public void setNode(DefaultMutableTreeNode node) {
		this.node = node;
	}
	public Object getUserObject() {
		return userObject;
	}
	public void setUserObject(Object userObject) {
		this.userObject = userObject;
	}
}
