package pt.tfc_ulusofona.androidquizbuilder.helpers;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

import pt.tfc_ulusofona.androidquizbuilder.Model.Questionario;
import pt.tfc_ulusofona.androidquizbuilder.Utils.NetworkUtils;

public class PerfilLoader extends AsyncTaskLoader{
    private String urlAdressQuestionarios;
    private String urlAdressResultados;
    private String urlAdressUtilizadores;
    private String user;
    public PerfilLoader(Context context, String urlAdressQuestionarios, String urlAdressResultados, String urlAdressUtilizadores, String user) {
        super(context);
        this.urlAdressQuestionarios=urlAdressQuestionarios;
        this.urlAdressResultados=urlAdressResultados;
        this.urlAdressUtilizadores=urlAdressUtilizadores;
        this.user = user;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<Questionario> loadInBackground() {
        return NetworkUtils.fetchDataPerfil(urlAdressQuestionarios, urlAdressResultados, urlAdressUtilizadores, user);
    }
}
