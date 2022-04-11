package com.br.advantage.runTest;

import com.br.advantage.utils.Constants;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        plugin = {"pretty", "html:build/reports/cucumber.html", "json:build/reports/cucumber.json"},
        features = {"./src/test/resources/features"},
        glue = "com.br.advantage.steps"
)
public class RunTest extends Constants {
}
