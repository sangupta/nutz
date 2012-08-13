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

import org.junit.Assert;
import org.junit.Test;

import com.sangupta.comparator.HTMLComparer;

/**
 * 
 * @author sangupta
 *
 */
public class MarkdownProcessorTest {
	
	private MarkdownProcessor processor = new MarkdownProcessor();
	
	@Test
	public void testHeadings() throws Exception {
		check("<h1>Heading</h1>", processor.toHtml("# Heading"));
		check("<h1>Heading</h1>", processor.toHtml("# Heading #"));
		check("<h1>Heading</h1>", processor.toHtml("# Heading ##"));
		check("<h1>Heading</h1>", processor.toHtml("# Heading ###"));
		
		check("<h2>Heading</h2>", processor.toHtml("## Heading"));
		check("<h2>Heading</h2>", processor.toHtml("## Heading #"));
		check("<h2>Heading</h2>", processor.toHtml("## Heading ##"));
		check("<h2>Heading</h2>", processor.toHtml("## Heading ###"));
		check("<h2>Heading</h2>", processor.toHtml("## Heading ####"));
		
		check("<h3>Heading</h3>", processor.toHtml("### Heading"));
		check("<h3>Heading</h3>", processor.toHtml("### Heading #"));
		check("<h3>Heading</h3>", processor.toHtml("### Heading ##"));
		check("<h3>Heading</h3>", processor.toHtml("### Heading ###"));
		
		check("<h4>Heading</h4>", processor.toHtml("#### Heading"));
		check("<h4>Heading</h4>", processor.toHtml("#### Heading #"));
		check("<h4>Heading</h4>", processor.toHtml("#### Heading ##"));
		check("<h4>Heading</h4>", processor.toHtml("#### Heading ###"));
		check("<h4>Heading</h4>", processor.toHtml("#### Heading####"));
		
		check("<h5>Heading</h5>", processor.toHtml("##### Heading"));
		check("<h5>Heading</h5>", processor.toHtml("##### Heading#"));
		check("<h5>Heading</h5>", processor.toHtml("##### Heading ##"));
		check("<h5>Heading</h5>", processor.toHtml("##### Heading###"));
		check("<h5>Heading</h5>", processor.toHtml("##### Heading###########"));
		
		check("<h6>Heading</h6>", processor.toHtml("###### Heading"));
		check("<h6>Heading</h6>", processor.toHtml("###### Heading #"));
		check("<h6>Heading</h6>", processor.toHtml("###### Heading ###"));
		check("<h6>Heading</h6>", processor.toHtml("###### Heading ######"));
		check("<h6>Heading</h6>", processor.toHtml("###### Heading ##########"));
		check("<h6>Heading</h6>", processor.toHtml("###### Heading#####"));
	}
	
	@Test
	public void testStrong() throws Exception {
		check("<p><strong>Heading</strong></p>", processor.toHtml(" **Heading**"));
	}
	
	@Test
	public void testEmphasis() throws Exception {
		check("<p><em>Heading</em></p>", processor.toHtml(" *Heading*"));
	}
	
	@Test
	public void testInternalLinks() throws Exception {
		check("<p>Here's an inline <a href=\"/script?foo=1&bar=2\">link</a>.</p>", processor.toHtml("Here's an inline [link](/script?foo=1&bar=2)."));
		check("<p>Here's an inline <a href=\"/script?foo=1&bar=2\">link</a>.</p>", processor.toHtml("Here's an inline [link](</script?foo=1&bar=2>)."));
	}
	
	@Test
	public void testCode() throws Exception {
		check("<p><code>foo</code></p>", processor.toHtml("`foo`"));
	}
	
	/**
	 * Convenience method that allows comparing two HTML values, than doing a manual
	 * comparison.
	 * 
	 * @param expected
	 * @param actual
	 */
	private void check(String expected, String actual) {
		Assert.assertTrue(HTMLComparer.compareHtml(expected, actual));
	}

}
