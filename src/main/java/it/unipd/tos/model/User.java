////////////////////////////////////////////////////////////////////
// CRISTIANO PANIGHEL 1201284
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj) {
            return true;
        }
        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return cognome.equals(user.cognome) && nome.equals(user.nome) && data.equals(user.data);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(cognome, nome, data);
    }
}