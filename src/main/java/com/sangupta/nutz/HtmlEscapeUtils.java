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
 * 
 * @author sangupta
 *
 */
public class HtmlEscapeUtils {
	
	public static void writeEscapedLine(final String line, StringBuilder builder) {
		char ch;
		char[] array = line.toCharArray();
		for(int index = 0; index < array.length; index++) {
			ch = array[index];
			switch(ch) {
				case Identifiers.AMPERSAND:
					builder.append("&amp;");
					break;
					
				case Identifiers.HTML_OR_AUTOLINK_START:
					builder.append("&lt;");
					break;
					
				case Identifiers.HTML_OR_AUTOLINK_END:
					builder.append("&gt;");
					break;
					
				default:
					builder.append(ch);
					break;
			}
		}
	}

}
