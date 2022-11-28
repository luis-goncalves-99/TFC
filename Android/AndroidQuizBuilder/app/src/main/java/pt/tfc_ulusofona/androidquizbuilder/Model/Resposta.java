package pt.tfc_ulusofona.androidquizbuilder.Model;

import java.io.Serializable;

public class Resposta implements Serializable {
    private int id;
    private String texto;
    private int correta;
    private int pergutaID;


    public Resposta(int id, String texto, int correta, int pergutaID) {
        this.id = id;
        this.texto = texto;
        this.correta = correta;
        this.pergutaID = pergutaID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getCorreta() {
        return correta;
    }

    public void setCorreta(int correta) {
        this.correta = correta;
    }

    public int getPergutaID() {
        return pergutaID;
    }

    public void setPergutaID(int pergutaID) {
        this.pergutaID = pergutaID;
    }
}
