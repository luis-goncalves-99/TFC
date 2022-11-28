package pt.tfc_ulusofona.androidquizbuilder.Controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Button;
import android.widget.TextView;

import pt.tfc_ulusofona.androidquizbuilder.Controller.ClassicoFragment;
import pt.tfc_ulusofona.androidquizbuilder.Controller.ContraRelogioFragment;
import pt.tfc_ulusofona.androidquizbuilder.Controller.MorteSubitaFragment;

public class RankingsAdapter extends FragmentPagerAdapter {

    private int qtd;

    RankingsAdapter(FragmentManager fm, int qtd)
    {
        super(fm);
        this.qtd = qtd;
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position) {
            case 0:
                ClassicoFragment classic = new ClassicoFragment();
                return classic;
            case 1:
                return new MorteSubitaFragment();
            case 2:
                return new ContraRelogioFragment();
            default:
                return null;
        }
    }
    @Override
    public int getCount()
    {
        return qtd;
    }
}
