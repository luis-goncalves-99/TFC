package pt.tfc_ulusofona.androidquizbuilder.helpers;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;

import pt.tfc_ulusofona.androidquizbuilder.Model.Questionario;
import pt.tfc_ulusofona.androidquizbuilder.Utils.NetworkUtils;

public class RankingClassicoLoader extends AsyncTaskLoader {
    private String urlAdressQuestionarios;
    private String urlAdressUtilizadores;
    private String urlAdressRankings;
    private String modo;
    public RankingClassicoLoader(Context context, String urlAdressQuestionarios, String urlAdressRankings, String urlAdressUtilizadores, String modo) {
        super(context);
        this.urlAdressRankings = urlAdressRankings;
        this.urlAdressQuestionarios=urlAdressQuestionarios;
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
        return NetworkUtils.fetchDataRankingClassico(urlAdressQuestionarios, urlAdressRankings, urlAdressUtilizadores, modo);
    }
}
