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

import com.sangupta.nutz.TextNodeParser;

/**
 * 
 * @author sangupta
 *
 */
public class StrongNode extends TextNode {
	
	private TextNode textNode;
	
	public StrongNode(Node parent) {
		super(parent);
	}
	
	public StrongNode(Node parent, String text) {
		super(parent);
		this.textNode = new TextNodeParser().parse(this, text);
	}
	
	public StrongNode(Node parent, TextNode textNode) {
		super(parent);
		this.textNode = textNode;
	}
	
	public void setTextNode(TextNode textNode) {
		this.textNode = textNode;
	}
	
	@Override
	public void write(StringBuilder builder, boolean atRootNode, Map<String, AnchorNode> referenceLinks) {
		builder.append("<strong>");
		this.textNode.write(builder, false, referenceLinks);
		builder.append("</strong>");
	}
	
	@Override
	public String toString() {
		return "<strong>" + textNode.toString() + "</strong>";
	}

	/**
	 * @return the textNode
	 */
	public TextNode getTextNode() {
		return textNode;
	}

}
