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

import java.io.IOException;

import org.junit.Assert;

import com.sangupta.comparator.HTMLComparer;

/**
 * Abstraction for all tests that need to compare HTML code.
 * 
 * @author sangupta
 *
 */
public abstract class AbstractHtmlTests {
	
	/**
	 * Convenience method that allows comparing two HTML values, than doing a manual
	 * comparison.
	 * 
	 * @param actualHTML the actual HTML that is expected back
	 * @param markup the markup string that will be converted
	 */
	protected void testMarkup(String actualHTML, String markup) {
		try {
			String expectedHTML = new MarkdownProcessor().toHtml(markup);
			Assert.assertTrue(HTMLComparer.compareHtml(actualHTML, expectedHTML));
		} catch(IOException e) {
			Assert.assertTrue("Failed with exception", false);
		}
	}

}
