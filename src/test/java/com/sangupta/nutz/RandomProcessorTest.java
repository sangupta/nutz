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
		String s2 = "src/main/resources/markdown_home_page.md";
		
		String fileName = "Images";
		String s3 = "src/test/resources/markdown/" + fileName + ".text";
		String o3 = "src/test/resources/markdown/" + fileName + ".html";
		
		String markup1 = "* * *";
		
		File file = new File(s3);
		String markup = FileUtils.readFileToString(file);

		long start = System.currentTimeMillis();
		RootNode rootNode = new MarkdownProcessor().parse(markup);
		String html = new HtmlEmitter().toHtml(rootNode);
		long end = System.currentTimeMillis();
		System.out.println("Time taken: " + (end - start) + " ms.");

		
		String out = FileUtils.readFileToString(new File(o3));
		FileUtils.write(new File("C:/users/sangupta/desktop/markdownTest.html"), html);
		
		boolean htmlEquals = HTMLComparer.compareHtml(out, html);
		System.out.println("HTML Equals: " + htmlEquals);
		
		if(!htmlEquals && html.length() < 100) {
			System.out.println("HTML: " + html);
		}
	}
	
}
