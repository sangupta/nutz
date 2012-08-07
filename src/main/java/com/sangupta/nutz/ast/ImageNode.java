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
public class ImageNode extends Node {
	
	private String url;
	
	private String alternateText;
	
	private String title;
	
	private boolean onID;
	
	public ImageNode(String url, String alternateText, boolean onID) {
		this.url = url;
		this.alternateText = alternateText;
		this.onID = onID;
	}
	
	public ImageNode(String url, String alternateText, String title) {
		this(url, alternateText, false);
		this.title = title;
	}
	
	@Override
	public void write(StringBuilder builder, boolean atRootNode, Map<String, AnchorNode> referenceLinks) {
		if(onID) {
			AnchorNode node = referenceLinks.get(this.url);
			if(node != null) {
				this.url = node.getUrl();
				this.title = node.getTitle();
			}
		}
		
		
		builder.append("<img src=\"");
		builder.append(url);
		builder.append("\"");
		
		// check for alternate value
		if(this.alternateText != null) {
			builder.append(" alt=\"");
			builder.append(this.alternateText);
			builder.append("\"");
		}
		
		// always add a title
		if(this.title == null) {
			this.title = "";
		}
		
		if(!onID || !this.title.isEmpty()) {
			builder.append(" title=\"");
			builder.append(this.title);
			builder.append("\"");
		}
		
		// close the tag
		builder.append(" />");
	}
	
	@Override
	public String toString() {
		return "IMAGE: " + this.url;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

}
