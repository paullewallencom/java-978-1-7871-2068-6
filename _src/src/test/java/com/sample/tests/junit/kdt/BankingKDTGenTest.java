package com.sample.tests.junit.kdt;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.sample.framework.utils.jira.JiraUtils;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "html:build/cucumber-html-report",
                "junit:build/cucumber-junit.xml",
                "json:build/cucumber.json",
                "pretty:build/cucumber-pretty.txt",
                "usage:build/cucumber-usage.json"
                },
        features = { "build/features" },
        glue = { "com/sample/tests/junit/kdt/steps" },
        tags = {"@gen"}
)
public class BankingKDTGenTest {
}
