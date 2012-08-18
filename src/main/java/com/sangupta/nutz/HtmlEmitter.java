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

import com.sangupta.nutz.ast.RootNode;

/**
 * Default HTML emitter that generates HTML representation of the
 * given {@link RootNode}.
 * 
 * @author sangupta
 * @since 0.1
 */
public class HtmlEmitter {
	
	/**
	 * Processing options to be used when generating HTML code
	 */
	private ProcessingOptions options;
	
	/**
	 * Default constructor that uses default processing options.
	 */
	public HtmlEmitter() {
		this.options = new ProcessingOptions();
	}
	
	/**
	 * Construct an instance of this emitter and use the given processing
	 * options.
	 * 
	 * @param options
	 */
	public HtmlEmitter(ProcessingOptions options) {
		this.options = options;
	}

	/**
	 * Emit the HTML code for the given root node.
	 * 
	 * @param root
	 * @return
	 */
	public String toHtml(RootNode root) {
		if(root == null) {
			throw new IllegalArgumentException("Root node cannot be null.");
		}
		
		StringBuilder builder = new StringBuilder();
		root.write(builder, true, null, options);
		return builder.toString();
	}
	
}
