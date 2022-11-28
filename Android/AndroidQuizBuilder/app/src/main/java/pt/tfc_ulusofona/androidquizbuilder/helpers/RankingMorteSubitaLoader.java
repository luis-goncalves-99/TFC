package pt.tfc_ulusofona.androidquizbuilder.helpers;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;

import pt.tfc_ulusofona.androidquizbuilder.Model.Questionario;
import pt.tfc_ulusofona.androidquizbuilder.Utils.NetworkUtils;

public class RankingMorteSubitaLoader extends AsyncTaskLoader {
    private String urlAdressQuestionarios;
    private String urlAdressUtilizadores;
    private String urlAdressRankings;
    private String modo;
    public RankingMorteSubitaLoader(Context context, String urlAdressQuestionarios, String urlAdressRankings, String urlAdressUtilizadores, String modo) {
        super(context);
        this.urlAdressQuestionarios=urlAdressQuestionarios;
        this.urlAdressRankings=urlAdressRankings;
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
        return NetworkUtils.fetchDataRankingMorteSubita(urlAdressQuestionarios, urlAdressRankings, urlAdressUtilizadores, modo);
    }
}
