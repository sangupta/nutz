package com.sangupta.nutz.ast;

import java.util.Map;

public class BlockQuoteNode extends Node {
	
	@Override
	public void write(StringBuilder builder, boolean atRootNode, Map<String, AnchorNode> referenceLinks) {
		if(this.children == null || this.children.isEmpty()) {
			return;
		}
		
		builder.append("<blockquote>");
		builder.append(NEW_LINE);
		
		for(Node child : this.children) {
			child.write(builder, atRootNode, referenceLinks);
		}
		
		builder.append(NEW_LINE);
		builder.append("</blockquote>");
	}

}
