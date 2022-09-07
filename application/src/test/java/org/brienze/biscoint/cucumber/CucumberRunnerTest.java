package org.brienze.biscoint.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features", 
		glue = {"org.brienze.biscoint.cucumber.steps.busca", "org.brienze.biscoint.cucumber.steps.cadastro"},
		plugin = "pretty")
public class CucumberRunnerTest {

}
