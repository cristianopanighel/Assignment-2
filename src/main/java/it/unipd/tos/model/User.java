////////////////////////////////////////////////////////////////////
// CRISTIANO PANIGHEL 1201284
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import java.time.LocalDate;

public class User {

    private final String cognome, nome;
    private final LocalDate data;

    public User(String surname, String name, LocalDate dob) {
        this.cognome = surname;
        this.nome = name;
        this.data = dob;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public LocalDate getDob() { 
        return data;
    }
}