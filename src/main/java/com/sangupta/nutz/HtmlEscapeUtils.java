package com.sangupta.nutz;

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
