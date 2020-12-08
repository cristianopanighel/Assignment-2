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

    @Test
    public void User_Equal_Hash_Test()
    {
        LocalDate data = LocalDate.of(1999, 10,06);
        User utente = new User("Panighel", "Cristiano", data);
        User user = new User("Rossi", "Mario", data);
        assertEquals(-1336033723, utente.hashCode());

        assertTrue(utente.equals(utente));
        assertFalse(utente.equals(user));
    }
}