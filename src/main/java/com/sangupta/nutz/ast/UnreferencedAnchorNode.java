package com.sangupta.nutz.ast;

import java.util.Map;

import com.sangupta.nutz.Identifiers;
import com.sangupta.nutz.TextNodeParser;

/**
 * Represents a probable unreferenced anchor node. If a reference
 * link to the same is found in the file, it will be resolved, else
 * it will be re-written as plain text in the output buffer.
 * 
 * @author sangupta
 *
 */
public class UnreferencedAnchorNode extends Node {
	
	private static StringBuilder builder = new StringBuilder(512);
	
	private String text;
	
	public UnreferencedAnchorNode(String text) {
		builder.setLength(0);
		
		char[] array = text.toCharArray();
		char ch;
		for(int index = 0; index < array.length; index++) {
			ch = array[index];
			if(ch == '\n') {
				if(array[index - 1] != Identifiers.SPACE) {
					builder.append(Identifiers.SPACE);
				}
			} else {
				builder.append(ch);
			}
		}
		
		this.text = builder.toString();
	}
	
	@Override
	public void write(StringBuilder builder, boolean atRootNode, Map<String, AnchorNode> referenceLinks) {
		AnchorNode node = referenceLinks.get(this.text);
		if(node == null) {
			builder.append('[');
			
			// do this text via text parsing routine
			TextNode textNode = new TextNodeParser().parse(this, this.text);
			textNode.write(builder, false, referenceLinks);
			builder.append(']');
			
			return;
		}
		
		// do it via the anchor
		AnchorNode newAnchor = new AnchorNode(null, this.text, node.getUrl(), node.getTitle(), false, 0);
		newAnchor.write(builder, atRootNode, referenceLinks);
	}
}
