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

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.sangupta.comparator.HTMLComparer;
import com.sangupta.nutz.ast.RootNode;

/**
 * 
 * @author sangupta
 *
 */
public class RandomProcessorTest {

	public static void main(String[] args) throws Exception {
		String fileName = "Markdown Documentation - Basics";
		// fileName = "tabs";
		String s3 = "src/test/resources/markdown/" + fileName + ".text";
		String o3 = "src/test/resources/markdown/" + fileName + ".html";
		
		File file = new File(s3);
		String markup = FileUtils.readFileToString(file);
		
//		markup = "*	this" +
//				"\n" +
//				"\n	*	sub" +
//				"\n" +
//				"\n	that";

		
		MarkdownProcessor processor = new MarkdownProcessor();
		HtmlEmitter emitter = new HtmlEmitter();
		
		long start = System.currentTimeMillis();
		RootNode rootNode = processor.parse(markup);
		long end = System.currentTimeMillis();
		System.out.println("Time taken in parsing: " + (end - start) + " ms.");
		
		start = System.currentTimeMillis();
		String html = emitter.toHtml(rootNode);
		end = System.currentTimeMillis();

		System.out.println("Time taken in emitting HTML: " + (end - start) + " ms.");

		String out = FileUtils.readFileToString(new File(o3));
		FileUtils.write(new File("C:/users/sangupta/desktop/markdownTest.html"), html);
		
		boolean htmlEquals = HTMLComparer.compareHtml(out, html);
		System.out.println("HTML Equals: " + htmlEquals);
		
		if(!htmlEquals && html.length() < 300) {
			System.out.println("HTML: " + html);
		}
		
//		System.out.println("\n\n\n\n");
//		DaringFireballTestSuite.main(args);
	}
	
}
