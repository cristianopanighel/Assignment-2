package it.unipd.tos.model;

import org.junit.Test;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void User_Test()
    {
        User utente = new User("Panighel", "Cristiano", LocalDate.of(1999,10,06));
        assertEquals("Panighel", utente.getCognome());
        assertEquals("Cristiano", utente.getNome());
        assertEquals(LocalDate.of(1999,10,06), utente.getDob());
    }
}