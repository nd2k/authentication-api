package com.example.authentication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
class AuthenticationApiApplicationTests {

	@Test
	void contextLoads() {
		// Arrange
		AuthenticationApiApplication authenticationApiApplicationMock = mock(AuthenticationApiApplication.class);

		// Assert & Act
		verify(authenticationApiApplicationMock, times(1)).main(new String[] {});

	}

}
