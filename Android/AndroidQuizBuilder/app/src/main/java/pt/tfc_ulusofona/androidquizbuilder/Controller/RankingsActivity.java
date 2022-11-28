package pt.tfc_ulusofona.androidquizbuilder.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

import pt.tfc_ulusofona.androidquizbuilder.R;

public class RankingsActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    TabItem tabClassico;
    TabItem tabContra_Relogio;
    TabItem tabMorteSubita;
    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rankings_activity);
        tabClassico = findViewById(R.id.tabClassico);
        tabContra_Relogio = findViewById(R.id.tabContraRelogio);
        tabMorteSubita = findViewById(R.id.tabMorteSubita);
        toolbar = findViewById(R.id.toolbar_ranking);

        //TextView teste = findViewById(R.id.teste);
        tabLayout = findViewById(R.id.tablayout);
        RankingsAdapter adapter = new RankingsAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(this);

        ImageView img = (ImageView) findViewById(R.id.image_view);
        String nomeUtilizador = getIntent().getStringExtra("username");
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RankingsActivity.this, WelcomeActivity.class);
                intent.putExtra("username", nomeUtilizador);
                startActivity(intent);
            }
        });




    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
