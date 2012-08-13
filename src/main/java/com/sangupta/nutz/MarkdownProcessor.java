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

package com.sangupta.nutz;

import java.io.IOException;
import java.io.Reader;

import com.sangupta.nutz.ast.RootNode;

/**
 * Markdown processor that converts Markdown syntax into valid
 * HTML code. The processor first parses the markup and generates
 * an Abstract-Syntax tree before tranforming and emitting the
 * HTML code. This allows for easier extension and validity of
 * parsing.
 * 
 * @author sangupta
 * @since 0.1
 */
public class MarkdownProcessor {

	/**
	 * Parse the given markup text and construct the syntax-tree.
	 * 
	 * @param markup
	 * @return
	 * @throws Exception
	 */
	public RootNode parse(String markup) throws IOException {
		return new Parser().parse(markup);
	}
	
	/**
	 * Parse the markup from given reader and construct the syntax-tree.
	 * 
	 * @param reader
	 * @return
	 * @throws IOException
	 */
	public RootNode parse(Reader reader) throws IOException {
		return new Parser().parse(reader);
	}
	
	/**
	 * Parse the given markup text and emit the HTML code.
	 * 
	 * @param markup
	 * @return
	 * @throws Exception
	 */
	public String toHtml(String markup) throws IOException {
		RootNode node = new Parser().parse(markup);
		return new HtmlEmitter().toHtml(node);
	}
	
	/**
	 * Parse the markup from the given reader and emit the HTML code.
	 *  
	 * @param reader
	 * @return
	 * @throws IOException
	 */
	public String toHtml(Reader reader) throws IOException {
		RootNode node = new Parser().parse(reader);
		return new HtmlEmitter().toHtml(node);
	}
	
}
