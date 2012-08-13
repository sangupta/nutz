package com.sangupta.nutz.ast;

import java.util.Map;

import com.sangupta.nutz.Identifiers;

public class NewLineNode extends TextNode {

	public NewLineNode(Node parent) {
		super(parent);
	}
	
	@Override
	public void write(StringBuilder builder, boolean atRootNode, Map<String, AnchorNode> referenceLinks) {
		builder.append(Identifiers.NEW_LINE);
	}
	
	@Override
	public String toString() {
		return "[\\n]";
	}
	
}
