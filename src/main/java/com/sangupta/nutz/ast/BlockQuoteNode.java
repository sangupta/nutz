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

import com.sangupta.nutz.ProcessingOptions;

/**
 * 
 * @author sangupta
 *
 */
public class BlockQuoteNode extends Node {
	
	public BlockQuoteNode(RootNode rootNode) {
		this.children = rootNode.getChildren();
	}
	
	@Override
	public void write(StringBuilder builder, boolean atRootNode, Map<String, AnchorNode> referenceLinks, ProcessingOptions options) {
		if(this.children == null || this.children.isEmpty()) {
			return;
		}
		
		builder.append("<blockquote>");
		builder.append(NEW_LINE);
		
		for(Node child : this.children) {
			child.write(builder, atRootNode, referenceLinks, options);
		}
		
		builder.append(NEW_LINE);
		builder.append("</blockquote>");
	}

}
