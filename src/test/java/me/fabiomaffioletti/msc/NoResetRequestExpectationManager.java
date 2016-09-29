package me.fabiomaffioletti.msc;

import org.springframework.test.web.client.UnorderedRequestExpectationManager;

public class NoResetRequestExpectationManager extends UnorderedRequestExpectationManager {

    @Override
    public void reset() {
        // do not reset or clear the expectation list
    }

}