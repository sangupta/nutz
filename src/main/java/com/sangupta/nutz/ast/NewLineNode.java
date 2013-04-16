package com.sangupta.nutz.ast;

import java.io.IOException;
import java.util.Map;

import com.sangupta.nutz.Identifiers;
import com.sangupta.nutz.ProcessingOptions;

public class NewLineNode extends TextNode {

	public NewLineNode(Node parent) {
		super(parent);
	}
	
	@Override
	public void write(Appendable builder, boolean atRootNode, Map<String, AnchorNode> referenceLinks, ProcessingOptions options) throws IOException {
		builder.append(Identifiers.NEW_LINE);
	}
	
	@Override
	public String toString() {
		return "[\\n]";
	}
	
}
