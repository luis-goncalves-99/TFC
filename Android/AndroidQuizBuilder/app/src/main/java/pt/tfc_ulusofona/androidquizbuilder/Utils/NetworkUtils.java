package pt.tfc_ulusofona.androidquizbuilder.Utils;

import android.support.v4.app.Fragment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import pt.tfc_ulusofona.androidquizbuilder.Controller.ClassicoFragment;
import pt.tfc_ulusofona.androidquizbuilder.Controller.ContraRelogioFragment;
import pt.tfc_ulusofona.androidquizbuilder.Controller.JogoActivity;
import pt.tfc_ulusofona.androidquizbuilder.Controller.MorteSubitaFragment;
import pt.tfc_ulusofona.androidquizbuilder.Controller.PerfilActivity;
import pt.tfc_ulusofona.androidquizbuilder.Model.Pergunta;
import pt.tfc_ulusofona.androidquizbuilder.Model.Questionario;
import pt.tfc_ulusofona.androidquizbuilder.Controller.QuestionarioActivity;
import pt.tfc_ulusofona.androidquizbuilder.Model.Resposta;
import pt.tfc_ulusofona.androidquizbuilder.Model.Resultado;
import pt.tfc_ulusofona.androidquizbuilder.Model.Utilizador;

public class NetworkUtils {
    private static final int RESULT_OK = 200;

    public static ArrayList<Questionario> fetchQuestionario(String urlAdressQuestionarios) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        ArrayList<Questionario> questionarios = new ArrayList<>();
        try {
            URL url = new URL(urlAdressQuestionarios);
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.connect();

                if (connection.getResponseCode() == RESULT_OK) {

                    inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    String line = "";
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    Log.d("log_tag", "testeQuestionario");
                    Log.d("log_tag", sb.toString());
                    JSONObject objectBase = new JSONObject(sb.toString());

                    JSONArray jArray = objectBase.getJSONArray("questionarios");
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        if (json_data.getString("Acesso").equals("publico")) {
                            questionarios.add(new Questionario(json_data.getInt("QuestionarioID"), json_data.getString("Modo"), json_data.getString("Titulo"), json_data.getString("Descricao"), json_data.getString("DataDeCriacao"), json_data.getString("Acesso"), json_data.getString("Dificuldade"), json_data.getInt("TimerMinutos"), json_data.getInt("TimerSegundos")));
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());

            }
        } catch (Exception e) {

        }
        return questionarios;
    }

    public static ArrayList<Questionario> fetchData(String urlAdressQuestionarios, String urlAdressPerguntas, String urlAdressRespostas, String urlAdressResultados, String urlAdressUtilizadores, String modo) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        ArrayList<Questionario> questionarios = new ArrayList<>();
        QuestionarioActivity.perguntas = new ArrayList<>();
        QuestionarioActivity.respostas = new ArrayList<>();
        QuestionarioActivity.resultados = new ArrayList<>();
        QuestionarioActivity.utilizadores = new ArrayList<>();
        try {
            URL url = new URL(urlAdressQuestionarios);
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.connect();

                if (connection.getResponseCode() == RESULT_OK) {

                    inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    String line = "";
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    Log.d("log_tag", "testeQuestionario");
                    Log.d("log_tag", sb.toString());
                    JSONObject objectBase = new JSONObject(sb.toString());

                    JSONArray jArray = objectBase.getJSONArray("questionarios");
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        if (json_data.getString("Acesso").equals("publico") && json_data.getString("Modo").equals(modo)) {
                            questionarios.add(new Questionario(json_data.getInt("QuestionarioID"), json_data.getString("Modo"), json_data.getString("Titulo"), json_data.getString("Descricao"), json_data.getString("DataDeCriacao"), json_data.getString("Acesso"), json_data.getString("Dificuldade"), json_data.getInt("TimerMinutos"), json_data.getInt("TimerSegundos")));
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());

            }
        } catch (Exception e) {

        }

        try {
            URL url = new URL(urlAdressPerguntas);
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.connect();

                if (connection.getResponseCode() == RESULT_OK) {

                    inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    String line = "";
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    Log.d("log_tag", sb.toString());
                    JSONObject objectBase = new JSONObject(sb.toString());

                    JSONArray jArray = objectBase.getJSONArray("perguntas");

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        QuestionarioActivity.perguntas.add(new Pergunta(json_data.getInt("PerguntaID"), json_data.getString("Texto"), json_data.getInt("Resposta"), json_data.getInt("QuestionarioID"), 0));
                    }

                }
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }

        try {
            URL url = new URL(urlAdressRespostas);
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.connect();

                if (connection.getResponseCode() == RESULT_OK) {

                    inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    String line = "";
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    Log.d("log_tag", sb.toString());
                    JSONObject objectBase = new JSONObject(sb.toString());

                    JSONArray jArray = objectBase.getJSONArray("respostas");

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        QuestionarioActivity.respostas.add(new Resposta(json_data.getInt("RespostaID"), json_data.getString("Texto"), json_data.getInt("Correta"), json_data.getInt("PerguntaID")));
                    }
                }
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }

        try {
            URL url = new URL(urlAdressResultados);
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.connect();

                if (connection.getResponseCode() == RESULT_OK) {

                    inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    String line = "";
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    Log.d("log_tag", sb.toString());
                    JSONObject objectBase = new JSONObject(sb.toString());

                    JSONArray jArray = objectBase.getJSONArray("resultados");
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        QuestionarioActivity.resultados.add(new Resultado(json_data.getInt("ResultadoID"), json_data.getInt("UtilizadorID"), json_data.getInt("QuestionarioID"), json_data.getString("DataDePreenchimento"), json_data.getInt("RespostasCertas"), json_data.getInt("RespostasErradas"), json_data.getInt("Score"), json_data.getString("Modo")));
                        Log.d("log_tag", "ResultadoID:" + json_data.getInt("ResultadoID") + " UtilizadorID:" + json_data.getInt("UtilizadorID") + " QuestionarioID:" + json_data.getInt("QuestionarioID"));
                    }

                }
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }

        try {
            URL url = new URL(urlAdressUtilizadores);
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.connect();

                if (connection.getResponseCode() == RESULT_OK) {

                    inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    String line = "";
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    Log.d("log_tag", sb.toString());
                    JSONObject objectBase = new JSONObject(sb.toString());

                    JSONArray jArray = objectBase.getJSONArray("utilizadores");
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        QuestionarioActivity.utilizadores.add(new Utilizador(json_data.getInt("id"), json_data.getString("nome")));
                        Log.d("log_tag", "ID:" + json_data.getInt("id") + " Nome:" + json_data.getString("nome"));
                    }

                }
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }

        return questionarios;
    }

    public static ArrayList<Questionario> fetchDataJogo(String urlAdressQuestionarios, String urlAdressPerguntas, String urlAdressRespostas, String urlAdressResultados, String urlAdressUtilizadores, String modo) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        ArrayList<Questionario> questionarios = new ArrayList<>();
        JogoActivity.resultados = new ArrayList<>();
        JogoActivity.perguntas = new ArrayList<>();
        JogoActivity.respostas = new ArrayList<>();
        JogoActivity.utilizadores = new ArrayList<>();
        try {
            URL url = new URL(urlAdressQuestionarios);
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.connect();

                if (connection.getResponseCode() == RESULT_OK) {

                    inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    String line = "";
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    Log.d("log_tag", "testeQuestionario");
                    Log.d("log_tag", sb.toString());
                    JSONObject objectBase = new JSONObject(sb.toString());

                    JSONArray jArray = objectBase.getJSONArray("questionarios");
                    if (questionarios.isEmpty() == true) {
                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject json_data = jArray.getJSONObject(i);
                            if (json_data.getString("Acesso").equals("publico") && json_data.getString("Modo").equals(modo)) {
                                questionarios.add(new Questionario(json_data.getInt("QuestionarioID"), json_data.getString("Modo"), json_data.getString("Titulo"), json_data.getString("Descricao"), json_data.getString("DataDeCriacao"), json_data.getString("Acesso"), json_data.getString("Dificuldade"), json_data.getInt("TimerMinutos"), json_data.getInt("TimerSegundos")));
                            } else if (json_data.getString("Acesso").equals("publico") && modo.equals("todos") && !json_data.getString("Modo").equals("questionario")) {
                                questionarios.add(new Questionario(json_data.getInt("QuestionarioID"), json_data.getString("Modo"), json_data.getString("Titulo"), json_data.getString("Descricao"), json_data.getString("DataDeCriacao"), json_data.getString("Acesso"), json_data.getString("Dificuldade"), json_data.getInt("TimerMinutos"), json_data.getInt("TimerSegundos")));
                            }
                        }
                    }
                }
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }

        try {
            URL url = new URL(urlAdressPerguntas);
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.connect();

                if (connection.getResponseCode() == RESULT_OK) {

                    inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    String line = "";
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    Log.d("log_tag", sb.toString());
                    JSONObject objectBase = new JSONObject(sb.toString());

                    JSONArray jArray = objectBase.getJSONArray("perguntas");
                    if (JogoActivity.perguntas.isEmpty() == true) {
                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject json_data = jArray.getJSONObject(i);
                            JogoActivity.perguntas.add(new Pergunta(json_data.getInt("PerguntaID"), json_data.getString("Texto"), json_data.getInt("Resposta"), json_data.getInt("QuestionarioID"), 0));
                        }
                    }
                }
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }

        try {
            URL url = new URL(urlAdressRespostas);
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.connect();

                if (connection.getResponseCode() == RESULT_OK) {

                    inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    String line = "";
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    Log.d("log_tag", sb.toString());
                    JSONObject objectBase = new JSONObject(sb.toString());

                    JSONArray jArray = objectBase.getJSONArray("respostas");
                    if (JogoActivity.respostas.isEmpty() == true) {
                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject json_data = jArray.getJSONObject(i);
                            JogoActivity.respostas.add(new Resposta(json_data.getInt("RespostaID"), json_data.getString("Texto"), json_data.getInt("Correta"), json_data.getInt("PerguntaID")));
                        }
                    }
                }
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }

        try {
            URL url = new URL(urlAdressResultados);
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.connect();

                if (connection.getResponseCode() == RESULT_OK) {

                    inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    String line = "";
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    Log.d("log_tag", sb.toString());
                    JSONObject objectBase = new JSONObject(sb.toString());

                    JSONArray jArray = objectBase.getJSONArray("resultados");
                    if (JogoActivity.resultados.isEmpty() == true) {
                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject json_data = jArray.getJSONObject(i);
                            JogoActivity.resultados.add(new Resultado(json_data.getInt("ResultadoID"), json_data.getInt("UtilizadorID"), json_data.getInt("QuestionarioID"), json_data.getString("DataDePreenchimento"), json_data.getInt("RespostasCertas"), json_data.getInt("RespostasErradas"), json_data.getInt("Score"), json_data.getString("Modo")));
                            Log.d("log_tag", "ResultadoID:" + json_data.getInt("ResultadoID") + " UtilizadorID:" + json_data.getInt("UtilizadorID") + " QuestionarioID:" + json_data.getInt("QuestionarioID"));
                        }
                    }
                }
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }

        try {
            URL url = new URL(urlAdressUtilizadores);
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.connect();

                if (connection.getResponseCode() == RESULT_OK) {

                    inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    String line = "";
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    Log.d("log_tag", sb.toString());
                    JSONObject objectBase = new JSONObject(sb.toString());

                    JSONArray jArray = objectBase.getJSONArray("utilizadores");
                    if (JogoActivity.utilizadores.isEmpty() == true) {
                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject json_data = jArray.getJSONObject(i);
                            JogoActivity.utilizadores.add(new Utilizador(json_data.getInt("id"), json_data.getString("nome")));
                            Log.d("log_tag", "ID:" + json_data.getInt("id") + " Nome:" + json_data.getString("nome"));
                        }
                    }
                }
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }

        return questionarios;
    }

    public static ArrayList<Questionario> fetchDataPerfil(String urlAdressQuestionarios, String urlAdressResultados, String urlAdressUtilizadores, String user) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        ArrayList<Questionario> questionarios = new ArrayList<>();
        PerfilActivity.resultados = new ArrayList<>();

        int userid = 0;
        try {
            URL url = new URL(urlAdressUtilizadores);
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.connect();

                if (connection.getResponseCode() == RESULT_OK) {

                    inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    String line = "";
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    Log.d("log_tag", sb.toString());
                    JSONObject objectBase = new JSONObject(sb.toString());

                    JSONArray jArray = objectBase.getJSONArray("utilizadores");
                    {
                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject json_data = jArray.getJSONObject(i);
                            if (json_data.getString("nome").equals(user)) {
                                Utilizador userajuda = new Utilizador(json_data.getInt("id"), json_data.getString("nome"));
                                PerfilActivity.user = userajuda;
                                userid = json_data.getInt("id");
                                Log.d("log_tag", "ID:" + json_data.getInt("id") + " Nome:" + json_data.getString("nome"));
                            }
                        }
                    }
                }
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }
        try {
            URL url = new URL(urlAdressQuestionarios);
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.connect();

                if (connection.getResponseCode() == RESULT_OK) {

                    inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    String line = "";
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    Log.d("log_tag", "testeQuestionario");
                    Log.d("log_tag", sb.toString());
                    JSONObject objectBase = new JSONObject(sb.toString());

                    JSONArray jArray = objectBase.getJSONArray("questionarios");
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        if (json_data.getString("Acesso").equals("publico")) {
                            questionarios.add(new Questionario(json_data.getInt("QuestionarioID"), json_data.getString("Modo"), json_data.getString("Titulo"), json_data.getString("Descricao"), json_data.getString("DataDeCriacao"), json_data.getString("Acesso"), json_data.getString("Dificuldade"), json_data.getInt("TimerMinutos"), json_data.getInt("TimerSegundos")));
                        }
                    }

                }
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }

        try {
            URL url = new URL(urlAdressResultados);
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.connect();

                if (connection.getResponseCode() == RESULT_OK) {

                    inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    String line = "";
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    Log.d("log_tag", sb.toString());
                    JSONObject objectBase = new JSONObject(sb.toString());

                    JSONArray jArray = objectBase.getJSONArray("resultados");

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        if (json_data.getInt("UtilizadorID") == userid) {
                            PerfilActivity.resultados.add(new Resultado(json_data.getInt("ResultadoID"), json_data.getInt("UtilizadorID"), json_data.getInt("QuestionarioID"), json_data.getString("DataDePreenchimento"), json_data.getInt("RespostasCertas"), json_data.getInt("RespostasErradas"), json_data.getInt("Score"), json_data.getString("Modo")));
                            Log.d("log_tag", "ResultadoID:" + json_data.getInt("ResultadoID") + " UtilizadorID:" + json_data.getInt("UtilizadorID") + " QuestionarioID:" + json_data.getInt("QuestionarioID"));
                        }
                    }

                }
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }

        return questionarios;
    }

    public static ArrayList<Questionario> fetchDataRankingClassico(String urlAdressQuestionarios, String urlAdressRankigs, String urlAdressUtilizadores, String modo) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        ArrayList<Questionario> questionarios = new ArrayList<>();
        ClassicoFragment.resultados = new ArrayList<>();
        ClassicoFragment.utilizadores = new ArrayList<>();

        try {
            URL url = new URL(urlAdressQuestionarios);
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.connect();

                if (connection.getResponseCode() == RESULT_OK) {

                    inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    String line = "";
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    Log.d("log_tag", "testeQuestionario");
                    Log.d("log_tag", sb.toString());
                    JSONObject objectBase = new JSONObject(sb.toString());

                    JSONArray jArray = objectBase.getJSONArray("questionarios");
                    if (questionarios.isEmpty() == true) {
                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject json_data = jArray.getJSONObject(i);
                            if (json_data.getString("Acesso").equals("publico") && json_data.getString("Modo").equals(modo)) {
                                questionarios.add(new Questionario(json_data.getInt("QuestionarioID"), json_data.getString("Modo"), json_data.getString("Titulo"), json_data.getString("Descricao"), json_data.getString("DataDeCriacao"), json_data.getString("Acesso"), json_data.getString("Dificuldade"), json_data.getInt("TimerMinutos"), json_data.getInt("TimerSegundos")));
                            } else if (json_data.getString("Acesso").equals("publico") && modo.equals("todos") && !json_data.getString("Modo").equals("questionario")) {
                                questionarios.add(new Questionario(json_data.getInt("QuestionarioID"), json_data.getString("Modo"), json_data.getString("Titulo"), json_data.getString("Descricao"), json_data.getString("DataDeCriacao"), json_data.getString("Acesso"), json_data.getString("Dificuldade"), json_data.getInt("TimerMinutos"), json_data.getInt("TimerSegundos")));
                            }
                        }
                    }
                }
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }
        try {
            URL url = new URL(urlAdressRankigs);
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.connect();

                if (connection.getResponseCode() == RESULT_OK) {

                    inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    String line = "";
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    Log.d("log_tag", sb.toString());
                    JSONObject objectBase = new JSONObject(sb.toString());

                    JSONArray jArray = objectBase.getJSONArray("rankings");

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        {
                            if (json_data.getString("Modo").equals(modo)) {
                                ArrayList<Resultado> aux = new ArrayList<>();
                                aux.addAll(ClassicoFragment.resultados);
                                if (ClassicoFragment.resultados.size() == 0) {
                                    ClassicoFragment.resultados.add(new Resultado(json_data.getInt("ResultadoID"), json_data.getInt("UtilizadorID"), json_data.getInt("QuestionarioID"), json_data.getString("DataDePreenchimento"), json_data.getInt("RespostasCertas"), json_data.getInt("RespostasErradas"), json_data.getInt("Score"), json_data.getString("Modo")));
                                    Log.d("log_tag", "ResultadoID:" + json_data.getInt("ResultadoID") + " UtilizadorID:" + json_data.getInt("UtilizadorID") + " QuestionarioID:" + json_data.getInt("QuestionarioID"));
                                } else if (ClassicoFragment.resultados.size() < 10) {
                                    boolean inserir = true;
                                    for (Resultado resultado : aux) {
                                        if (resultado.getUtilizadorID() == json_data.getInt("UtilizadorID")) {
                                            inserir = false;
                                        }
                                    }
                                    if (inserir) {
                                        ClassicoFragment.resultados.add(new Resultado(json_data.getInt("ResultadoID"), json_data.getInt("UtilizadorID"), json_data.getInt("QuestionarioID"), json_data.getString("DataDePreenchimento"), json_data.getInt("RespostasCertas"), json_data.getInt("RespostasErradas"), json_data.getInt("Score"), json_data.getString("Modo")));
                                        Log.d("log_tag", "ResultadoID:" + json_data.getInt("ResultadoID") + " UtilizadorID:" + json_data.getInt("UtilizadorID") + " QuestionarioID:" + json_data.getInt("QuestionarioID"));
                                    }
                                } else if ((aux.get(aux.size() - 1).getUtilizadorID() != json_data.getInt("UtilizadorID") && aux.get(aux.size() - 1).getScore() == json_data.getInt("Score"))) {
                                    ClassicoFragment.resultados.add(new Resultado(json_data.getInt("ResultadoID"), json_data.getInt("UtilizadorID"), json_data.getInt("QuestionarioID"), json_data.getString("DataDePreenchimento"), json_data.getInt("RespostasCertas"), json_data.getInt("RespostasErradas"), json_data.getInt("Score"), json_data.getString("Modo")));
                                    Log.d("log_tag", "ResultadoID:" + json_data.getInt("ResultadoID") + " UtilizadorID:" + json_data.getInt("UtilizadorID") + " QuestionarioID:" + json_data.getInt("QuestionarioID"));
                                }
                            }
                        }
                    }

                }
            } catch (Exception e) {
                Log.d("erro", e.getMessage());

            }
        } catch (Exception e) {

        }

        try {
            URL url = new URL(urlAdressUtilizadores);
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.connect();

                if (connection.getResponseCode() == RESULT_OK) {

                    inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    String line = "";
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    Log.d("log_tag", sb.toString());
                    JSONObject objectBase = new JSONObject(sb.toString());

                    JSONArray jArray = objectBase.getJSONArray("utilizadores");

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        ClassicoFragment.utilizadores.add(new Utilizador(json_data.getInt("id"), json_data.getString("nome")));
                        Log.d("log_tag", "ID:" + json_data.getInt("id") + " Nome:" + json_data.getString("nome"));
                    }

                }
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }

        return questionarios;
    }

    public static ArrayList<Questionario> fetchDataRankingMorteSubita(String urlAdressQuestionarios, String urlAdressRankigs, String urlAdressUtilizadores, String modo) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        ArrayList<Questionario> questionarios = new ArrayList<>();
        MorteSubitaFragment.resultados = new ArrayList<>();
        MorteSubitaFragment.utilizadores = new ArrayList<>();

        try {
            URL url = new URL(urlAdressQuestionarios);
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.connect();

                if (connection.getResponseCode() == RESULT_OK) {

                    inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    String line = "";
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    Log.d("log_tag", "testeQuestionario");
                    Log.d("log_tag", sb.toString());
                    JSONObject objectBase = new JSONObject(sb.toString());

                    JSONArray jArray = objectBase.getJSONArray("questionarios");
                    if (questionarios.isEmpty() == true) {
                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject json_data = jArray.getJSONObject(i);
                            if (json_data.getString("Acesso").equals("publico") && json_data.getString("Modo").equals(modo)) {
                                questionarios.add(new Questionario(json_data.getInt("QuestionarioID"), json_data.getString("Modo"), json_data.getString("Titulo"), json_data.getString("Descricao"), json_data.getString("DataDeCriacao"), json_data.getString("Acesso"), json_data.getString("Dificuldade"), json_data.getInt("TimerMinutos"), json_data.getInt("TimerSegundos")));
                            } else if (json_data.getString("Acesso").equals("publico") && modo.equals("todos") && !json_data.getString("Modo").equals("questionario")) {
                                questionarios.add(new Questionario(json_data.getInt("QuestionarioID"), json_data.getString("Modo"), json_data.getString("Titulo"), json_data.getString("Descricao"), json_data.getString("DataDeCriacao"), json_data.getString("Acesso"), json_data.getString("Dificuldade"), json_data.getInt("TimerMinutos"), json_data.getInt("TimerSegundos")));
                            }
                        }
                    }
                }
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }
        try {
            URL url = new URL(urlAdressRankigs);
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.connect();

                if (connection.getResponseCode() == RESULT_OK) {

                    inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    String line = "";
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    Log.d("log_tag", sb.toString());
                    JSONObject objectBase = new JSONObject(sb.toString());

                    JSONArray jArray = objectBase.getJSONArray("rankings");

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        {
                            if (json_data.getString("Modo").equals(modo)) {
                                ArrayList<Resultado> aux = new ArrayList<>();
                                aux.addAll(MorteSubitaFragment.resultados);
                                if (MorteSubitaFragment.resultados.size() == 0) {
                                    MorteSubitaFragment.resultados.add(new Resultado(json_data.getInt("ResultadoID"), json_data.getInt("UtilizadorID"), json_data.getInt("QuestionarioID"), json_data.getString("DataDePreenchimento"), json_data.getInt("RespostasCertas"), json_data.getInt("RespostasErradas"), json_data.getInt("Score"), json_data.getString("Modo")));
                                    Log.d("log_tag", "ResultadoID:" + json_data.getInt("ResultadoID") + " UtilizadorID:" + json_data.getInt("UtilizadorID") + " QuestionarioID:" + json_data.getInt("QuestionarioID"));
                                } else if (MorteSubitaFragment.resultados.size() < 10) {
                                    boolean inserir = true;
                                    for (Resultado resultado : aux) {
                                        if (resultado.getUtilizadorID() == json_data.getInt("UtilizadorID")) {
                                            inserir = false;
                                        }
                                    }
                                    if (inserir) {
                                        MorteSubitaFragment.resultados.add(new Resultado(json_data.getInt("ResultadoID"), json_data.getInt("UtilizadorID"), json_data.getInt("QuestionarioID"), json_data.getString("DataDePreenchimento"), json_data.getInt("RespostasCertas"), json_data.getInt("RespostasErradas"), json_data.getInt("Score"), json_data.getString("Modo")));
                                        Log.d("log_tag", "ResultadoID:" + json_data.getInt("ResultadoID") + " UtilizadorID:" + json_data.getInt("UtilizadorID") + " QuestionarioID:" + json_data.getInt("QuestionarioID"));
                                    }
                                } else if ((aux.get(aux.size() - 1).getUtilizadorID() != json_data.getInt("UtilizadorID") && aux.get(aux.size() - 1).getScore() == json_data.getInt("Score"))) {
                                    MorteSubitaFragment.resultados.add(new Resultado(json_data.getInt("ResultadoID"), json_data.getInt("UtilizadorID"), json_data.getInt("QuestionarioID"), json_data.getString("DataDePreenchimento"), json_data.getInt("RespostasCertas"), json_data.getInt("RespostasErradas"), json_data.getInt("Score"), json_data.getString("Modo")));
                                    Log.d("log_tag", "ResultadoID:" + json_data.getInt("ResultadoID") + " UtilizadorID:" + json_data.getInt("UtilizadorID") + " QuestionarioID:" + json_data.getInt("QuestionarioID"));
                                }
                            }
                        }
                    }

                }
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }

        try {
            URL url = new URL(urlAdressUtilizadores);
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.connect();

                if (connection.getResponseCode() == RESULT_OK) {

                    inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    String line = "";
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    Log.d("log_tag", sb.toString());
                    JSONObject objectBase = new JSONObject(sb.toString());

                    JSONArray jArray = objectBase.getJSONArray("utilizadores");

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        MorteSubitaFragment.utilizadores.add(new Utilizador(json_data.getInt("id"), json_data.getString("nome")));
                        Log.d("log_tag", "ID:" + json_data.getInt("id") + " Nome:" + json_data.getString("nome"));
                    }

                }
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }

        return questionarios;
    }

    public static ArrayList<Questionario> fetchDataRankingContraRelogio(String urlAdressQuestionarios, String urlAdressRankigs, String urlAdressUtilizadores, String modo) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        ArrayList<Questionario> questionarios = new ArrayList<>();
        ContraRelogioFragment.resultados = new ArrayList<>();
        ContraRelogioFragment.utilizadores = new ArrayList<>();

        try {
            URL url = new URL(urlAdressQuestionarios);
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.connect();

                if (connection.getResponseCode() == RESULT_OK) {

                    inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    String line = "";
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    Log.d("log_tag", "testeQuestionario");
                    Log.d("log_tag", sb.toString());
                    JSONObject objectBase = new JSONObject(sb.toString());

                    JSONArray jArray = objectBase.getJSONArray("questionarios");
                    if (questionarios.isEmpty() == true) {
                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject json_data = jArray.getJSONObject(i);
                            if (json_data.getString("Acesso").equals("publico") && json_data.getString("Modo").equals(modo)) {
                                questionarios.add(new Questionario(json_data.getInt("QuestionarioID"), json_data.getString("Modo"), json_data.getString("Titulo"), json_data.getString("Descricao"), json_data.getString("DataDeCriacao"), json_data.getString("Acesso"), json_data.getString("Dificuldade"), json_data.getInt("TimerMinutos"), json_data.getInt("TimerSegundos")));
                            } else if (json_data.getString("Acesso").equals("publico") && modo.equals("todos") && !json_data.getString("Modo").equals("questionario")) {
                                questionarios.add(new Questionario(json_data.getInt("QuestionarioID"), json_data.getString("Modo"), json_data.getString("Titulo"), json_data.getString("Descricao"), json_data.getString("DataDeCriacao"), json_data.getString("Acesso"), json_data.getString("Dificuldade"), json_data.getInt("TimerMinutos"), json_data.getInt("TimerSegundos")));
                            }
                        }
                    }
                }
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }
        try {
            URL url = new URL(urlAdressRankigs);
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.connect();

                if (connection.getResponseCode() == RESULT_OK) {

                    inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    String line = "";
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    Log.d("log_tag", sb.toString());
                    JSONObject objectBase = new JSONObject(sb.toString());

                    JSONArray jArray = objectBase.getJSONArray("rankigs");

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        {
                            if (json_data.getString("Modo").equals(modo)) {
                                ArrayList<Resultado> aux = new ArrayList<>();
                                aux.addAll(ContraRelogioFragment.resultados);
                                if (ContraRelogioFragment.resultados.size() == 0) {
                                    ContraRelogioFragment.resultados.add(new Resultado(json_data.getInt("ResultadoID"), json_data.getInt("UtilizadorID"), json_data.getInt("QuestionarioID"), json_data.getString("DataDePreenchimento"), json_data.getInt("RespostasCertas"), json_data.getInt("RespostasErradas"), json_data.getInt("Score"), json_data.getString("Modo")));
                                    Log.d("log_tag", "ResultadoID:" + json_data.getInt("ResultadoID") + " UtilizadorID:" + json_data.getInt("UtilizadorID") + " QuestionarioID:" + json_data.getInt("QuestionarioID"));
                                } else if (ContraRelogioFragment.resultados.size() < 10) {
                                    boolean inserir = true;
                                    for (Resultado resultado : aux) {
                                        if (resultado.getUtilizadorID() == json_data.getInt("UtilizadorID")) {
                                            inserir = false;
                                        }
                                    }
                                    if (inserir) {
                                        ContraRelogioFragment.resultados.add(new Resultado(json_data.getInt("ResultadoID"), json_data.getInt("UtilizadorID"), json_data.getInt("QuestionarioID"), json_data.getString("DataDePreenchimento"), json_data.getInt("RespostasCertas"), json_data.getInt("RespostasErradas"), json_data.getInt("Score"), json_data.getString("Modo")));
                                        Log.d("log_tag", "ResultadoID:" + json_data.getInt("ResultadoID") + " UtilizadorID:" + json_data.getInt("UtilizadorID") + " QuestionarioID:" + json_data.getInt("QuestionarioID"));
                                    }
                                } else if ((aux.get(aux.size() - 1).getUtilizadorID() != json_data.getInt("UtilizadorID") && aux.get(aux.size() - 1).getScore() == json_data.getInt("Score"))) {
                                    ContraRelogioFragment.resultados.add(new Resultado(json_data.getInt("ResultadoID"), json_data.getInt("UtilizadorID"), json_data.getInt("QuestionarioID"), json_data.getString("DataDePreenchimento"), json_data.getInt("RespostasCertas"), json_data.getInt("RespostasErradas"), json_data.getInt("Score"), json_data.getString("Modo")));
                                    Log.d("log_tag", "ResultadoID:" + json_data.getInt("ResultadoID") + " UtilizadorID:" + json_data.getInt("UtilizadorID") + " QuestionarioID:" + json_data.getInt("QuestionarioID"));
                                }
                            }
                        }
                    }

                }
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }

        try {
            URL url = new URL(urlAdressUtilizadores);
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.connect();

                if (connection.getResponseCode() == RESULT_OK) {

                    inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    String line = "";
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    Log.d("log_tag", sb.toString());
                    JSONObject objectBase = new JSONObject(sb.toString());

                    JSONArray jArray = objectBase.getJSONArray("utilizadores");

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        ContraRelogioFragment.utilizadores.add(new Utilizador(json_data.getInt("id"), json_data.getString("nome")));
                        Log.d("log_tag", "ID:" + json_data.getInt("id") + " Nome:" + json_data.getString("nome"));
                    }

                }
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }

        return questionarios;
    }


}
