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

import org.junit.Test;

/**
 * Simple tests that test against one given rule of markup at a time.
 * 
 * @author sangupta
 *
 */
public class MarkdownProcessorTest extends AbstractHtmlTests {
	
	@Test
	public void testHeadings() throws IOException {
		testMarkup("<h1>Heading</h1>", "# Heading");
		testMarkup("<h1>Heading</h1>", "# Heading #");
		testMarkup("<h1>Heading</h1>", "# Heading ##");
		testMarkup("<h1>Heading</h1>", "# Heading ###");
		
		testMarkup("<h2>Heading</h2>", "## Heading");
		testMarkup("<h2>Heading</h2>", "## Heading #");
		testMarkup("<h2>Heading</h2>", "## Heading ##");
		testMarkup("<h2>Heading</h2>", "## Heading ###");
		testMarkup("<h2>Heading</h2>", "## Heading ####");
		
		testMarkup("<h3>Heading</h3>", "### Heading");
		testMarkup("<h3>Heading</h3>", "### Heading #");
		testMarkup("<h3>Heading</h3>", "### Heading ##");
		testMarkup("<h3>Heading</h3>", "### Heading ###");
		
		testMarkup("<h4>Heading</h4>", "#### Heading");
		testMarkup("<h4>Heading</h4>", "#### Heading #");
		testMarkup("<h4>Heading</h4>", "#### Heading ##");
		testMarkup("<h4>Heading</h4>", "#### Heading ###");
		testMarkup("<h4>Heading</h4>", "#### Heading####");
		
		testMarkup("<h5>Heading</h5>", "##### Heading");
		testMarkup("<h5>Heading</h5>", "##### Heading#");
		testMarkup("<h5>Heading</h5>", "##### Heading ##");
		testMarkup("<h5>Heading</h5>", "##### Heading###");
		testMarkup("<h5>Heading</h5>", "##### Heading###########");
		
		testMarkup("<h6>Heading</h6>", "###### Heading");
		testMarkup("<h6>Heading</h6>", "###### Heading #");
		testMarkup("<h6>Heading</h6>", "###### Heading ###");
		testMarkup("<h6>Heading</h6>", "###### Heading ######");
		testMarkup("<h6>Heading</h6>", "###### Heading ##########");
		testMarkup("<h6>Heading</h6>", "###### Heading#####");
	}
	
	@Test
	public void testHeadingWithLink() throws IOException {
		testMarkup("<h2><a href=\"http://a.b/c\">C</a> Reference</h2>", "[C](http://a.b/c) Reference\n-----------------");
	}
	
	@Test
	public void testStrong() throws IOException {
		testMarkup("<p><strong>Heading</strong></p>", " **Heading**");
	}
	
	@Test
	public void testEmphasis() throws IOException {
		testMarkup("<p><em>Heading</em></p>", " *Heading*");
	}
	
	@Test
	public void testInternalLinks() throws IOException {
		testMarkup("<p>Here's an inline <a href=\"/script?foo=1&bar=2\">link</a>.</p>", "Here's an inline [link](/script?foo=1&bar=2).");
		testMarkup("<p>Here's an inline <a href=\"/script?foo=1&bar=2\">link</a>.</p>", "Here's an inline [link](</script?foo=1&bar=2>).");
	}
	
	@Test
	public void testCode() throws IOException {
		testMarkup("<p><code>foo</code></p>", "`foo`");
	}
	
	@Test
	public void testImageInAnchor() throws IOException {
		testMarkup("<p><a href=\"http://travis-ci.org/sangupta/jerry\"><img src=\"https://secure.travis-ci.org/sangupta/jerry.png?branch=master\" alt=\"Build Status\" title=\"\" /></a></p>",
					"[![Build Status](https://secure.travis-ci.org/sangupta/jerry.png?branch=master)](http://travis-ci.org/sangupta/jerry)");
	}
	
}
