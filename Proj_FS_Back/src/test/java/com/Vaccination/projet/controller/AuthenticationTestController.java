package com.Vaccination.projet.controller;

import com.Vaccination.projet.controller.AuthenticationController;
import com.Vaccination.projet.dto.AuthDto;
import com.Vaccination.projet.utils.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthenticationControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void authenticate_ShouldReturnToken_WhenCredentialsAreValid() {
        // Arrange
        AuthDto authDto = new AuthDto();
        Authentication authentication = mock(Authentication.class);
        UserDetails userDetails = mock(UserDetails.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(userDetailsService.loadUserByUsername("testUser")).thenReturn(userDetails);
        when(jwtTokenProvider.generateToken(userDetails)).thenReturn("mockedToken");

        // Act
        Map<String, String> response = authenticationController.authenticate(authDto);

        // Assert
        assertEquals("mockedToken", response.get("token"));
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userDetailsService).loadUserByUsername("testUser");
        verify(jwtTokenProvider).generateToken(userDetails);
    }
}
