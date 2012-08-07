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
 * Utility functions used when constructing the Abstract Syntax Tree
 * of the markup presented.
 * 
 * @author sangupta
 *
 */
public class MarkupUtils {

	/**
	 * Checks if the given text represents a valid email or not.
	 * 
	 * @param text the text to be tested
	 * 
	 * @return <code>true</code if the text represents a valid email, <code>false</code>
	 * otherwise
	 */
	public static boolean isEmail(String text) {
		if(text.contains("@") && text.contains(".")) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Checks if the given text is actually a hyperlink or not.
	 * 
	 * @param text the text to be tested
	 * 
	 * @return <code>true</code if the text represents a valid link, <code>false</code>
	 * otherwise
	 */
	public static boolean isHyperLink(String text) {
		if(text.contains("://") && text.contains(".")) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Extract title and link from the given link text
	 * 
	 * @param link the link text from which the actual link and the title
	 * need to be parsed and extracted
	 * 
	 * @return a string array of 2 objects, the first one being the parsed link and the
	 * 		second one being the title if present.
	 */
	public static String[] parseLinkAndTitle(String link) {
		String[] tokens = new String[2];

		int index = link.indexOf(Identifiers.SPACE);
		if(index == -1) {
			tokens[0] = link;
		} else {
			String text = link.substring(index).trim();
			if(text.charAt(0) == Identifiers.DOUBLE_QUOTES) {
				int pos = index;

				// find next quotes
				index = text.indexOf(Identifiers.DOUBLE_QUOTES, 1);
				if(index == -1) {
					// none found
					index = text.length(); 
				} else if((index + 1) != text.length()) {
					index = text.length();
				}
				
				tokens[0] = link.substring(0, pos);
				tokens[1] = text.substring(1, index);
			}
		}
		
		// make sure we remove the starting and closing angle brackets if present
		// this is a present for Daring Fireball test for 'Amps and angle encoding.text'
		// test
		String token = tokens[0];
		if(!token.isEmpty() && token.charAt(0) == '<' && token.charAt(token.length() - 1) == '>') {
			tokens[0] = token.substring(1, token.length() - 1);
		}
		
		return tokens; 
	}

	/**
	 * Finds the number of leading spaces and the current leading position in the given
	 * line. Returns an integer array containing the two values. The first value is the
	 * leading position or the leading index in the string, and the second value is the
	 * leading count of spaces where tabs have been converted to 4 counts of spaces.
	 * 
	 * @param line
	 * @return
	 */
	public static int[] findLeadingSpaces(String line) {
		int[] array = new int[2];
		array[0] = 0;
		array[1] = 0;
		
		if(line.isEmpty()) {
			return array;
		}
		
		int index = 0;
		int spaces = 0;
		do {
			if(index >= line.length()) {
				break;
			}
			
			char c = line.charAt(index);
			if(c == ' ') {
				index++;
				spaces++;
				continue;
			}
			
			if(c == '\t') {
				index++;
				spaces += 4;
				continue;
			}
			
			break;
		}
		while(true);
		
		array[0] = index;
		array[1] = spaces;
		
		return array;
	}
	
	/**
	 * Finds the index of specified terminator character, counting greedily and skipping closing terminators
	 * when the pair terminator is found.
	 *  
	 * @param line
	 * @param ch
	 * @param startIndex
	 * @return
	 */
	public static int indexOfSkippingForPairCharacter(String line, char terminator, char terminatorPair, int startIndex) {
		char[] array = line.toCharArray();
		
		int length = array.length;
		int open = 1;
		
		do {
			char c = array[startIndex++];
			
			if(c == terminator) {
				open--;
			} else if(c == terminatorPair) {
				open++;
			}
			
			if(open == 0) {
				return startIndex - 1;
			}
			
			if(startIndex == length) {
				break;
			}
		} while(true);
		
		return -1;
	}
	
	/**
	 * Check if the given line contains only spaces and the given character
	 * 
	 * @param line
	 * @param character
	 * @return
	 */
	public static boolean isOnlySpaceAndCharacter(String line, char character) {
		char[] array = line.toCharArray();
		for(int index = 0; index < array.length; index++) {
			char c = array[index];
			switch(c) {
				case Identifiers.SPACE:
				case Identifiers.TAB:
					continue;
					
				default:
					if(c != character) {
						return false;
					}
			}
		}
		
		return true;
	}

}
