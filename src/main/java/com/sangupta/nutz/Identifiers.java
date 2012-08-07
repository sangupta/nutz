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

/**
 * Static constants that are used as identifiers in the markdown
 * format.
 * 
 * @author sangupta
 *
 */
public interface Identifiers {

	public static final char SPACE = ' ';
	
	public static final char ESCAPE_CHARACTER = '\\';
	
	public static final char CODE_MARKER = '`';
	
	public static final char ITALIC_OR_BOLD = '*';
	
	public static final char LINK_START = '[';
	
	public static final char LINK_END = ']';
	
	public static final char HREF_START = '(';
	
	public static final char HREF_END = ')';
	
	public static final char ITALIC_OR_BOLD_UNDERSCORE = '_';
	
	public static final char DOUBLE_QUOTES = '\"';
	
	public static final char EXCLAIMATION = '!';
	
	public static final String IMAGE_IDENTIFIER = "![";
	
	public static final char HTML_OR_AUTOLINK_START = '<';
	
	public static final char HTML_OR_AUTOLINK_END = '>';
	
	public static final char AMPERSAND = '&';
	
	public static final char TAB = '\t';
	
}
