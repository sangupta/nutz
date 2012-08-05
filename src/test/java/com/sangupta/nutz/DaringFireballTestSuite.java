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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.sangupta.comparator.HTMLComparer;

/**
 * 
 * @author sangupta
 *
 */
public class DaringFireballTestSuite {
	
	public static void main(String[] args) throws Exception {
		File dir = new File("C:/projects/git/markdown/src/main/resources/markdown");
		File[] files = dir.listFiles();
		
		List<File> tests = new ArrayList<File>();
		for(File file : files) {
			if(file.getName().endsWith(".text")) {
				tests.add(file);
			}
		}
		
		runTests(tests);
	}

	private static void runTests(List<File> tests) throws Exception {
		int testsPass = 0;
		int testsFail = 0;
		
		for(File file : tests) {
			String output = file.getAbsolutePath();
			output = output.replace(".text", ".html");
			output = FileUtils.readFileToString(new File(output));
			
			String markup = FileUtils.readFileToString(file);
			MarkdownProcessor processor = new MarkdownProcessor();
			
			String html = null;

			try {
				html = processor.toHtml(markup);
			} catch(Throwable t) {
				// do nothing
				System.out.println("Test failed (exception): " + file.getAbsolutePath());
				testsFail++;
				continue;
			}
			
			if(HTMLComparer.compareHtml(output, html)) {
				System.out.println("Test PASS: " + file.getAbsolutePath());
				testsPass++;
			} else {
				System.out.println("Test failed: " + file.getAbsolutePath());
				testsFail++;
			}
		}
		
		System.out.println("Tests pass: " + testsPass + "; fail: " + testsFail);
	}
	
}
