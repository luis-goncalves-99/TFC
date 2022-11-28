package pt.tfc_ulusofona.androidquizbuilder.helpers;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;

import pt.tfc_ulusofona.androidquizbuilder.Model.Questionario;
import pt.tfc_ulusofona.androidquizbuilder.Utils.NetworkUtils;

public class QuestionarioLoader extends AsyncTaskLoader{
    private String urlAdressQuestionarios;
    private String urlAdressPerguntas;
    private String urlAdressRespostas;
    private String urlAdressResultados;
    private String urlAdressUtilizadores;
    private String modo;
    public QuestionarioLoader(Context context, String urlAdressQuestionarios, String urlAdressPerguntas, String urlAdressRespostas, String urlAdressResultados, String urlAdressUtilizadores, String modo) {
        super(context);
        this.urlAdressQuestionarios=urlAdressQuestionarios;
        this.urlAdressPerguntas=urlAdressPerguntas;
        this.urlAdressRespostas=urlAdressRespostas;
        this.urlAdressResultados=urlAdressResultados;
        this.urlAdressUtilizadores=urlAdressUtilizadores;
        this.modo = modo;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<Questionario> loadInBackground() {
        return NetworkUtils.fetchData(urlAdressQuestionarios, urlAdressPerguntas, urlAdressRespostas, urlAdressResultados, urlAdressUtilizadores, modo);
    }
}
