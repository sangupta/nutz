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
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import com.sangupta.comparator.HTMLComparer;

/**
 * Test suite that executes all 23 tests of Daring Fireball's Markdown
 * test bundle.
 * 
 * @author sangupta
 * @since 0.1
 */
public class DaringFireballTestSuite {
	
	/**
	 * Utility main method that allows running of test suite from the
	 * command line.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		DaringFireballTestSuite testSuite = new DaringFireballTestSuite();
		testSuite.executeDaringFireballTestSuite();
	}
	
	@Test
	public void executeDaringFireballTestSuite() throws IOException {
		Assert.assertTrue(runIndividualTest("Amps and angle encoding"));
		Assert.assertTrue(runIndividualTest("Auto links"));
		Assert.assertTrue(runIndividualTest("Backslash escapes"));
		Assert.assertTrue(runIndividualTest("Blockquotes with code blocks"));
		Assert.assertTrue(runIndividualTest("Code Blocks"));
		Assert.assertTrue(runIndividualTest("Code Spans"));
		Assert.assertTrue(runIndividualTest("Hard-wrapped paragraphs with list-like lines"));
		Assert.assertTrue(runIndividualTest("Horizontal rules"));
		Assert.assertTrue(runIndividualTest("Images"));
		Assert.assertTrue(runIndividualTest("Inline HTML (Advanced)"));
		Assert.assertTrue(runIndividualTest("Inline HTML (Simple)"));
		Assert.assertTrue(runIndividualTest("Inline HTML comments"));
		Assert.assertTrue(runIndividualTest("Links, inline style"));
		Assert.assertTrue(runIndividualTest("Links, reference style"));
		Assert.assertTrue(runIndividualTest("Links, shortcut references"));
		Assert.assertTrue(runIndividualTest("Literal quotes in titles"));
		Assert.assertTrue(runIndividualTest("Markdown Documentation - Basics"));
		Assert.assertTrue(runIndividualTest("Markdown Documentation - Syntax"));
		Assert.assertTrue(runIndividualTest("Nested blockquotes"));
		Assert.assertTrue(runIndividualTest("Ordered and unordered lists"));
		Assert.assertTrue(runIndividualTest("Strong and em together"));
		Assert.assertTrue(runIndividualTest("Tabs"));
		Assert.assertTrue(runIndividualTest("Tidyness"));
	}
	
	private boolean runIndividualTest(String testName) throws IOException {
		File dir = new File("src/test/resources/markdown");
		return executeSingleTest(new File(dir, testName + ".text"));
	}

	private boolean executeSingleTest(final File file) throws IOException {
		String originalHTML = file.getAbsolutePath();
		originalHTML = originalHTML.replace(".text", ".html");
		originalHTML = FileUtils.readFileToString(new File(originalHTML));
		
		final String markup = FileUtils.readFileToString(file);
		MarkdownProcessor processor = new MarkdownProcessor();
		
		String processedHTML = null;

		try {
			processedHTML = processor.toHtml(markup);
		} catch(Throwable t) {
			// do nothing
		}
		if(HTMLComparer.compareHtml(originalHTML, processedHTML)) {
			return true;
		} else {
			return false;
		}
	}
	
}
