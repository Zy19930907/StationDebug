package com.zou.tools;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class MyTree extends JTree{
	private static final long serialVersionUID = 1L;
	DefaultMutableTreeNode rootNode;
	DefaultTreeCellRenderer render = new DefaultTreeCellRenderer();
	private List<NodeObject> nodeObjects = new ArrayList<NodeObject>();
	@Override
	public void setModel(TreeModel newModel) {
		super.setModel(newModel);
	}
	
	public MyTree(String rootString) {
		rootNode = new DefaultMutableTreeNode(rootString);
		setModel(new DefaultTreeModel(rootNode, true));
	}
	
	public void AddNode(String nString,Object userObject) {
		NodeObject object = new NodeObject();
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(nString);
		object.setNode(node);
		object.setNodeString(nString);
		object.setUserObject(userObject);
		if(!hasNode(nString)) {
			rootNode.add(object.getNode());
			setSelectionPath(new TreePath(node.getPath()));
			nodeObjects.add(object);
			updateUI();
		}
	}
	
	public void removeNode(String nodeString) {
		int i;
		for(i=0;i<nodeObjects.size();i++) {
			if(nodeObjects.get(i).getNodeString().equals(nodeString)) {
				rootNode.remove(nodeObjects.get(i).getNode());
				nodeObjects.remove(i);
				updateUI();
				return;
			}
		}
	}
	
	public Object getSelectObject() {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)getLastSelectedPathComponent();
		for(NodeObject object : nodeObjects) {
			if(object.getNode().equals(node))
				return object.getUserObject();
		}
		return null;
	}
	
	private boolean hasNode(String noString) {
		int i;
		for(i=0;i<nodeObjects.size();i++) {
			if(nodeObjects.get(i).getNodeString().equals(noString)) 
				return true;
		}
		return false;
	}
}
