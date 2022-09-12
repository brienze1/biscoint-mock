package org.brienze.biscoint.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "org.brienze.biscoint.cucumber.steps",
        plugin = "pretty")
public class CucumberRunnerTest {
}
