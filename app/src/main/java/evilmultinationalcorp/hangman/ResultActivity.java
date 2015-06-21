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
import android.widget.TextView;

import java.io.IOException;


public class ResultActivity extends Activity {

    private Menu menu;
    private String exit;
    private String restart;
    private boolean nor;
    private String result;

    private String rulesButton;
    private String rules;
    private String back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        result=extras.getString("report");
        nor=extras.getBoolean("nor");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        loadResult();
    }
    private void loadResult() {

        final TextView resultText =(TextView) findViewById(R.id.resultText);
        final Button restartButton =(Button) findViewById(R.id.restartButton);
        final Button exitButton =(Button) findViewById(R.id.exitButton);
        resultText.setText(result);
        if(nor){
            exit="Avslutt spill";
            restart="Starte spillet på nytt";
            rulesButton="Regler";
            rules="I hangman skal man gjette på enkle bokstaver for å til slutt finne ett ord, for hver bokstav man gjetter feil kommer man nærmere og bli hengt";
            back="tilbake til spillet";
        }
        else {
            exit="Quit the game";
            restart="Start new game";
            rulesButton="Rules";
            rules="In Hangman you guess letters trying to fin the word, for each incorrect guess you get one step closer to beeing hanged";
            back="back to game";
        }

        exitButton.setText(exit);
        restartButton.setText(restart);
    }

    public void onClickRestart(View view) throws IOException {
        navigateUpTo(new Intent(this, StartActivity.class));

    }

    public void onClickExit(View view) throws IOException {
        Intent intent = new Intent(this, StartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("Exit me", true);
        startActivity(intent);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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

            AlertDialog.Builder builder1 = new AlertDialog.Builder(ResultActivity.this);
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
            Intent intent = new Intent(this, StartActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("Exit me", true);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
