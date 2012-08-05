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

import junit.framework.Assert;

import org.junit.Test;

/**
 * 
 * @author sangupta
 *
 */
public class MarkupUtilsTest {
	
	/**
	 * Tests for {@link MarkupUtils#isEmail(String)}
	 */
	@Test
	public void testIsEmail() {
		Assert.assertTrue(MarkupUtils.isEmail("sangupta@sangupta.com"));
		Assert.assertFalse(MarkupUtils.isEmail("sangupta"));
	}
	
	/**
	 * Tests for {@link MarkupUtils#isHyperLink(String)}
	 */
	@Test
	public void testIsHyperlink() {
		Assert.assertTrue(MarkupUtils.isHyperLink("http://www.google.com"));
		Assert.assertFalse(MarkupUtils.isHyperLink("sangupta"));
	}
	
	/**
	 * Tests for {@link MarkupUtils#parseLinkAndTitle(String)}
	 */
	@Test
	public void testParseLinkAndTitle() {
		String link = "http://att.com/  \"AT&T\"";
		String[] tokens = MarkupUtils.parseLinkAndTitle(link);
		
		Assert.assertEquals("http://att.com/", tokens[0]);
		Assert.assertEquals("AT&T", tokens[1]);
	}

}
