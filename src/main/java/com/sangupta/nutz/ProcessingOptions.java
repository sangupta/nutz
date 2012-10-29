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
 * An <i>immutable</i> class for storing the various processing options.
 * <p/>
 * Usage:
 * <code><pre>
 * ProcessingOptions options = new ProcessingOptions().enablePhpFencedCodeBlocks(false);
 * </pre></code>
 * @author sangupta
 *
 */
public class ProcessingOptions {
	
	/**
	 * If set to <code>true</code> enables the parsing of PHP
	 * Markdown Extra defined fenced code blocks. These start and
	 * end in <code>~~~</code>. We also support the concept of
	 * specifying a language that can then be used for syntax
	 * highlighting of code. 
	 */
	public final boolean enablePhpFencedCodeBlocks;
	
	/**
	 * If set to <code>true</code> enabled the parsing of Github Flavored
	 * Markdown defined fenced code blocks. These start and end in
	 * <code>```</code>. Specifying of language at the end of start tag
	 * is supported.
	 */
	public final boolean enableGithubFencedCodeBlocks;
	
	/**
	 * Defines the syntax highlighting option to be used on code blocks. The 
	 * default behavior is to use Pepmint library for the same.
	 */
	public final SyntaxHighlightingOption syntaxHighlightingOption;

	/**
	 * Sets the default options for processing.
	 */
	public ProcessingOptions() {
		this(true, true, SyntaxHighlightingOption.Pepmint);
	}
	
	/**
	 * Configures all the available options, generally not used in favour of the setter methods.
	 */
	public ProcessingOptions(boolean enablePhpFencedCodeBlocks, boolean enableGithubFencedCodeBlocks, SyntaxHighlightingOption syntaxHighlightingOption) {
		this.enablePhpFencedCodeBlocks = enablePhpFencedCodeBlocks;
		this.enableGithubFencedCodeBlocks = enableGithubFencedCodeBlocks;
		this.syntaxHighlightingOption = syntaxHighlightingOption;
	}
	
	public ProcessingOptions enablePhpFencedCodeBlocks(boolean enablePhpFencedCodeBlocks) {
		return new ProcessingOptions(enablePhpFencedCodeBlocks, enableGithubFencedCodeBlocks, syntaxHighlightingOption);
	}
	
	public ProcessingOptions enableGithubFencedCodeBlocks(boolean enableGithubFencedCodeBlocks) {
		return new ProcessingOptions(enablePhpFencedCodeBlocks, enableGithubFencedCodeBlocks, syntaxHighlightingOption);
	}
	
	public ProcessingOptions syntaxHighlightingOption(SyntaxHighlightingOption syntaxHighlightingOption) {
		return new ProcessingOptions(enablePhpFencedCodeBlocks, enableGithubFencedCodeBlocks, syntaxHighlightingOption);
	}
	
	/**
	 * Defines the syntax highlighting option to be used.
	 * 
	 * @author sangupta
	 *
	 */
	public static enum SyntaxHighlightingOption {
		
		/**
		 * Do not use any syntax-highlighting option and just emit the
		 * plain pre-code tag combine.
		 */
		None,
		
		/**
		 * Use the syntax highlighter by Alex Gorbatchev. When using this highlighter
		 * we only append <code>brush: langname</code> to the <code>pre</code> tag
		 * during HTML emission. 
		 */
		SyntaxHighlighter,

		/**
		 * Use the pepmint library for syntax highlighting of code blocks
		 */
		Pepmint;
	}
}
