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
import java.util.Map;

/**
 * 
 * @author sangupta
 *
 */
public class ParagraphNode extends TextNode {
	
	private int current = -1;
	
	public ParagraphNode(Node parent) {
		super(parent);
	}
	
	public ParagraphNode(Node parent, String text) {
		super(parent);
		add(new PlainTextNode(parent, text));
	}
	
	public void addChild(TextNode node) {
		add(node);
	}

	public boolean breakIntoTextAndHeading(int i) {
		// find the last non-null index
		if(current < 0) {
			return false;
		}
		
		if(this.children.size() == 1) {
			Node child = this.children.get(0);
			// convert this to heading node
			HeadingNode node = new HeadingNode(i, child);
			this.parent.replaceNode(this, node);
			return true;
		}
		
		// this is a text node
		Node node = this.children.get(current);
		
		// convert this to a heading node
		// TODO: fix this
		
		return false;
	}
	
	private void add(TextNode node) {
		if(this.children == null) {
			this.children = new ArrayList<Node>();
		}
		
		this.children.add(node);
		current++;
	}
	
	@Override
	public void write(StringBuilder builder, boolean atRootNode, Map<String, AnchorNode> referenceLinks) {
		if(this.children == null || this.children.isEmpty()) {
			return;
		}
		
		if(this.children.size() == 1) {
			Node firstNode = firstNode();
			if(firstNode instanceof HtmlCommentNode || firstNode instanceof XmlNode) {
				atRootNode = false;
			}
			
			if(atRootNode) {
				builder.append("<p>");
			}
			
			this.children.get(0).write(builder, atRootNode, referenceLinks);
			
			// The following is only needed for
			// tidy purposes and can be skipped to 
			// pace up
			if(firstNode instanceof XmlNode) {
				builder.append(NEW_LINE);
				builder.append(NEW_LINE);
			}
			
			if(atRootNode) {
				builder.append("</p>");
				builder.append(NEW_LINE);
				builder.append(NEW_LINE);
			}
			
			return;
		}
		
		builder.append("<p>");
		for(Node node : this.children) {
			node.write(builder, atRootNode, referenceLinks);
		}
		
		builder.append("</p>");
		builder.append(NEW_LINE);
		builder.append(NEW_LINE);
	}
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("[PARA: ");
		for(Node node : this.children) {
			b.append(node.toString());
			b.append(",");
		}
		b.append("]");
		return b.toString();
	}

}
