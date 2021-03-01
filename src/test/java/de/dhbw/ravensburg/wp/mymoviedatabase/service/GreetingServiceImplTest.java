package de.dhbw.ravensburg.wp.mymoviedatabase.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GreetingServiceImplTest {

    @Autowired
    GreetingService greetingService;


    @Test
    public void shouldSuccessfullyReturnGreeting(){
        String username = "Peter";
        String language = "de";
        String expection = "Hallo Peter, herzlich willkommen zu MyMovieDatabase";
        String result = greetingService.personalizeGreeting(username, language);
        assertEquals(expection, result);
    }


    @ParameterizedTest
    @CsvSource({"max.muster, de, 'Hallo max.muster, herzlich willkommen zu MyMovieDatabase'",
            "Alex, fr, 'Salut Alex, bienvenue à MyMovieDatabase'",
            "John, xy, 'Hola John, bienvenido a MyMovieDatabase'",
            "Sally, en, 'Hello Sally, wellcome to MyMovieDatabase'"})
    public void shouldSuccessfullyReturnGreeting2(String username, String language, String expection){
        String result = greetingService.personalizeGreeting(username, language);
        assertEquals(expection, result);
    }

}
