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

	public boolean breakIntoTextAndHeading(int headingStyle) {
		// remove all ending new line nodes
		Node node;
		while((node = lastNode()) instanceof NewLineNode) {
			this.children.remove(node);
		}
		
		Node child = this.children.get(this.children.size() - 1);
		if(child instanceof PlainTextNode) {
			// convert this to heading node
			node = new HeadingNode(headingStyle, child);
			this.parent.replaceNode(this, node);
			return true;
		}
		
		return false;
	}
	
	private void add(TextNode node) {
		if(this.children == null) {
			this.children = new ArrayList<Node>();
		}
		
		this.children.add(node);
	}
	
	@Override
	public void write(StringBuilder builder, boolean atRootNode, Map<String, AnchorNode> referenceLinks) {
		if(this.children == null || this.children.isEmpty()) {
			return;
		}
		
		Node firstNode = firstNode();
		if(atRootNode) {
			if(firstNode instanceof HtmlCommentNode || firstNode instanceof XmlNode) {
				atRootNode = false;
			}
		}

		// only one element
		if(this.children.size() == 1) {
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
		
		// handle more than one element
		boolean paraStart = false;
		if(atRootNode) {
			builder.append("<p>");
			paraStart = true;
		}

		for(int index = 0; index < this.children.size(); index++) {
			Node node = this.children.get(index);
			
			// two simultaneous new line ndoes
			if(node instanceof NewLineNode) {
				// we check for 2 to make sure that we have something remaining
				// after the two new line nodes - if there is no text
				// after that - there is no point to switch paragrpahs
				if((index + 2) < this.children.size()) {
					if(this.children.get(index + 1) instanceof NewLineNode) {
						builder.append("</p>");
						builder.append(NEW_LINE);
						builder.append("<p>");
						
						index++;
						
						continue;
					}
				}
			}

			if(node instanceof ParagraphNode) {
				if(paraStart) {
					builder.append("</p>");
					builder.append(NEW_LINE);
					builder.append(NEW_LINE);
				}
				builder.append("<p>");
				paraStart = true;
			}
			
			node.write(builder, atRootNode, referenceLinks);
		}
		
		if(atRootNode) {
			if(paraStart) {
				builder.append("</p>");
			}
			
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

}
