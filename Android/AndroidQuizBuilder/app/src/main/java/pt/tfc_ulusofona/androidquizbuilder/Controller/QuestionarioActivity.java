package pt.tfc_ulusofona.androidquizbuilder.Controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;


import java.util.ArrayList;
import java.util.Collections;

import pt.tfc_ulusofona.androidquizbuilder.Model.Pergunta;
import pt.tfc_ulusofona.androidquizbuilder.Model.Questionario;
import pt.tfc_ulusofona.androidquizbuilder.Model.Resposta;
import pt.tfc_ulusofona.androidquizbuilder.Model.Resultado;
import pt.tfc_ulusofona.androidquizbuilder.Model.Utilizador;
import pt.tfc_ulusofona.androidquizbuilder.R;
import pt.tfc_ulusofona.androidquizbuilder.helpers.QuestionarioLoader;

public class QuestionarioActivity extends Activity implements LoaderManager.LoaderCallbacks<ArrayList<Questionario>> {
    private TextView tvWelcome;
    private final String URL_ADRESS_QUESTIONARIO = "http://" + Common.ip + "/TFC/AndroidQuizBuilder-master/php/www/teste.php?action=questionarios";
    private final String URL_ADRESS_PERGUNTAS = "http://" + Common.ip + "/TFC/AndroidQuizBuilder-master/php/www/teste.php?action=perguntas";
    private final String URL_ADRESS_RESPOSTAS = "http://" + Common.ip + "/TFC/AndroidQuizBuilder-master/php/www/teste.php?action=respostas";
    private final String URL_ADRESS_RESULTADOS = "http://" + Common.ip + "/TFC/AndroidQuizBuilder-master/php/www/teste.php?action=resultados";
    private final String URL_ADRESS_UTILIZADORES = "http://" + Common.ip + "/TFC/AndroidQuizBuilder-master/php/www/teste.php?action=utilizadores";
    private ArrayList<Questionario> questionarios = new ArrayList<>();
    public static ArrayList<Pergunta> perguntas = new ArrayList<>();
    public static ArrayList<Resposta> respostas = new ArrayList<>();
    public static ArrayList<Resultado> resultados = new ArrayList<>();
    public static ArrayList<Utilizador> utilizadores = new ArrayList<>();
    private int i = 0;
    private String nomeUtilizador;
    private int utilizadorID;
    private int respondido;
    private String modo;
    boolean ajuda_titulo = true;
    boolean ajuda_data = true;
    boolean ajuda_dificuldade = true;
    Boolean isFABOpen = false;
    FloatingActionButton fabTitulo;
    FloatingActionButton fabDificuldade;
    FloatingActionButton fabData;
    FloatingActionMenu fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionario);
        tvWelcome = (TextView) findViewById(R.id.tv_welcome);
        nomeUtilizador = getIntent().getStringExtra("username");
        tvWelcome.setText("Modo Questionário");
        if (getIntent().getStringExtra("modo") == null) {
            modo = "questionario";
        }
        fab = findViewById(R.id.button_filtro);
        fabTitulo =  findViewById(R.id.button_titulo);
        fabDificuldade =  findViewById(R.id.button_dificuldade);
        fabData = findViewById(R.id.button_data);
        getLoaderManager().initLoader(100, null, this);

    }
    private void showFABMenu() {
        isFABOpen = true;
        fabTitulo.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fabDificuldade.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        fabData.animate().translationY(-getResources().getDimension(R.dimen.standard_155));
    }

    private void closeFABMenu() {
        isFABOpen = false;
        fabTitulo.animate().translationY(0);
        fabDificuldade.animate().translationY(0);
        fabData.animate().translationY(0);
    }

    @Override
    public Loader<ArrayList<Questionario>> onCreateLoader(int id, Bundle args) {
        return new QuestionarioLoader(QuestionarioActivity.this, URL_ADRESS_QUESTIONARIO, URL_ADRESS_PERGUNTAS, URL_ADRESS_RESPOSTAS, URL_ADRESS_RESULTADOS, URL_ADRESS_UTILIZADORES, modo);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Questionario>> loader, ArrayList<Questionario> questionarios) {

        this.questionarios = questionarios;

        ListView lista = (ListView) findViewById(R.id.lista_questionario);

        final listaAdapter adapter = new listaAdapter(this, questionarios);
        lista.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClicarQuestionario(view, adapter, position);
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFABOpen) {
                    showFABMenu();
                } else {
                    closeFABMenu();
                }
            }
        });
        fabTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ajuda_titulo == false) {
                    OrdenarTitulo(ajuda_titulo);
                } else {
                    OrdenarTitulo(ajuda_titulo);
                }
                ajuda_titulo = !ajuda_titulo;
                adapter.listaQuestionario = questionarios;
                lista.setAdapter(adapter);
            }
        });
        fabDificuldade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ajuda_dificuldade == false) {
                    OrdenarDificuldade(ajuda_dificuldade);
                } else {
                    OrdenarDificuldade(ajuda_dificuldade);
                }
                ajuda_dificuldade = !ajuda_dificuldade;
                adapter.listaQuestionario = questionarios;
                lista.setAdapter(adapter);
            }
        });
        fabData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ajuda_titulo == false) {
                    OrdenarData(ajuda_data);
                } else {
                    OrdenarData(ajuda_data);
                }
                ajuda_data = !ajuda_data;
                adapter.listaQuestionario = questionarios;
                lista.setAdapter(adapter);
            }
        });

        ImageView img = (ImageView) findViewById(R.id.image_view);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionarioActivity.this, WelcomeActivity.class);
                intent.putExtra("username", nomeUtilizador);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Questionario>> loader) {
    }

    public void ClicarQuestionario(View view, listaAdapter adapter, int position) {

        respondido = 0;
        Questionario questionario_selecionado = adapter.getQuestionario(adapter.getQuestionarioId(position));
        ArrayList<Resultado> lista_resultados_user = new ArrayList<>();

        for (int j = 0; j < utilizadores.size(); j++) {
            if (utilizadores.get(j).getNome().equals(nomeUtilizador)) {
                utilizadorID = utilizadores.get(j).getId();
            }
        }
        for (int k = 0; k < resultados.size(); k++) {
            if ((resultados.get(k).getUtilizadorID() == utilizadorID) && (resultados.get(k).getQuestionarioID() == questionario_selecionado.getId()) && resultados.get(k).getModo().equals("questionario")) {
                respondido = 1;
                break;
            }if(resultados.get(k).getUtilizadorID() == utilizadorID && resultados.get(k).getQuestionarioID() == questionario_selecionado.getId()){
                lista_resultados_user.add(resultados.get(k));
            }
        }
        if (respondido == 0) {
            ArrayList<Pergunta> lista_perguntas = PerguntasQuestionário(questionario_selecionado.getId());
            ArrayList<Resposta> lista_respostas = RespostasQuestionário(lista_perguntas);
            Intent intent = new Intent(QuestionarioActivity.this, PerguntaActivity.class);
            //intent.putExtra("respostaID", "" + 0);
            //intent.putExtra("perguntaID", "" + 0);
            intent.putExtra("nomeUtilizador", nomeUtilizador);
            intent.putExtra("questionarioID", "" + questionario_selecionado.getId());
            intent.putExtra("timer", "" + questionario_selecionado.getTimerMinutos());
            intent.putExtra("timer1", "" + questionario_selecionado.getTimerSegundos());
            intent.putExtra("perguntas", lista_perguntas);
            intent.putExtra("respostas", lista_respostas);
            intent.putExtra("totalperguntas", "" + lista_perguntas.size());
            intent.putExtra("modo", "questionario");
            intent.putExtra("lista_resultados_user",lista_resultados_user);
            startActivity(intent);

        } else {

            AlertDialog.Builder builder = new AlertDialog.Builder(QuestionarioActivity.this, R.style.AlertDialogStyle);
            builder.setMessage("Já respondeu a esse Questionário.");
            builder.setIcon(R.drawable.ic_add_alert_black_24dp);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.create().show();

        }
    }

    public ArrayList<Pergunta> PerguntasQuestionário(int id) {
        ArrayList<Pergunta> lista_perguntas = new ArrayList<>();
        for (Pergunta pergunta : perguntas) {
            if (pergunta.getQuestionarioID() == id) {
                lista_perguntas.add(pergunta);
            }
        }
        return lista_perguntas;
    }

    public ArrayList<Resposta> RespostasQuestionário(ArrayList<Pergunta> perguntas) {
        ArrayList<Resposta> lista_resposta = new ArrayList<>();
        for (Pergunta pergunta : perguntas) {
            for (Resposta resposta : respostas) {
                if (resposta.getPergutaID() == pergunta.getId()) {
                    lista_resposta.add(resposta);
                }
            }
        }
        return lista_resposta;
    }

    public void OrdenarTitulo(boolean first) {
        ArrayList<String> array_list = new ArrayList<String>();
        ArrayList<Questionario> ajuda_questionario = new ArrayList<Questionario>();


        for (Questionario idx : questionarios) {
            array_list.add(idx.getTitulo() + " " + idx.getId());

        }
        Collections.sort(array_list);
        if (first == false) {
            for (int i = array_list.size() - 1; i >= 0; i--) {

                String[] palavras = array_list.get(i).split(" ");
                int indice = Integer.parseInt(palavras[palavras.length - 1]);
                for (Questionario questionario : questionarios) {
                    if (questionario.getId() == indice) {
                        ajuda_questionario.add(questionario);
                    }
                }
            }
        } else {
            for (int i = 0; i < array_list.size(); i++) {

                String[] palavras = array_list.get(i).split(" ");
                int indice = Integer.parseInt(palavras[palavras.length - 1]);
                for (Questionario questionario : questionarios) {
                    if (questionario.getId() == indice) {
                        ajuda_questionario.add(questionario);
                    }
                }
            }
        }
        questionarios.clear();
        questionarios.addAll(ajuda_questionario);
    }

    public void OrdenarDificuldade(boolean first) {
        ArrayList<String> array_list = new ArrayList<String>();
        ArrayList<Questionario> ajuda_questionario = new ArrayList<Questionario>();


        for (Questionario idx : questionarios) {
            array_list.add(idx.getDificuldade() + " " + idx.getId());

        }
        Collections.sort(array_list);
        if (first == false) {
            for (int i = array_list.size() - 1; i >= 0; i--) {

                String[] palavras = array_list.get(i).split(" ");
                int indice = Integer.parseInt(palavras[palavras.length - 1]);
                for (Questionario questionario : questionarios) {
                    if (questionario.getId() == indice) {
                        ajuda_questionario.add(questionario);
                    }
                }
            }
        } else {
            for (int i = 0; i < array_list.size(); i++) {

                String[] palavras = array_list.get(i).split(" ");
                int indice = Integer.parseInt(palavras[palavras.length - 1]);
                for (Questionario questionario : questionarios) {
                    if (questionario.getId() == indice) {
                        ajuda_questionario.add(questionario);
                    }
                }
            }
        }
        questionarios.clear();
        questionarios.addAll(ajuda_questionario);
    }

    public void OrdenarData(boolean first) {
        ArrayList<String> array_list = new ArrayList<String>();
        ArrayList<Questionario> ajuda_questionario = new ArrayList<Questionario>();


        for (Questionario idx : questionarios) {
            array_list.add(idx.getDataDeCriacao() + " " + idx.getId());

        }
        Collections.sort(array_list);
        if (first == false) {
            for (int i = array_list.size() - 1; i >= 0; i--) {

                String[] palavras = array_list.get(i).split(" ");
                int indice = Integer.parseInt(palavras[palavras.length - 1]);
                for (Questionario questionario : questionarios) {
                    if (questionario.getId() == indice) {
                        ajuda_questionario.add(questionario);
                    }
                }
            }
        } else {
            for (int i = 0; i < array_list.size(); i++) {

                String[] palavras = array_list.get(i).split(" ");
                int indice = Integer.parseInt(palavras[palavras.length - 1]);
                for (Questionario questionario : questionarios) {
                    if (questionario.getId() == indice) {
                        ajuda_questionario.add(questionario);
                    }
                }
            }
        }
        questionarios.clear();
        questionarios.addAll(ajuda_questionario);
    }
}