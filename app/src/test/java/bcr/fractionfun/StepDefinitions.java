package bcr.fractionfun;

import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Then;

public class StepDefinitions {
    private Fraction result;

    @When("we have the input {string}")
    public void we_have_the_input(String string) {
        result = Fraction.computeExpression(string);
    }
    @Then("the output should be {string}")
    public void the_output_should_be(String string) {
        assertEquals(string, result.toString());
    }    
}
