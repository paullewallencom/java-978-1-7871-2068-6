package com.sample.tests.junit.kdt;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sample.framework.utils.jira.JiraUtils;



public class GenerateTest {
	
	@BeforeClass
	public static void farBefore() throws Exception {
		String featurePath = "build/features";
		Map<String, String> content = JiraUtils.getField("http://localhost:9090", "mkolisnyk", "password",
				"project = SAM AND type = Test AND status in (Done, Failed, Passed)", "description");
		for (Entry<String, String> entry : content.entrySet()) {
			String fileName = entry.getKey().replaceAll("(\\W+)", "_") + ".feature";
			String result = "@gen @" + entry.getKey().split(" ")[0] + System.lineSeparator() + entry.getValue();
			File output = new File(featurePath + File.separator + fileName);
			output.getParentFile().mkdirs();
			FileUtils.writeStringToFile(new File(featurePath + File.separator + fileName), result, "UTF-8");
		}
	}
	@Test
	public void testRun() {}
}
