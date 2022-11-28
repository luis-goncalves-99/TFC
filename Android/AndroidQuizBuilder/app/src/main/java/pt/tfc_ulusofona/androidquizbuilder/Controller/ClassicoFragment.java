package pt.tfc_ulusofona.androidquizbuilder.Controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import pt.tfc_ulusofona.androidquizbuilder.Model.Pergunta;
import pt.tfc_ulusofona.androidquizbuilder.Model.Questionario;
import pt.tfc_ulusofona.androidquizbuilder.Model.Resposta;
import pt.tfc_ulusofona.androidquizbuilder.Model.Resultado;
import pt.tfc_ulusofona.androidquizbuilder.Model.Utilizador;
import pt.tfc_ulusofona.androidquizbuilder.R;
import pt.tfc_ulusofona.androidquizbuilder.helpers.RankingClassicoLoader;

public class ClassicoFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<Questionario>>{

    private final String URL_ADRESS_QUESTIONARIO = "http://" + Common.ip + "/TFC/AndroidQuizBuilder-master/php/www/teste.php?action=questionarios";
    private final String URL_ADRESS_RANKINGS = "http://" + Common.ip + "/TFC/AndroidQuizBuilder-master/php/www/teste.php?action=rankings";
    private final String URL_ADRESS_UTILIZADORES = "http://" + Common.ip + "/TFC/AndroidQuizBuilder-master/php/www/teste.php?action=utilizadores";
    private ArrayList<Questionario> questionarios = new ArrayList<>();
    public static ArrayList<Pergunta> perguntas = new ArrayList<>();
    public static ArrayList<Resposta> respostas = new ArrayList<>();
    public static ArrayList<Resultado> resultados = new ArrayList<>();
    public static ArrayList<Utilizador> utilizadores = new ArrayList<>();
    private String modo = "classico";




    public ClassicoFragment() {
        // Required empty public constructor
    }


    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            modo = getArguments().getString(ARG_PARAM1);
        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getLoaderManager().initLoader(100, null, this);
        return inflater.inflate(R.layout.fragment_classico, container, false);


    }

    @NonNull
    @Override
    public Loader<ArrayList<Questionario>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new RankingClassicoLoader(getContext(), URL_ADRESS_QUESTIONARIO, URL_ADRESS_RANKINGS, URL_ADRESS_UTILIZADORES, modo);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Questionario>> loader, ArrayList<Questionario> questionarios) {
        this.questionarios = questionarios;
        ListView lista = (ListView) getView().findViewById(R.id.lista_classico);

        final listaResultadosAdapter adapter = new listaResultadosAdapter(getActivity(),resultados,utilizadores);

        lista.setAdapter(adapter);
        adapter.notifyDataSetChanged();



    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Questionario>> loader) {

    }
}
