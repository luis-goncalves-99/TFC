package pt.tfc_ulusofona.androidquizbuilder.Controller;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import pt.tfc_ulusofona.androidquizbuilder.Model.Pergunta;
import pt.tfc_ulusofona.androidquizbuilder.Model.Resposta;
import pt.tfc_ulusofona.androidquizbuilder.Model.Resultado;
import pt.tfc_ulusofona.androidquizbuilder.R;
import pt.tfc_ulusofona.androidquizbuilder.Utils.Delay;


public class PerguntaActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private Intent intent;
    private ArrayList<Pergunta> perguntas = new ArrayList<>();
    private ArrayList<Resposta> respostas = new ArrayList<>();
    private ArrayList<Resultado> resultados = new ArrayList<>();
    private int questionarioID, respostaID, perguntaID;
    private int i = 0;
    private int qtdPerguntas;
    private int segundos=1;
    private String nomeUtilizador;
    private String modo;
    private int qtd_certas;
    private int qtd_erradas;
    int idPergunta = 0;
    int totalPerguntas;
    private String ajuda;
    ArrayList<Resposta> auxiliar;
    MediaPlayer sound;
    private TextView timerText;
    private TextView score_layout;
    private int score = 0;
    private int tempoBDMin;
    private int tempoBDSeg;
    private CountDownTimer timer;
    private CountDownTimer timer2;
    private long tempo = 0;
    private int id_certa = 0;
    View viewCerta = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.escolher_pergunta_layout2);
        intent = getIntent();
        questionarioID = Integer.parseInt(intent.getStringExtra("questionarioID"));
        perguntas = (ArrayList<Pergunta>) intent.getSerializableExtra("perguntas");
        respostas = (ArrayList<Resposta>) intent.getSerializableExtra("respostas");
        resultados = (ArrayList<Resultado>) intent.getSerializableExtra("lista_resultados_user");
        nomeUtilizador = intent.getStringExtra("nomeUtilizador");
        tempoBDMin = Integer.parseInt(intent.getStringExtra("timer"));
        tempoBDSeg = Integer.parseInt(intent.getStringExtra("timer1"));
        modo = intent.getStringExtra("modo");
        totalPerguntas = Integer.parseInt(intent.getStringExtra("totalperguntas"));
        Log.d("log_tag", nomeUtilizador);
        if (intent.getStringExtra("ajuda") != null) {
            score = Integer.parseInt(intent.getStringExtra("score"));
            qtd_erradas = Integer.parseInt(intent.getStringExtra("respostas_erradas"));
            qtd_certas = Integer.parseInt(intent.getStringExtra("respostas_certas"));
            qtdPerguntas = Integer.parseInt(intent.getStringExtra("qtdperguntas"));
        } else {
            qtdPerguntas = 1;
        }


        Random rand = new Random(System.currentTimeMillis());
        Collections.shuffle(perguntas);
        idPergunta = rand.nextInt(perguntas.size());


        auxiliar = respostasPergunta(perguntas.get(idPergunta).getId());

        Collections.shuffle(auxiliar);

        definir_id_certo(auxiliar);

        if(id_certa > 1)
            definirLayout(auxiliar.size(),id_certa);
        else
            definirLayout(auxiliar.size(),-1);

        //setContentView(R.layout.escolher_pergunta_layout);
        Button button_resposta_1 = (Button) findViewById(R.id.row_1);
        Button button_resposta_2 = (Button) findViewById(R.id.row_2);
        button_resposta_1.setTransformationMethod(null);
        button_resposta_2.setTransformationMethod(null);
        button_resposta_1.setText(auxiliar.get(0).getTexto());
        button_resposta_1.setTag(auxiliar.get(0).getCorreta());
        button_resposta_2.setText(auxiliar.get(1).getTexto());
        button_resposta_2.setTag(auxiliar.get(1).getCorreta());

        if(id_certa == 0)
            viewCerta = button_resposta_1;
        else if(id_certa == 1)
            {
                viewCerta = button_resposta_2;
            }



        getLoaderManager().initLoader(100, null, this);


        TextView textView = (TextView) findViewById(R.id.pergunta);
        textView.setText(perguntas.get(idPergunta).getTexto());


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView toolbar_pergunta = (TextView) findViewById(R.id.toolbar_pergunta);
        toolbar_pergunta.setText(qtdPerguntas + "/" + totalPerguntas);




        if (!modo.equals("questionario")) {
            score_layout = (TextView) findViewById(R.id.score);
            score_layout.setText(score + " pts");
        }


        //respostaID=Integer.parseInt(intent.getStringExtra("respostaID"));
        //perguntaID=Integer.parseInt(intent.getStringExtra("perguntaID"));
        if (tempoBDMin != 0 || tempoBDSeg != 0) {

            tempo = (tempoBDMin*60 * 1000) + (tempoBDSeg*1000);
            timerText = (TextView) findViewById(R.id.timer_teste);
            startTimer(this);
        }

        ImageView img = (ImageView) findViewById(R.id.image_view);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PerguntaActivity.this, R.style.AlertDialogStyle);
                builder.setMessage("Quer mesmo sair ? O seu progresso não será guardado.");
                builder.setIcon(R.drawable.ic_add_alert_black_24dp);
                builder.setNegativeButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(PerguntaActivity.this, WelcomeActivity.class);
                        intent.putExtra("username", nomeUtilizador);
                        startActivity(intent);

                    }
                });
                builder.setPositiveButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.create().show();
            }
        });

    }
    public void definir_id_certo(ArrayList<Resposta> respostas){
        int idx=0;
        for (Resposta resposta : respostas){
            if(resposta.getCorreta() == 1)
            {
               id_certa = idx;
            }
            idx++;
        }
    }

    public void definirLayout(int qtd,int id){
        switch (auxiliar.size())
        {

            case 3: {
                setContentView(R.layout.escolher_pergunta_layout2);
                Button button_resposta_3 = (Button) findViewById(R.id.row_3);
                if(id == 2){
                    viewCerta = button_resposta_3;
                }
                button_resposta_3.setTransformationMethod(null);
                button_resposta_3.setText(auxiliar.get(2).getTexto());
                button_resposta_3.setTag(auxiliar.get(2).getCorreta());
                break;
            }
            case 4:{
                setContentView(R.layout.escolher_pergunta_layout);
                Button button_resposta_3 = (Button) findViewById(R.id.row_3);
                Button button_resposta_4 = (Button) findViewById(R.id.row_4);
                if(id == 2){
                    viewCerta = button_resposta_3;
                }else if(id == 3)
                {
                    viewCerta = button_resposta_4;
                }
                button_resposta_3.setTransformationMethod(null);
                button_resposta_4.setTransformationMethod(null);
                button_resposta_3.setText(auxiliar.get(2).getTexto());
                button_resposta_3.setTag(auxiliar.get(2).getCorreta());
                button_resposta_4.setText(auxiliar.get(3).getTexto());
                button_resposta_4.setTag(auxiliar.get(3).getCorreta());
            } break;
            case 5:{
                setContentView(R.layout.escolher_pergunta_layout1);
                Button button_resposta_3 = (Button) findViewById(R.id.row_3);
                Button button_resposta_4 = (Button) findViewById(R.id.row_4);
                Button button_resposta_5 = (Button) findViewById(R.id.row_5);
                if(id == 2){
                    viewCerta = button_resposta_3;
                }else if(id == 3)
                {
                    viewCerta = button_resposta_4;
                }else if(id == 4)
                {
                    viewCerta = button_resposta_5;
                }
                button_resposta_3.setTransformationMethod(null);
                button_resposta_4.setTransformationMethod(null);
                button_resposta_5.setTransformationMethod(null);
                button_resposta_3.setText(auxiliar.get(2).getTexto());
                button_resposta_3.setTag(auxiliar.get(2).getCorreta());
                button_resposta_4.setText(auxiliar.get(3).getTexto());
                button_resposta_4.setTag(auxiliar.get(3).getCorreta());
                button_resposta_5.setText(auxiliar.get(4).getTexto());
                button_resposta_5.setTag(auxiliar.get(4).getCorreta());
            }break;
            case 6:{
                setContentView(R.layout.escolher_pergunta_layout3);
                Button button_resposta_3 = (Button) findViewById(R.id.row_3);
                Button button_resposta_4 = (Button) findViewById(R.id.row_4);
                Button button_resposta_5 = (Button) findViewById(R.id.row_5);
                Button button_resposta_6 = (Button) findViewById(R.id.row_6);
                if(id == 2){
                    viewCerta = button_resposta_3;
                }else if(id == 3)
                {
                    viewCerta = button_resposta_4;
                }else if(id == 4)
                {
                    viewCerta = button_resposta_5;
                }else if(id == 5)
                {
                    viewCerta = button_resposta_6;
                }
                button_resposta_3.setTransformationMethod(null);
                button_resposta_4.setTransformationMethod(null);
                button_resposta_5.setTransformationMethod(null);
                button_resposta_6.setTransformationMethod(null);
                button_resposta_3.setText(auxiliar.get(2).getTexto());
                button_resposta_3.setTag(auxiliar.get(2).getCorreta());
                button_resposta_4.setText(auxiliar.get(3).getTexto());
                button_resposta_4.setTag(auxiliar.get(3).getCorreta());
                button_resposta_5.setText(auxiliar.get(4).getTexto());
                button_resposta_5.setTag(auxiliar.get(4).getCorreta());
                button_resposta_6.setText(auxiliar.get(5).getTexto());
                button_resposta_6.setTag(auxiliar.get(5).getCorreta());

            }break;
        }
    }

    public ArrayList<Resposta> respostasPergunta(int idx) {
        ArrayList<Resposta> auxiliar = new ArrayList<>();
        for (Resposta resposta : respostas) {
            if (resposta.getPergutaID() == idx) {
                auxiliar.add(resposta);
            }
        }
        return auxiliar;
    }

    public void apagaRespostas(ArrayList<Resposta> aux) {
        ArrayList<Resposta> auxiliar = new ArrayList<>();
        Integer pergunta_id1 = aux.get(0).getPergutaID();
        for (Resposta resposta : respostas) {
            if (resposta.getPergutaID() != pergunta_id1) {
                auxiliar.add(resposta);
            }
        }
        respostas = auxiliar;
    }

    public void onEnviar(View view){
        if (tempoBDSeg != 0 || tempoBDMin!=0) {
            if (perguntas.size() == 1 || modo.equals("morte_subita") && qtd_erradas > 0) {

                Intent intent = new Intent(this, ResultadoActivity.class);
                intent.putExtra("questionarioID", "" + questionarioID);
                intent.putExtra("nomeUtilizador", nomeUtilizador);
                intent.putExtra("perguntas", perguntas);
                intent.putExtra("respostas", respostas);
                intent.putExtra("respostas_certas", "" + qtd_certas);
                intent.putExtra("respostas_erradas", "" + qtd_erradas);
                intent.putExtra("score", "" + score);
                intent.putExtra("modo", modo);
                intent.putExtra("timer", "" + tempoBDMin);
                intent.putExtra("timer1", "" + tempoBDSeg);
                intent.putExtra("lista_resultados_user", resultados);
                startActivity(intent);
            } else {
                perguntas.remove(perguntas.get(idPergunta));
                apagaRespostas(auxiliar);
                Intent intent = new Intent(this, PerguntaActivity.class);
                //intent.putExtra("modo", modo);
                intent.putExtra("questionarioID", "" + questionarioID);
                intent.putExtra("nomeUtilizador", nomeUtilizador);
                intent.putExtra("perguntas", perguntas);
                intent.putExtra("respostas", respostas);
                intent.putExtra("respostas_certas", "" + qtd_certas);
                intent.putExtra("respostas_erradas", "" + qtd_erradas);
                intent.putExtra("ajuda", ajuda = "teste");
                intent.putExtra("qtdperguntas", "" + qtdPerguntas);
                intent.putExtra("totalperguntas", "" + totalPerguntas);
                intent.putExtra("lista_resultados_user", resultados);
                if (modo.equals("contra_relogio")) {
                    int auxmin = (int) tempo / 1000 / 60;
                    int auxseg = (int) tempo % 60000 / 1000;
                    intent.putExtra("timer", "" + auxmin);
                    intent.putExtra("timer1", "" + auxseg);
                } else {
                    intent.putExtra("timer", "" + tempoBDMin);
                    intent.putExtra("timer1", "" + tempoBDSeg);
                }
                intent.putExtra("score", "" + score);
                intent.putExtra("modo", modo);
                startActivity(intent);
            }
        }else{
            if (perguntas.size() == 1) {
                Intent intent = new Intent(this, ResultadoActivity.class);
                intent.putExtra("questionarioID", "" + questionarioID);
                intent.putExtra("nomeUtilizador", nomeUtilizador);
                intent.putExtra("perguntas", perguntas);
                intent.putExtra("respostas", respostas);
                intent.putExtra("respostas_certas", "" + qtd_certas);
                intent.putExtra("respostas_erradas", "" + qtd_erradas);
                intent.putExtra("score", "" + score);
                intent.putExtra("modo", modo);
                intent.putExtra("timer", "" + tempoBDMin);
                intent.putExtra("timer1", "" + tempoBDSeg);
                intent.putExtra("lista_resultados_user", resultados);
                startActivity(intent);
            } else {
                perguntas.remove(perguntas.get(idPergunta));
                apagaRespostas(auxiliar);
                Intent intent = new Intent(this, PerguntaActivity.class);
                //intent.putExtra("modo", modo);
                intent.putExtra("questionarioID", "" + questionarioID);
                intent.putExtra("nomeUtilizador", nomeUtilizador);
                intent.putExtra("perguntas", perguntas);
                intent.putExtra("respostas", respostas);
                intent.putExtra("respostas_certas", "" + qtd_certas);
                intent.putExtra("respostas_erradas", "" + qtd_erradas);
                intent.putExtra("ajuda", ajuda = "teste");
                intent.putExtra("qtdperguntas", "" + qtdPerguntas);
                intent.putExtra("totalperguntas", "" + totalPerguntas);
                intent.putExtra("score", "" + score);
                intent.putExtra("modo", modo);
                intent.putExtra("timer", "" + tempoBDMin);
                intent.putExtra("timer1", "" + tempoBDSeg);
                intent.putExtra("lista_resultados_user", resultados);
                startActivity(intent);
            }
        }
    }
    public void onClickResposta(View view) {
        if (tempoBDSeg != 0 || tempoBDMin!=0) {
            timer.cancel();
            qtdPerguntas++;
            if (view.getTag().toString().compareTo("1") == 0) {
                sound = MediaPlayer.create(this, R.raw.correct_sound);
                view.setBackgroundResource(R.drawable.button_respostas_certa);
                sound.start();
                qtd_certas++;
                if (!modo.equals("questionario")) {
                    score += (tempo) * 0.01;
                }
            } else {
                view.setBackgroundResource(R.drawable.button_respostas_errada);
                viewCerta.setBackgroundResource(R.drawable.button_respostas_certa);
                sound = MediaPlayer.create(this, R.raw.wrong_sound);
                sound.start();
                qtd_erradas++;
                score += 0;
            }
            Delay.delay(segundos, new Delay.Teste() {
                @Override
                public void depoisDelay() {
                    onEnviar(view);
                }
            });
        } else {
            qtdPerguntas++;
            if (view.getTag().toString().compareTo("1") == 0) {
                sound = MediaPlayer.create(this, R.raw.correct_sound);
                view.setBackgroundResource(R.drawable.button_respostas_certa);
                sound.start();
                qtd_certas++;
            } else {
                sound = MediaPlayer.create(this, R.raw.wrong_sound);
                view.setBackgroundResource(R.drawable.button_respostas_errada);
                viewCerta.setBackgroundResource(R.drawable.button_respostas_certa);
                sound.start();
                qtd_erradas++;
            }
            Delay.delay(segundos, new Delay.Teste() {
                @Override
                public void depoisDelay() {
                    onEnviar(view);
                }
            });
        }

    }
    public void startTimer(Context context) {
        if (tempo != 0) {
            timer = new CountDownTimer(tempo, 1000) {
                @Override
                public void onTick(long l) {
                    tempo = l;
                    updateTimer();
                }

                @Override
                public void onFinish() {
                    timerText.setText("0");
                    timer.cancel();
                    qtdPerguntas++;
                    viewCerta.setBackgroundResource(R.drawable.button_respostas_certa);
                    sound = MediaPlayer.create(context, R.raw.wrong_sound);
                    sound.start();
                    qtd_erradas++;
                    score += 0;
                    if (perguntas.size() == 1 || modo.equals("morte_subita"))
                    {
                        Intent intent = new Intent(context, ResultadoActivity.class);
                        intent.putExtra("questionarioID", "" + questionarioID);
                        intent.putExtra("nomeUtilizador", nomeUtilizador);
                        intent.putExtra("perguntas", perguntas);
                        intent.putExtra("respostas", respostas);
                        intent.putExtra("respostas_certas", "" + qtd_certas);
                        intent.putExtra("respostas_erradas", "" + qtd_erradas);
                        intent.putExtra("score", "" + score);
                        intent.putExtra("modo", modo);
                        intent.putExtra("timer", "" + tempoBDMin);
                        intent.putExtra("timer1", "" + tempoBDSeg);
                        intent.putExtra("lista_resultados_user", resultados);
                        startActivity(intent);

                    } else {
                        if(modo.equals("contra_relogio")){
                            Intent intent = new Intent(context, ResultadoActivity.class);
                            intent.putExtra("questionarioID", "" + questionarioID);
                            intent.putExtra("nomeUtilizador", nomeUtilizador);
                            intent.putExtra("perguntas", perguntas);
                            intent.putExtra("respostas", respostas);
                            intent.putExtra("respostas_certas", "" + qtd_certas);
                            intent.putExtra("respostas_erradas", "" + qtd_erradas);
                            intent.putExtra("score", "" + score);
                            intent.putExtra("modo", modo);
                            intent.putExtra("timer", "" + tempoBDMin);
                            intent.putExtra("timer1", "" + tempoBDSeg);
                            intent.putExtra("lista_resultados_user", resultados);
                            startActivity(intent);
                        }
                        else {
                        perguntas.remove(perguntas.get(idPergunta));
                        apagaRespostas(auxiliar);
                        Intent intent = new Intent(context, PerguntaActivity.class);
                        //intent.putExtra("modo", modo);
                        intent.putExtra("questionarioID", "" + questionarioID);
                        intent.putExtra("nomeUtilizador", nomeUtilizador);
                        intent.putExtra("perguntas", perguntas);
                        intent.putExtra("respostas", respostas);
                        intent.putExtra("respostas_certas", "" + qtd_certas);
                        intent.putExtra("respostas_erradas", "" + qtd_erradas);
                        intent.putExtra("ajuda", ajuda = "teste");
                        intent.putExtra("qtdperguntas", "" + qtdPerguntas);
                        intent.putExtra("totalperguntas", "" + totalPerguntas);
                        intent.putExtra("timer", "" + tempoBDMin);
                        intent.putExtra("timer1", "" + tempoBDSeg);
                        intent.putExtra("score", "" + score);
                        intent.putExtra("modo", modo);
                        startActivity(intent);
                        }
                    }

                }
            }.start();
        }

    }

    public void updateTimer() {
        int segundos = (int) tempo % 60000 / 1000;
        int minutos = (int) tempo / 1000 / 60;
        String tempoRestante = "";
        if(segundos<10)
        {
            tempoRestante += minutos+":0"+segundos;
        }else if(minutos == 0)
        {
            tempoRestante += segundos;
        }
        else
        {
            tempoRestante += minutos+":"+segundos;
        }
        timerText.setText(tempoRestante);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("i", i);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        i = savedInstanceState.getInt("i");

    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
    }
}
