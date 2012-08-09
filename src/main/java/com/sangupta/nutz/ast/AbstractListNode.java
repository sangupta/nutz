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
 * 
 * @author sangupta
 *
 */
public abstract class AbstractListNode extends Node {
	
	private String tagName;
	
	protected AbstractListNode(String tagName) {
		this.tagName = tagName;
	}
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder(1024);
		b.append(this.tagName);
		b.append(":\n");
		for(Node node : this.children) {
			b.append(node.toString());
			b.append("\n");
		}
		
		return b.toString();
	}
	
	@Override
	public void write(StringBuilder builder, boolean atRootNode, Map<String, AnchorNode> referenceLinks) {
		builder.append('<');
		builder.append(this.tagName);
		builder.append('>');
		builder.append(NEW_LINE);
		
		for(Node node : this.children) {
			builder.append("<li>");
			node.write(builder, true, referenceLinks);
			builder.append("</li>");
			builder.append(NEW_LINE);
		}
		
		builder.append("</");
		builder.append(this.tagName);
		builder.append('>');

		builder.append(NEW_LINE);
		builder.append(NEW_LINE);
	}
	
}
