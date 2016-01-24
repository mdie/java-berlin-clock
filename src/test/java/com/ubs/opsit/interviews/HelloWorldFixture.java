package com.ubs.opsit.interviews;

import static com.ubs.opsit.interviews.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;
import static org.assertj.core.api.Assertions.assertThat;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;

/**
 * @author mdie
 * 
 * Acceptance test class that uses the JBehave (Gerkin) syntax for writing stories (Hello World example).  
 */
public class HelloWorldFixture {

    private HelloWorld helloWorld;
    private String theInputDate;

    @Test
    public void helloWorldAcceptanceTests() throws Exception {
        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("hello-world.story")
                .run();
    }

    @When("the date is $date")
    public void whenTheDateIs(String date) {
        theInputDate = date;
    }

    @Then("the message should look like $")
    public void thenTheMessageShouldLookLike(String theExpectedMessage) {
        assertThat(helloWorld.sayHelloAndDayOfTheWeek(theInputDate)).isEqualTo(theExpectedMessage);
    }
}

