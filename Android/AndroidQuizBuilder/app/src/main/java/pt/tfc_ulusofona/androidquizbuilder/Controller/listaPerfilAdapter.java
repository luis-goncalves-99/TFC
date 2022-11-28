package pt.tfc_ulusofona.androidquizbuilder.Controller;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import pt.tfc_ulusofona.androidquizbuilder.Model.Questionario;
import pt.tfc_ulusofona.androidquizbuilder.Model.Resultado;
import pt.tfc_ulusofona.androidquizbuilder.R;

public class listaPerfilAdapter extends BaseAdapter
{
    public ArrayList<Resultado> resultadosEstatísticas;
    public ArrayList<Questionario> listaQuestionarios;
    public ArrayList<Resultado> listaResultados;
    int id;
    int qtd;
    Activity activity;
    Context context;

    public listaPerfilAdapter(Activity activity, ArrayList<Questionario> listaQuestionarios, ArrayList<Resultado> listaResultados,int id,int qtd) {
        super();
        this.activity = activity;
        this.listaQuestionarios = listaQuestionarios;
        this.listaResultados = listaResultados;
        this.id = id;
        this.qtd = qtd;
        this.resultadosEstatísticas = new ArrayList<Resultado>();
    }

    @Override
    public int getCount() {
        return qtd+1;
    }

    @Override
    public Object getItem(int i) {
        return listaResultados.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public Resultado lista_resutlados_modo(String modo){
        ArrayList<Resultado> res = new ArrayList<>();
        for(Resultado resultado : listaResultados){
            if(resultado.getModo().equals(modo) && resultado.getUtilizadorID()==id){
                res.add(resultado);
            }
        }
        Collections.sort(res,Resultado.Score);
        if(res.size()>0){
            return res.get(res.size()-1);
        }
        else
        {
            return null;}
    }

    private class ViewHolder {
        TextView modo;
        TextView score;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.lista_row2, null);
            holder = new ViewHolder();
            holder.modo = (TextView) convertView.findViewById(R.id.modo_perfil);
            holder.score = (TextView) convertView.findViewById(R.id.score_perfil);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(position==0){
            holder.modo.setText("Modo");
            holder.score.setText("Score");
            return convertView;
        }
        resultadosEstatísticas = new ArrayList<Resultado>();
        Resultado lista_classico = lista_resutlados_modo("classico");
        Resultado lista_morte = lista_resutlados_modo("morte_subita");
        Resultado lista_relogio = lista_resutlados_modo("contra_relogio");
        if(lista_classico != null){
            resultadosEstatísticas.add(lista_classico);
        }
        if(lista_morte != null){
            resultadosEstatísticas.add(lista_morte);
        }
        if(lista_relogio != null){
            resultadosEstatísticas.add(lista_relogio);
        }
        if(resultadosEstatísticas.get(position-1)!=null){
            Resultado resultado = resultadosEstatísticas.get(position-1);
            String modo="";
            String resajuda ="";
            if(resultado.getModo().equals("classico")){
                modo="Clássico";
            }else if(resultado.getModo().equals("morte_subita")){
                modo="Morte Súbita";
            }else if(resultado.getModo().equals("contra_relogio")){
                modo="ContraRelógio";
            }
            holder.modo.setText(modo.toString());
            holder.score.setText(String.valueOf(resultado.getScore()));
            return convertView;
        }else{
            return convertView;
        }
    }
}
