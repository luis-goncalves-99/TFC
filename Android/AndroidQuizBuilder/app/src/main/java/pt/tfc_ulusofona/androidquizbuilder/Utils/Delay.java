package pt.tfc_ulusofona.androidquizbuilder.Utils;

import android.os.Handler;

import java.util.logging.LogRecord;

public class Delay {

    public interface Teste{
        void depoisDelay();
    }
    public static void delay(int tempo,final Teste teste){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                teste.depoisDelay();
            }
        },tempo*1000);
    }

}
