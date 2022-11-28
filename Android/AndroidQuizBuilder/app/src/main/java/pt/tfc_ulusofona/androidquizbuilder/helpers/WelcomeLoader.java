package pt.tfc_ulusofona.androidquizbuilder.helpers;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

import pt.tfc_ulusofona.androidquizbuilder.Model.Questionario;
import pt.tfc_ulusofona.androidquizbuilder.Utils.NetworkUtils;

public class WelcomeLoader extends AsyncTaskLoader{
    private String urlAdressQuestionarios;
    public WelcomeLoader(Context context, String urlAdressQuestionarios) {
        super(context);
        this.urlAdressQuestionarios=urlAdressQuestionarios;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<Questionario> loadInBackground() {
        return NetworkUtils.fetchQuestionario(urlAdressQuestionarios);
    }
}
