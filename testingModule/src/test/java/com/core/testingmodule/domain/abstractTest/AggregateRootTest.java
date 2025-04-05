package com.core.testingmodule.domain.abstractTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.core.domain.abstracts.AggregateRoot;
import org.junit.jupiter.api.Test;

class AggregateRootTest {

    static class TestAggregateRoot extends AggregateRoot {}

    @Test
    void shouldInstantiateAggregateRoot() {
        TestAggregateRoot aggregateRoot = new TestAggregateRoot();
        assertNotNull(aggregateRoot, "AggregateRoot instance should not be null");
    }
}
