package pt.tfc_ulusofona.androidquizbuilder.Model;

import java.io.Serializable;
import java.util.Comparator;

public class Resultado implements Serializable{
    private int id;
    private int utilizadorID;
    private int questionarioID;
    private int qtd_corretas;
    private int qtd_erradas;
    private int score;
    private String dataDeCriacao;
    private String modo;

    public int getId() {
        return id;
    }

    public int getUtilizadorID() {
        return utilizadorID;
    }

    public void setUtilizadorID(int utilizadorID) {
        this.utilizadorID = utilizadorID;
    }

    public int getQuestionarioID() {
        return questionarioID;
    }

    public void setQuestionarioID(int questionarioID) {
        this.questionarioID = questionarioID;
    }

    public int getQtd_corretas() {
        return qtd_corretas;
    }

    public void setQtd_corretas(int qtd_corretas) {
        this.qtd_corretas = qtd_corretas;
    }

    public String getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(String dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public int getQtd_erradas() {
        return qtd_erradas;
    }

    public void setQtd_erradas(int qtd_erradas) {
        this.qtd_erradas = qtd_erradas;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public Resultado(int id, int utilizadorID, int questionarioID, String dataDeCriacao, int qtd_corretas, int qtd_erradas, int score, String modo) {
        this.id = id;
        this.dataDeCriacao = dataDeCriacao;
        this.utilizadorID = utilizadorID;
        this.questionarioID = questionarioID;
        this.qtd_corretas = qtd_corretas;
        this.qtd_erradas = qtd_erradas;
        this.score = score;
        this.modo = modo;
    }
    public static Comparator<Resultado> Score = new Comparator<Resultado>() {
        @Override
        public int compare(Resultado resultado, Resultado resultado1) {
           return resultado.getScore()-resultado1.getScore();
        }};
}
