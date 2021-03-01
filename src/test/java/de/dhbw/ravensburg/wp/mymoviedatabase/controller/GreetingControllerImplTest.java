package de.dhbw.ravensburg.wp.mymoviedatabase.controller;

import de.dhbw.ravensburg.wp.mymoviedatabase.service.GreetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class GreetingControllerImplTest {

    @Autowired
    GreetingController greetingController;

    @MockBean
    GreetingService greetingService;

    @BeforeEach
    public void setUp(){
        Mockito.when(greetingService.personalizeGreeting(any(), any()))
                .thenReturn("Hallo Max Muster, herzlich willkommen zu MyMovieDatabase");
    }

    @Test
    public void shouldSuccessfullyReturnGreetingNormalCase(){
        String result = greetingController.personalizeGreeting("Max Muster", "de");
        //Sicherstellen dass personalizeGreeting aufgerufen wird (mit beliebigen Parametern)
        verify(greetingService).personalizeGreeting(any(), any());
        //Endergebnis überprüfen
        assertTrue(result.equals("Hallo Max Muster, herzlich willkommen zu MyMovieDatabase"));
    }


    @Test
    public void shouldSuccessfullyReturnGreetingNoUsernameNoLanguage(){
        String result = greetingController.personalizeGreeting("", "de");
        //Sicherstellen dass personalizeGreeting aufgerufen wird (mit dem neuen Usernamen)
        verify(greetingService).personalizeGreeting(eq("Max Mustermann"), any());
        //Endergebnis überprüfen (obwohl Service by default Spanisch zurückgibt, konkreter Wert
        // hier nicht relevant, da das Verhalten im Mock definiert wurde.
        assertTrue(result.equals("Hallo Max Muster, herzlich willkommen zu MyMovieDatabase"));
    }


}
