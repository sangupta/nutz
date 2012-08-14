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

import com.sangupta.nutz.ast.LineType;

/**
 * Represents a line of text.
 * 
 * @author sangupta
 *
 */
public class TextLine {
	
	String text;
	
	int leadingPosition = -1;
	
	int leadingSpaces = -1;
	
	int length = -1;
	
	LineType lineType = null;
	
	char terminator = 0;
	
	boolean horizontalRule = false;
	
	boolean trimEmpty = false;
	
	boolean isEmpty = false;
	
	boolean isNull = false;
	
	private ProcessingOptions options;
	
	public TextLine(String text, ProcessingOptions options) {
		// save options
		this.options = options;
		
		// setup and initialize
		this.text = text;
		initialize();
	}
	
	private void initialize() {
		if(text == null) {
			isEmpty = true;
			isNull = true;
			length = 0;
			return;
		}
		
		if(text.isEmpty()) {
			isEmpty = true;
		} else {
			isEmpty = false;
		}

		this.length = this.text.length();
		this.trimEmpty = this.text.trim().isEmpty();
		
		final int[] spaceTokens = MarkupUtils.findLeadingSpaces(text);
		
		this.leadingPosition = spaceTokens[0];
		this.leadingSpaces = spaceTokens[1];
		
		this.lineType = decipherLineType();
	}
	
	private LineType decipherLineType() {
		if(this.trimEmpty) {
			return LineType.Empty;
		}
		
		if(this.leadingSpaces >= 4) {
			return LineType.CodeBlock;
		}
		
		if(this.text.startsWith("<!--", this.leadingPosition)) {
			return LineType.HtmlComment;
		}
		
		if(this.text.startsWith("#", this.leadingPosition)) {
			return LineType.Heading;
		}
		
		if(this.options.enableGithubFencedCodeBlocks && this.text.startsWith("```", this.leadingPosition)) {
			this.terminator = '`';
			return LineType.FencedCodeBlock;
		}

		if(this.options.enablePhpFencedCodeBlocks && this.text.startsWith("~~~", this.leadingPosition)) {
			this.terminator = '~';
			return LineType.FencedCodeBlock;
		}
		
		if(this.text.startsWith("===", this.leadingPosition)) {
			return LineType.HeadingIndicator;
		}

		if(this.text.startsWith("---", this.leadingPosition)) {
			this.horizontalRule = isHorizontalRule();
			return LineType.HeadingIndicator;
		}
		
		if(this.text.startsWith("[", this.leadingPosition)) {
			return LineType.LinkReference;
		}
		
		if(this.text.startsWith(">", this.leadingPosition)) {
			return LineType.BlockQuote;
		}
		
		if(isUnorderedList()) {
			return LineType.UnorderedList;
		}
		
		if(lineStartsWithNumber()) {
			return LineType.OrderedList;
		}
		
		if(isHorizontalRule()) {
			return LineType.HRule;
		}
		
		return LineType.UnknownText;
	}
	
	/**
	 * Checks if line data at given leading position is a horizontal rule or not.
	 * 
	 * @param line
	 * @param leadingPosition
	 * @return
	 */
	private boolean isHorizontalRule() {
		if(MarkupUtils.isOnlySpaceAndCharacter(this.text, '-')) {
			if(this.text.startsWith("---", leadingPosition)) {
				return true;
			}
	
			if(this.text.startsWith("- - -", leadingPosition)) {
				return true;
			}
		}

		if(MarkupUtils.isOnlySpaceAndCharacter(this.text, '*')) {
			if(this.text.startsWith("***", leadingPosition)) {
				return true;
			}
	
			if(this.text.startsWith("* * *", leadingPosition)) {
				return true;
			}
		}
		
		if(MarkupUtils.isOnlySpaceAndCharacter(this.text, '_')) {
			if(this.text.startsWith("___", leadingPosition)) {
				return true;
			}
	
			if(this.text.startsWith("_ _ _", leadingPosition)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean lineStartsWithNumber() {
		int dot = this.text.indexOf('.');
		if(dot == -1) {
			return false;
		}
		
		try {
			Integer.parseInt(this.text.substring(0, dot));
			return true;
		} catch(NumberFormatException e) {
			// do nothing
			// we will continue parsing to see
			// if any of the other tokens can match
			// this parsing schedule
		}
		
		return false;
	}

	public boolean isUnorderedList() {
		if(isUnorderedListOfChar('*') || isUnorderedListOfChar('+') || isUnorderedListOfChar('-')) {
			if(!isHorizontalRule()) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isUnorderedListOfChar(char terminator) {
		if((this.text.startsWith(terminator + " ", this.leadingPosition) || this.text.startsWith(terminator + "\t", this.leadingPosition)) && !MarkupUtils.isOnlySpaceAndCharacter(this.text, terminator)) {
			return true;
		}
		
		return false;
	}
	
	public String substring(int index) {
		return this.text.substring(index);
	}
	
	public String substring(int start, int end) {
		return this.text.substring(start, end);
	}
	
	public int indexOf(char c) {
		return this.text.indexOf(c);
	}
	
	public int indexOf(char c, int startIndex) {
		return this.text.indexOf(c, startIndex);
	}
	
	public boolean startsWith(String str) {
		return this.text.startsWith(str);
	}
	
	public boolean startsWith(char c) {
		return this.text.startsWith(String.valueOf(c));
	}
	
	public boolean endsWith(String str) {
		return this.text.endsWith(str);
	}
	
	public char charAt(int index) {
		return this.text.charAt(index);
	}
	
	public TextLine trim() {
		this.text = this.text.trim();
		return this;
	}
	
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	
	@Override
	public String toString() {
		return this.text;
	}

}
