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

import com.sangupta.nutz.Identifiers;
import com.sangupta.nutz.TextNodeParser;

/**
 * 
 * @author sangupta
 *
 */
public class AnchorNode extends TextNode {
	
	private TextNode text;
	
	private String url;
	
	private String title;
	
	private boolean onID;
	
	public AnchorNode(Node parent, String url) {
		super(parent);
		this.text = null;
		this.url = url;
		this.title = null;
	}
	
	public AnchorNode(Node parent, String text, String url, String title, boolean onID) {
		super(parent);
		if(text != null) {
			this.text = new TextNodeParser().parse(this, text);
		}
		this.url = url;
		this.title = title;
		this.onID = onID;
	}
	
	@Override
	public void write(StringBuilder builder, boolean atRootNode, Map<String, AnchorNode> referenceLinks) {
		builder.append("<a href=\"");
		
		if(onID) {
			AnchorNode anchor = referenceLinks.get(this.url);
			if(anchor != null) {
				this.url = anchor.getUrl();
				this.title = anchor.getTitle();
			}
		}

		builder.append(this.url);
		builder.append("\"");
		
		// check for title
		if(this.title != null) {
			builder.append(" title=\"");
			
			char ch;
			char[] array = this.title.toCharArray();
			for(int index = 0; index < array.length; index++) {
				ch = array[index];
				switch(ch) {
					case Identifiers.AMPERSAND:
						builder.append("&amp;");
						break;
						
					default:
						builder.append(ch);
						break;
				}
			}
			
			builder.append("\"");
		}
		
		builder.append(">");
		
		// write the text for anchor
		if(this.text != null) {
			this.text.write(builder, false, referenceLinks);
		} else {
			builder.append(this.url);
		}
		
		// close the anchor
		builder.append("</a>");
	}
	
	@Override
	public String toString() {
		return "[ANCHOR: " + this.url + "]";
	}

	/**
	 * @return the text
	 */
	public TextNode getText() {
		return text;
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
