package pt.tfc_ulusofona.androidquizbuilder.Model;

import java.io.Serializable;

public class Pergunta implements Serializable{
    private int id;
    private String texto;
    private int resposta;
    private int questionarioID;
    private int respostaID;

    public Pergunta(int id, String texto, int resposta, int questionarioID, int respostaID) {
        this.id = id;
        this.texto = texto;
        this.resposta = resposta;
        this.questionarioID = questionarioID;
        this.respostaID= respostaID;
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

    public int getResposta() {
        return resposta;
    }

    public void setResposta(int resposta) {
        this.resposta = resposta;
    }

    public int getQuestionarioID() {
        return questionarioID;
    }

    public void setQuestionarioID(int questionarioID) {
        this.questionarioID = questionarioID;
    }

    public int getRespostaID() {
        return respostaID;
    }

    public void setRespostaID(int respostaID) {
        this.respostaID = respostaID;
    }
}
