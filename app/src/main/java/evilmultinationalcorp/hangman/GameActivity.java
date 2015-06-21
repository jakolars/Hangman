package evilmultinationalcorp.hangman;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class GameActivity extends Activity {

    Intent intent = new Intent("evilmultinationalcorp.hangman.ResultActivity");
    private String report;
    private Menu menu;

    GridView GridAlphabet;

    private String[] alphabet;
    private boolean nor;

    private Hangman game;
    private int image;
    private int games;
    private int gamesWon;
    private int gamesLost;
    ArrayList<String> wordsDone;
    ArrayList<String> wordsFailedStrings;


    // Strings:
    private String numberWordsText;
    private String wordsDoneText;
    private String numbFailedText;
    private String wordCompleteText;
    private String wordFailedText;
    private String nextWordtext;
    private String resultText;
    private String report1;
    private String report2;
    private String report3;

    private String rulesButton;
    private String exit;
    private String rules;
    private String back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle extras = getIntent().getExtras();
        nor=extras.getBoolean("nor");
        game=new Hangman(nor);
        games=0;
        gamesWon=0;
        gamesLost=0;
        wordsDone=new ArrayList<String>();
        wordsFailedStrings=new ArrayList<String>();

        if(nor){

            alphabet = new String[] {
                    "A", "B", "C", "D", "E",
                    "F", "G", "H", "I", "J",
                    "K", "L", "M", "N", "O",
                    "P", "Q", "R", "S", "T",
                    "U", "V", "W", "X", "Y", "Z","Æ","Ø","Å"};
            numberWordsText="Antall ord: ";
            wordsDoneText="Ord klart: ";
            numbFailedText="Ganger hengt: ";
            wordCompleteText="Du klarte ordet!, ordet var: ";
            wordFailedText="Du ble desverre hengt, ordet var: ";
            nextWordtext="Neste ord";
            resultText="Resultat";
            report1="Du har vært igjennom alle ordene."+"\n"+"Totalt klarte du: ";
            report2="\n"+"Du ble hengt: ";
            report3=" gang(er)";
            rulesButton="Regler";
            exit="Avslutt";
            rules="I hangman skal man gjette på enkle bokstaver for å tilslutt finne ett ord, for hver bokstav man gjetter feil kommer man nærmere og bli hengt";
            back="tilbake til spillet";


        }
        else{

            alphabet = new String[] {
                    "A", "B", "C", "D", "E",
                    "F", "G", "H", "I", "J",
                    "K", "L", "M", "N", "O",
                    "P", "Q", "R", "S", "T",
                    "U", "V", "W", "X", "Y", "Z"};
            numberWordsText="Total words: ";
            wordsDoneText="Words done: ";
            numbFailedText="Words failed: ";
            wordCompleteText="You did it!, the word was: ";
            wordFailedText="You got hanged, the word was: ";
            nextWordtext="Next word";
            resultText="Result";
            report1="You have completed all the words."+"\n"+"Words you completed: ";
            report2="\n"+"You got hanged: ";
            report3="time(s)";
            rulesButton="Rules";
            exit="Exit";
            rules="In Hangman you guess letters trying to find the word, for each incorrect guess you get one step closer to beeing hanged";
            back="back to game";
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        loadGame();

    }


    private void loadGame() {

        final CharSequence[] result = new String[game.getLength()];
        String print="";


        for(int i=0; game.getLength()>i; i++){
            result[i]="_ ";
            print+="_ ";
        }

        GridAlphabet = (GridView) findViewById(R.id.alphabet);

        final TextView TextResult =(TextView) findViewById(R.id.result);
        final TextView wordsPlayedText =(TextView) findViewById(R.id.wordsPlayedText);
        final TextView wordsPlayed =(TextView) findViewById(R.id.wordsPlayed);
        final TextView totalWords =(TextView) findViewById(R.id.totalWords);
        final TextView totalWordsText =(TextView) findViewById(R.id.totalWordsText);
        final TextView wordsFailed =(TextView) findViewById(R.id.wordsFailed);
        final TextView wordsFailedText=(TextView) findViewById(R.id.wordsfailedText);
        final ImageView hangman =(ImageView) findViewById(R.id.hangman);
        image=0;
        final ArrayList<Integer> images=new ArrayList<Integer>();
        images.add(R.drawable.start);
        images.add(R.drawable.one);
        images.add(R.drawable.two);
        images.add(R.drawable.three);
        images.add(R.drawable.four);
        images.add(R.drawable.five);
        images.add(R.drawable.six);
        images.add(R.drawable.seven);
        images.add(R.drawable.eight);
        images.add(R.drawable.nine);
        images.add(R.drawable.ten);
        final String solution=game.getWord();


        hangman.setImageResource(images.get(image));
        TextResult.setText(print);
        totalWordsText.setText(numberWordsText);
        wordsPlayedText.setText(wordsDoneText);
        wordsFailed.setText(Integer.toString(gamesLost));
        totalWords.setText(Integer.toString(game.getTotalWords()));
        wordsPlayed.setText(Integer.toString(gamesWon));
        wordsFailedText.setText(numbFailedText);
        ArrayAdapter alphaAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, alphabet);



        GridAlphabet.setAdapter(alphaAdapter);

        GridAlphabet.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView parent, View v, int position, long id) {

                CharSequence seqIn=((TextView) v).getText();

                String test=seqIn.toString();

                if(game.guess(test)){

                    ArrayList<Integer> pos=game.getPositions();
                    String nResult=new String();
                    for(int i=0;i<pos.size();i++){
                        result[pos.get(i)]=test;
                    }
                    for(int j=0; j<result.length; j++){
                        nResult+=result[j];
                    }
                    TextResult.setText(nResult);


                    ((TextView) v).setTextColor(0xffbdbdbd);
                    ((TextView) v).setOnClickListener(null);
                    if(game.won()){
                        GridAlphabet.setOnItemClickListener(null);
                        gamesWon++;
                        wordsDone.add(solution);
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(GameActivity.this);
                        builder1.setMessage(wordCompleteText + solution);
                        builder1.setCancelable(false);
                        if(gamesWon+gamesLost<game.getTotalWords()) {
                            builder1.setPositiveButton(nextWordtext,
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            loadGame();
                                            dialog.cancel();

                                        }
                                    });

                        }
                        else{
                            builder1.setPositiveButton(resultText,
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            report= report1+Integer.toString(gamesWon)+report2+Integer.toString(gamesLost)+report3;
                                            intent.putExtra("report", report);
                                            intent.putExtra("nor",nor);
                                            startActivity(intent);
                                            dialog.cancel();

                                        }
                                    });

                        }

                        AlertDialog alert11 = builder1.create();
                        alert11.show();

                    }

                }
                else{

                    image++;
                    hangman.setImageResource(images.get(image));



                    ((TextView) v).setTextColor(0xffbdbdbd);
                    ((TextView) v).setOnClickListener(null);

                    if(game.gameover()){
                        gamesLost++;
                        wordsFailedStrings.add(solution);
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(GameActivity.this);
                        builder1.setMessage(wordFailedText + solution);
                        builder1.setCancelable(false);
                        if(gamesWon+gamesLost<game.getTotalWords()) {
                            builder1.setPositiveButton(nextWordtext,
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            loadGame();
                                            dialog.cancel();

                                        }
                                    });
                        }
                        else{
                            builder1.setPositiveButton(resultText,
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            report= report1+Integer.toString(gamesWon)+report2+Integer.toString(gamesLost)+report3;
                                            intent.putExtra("report", report);
                                            intent.putExtra("nor",nor);
                                            startActivity(intent);
                                            dialog.cancel();

                                        }
                                    });

                        }
                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }

                }

            }

        });

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

            AlertDialog.Builder builder1 = new AlertDialog.Builder(GameActivity.this);
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
