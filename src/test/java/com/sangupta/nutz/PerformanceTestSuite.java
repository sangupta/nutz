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
import org.pegdown.PegDownProcessor;

import com.github.rjeschke.txtmark.Processor;
import com.sangupta.comparator.HTMLComparer;

/**
 * 
 * @author sangupta
 *
 */
public class PerformanceTestSuite {
	
	private static final int PER_TEST_RUNS = 1;
	
	private static final int NUM_TEST_SUITE_RUNS = 1;
	
	private static List<TestData> tests = new ArrayList<PerformanceTestSuite.TestData>();
	
	public static void main(String[] args) throws Exception {
		File dir = new File("src/test/resources/markdown");
		File[] files = dir.listFiles();
		
		for(File file : files) {
			if(file.getName().endsWith(".text")) {
				final String markup = FileUtils.readFileToString(file);
				
				String html = file.getAbsolutePath();
				html = html.replace(".text", ".html");
				html = FileUtils.readFileToString(new File(html));

				TestData testData = new TestData(markup, html);
				tests.add(testData);
			}
		}
		
		TestResults nutz = runTests(new TestExecutor() {
			
			private MarkdownProcessor processor = new MarkdownProcessor();
			
			@Override
			public String convertMarkup(String markup) throws Exception {
				return processor.toHtml(markup);
			}
			
		});
		
		TestResults txtmark = runTests(new TestExecutor() {

			@Override
			public String convertMarkup(String markup) throws Exception {
				return Processor.process(markup);
			}
			
		});
		
		TestResults pegdown = runTests(new TestExecutor() {
			
			private PegDownProcessor processor = new PegDownProcessor();

			@Override
			public String convertMarkup(String markup) throws Exception {
				return processor.markdownToHtml(markup);
			}
			
		});
		
		System.out.println("\n\n\n\n\n");
		System.out.println("Nutz: " + nutz);
		System.out.println("Pegdown: " + pegdown);
		System.out.println("TextMark: " + txtmark);
	}
	
	private static PerformanceTestSuite.TestResults runTests(TestExecutor testExecutor) throws Exception {
		int testsPass = 0;
		int testsFail = 0;
		int testsException = 0;
		
		long totalTime = 0;
		
		// iterate test suite
		for(int testSuiteCounter = 0; testSuiteCounter < NUM_TEST_SUITE_RUNS; testSuiteCounter++) {

			// run each test
			for(int currentTestIndex = 0; currentTestIndex < tests.size(); currentTestIndex++) {
				TestData testData = tests.get(currentTestIndex);
				boolean firstRun = true;
				
				// iterate each test
				for(int testIterationCounter = 0; testIterationCounter < PER_TEST_RUNS; testIterationCounter++) {
					String html = null;

					long start = System.currentTimeMillis();
					try {
						html = testExecutor.convertMarkup(testData.markup);
					} catch(Throwable t) {
						if(firstRun) {
							testsException++;
						}
					}
					long end = System.currentTimeMillis();
					
					totalTime += (end - start);
				
					if(firstRun) {
						if(HTMLComparer.compareHtml(testData.html, html)) {
							testsPass++;
						} else {
							testsFail++;
						}
					}
				
					firstRun = false;
				}
			}
		}
		
		return new PerformanceTestSuite.TestResults(testsPass, testsFail, testsException, totalTime);
	}
	
	public static class TestData {
		
		private String markup;
		
		private String html;
		
		public TestData(String markup, String html) {
			this.markup = markup;
			this.html = html;
		}
		
	}
	
	public static interface TestExecutor {
		
		public String convertMarkup(String markup) throws Exception;
	}
	
	public static class TestResults {
		
		private int pass;
		
		private int fail;
		
		private int threwException;
		
		private long time;
		
		public TestResults(int pass, int fail, int threwException, long time) {
			this.pass = pass;
			this.fail = fail;
			this.threwException = threwException;
			this.time = time;
		}
		
		@Override
		public String toString() {
			return "Time: " + time + "ms; Pass: " + this.pass + "; Fail: " + this.fail + "; Threw exception: " + this.threwException;
		}
	}
	
}
