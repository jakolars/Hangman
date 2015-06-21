package evilmultinationalcorp.hangman;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;


public class StartActivity extends Activity {
    Intent intent = new Intent("evilmultinationalcorp.hangman.GameActivity");
    private boolean nor = true;
    private Menu menu;
    private String rulesButton="Regler";
    private String exit="Avslutt";
    private String rules="I hangman skal man gjette på enkle bokstaver for å tilslutt finne ett ord, for hver bokstav man gjetter feil kommer man nærmere og bli hengt";
    private String back="tilbake til spillet";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);
        if( getIntent().getBooleanExtra("Exit me", false)){
            finish();
            return;
        }
    }


    public void onClickStart(View view) throws IOException {
        intent.putExtra("nor", nor);
        startActivity(intent);

    }
    public void onClickEnglish(View view) throws IOException {
        final Button startButton =(Button) findViewById(R.id.startButton);
        startButton.setText("Start english game");
        rulesButton="Rules";
        exit="Exit";
        rules="In Hangman you guess letters trying to find the word, for each incorrect guess you get one step closer to beeing hanged";
        back="back to game";
        MenuItem rulesItem = menu.findItem(R.id.action_settings);
        MenuItem exitItem = menu.findItem(R.id.exit_settings);
        rulesItem.setTitle(rulesButton);
        exitItem.setTitle(exit);
        nor=false;

    }
    public void onClickNorwegian(View view) throws IOException {
        final Button startButton =(Button) findViewById(R.id.startButton);
        startButton.setText("Start norsk spill");
        rulesButton="Regler";
        exit="Avslutt";
        rules="I hangman skal man gjette på enkle bokstaver for å til slutt finne ett ord, for hver bokstav man gjetter feil kommer man nærmere og bli hengt";
        back="tilbake til spillet";
        MenuItem rulesItem = menu.findItem(R.id.action_settings);
        MenuItem exitItem = menu.findItem(R.id.exit_settings);
        rulesItem.setTitle(rulesButton);
        exitItem.setTitle(exit);
        nor=true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.start, menu);
        this.menu=menu;
        MenuItem rulesItem = menu.findItem(R.id.action_settings);
        MenuItem exitItem = menu.findItem(R.id.exit_settings);
        rulesItem.setTitle(rulesButton);
        exitItem.setTitle(exit);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        int id = item.getItemId();
        if (id == R.id.action_settings) {

            AlertDialog.Builder builder1 = new AlertDialog.Builder(StartActivity.this);
            builder1.setMessage(rules);

                builder1.setPositiveButton(back,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();

                            }
                        });
            AlertDialog alert11 = builder1.create();
            alert11.show();


            return true;
        }
        else{
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



}
