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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.sangupta.nutz.ProcessingOptions;

/**
 * Represent the root node of the Abstract-Syntax Tree.
 * 
 * @author sangupta
 *
 */
public class RootNode extends Node {
	
	/**
	 * Reference links that need to be stored for this tree. These are later used while rendering.
	 */
	private final Map<String, AnchorNode> referenceLinks = new HashMap<String, AnchorNode>();
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder(1024);
		b.append("ROOT\n");
		
		for(Entry<String, AnchorNode> entry : this.referenceLinks.entrySet()) {
			b.append(entry.getKey());
			b.append(": ");
			b.append(entry.getValue().getUrl());
			b.append('\n');
		}
		
		return b.toString();
	}
	
	@Override
	public void write(StringBuilder builder, boolean atRootNode, Map<String, AnchorNode> referenceLinks, ProcessingOptions options) {
		if(this.children != null && this.children.size() > 0) {
			for(Node node : this.children) {
				node.write(builder, true, this.referenceLinks, options);
			}
		}
	}
	
	public void addReferenceLink(String id, String link, String title) {
		this.referenceLinks.put(id, new AnchorNode(null, null, link, title, false, 0));
	}
	
	/**
	 * Return the Anchor node associated with the given ID as might have been
	 * saved as a reference link earlier.
	 * 
	 * @param id
	 *            lookup identifier
	 * 
	 * @return associated {@link AnchorNode} if any
	 */
	public AnchorNode getLink(String id) {
		return this.referenceLinks.get(id);
	}

	/**
	 * @return the referenceLinks
	 */
	public Map<String, AnchorNode> getReferenceLinks() {
		return referenceLinks;
	}
	
}
