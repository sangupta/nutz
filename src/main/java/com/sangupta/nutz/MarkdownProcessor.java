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
	
	private Parser parser;
	
	private HtmlEmitter htmlEmitter;
	
	/**
	 * Construct an instance of markdown processor using the
	 * default processing options.
	 */
	public MarkdownProcessor() {
		this.parser = new Parser();
		this.htmlEmitter = new HtmlEmitter();
	}
	
	/**
	 * Construct an instance of markdown processor using the
	 * given processing options.
	 * 
	 * @param options
	 */
	public MarkdownProcessor(ProcessingOptions options) {
		this.parser = new Parser(options);
		this.htmlEmitter = new HtmlEmitter(options);
	}
	
	/**
	 * Parse the given markup text and construct the syntax-tree.
	 * 
	 * @param markup
	 * @return
	 * @throws Exception
	 */
	public RootNode parse(String markup) throws IOException {
		return this.parser.parse(markup);
	}
	
	/**
	 * Parse the markup from given reader and construct the syntax-tree.
	 * 
	 * @param reader
	 * @return
	 * @throws IOException
	 */
	public RootNode parse(Reader reader) throws IOException {
		return this.parser.parse(reader);
	}
	
	/**
	 * Parse the given markup text and emit the HTML code.
	 * 
	 * @param markup
	 * @return
	 * @throws Exception
	 */
	public String toHtml(String markup) throws IOException {
		RootNode node = this.parser.parse(markup);
		return this.htmlEmitter.toHtml(node);
	}
	
	/**
	 * Parse the given markup text and emit the HTML code using the given
	 * processing options (overriding any previous options).
	 * 
	 * @param markup
	 * @param options
	 * @return
	 * @throws IOException
	 */
	public static String toHtml(String markup, ProcessingOptions options) throws IOException {
		RootNode node = new Parser(options).parse(markup);
		return new HtmlEmitter(options).toHtml(node);
	}
	
	/**
	 * Parse the markup from the given reader and emit the HTML code.
	 *  
	 * @param reader
	 * @return
	 * @throws IOException
	 */
	public String toHtml(Reader reader) throws IOException {
		RootNode node = this.parser.parse(reader);
		return this.htmlEmitter.toHtml(node);
	}
	
	/**
	 * Parse the markup from the given reader and emit the HTML code using the
	 * given processing options (overriding any previous options).
	 * 
	 * @param reader
	 * @param options
	 * @return
	 * @throws IOException
	 */
	public static String toHtml(Reader reader, ProcessingOptions options) throws IOException {
		RootNode node = new Parser(options).parse(reader);
		return new HtmlEmitter(options).toHtml(node);
	}
	
	/**
	 * Parse the markup from the given reader and append the html to the given writer.
	 *
	 * @param reader
	 * @param writer
	 * @return
	 * @throws IOException
	 */
	public <T extends Appendable> T toHtml(Reader reader, T writer) throws IOException {
		RootNode node = this.parser.parse(reader);
		return this.htmlEmitter.toHtml(node, writer);
	}
	
	/**
	 * Parse the markup from the given reader and append the html to the given writer
	 * using the given processing options (overriding any previous options).
	 *
	 * @param reader
	 * @param writer
	 * @param options
	 * @return
	 * @throws IOException
	 */
	public <T extends Appendable> T toHtml(Reader reader, T writer, ProcessingOptions options) throws IOException {
		RootNode node = this.parser.parse(reader);
		return new HtmlEmitter(options).toHtml(node, writer);
	}
	
}
