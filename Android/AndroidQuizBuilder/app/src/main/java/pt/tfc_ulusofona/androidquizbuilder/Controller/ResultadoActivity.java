package pt.tfc_ulusofona.androidquizbuilder.Controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pt.tfc_ulusofona.androidquizbuilder.Model.Pergunta;
import pt.tfc_ulusofona.androidquizbuilder.Model.Resposta;
import pt.tfc_ulusofona.androidquizbuilder.Model.Resultado;
import pt.tfc_ulusofona.androidquizbuilder.R;

public class ResultadoActivity extends AppCompatActivity {

    private final String URL_ADRESS_RESPOSTA = "http://" + Common.ip + "/TFC/AndroidQuizBuilder-master/php/www/teste.php?action=resultado";
    private Intent intent;
    private ArrayList<Pergunta> perguntas;
    private ArrayList<Resposta> respostas;
    private ArrayList<Resultado> resultados;
    private int perguntaID, questionarioID;
    private int i = 0, j = 0;
    private String nomeUtilizador;
    private int qtd_certas;
    private int qtd_erradas;
    private int max_score=0;
    private int score;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.responder_pergunta_layout);
        TextView tvWelcome = (TextView) findViewById(R.id.tv_welcome);
        intent = getIntent();
        questionarioID = Integer.parseInt(intent.getStringExtra("questionarioID"));
        /*perguntas=(ArrayList<Pergunta>)intent.getSerializableExtra("perguntas");
        respostas=(ArrayList<Resposta>)intent.getSerializableExtra("respostas");*/
        qtd_certas = Integer.parseInt(intent.getStringExtra("respostas_certas"));
        qtd_erradas = Integer.parseInt(intent.getStringExtra("respostas_erradas"));
        score = Integer.parseInt(intent.getStringExtra("score"));
        resultados = (ArrayList<Resultado>) intent.getSerializableExtra("lista_resultados_user");
        String modo =intent.getStringExtra("modo");

        nomeUtilizador = intent.getStringExtra("nomeUtilizador");

        for(Resultado resultado : resultados){
            if(resultado.getScore() > max_score) {
                max_score = resultado.getScore();
            }
        }
        if(score > max_score){
            android.app.AlertDialog.Builder builder = new AlertDialog.Builder(ResultadoActivity.this, R.style.AlertDialogStyle);
            builder.setMessage("Novo Recorde!");
            builder.setIcon(R.drawable.ic_add_alert_black_24dp);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            final AlertDialog dialog = builder.create();
            dialog.show();
            Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            LinearLayout positiveButtonLL = (LinearLayout) positiveButton.getParent();
            positiveButtonLL.setGravity(Gravity.CENTER_HORIZONTAL);
            View space = positiveButtonLL.getChildAt(1);
            space.setVisibility(View.GONE);
        }

        ImageView image = (ImageView) findViewById(R.id.img1);
        if(qtd_certas > qtd_erradas){
            image.setImageResource(R.drawable.ic_happy_face);
        }else if(qtd_erradas > qtd_certas){
            image.setImageResource(R.drawable.ic_sad_face);
        }else{
            image.setImageResource(R.drawable.ic_neutral_face);
        }
        TextView certas = (TextView) findViewById(R.id.txt_qtd_certas);
        TextView erradas = (TextView) findViewById(R.id.txt_qtd_erradas);
        TextView txtscore = (TextView) findViewById(R.id.txt_score);
        certas.setText(String.valueOf("Respostas Corretas : " + this.qtd_certas));
        erradas.setText(String.valueOf("Respostas Incorretas : " + qtd_erradas));
        if(!modo.equals("questionario"))
        {
            txtscore.setText(String.valueOf("Score Total : " + score));

        }

        ImageView img = (ImageView) findViewById(R.id.image_view);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(ResultadoActivity.this, R.style.AlertDialogStyle);
                builder.setMessage("Quer mesmo sair ? O seu progresso não será guardado.");
                builder.setIcon(R.drawable.ic_add_alert_black_24dp);
                builder.setNegativeButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(ResultadoActivity.this, WelcomeActivity.class);
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

        Button botao = (Button) findViewById(R.id.botao_enviar);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue = Volley.newRequestQueue(ResultadoActivity.this);
                StringRequest request = new StringRequest(Request.Method.POST, URL_ADRESS_RESPOSTA,
                        new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("HttpClient", "success! response: " + response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("log_tag","error" + error.toString());
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams()  {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("action", "qtd_certas");
                        parameters.put("certas", "" + qtd_certas);
                        parameters.put("erradas", "" + qtd_erradas);
                        parameters.put("score","" + score);
                        parameters.put("questionarioID", "" + questionarioID);
                        parameters.put("nomeUtilizador", nomeUtilizador);
                        parameters.put("modo",modo);
                        return parameters;
                    }
                };
                requestQueue.add(request);
                Intent intent = new Intent(ResultadoActivity.this, WelcomeActivity.class);
                intent.putExtra("username", nomeUtilizador);
                startActivity(intent);
            }
        });
    }
}
