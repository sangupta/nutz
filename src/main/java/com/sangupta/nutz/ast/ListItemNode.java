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

import java.util.Map;

/**
 * Encapsulates a list item in an ordered, or un-ordered HTML list.
 * 
 * @author sangupta
 *
 */
public class ListItemNode extends TextNode {
	
	private boolean looseItems = false;

	public ListItemNode(Node parent) {
		super(parent);
	}
	
	@Override
	public void write(StringBuilder builder, boolean atRootNode, Map<String, AnchorNode> referenceLinks) {
		if(this.children == null || this.children.isEmpty()) {
			return;
		}
		
		// remove all new line nodes from the end
		// we don't need them
		Node lastNode;
		while((lastNode = lastNode()) instanceof NewLineNode) {
			this.children.remove(lastNode);
		}
		
		// output all nodes
		if(this.looseItems) {
			builder.append("<p>");
		}
		
		boolean alreadyClosed = false;
		for(int index = 0; index < this.children.size(); index++) {
			Node node = this.children.get(index);
			
			// check if this is an abstract list and the previous node was plain text node
			if(node instanceof AbstractListNode) {
				if(this.looseItems) {
					// close down the tag
					builder.append("</p>");
					alreadyClosed = true;
				}
			} else {
				if(this.looseItems && alreadyClosed) {
					builder.append("<p>");
					alreadyClosed = false;
				}
			}
			
			node.write(builder, atRootNode, referenceLinks);
			
			// check if we have double new line nodes
			// we need a para inserted
			if(node instanceof NewLineNode) {
				if(index + 1 < this.children.size() && this.children.get(index + 1)  instanceof NewLineNode && !(this.children.get(index + 2) instanceof AbstractListNode)) {
					builder.append("</p>");
					builder.append(NEW_LINE);
					builder.append("<p>");
					index++;
				}
			}
		}
		
		if(this.looseItems && !alreadyClosed) {
			builder.append("</p>");
		}
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("[PARA: ");
		
		if(this.children != null) {
			for(Node node : this.children) {
				b.append(node.toString());
				b.append(",");
			}
		}
		
		b.append("]");
		return b.toString();
	}

	/**
	 * @param looseItems the looseItems to set
	 */
	public void setLooseItems(boolean looseItems) {
		this.looseItems = looseItems;
	}
}
