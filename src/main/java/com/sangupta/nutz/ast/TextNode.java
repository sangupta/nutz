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

/**
 * 
 * @author sangupta
 *
 */
public abstract class TextNode extends Node {
	
	protected Node parent;
	
	public TextNode(Node parent) {
		this.parent = parent;
	}

	/**
	 * @return the parent
	 */
	public Node getParent() {
		return parent;
	}

	public String getPlainText() {
		StringBuilder builder = new StringBuilder(512);
		
		if(this.children != null) {
			for(Node child : this.children) {
				if(child instanceof TextNode) {
					builder.append(((TextNode) child).getPlainText());
				} else {
					builder.append(child.toString());
				}
			}
		}
		
		return builder.toString();
	}

}
