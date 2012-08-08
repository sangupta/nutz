/**
 *
 * nutz - Markdown processor for JVM
 * Copyright (c) 2012, Sandeep Gupta
 * 
 * http://www.sangupta/projects/nutz
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.sangupta.nutz.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author sangupta
 *
 */
public abstract class Node {
	
	protected static final String NEW_LINE = System.getProperty("line.separator");
	
	protected List<Node> children = null;
	
	public void addChild(Node node) {
		if(node == null) {
			throw new IllegalArgumentException("Cannot add a null child node.");
		}
		
		if(node instanceof RootNode) {
			throw new IllegalArgumentException("Cannot add a type of root node.");
		}
		
		if(this.children == null) {
			this.children = new ArrayList<Node>();
		}
		
		this.children.add(node);
	}
	
	public boolean hasChild() {
		if(this.children != null && !this.children.isEmpty()) {
			return true;
		}
		
		return false;
	}
	
	public Node firstNode() {
		if(hasChild()) {
			return this.children.get(0);
		}
		
		return null;
	}
	
	public Node lastNode() {
		if(hasChild()) {
			return this.children.get(this.children.size() - 1);
		}
		
		return null;
	}
	
	/**
	 * Replace the older node with the newer node
	 * 
	 * @param oldNode
	 * @param newNode
	 */
	public void replaceNode(Node oldNode, Node newNode) {
		for(int index = 0; index < this.children.size(); index++) {
			Node node = this.children.get(index);
			if(node == oldNode) {
				this.children.add(index, newNode);
				this.children.remove(oldNode);
			}
		}
	}
	
	public void write(StringBuilder builder, boolean atRootNode, Map<String, AnchorNode> referenceLinks) {
		builder.append(this.toString());
	}
	
	/**
	 * Debug function that prints the tree of this Node
	 * 
	 */
	public void print(int level) {
		for(int i = 0; i < (level * 4); i++) {
			System.out.print(" ");
		}
		
		System.out.println(this.toString());

		if(!(this instanceof ParagraphNode) && this.children != null && this.children.size() > 0) {
			System.out.print("\n");
			for(Node node : this.children) {
				node.print(level + 1);
			}
		}

		System.out.print("\n");
	}

	// Usual accessors follow

	/**
	 * @return the children
	 */
	public List<Node> getChildren() {
		return children;
	}
	
}
