package pt.tfc_ulusofona.androidquizbuilder.Controller;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pt.tfc_ulusofona.androidquizbuilder.Model.Questionario;
import pt.tfc_ulusofona.androidquizbuilder.Model.Resultado;
import pt.tfc_ulusofona.androidquizbuilder.Model.Utilizador;
import pt.tfc_ulusofona.androidquizbuilder.R;

public class listaResultadosAdapter extends BaseAdapter
{
    public ArrayList<Resultado> listaResultados;
    public ArrayList<Utilizador> utilizadores;
    Activity activity;

    public listaResultadosAdapter(Activity activity, ArrayList<Resultado> listaResultados, ArrayList<Utilizador> utilizadores) {
        super();
        this.activity = activity;
        this.listaResultados = listaResultados;
        this.utilizadores = utilizadores;
    }

    @Override
    public int getCount() {
        return listaResultados.size();
    }

    @Override
    public Object getItem(int i) {
        return listaResultados.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public Resultado getResultadoID(int idx)
    {
        for(Resultado resultado : listaResultados)
        {
            if(resultado.getId()==idx)
            {
                return resultado;
            }
        }
        return null;
    }

    public String nomeUser(int utilizadorID)
    {
        for(Utilizador utilizador : utilizadores)
        {
            if(utilizador.getId() == utilizadorID)
            {
                return utilizador.getNome();
            }
        }
        return "";
    }

    public int getQuestionarioId(int i)
    {
        return listaResultados.get(i).getId();
    }



    private class ViewHolder {
        TextView nome;
        TextView score;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.lista_row1, null);
            holder = new ViewHolder();
            holder.nome = (TextView) convertView.findViewById(R.id.nome_ranking);
            holder.score = (TextView) convertView.findViewById(R.id.score_ranking);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(position%2==0)
        {
            convertView.setBackgroundColor(Color.parseColor("#0101DF"));
        }
        else
        {
            convertView.setBackgroundColor(Color.parseColor("#3366ff"));

        }
        Resultado item = listaResultados.get(position);
        //String id = Integer.toString(item.getId());
        String score = Integer.toString(item.getScore());
        String nome = nomeUser(item.getUtilizadorID());
        holder.nome.setText(nome);
        holder.score.setText(score+" pts");

        return convertView;
    }
}
