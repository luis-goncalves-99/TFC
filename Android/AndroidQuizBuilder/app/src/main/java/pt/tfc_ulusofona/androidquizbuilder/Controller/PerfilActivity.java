package pt.tfc_ulusofona.androidquizbuilder.Controller;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pt.tfc_ulusofona.androidquizbuilder.Model.Pergunta;
import pt.tfc_ulusofona.androidquizbuilder.Model.Questionario;
import pt.tfc_ulusofona.androidquizbuilder.Model.Resposta;
import pt.tfc_ulusofona.androidquizbuilder.Model.Resultado;
import pt.tfc_ulusofona.androidquizbuilder.Model.Utilizador;
import pt.tfc_ulusofona.androidquizbuilder.R;
import pt.tfc_ulusofona.androidquizbuilder.helpers.PerfilLoader;
import pt.tfc_ulusofona.androidquizbuilder.helpers.QuestionarioLoader;

public class PerfilActivity extends Activity implements LoaderManager.LoaderCallbacks<ArrayList<Questionario>> {

    private String nomeUtilizador;
    private final String URL_ADRESS_QUESTIONARIO = "http://" + Common.ip + "/TFC/AndroidQuizBuilder-master/php/www/teste.php?action=questionarios";
    private final String URL_ADRESS_RESULTADOS = "http://" + Common.ip + "/TFC/AndroidQuizBuilder-master/php/www/teste.php?action=resultados";
    private final String URL_ADRESS_UTILIZADORES = "http://" + Common.ip + "/TFC/AndroidQuizBuilder-master/php/www/teste.php?action=utilizadores";
    private ArrayList<Questionario> questionarios = new ArrayList<>();
    public static ArrayList<Pergunta> perguntas = new ArrayList<>();
    public static ArrayList<Resposta> respostas = new ArrayList<>();
    public static ArrayList<Resultado> resultados = new ArrayList<>();
    public static Utilizador user = new Utilizador();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        nomeUtilizador = getIntent().getStringExtra("username");
        TextView tvWelcome = (TextView) findViewById(R.id.tv_welcome);
        getLoaderManager().initLoader(100, null, this);
        ImageView img = (ImageView) findViewById(R.id.image_view);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PerfilActivity.this, WelcomeActivity.class);
                intent.putExtra("username", nomeUtilizador);
                startActivity(intent);
            }
        });
    }
    @Override
    public Loader<ArrayList<Questionario>> onCreateLoader(int id, Bundle args) {
        return new PerfilLoader(PerfilActivity.this, URL_ADRESS_QUESTIONARIO, URL_ADRESS_RESULTADOS, URL_ADRESS_UTILIZADORES,nomeUtilizador);
    }
    @Override
    public void onLoadFinished(Loader<ArrayList<Questionario>> loader, ArrayList<Questionario> questionarios) {

        this.questionarios = questionarios;

        TextView total_questionarios = (TextView) findViewById(R.id.total_questionarios);
        TextView total_perguntas = (TextView) findViewById(R.id.total_perguntas_respondidas);
        TextView total_score = (TextView) findViewById(R.id.total_score);
        int totalscore=0;
        int totalperguntas=0;
        int totalquestionarios=resultados.size();
        int id=-1;
        Map<String,String> mapa = new HashMap<>();


        for(Resultado resultado : resultados)
        {
            if(!resultado.getModo().equals("questionario")){
                mapa.put(resultado.getModo(),resultado.getModo());
                id=resultado.getUtilizadorID();
                totalscore+=resultado.getScore();
                totalperguntas += resultado.getQtd_corretas()+resultado.getQtd_erradas();
            }
        }
        total_questionarios.setText("Questionários Respondidos: "+totalquestionarios);
        total_perguntas.setText("Perguntas Respondidas: "+totalperguntas);
        total_score.setText("Total Score: "+totalscore+" pts");

        ListView lista = (ListView) findViewById(R.id.lista_perfil);
        final listaPerfilAdapter adapter = new listaPerfilAdapter(this, questionarios,resultados,id,mapa.size());
        lista.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onLoaderReset(Loader<ArrayList<Questionario>> loader) { }
}
