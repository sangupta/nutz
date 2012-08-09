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

/**
 * 
 * @author sangupta
 *
 */
public class SpecialCharacterNode extends TextNode {
	
	private char character;
	
	public SpecialCharacterNode(Node parent, char ch) {
		super(parent);
		this.character = ch;
	}
	
	@Override
	public void write(StringBuilder builder, boolean atRootNode, Map<String, AnchorNode> referenceLinks) {
		builder.append(this.toString());
	}

	/**
	 * @return the character
	 */
	public char getCharacter() {
		return character;
	}

	/**
	 * @param character the character to set
	 */
	public void setCharacter(char character) {
		this.character = character;
	}
	
	@Override
	public String toString() {
		switch(this.character) {
			case Identifiers.AMPERSAND:
				return "&amp;";
				
			case Identifiers.HTML_OR_AUTOLINK_START:
				return "&lt;";
				
			case Identifiers.HTML_OR_AUTOLINK_END:
				return "&gt";
				
			case '\n':
				return "";
		}
		
		return String.valueOf(this.character);
	}

}
