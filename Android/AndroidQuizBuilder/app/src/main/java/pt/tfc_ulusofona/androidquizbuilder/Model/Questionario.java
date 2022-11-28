package pt.tfc_ulusofona.androidquizbuilder.Model;

import java.io.Serializable;

public class Questionario implements Serializable {
    private int id;
    public String modo;
    private String titulo;
    private String descricao;
    private String dataDeCriacao;
    private String estado;
    private String dificuldade;
    public int timerMinutos;
    public int timerSegundos;

    public Questionario(int id, String modo, String titulo, String descricao, String dataDeCriacao, String estado, String dificuldade, int timer, int timer1){
        this.id=id;
        this.modo=modo;
        this.titulo=titulo;
        this.descricao=descricao;
        this.dataDeCriacao=dataDeCriacao;
        this.estado=estado;
        this.dificuldade = dificuldade;
        this.timerMinutos = timer;
        this.timerSegundos = timer1;
    }
    public Questionario(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModo() { return modo; }

    public String getTitulo() {
        return titulo;
    }

    public int getTimerMinutos() { return timerMinutos; }

    public int getTimerSegundos() { return timerSegundos; }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(String dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDificuldade() { return dificuldade; }

    public void setDificuldade(String dificuldade) { this.dificuldade = dificuldade; }
}
