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
import com.sangupta.nutz.ProcessingOptions;
import com.sangupta.nutz.ProcessingOptions.SyntaxHighlightingOption;
import com.sangupta.pepmint.Formatter;
import com.sangupta.pepmint.Lexer;
import com.sangupta.pepmint.Pepmint;

/**
 * Represents a fenced code block in the AST.
 * 
 * @author sangupta
 *
 */
public class CodeBlockNode extends Node {
	
	/**
	 * The language value of this fenced code block - if specified.
	 */
	private String language;
	
	/**
	 * Actual code that is stored in this node.
	 */
	private String code;
	
	public CodeBlockNode(String code, String lang) {
		this.code = code;
		this.language = lang;
	}
	
	@Override
	public void write(StringBuilder builder, boolean atRootNode, Map<String, AnchorNode> referenceLinks, ProcessingOptions options) {
		boolean needsEscaping = true;
		boolean addClosingTags = true;
		String formattedCode = this.code;

		SyntaxHighlightingOption option = options.syntaxHighlightingOption;
		if(this.language == null) {
			option = SyntaxHighlightingOption.None;
		}
		
		switch(option) {
			case SyntaxHighlighter:
				builder.append("<pre class=\"brush: ");
				builder.append(this.language);
				builder.append("\"><code>");
				break;
				
			case Pepmint:
				needsEscaping = false;
				addClosingTags = false;
				
				Pepmint pepmint = new Pepmint();
				Lexer lexer = pepmint.newLexer(this.language);
				Formatter formatter = pepmint.newHtmlFormatter("");
				formattedCode = pepmint.highlight(formattedCode, lexer, formatter);
				break;
				
			case None:
				builder.append("<pre><code>");
				break;
		}
		
		if(needsEscaping) {
			HtmlEscapeUtils.writeEscapedLine(formattedCode, builder);
		} else {
			builder.append(formattedCode);
		}
		
		if(addClosingTags) {
			builder.append("</code></pre>");
		}

		builder.append(NEW_LINE);
		builder.append(NEW_LINE);
	}

	@Override
	public String toString() {
		return "CODE: [" + this.language + "]=" + this.code;
	}

}
