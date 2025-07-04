package com.core.testingmodule.application.user;

import com.core.application.user.getUser.GetUsersQuery;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GetUsersQueryTest {

    @Test
    void testCanInstantiate() {
        // Arrange & Act
        GetUsersQuery query = new GetUsersQuery();

        // Assert
        assertThat(query).isNotNull();
    }
}
