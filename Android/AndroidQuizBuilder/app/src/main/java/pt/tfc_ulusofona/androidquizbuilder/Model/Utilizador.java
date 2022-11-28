package pt.tfc_ulusofona.androidquizbuilder.Model;

import java.io.Serializable;

public class Utilizador implements Serializable{
    private int id;
    private String nome;

    public Utilizador(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    public Utilizador() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
