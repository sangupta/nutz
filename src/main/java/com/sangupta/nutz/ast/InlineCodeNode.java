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

import com.sangupta.nutz.HtmlEscapeUtils;

/**
 * 
 * @author sangupta
 *
 */
public class InlineCodeNode extends TextNode {
	
	private String code;
	
	public InlineCodeNode(Node parent, String code) {
		super(parent);
		this.code = code;
	}
	
	@Override
	public void write(StringBuilder builder, boolean atRootNode, Map<String, AnchorNode> referenceLinks) {
		builder.append("<code>");
		HtmlEscapeUtils.writeEscapedLine(this.code, builder);
		builder.append("</code>");
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return "Code: " + this.code;
	}
}
