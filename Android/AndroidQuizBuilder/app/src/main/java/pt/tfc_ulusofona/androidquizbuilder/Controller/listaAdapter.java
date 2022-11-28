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

import pt.tfc_ulusofona.androidquizbuilder.Model.Questionario;
import pt.tfc_ulusofona.androidquizbuilder.R;

public class listaAdapter extends BaseAdapter
{
    public ArrayList<Questionario> listaQuestionario;
    Activity activity;
    Context context;

    public listaAdapter(Activity activity, ArrayList<Questionario> listaQuestionario) {
        super();
        this.activity = activity;
        this.listaQuestionario = listaQuestionario;
    }

    @Override
    public int getCount() {
        return listaQuestionario.size();
    }

    @Override
    public Object getItem(int i) {
        return listaQuestionario.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public Questionario getQuestionario(int idx)
    {
        for(Questionario questionario : listaQuestionario)
        {
            if(questionario.getId()==idx)
            {
                return questionario;
            }
        }
        return null;
    }

    public int getQuestionarioId(int i)
    {
        return listaQuestionario.get(i).getId();
    }



    private class ViewHolder {
        TextView titulo;
        TextView data;
        TextView dificuldade;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.lista_row, null);
            holder = new ViewHolder();
            holder.titulo = (TextView) convertView.findViewById(R.id.titulo);
            holder.data = (TextView) convertView.findViewById(R.id.data);
            holder.dificuldade = (TextView) convertView.findViewById(R.id.dificuldade);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Questionario item = listaQuestionario.get(position);
        holder.titulo.setText(item.getTitulo().toString());
        holder.data.setText(item.getDataDeCriacao().toString());
        holder.dificuldade.setText(item.getDificuldade().toString());

        return convertView;
    }
}
