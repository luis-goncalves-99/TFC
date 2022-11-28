package pt.tfc_ulusofona.androidquizbuilder.Controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pt.tfc_ulusofona.androidquizbuilder.Model.Pergunta;
import pt.tfc_ulusofona.androidquizbuilder.Model.Questionario;
import pt.tfc_ulusofona.androidquizbuilder.Model.Resposta;
import pt.tfc_ulusofona.androidquizbuilder.Model.Resultado;
import pt.tfc_ulusofona.androidquizbuilder.Model.Utilizador;
import pt.tfc_ulusofona.androidquizbuilder.R;
import pt.tfc_ulusofona.androidquizbuilder.helpers.QuestionarioLoader;
import pt.tfc_ulusofona.androidquizbuilder.helpers.WelcomeLoader;

public class WelcomeActivity extends Activity implements LoaderManager.LoaderCallbacks<ArrayList<Questionario>> {

    private TextView tvWelcome;
    private final String URL_ADRESS_QUESTIONARIO = "http://" + Common.ip + "/TFC/AndroidQuizBuilder-master/php/www/teste.php?action=questionarios";
    private ArrayList<Questionario> questionarios = new ArrayList<>();
    public static ArrayList<Pergunta> perguntas = new ArrayList<>();
    public static ArrayList<Resposta> respostas = new ArrayList<>();
    public static ArrayList<Resultado> resultados = new ArrayList<>();
    public static ArrayList<Utilizador> utilizadores = new ArrayList<>();
    private int i = 0;
    private String nomeUtilizador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        tvWelcome = (TextView) findViewById(R.id.tv_welcome);
        nomeUtilizador = getIntent().getStringExtra("username");
        tvWelcome.setText("Bem vindo: " + nomeUtilizador);
        getLoaderManager().initLoader(100, null, this);

        Button button_jogo = (Button) findViewById(R.id.button_inicial_jogo);
        Button button_questionario = (Button) findViewById(R.id.button_inicial_questionário);
        Button button_ranking = (Button) findViewById(R.id.button_inicial_ranking);
        Button button_perfil = (Button) findViewById(R.id.button_inicial_perfil);

        button_jogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botaoJogo();
            }
        });
        button_questionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botaoQuestionario();
            }
        });
        button_ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botaoRanking();
            }
        });
        button_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botaoPerfil();
            }
        });
        ImageView img = (ImageView) findViewById(R.id.image_view);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public Loader<ArrayList<Questionario>> onCreateLoader(int id, Bundle args) {
        return new WelcomeLoader(WelcomeActivity.this, URL_ADRESS_QUESTIONARIO);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Questionario>> loader, ArrayList<Questionario> questionarios) {
        this.questionarios = questionarios;
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Questionario>> loader) {
    }

    public void botaoJogo() {

        AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.modos_jogo, null);
        Button botao_classico = (Button) mView.findViewById(R.id.button_classico);
        Button botao_contra_relogio = (Button) mView.findViewById(R.id.button_contra_relogio);
        Button botao_morte_subita = (Button) mView.findViewById(R.id.button_morte_subita);
        Button botao_modo_todos = (Button) mView.findViewById(R.id.button_todos);

        botao_classico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botoesdJogo("classico");
            }
        });
        botao_contra_relogio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botoesdJogo("contra_relogio");
            }
        });
        botao_morte_subita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botoesdJogo("morte_subita");
            }
        });
        botao_modo_todos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botoesdJogo("todos");
            }
        });

        builder.setView(mView);
        AlertDialog dialog = builder.create();
        dialog.show();

        /*Intent i = new Intent(getApplicationContext(), JogoActivity.class);
        i.putExtra("username", nomeUtilizador);
        startActivity(i);*/
    }

    public void botoesdJogo(String modo) {
        if (existeQuestionario(modo)) {
            Intent i = new Intent(getApplicationContext(), JogoActivity.class);
            i.putExtra("username", nomeUtilizador);
            i.putExtra("modo", modo);
            startActivity(i);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this, R.style.AlertDialogStyle);
            builder.setMessage("Nao existe nenhum Questionário desse modo.");
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

    public void botaoQuestionario() {
        if (existeQuestionario("questionario")) {
            Intent i = new Intent(getApplicationContext(), QuestionarioActivity.class);
            i.putExtra("username", nomeUtilizador);
            startActivity(i);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this, R.style.AlertDialogStyle);
            builder.setMessage("Nao existe nenhuma Questionário desse modo.");
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

    public void botaoRanking() {
        Intent i = new Intent(getApplicationContext(), RankingsActivity.class);
        i.putExtra("username", nomeUtilizador);
        startActivity(i);
    }

    public void botaoPerfil() {
        Intent i = new Intent(getApplicationContext(), PerfilActivity.class);
        i.putExtra("username", nomeUtilizador);
        startActivity(i);
    }

    public boolean existeQuestionario(String modo) {
        for (Questionario questionario : questionarios) {
            if (questionario.getModo().equals(modo)) {
                return true;
            }else if(modo.equals("todos"))
            {
                if(!questionario.getModo().equals("questionario")){
                    return true;
                }

            }
        }
        return false;
    }

}