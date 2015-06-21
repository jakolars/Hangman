package evilmultinationalcorp.hangman;

/**
 * Created by jakol_000 on 28/10/2014.
 */
import java.util.*;


public class Hangman {
    private static String[] noWords = {"BYGNING", "ONOMATEPOETIKON", "SKISTUE", "HUS","KATT","HUND","DATAMASKIN","BUSS","SYKKEL","FORGJENGER"};
    private static String[] engWords =
            {"FACE", "CAT", "TODAY", "JAVA", "ANDROID","PEOPLE","BUS","FANCY","KING","GAME"};





    private String word;
    private int wordnumb;
    private boolean nor;
    private ArrayList<Character> hits;   // correct guesses
    private int misses; // incorrect guesses
    ArrayList<Integer> position;
    ArrayList<Integer> Wordnumbers;

    public Hangman(boolean nor) {

        this.nor=nor;

        Wordnumbers = new ArrayList<Integer>();
        Random randomGenerator = new Random();
        while (Wordnumbers.size() < 10) {

            int random = randomGenerator.nextInt(10);
            if (!Wordnumbers.contains(random)) {
                Wordnumbers.add(random);
            }
        }

        wordnumb=0;
        if (nor) {
           word = noWords[Wordnumbers.get(wordnumb)];
        } else {
           word = engWords[Wordnumbers.get(wordnumb)];
        }
        hits = new ArrayList<Character>();


        misses = 0;
    }

    public boolean guess(String in){
        position= new ArrayList<Integer>(); // position of letter
        char inChar=in.charAt(0);

        if(word.indexOf(inChar)!=-1){



          for(int i=0;i<word.length(); i++){
              if(word.charAt(i)==inChar){

                  hits.add(inChar);
                  position.add(i);

              }

          }
          return true;
      }
      else{
          misses++;
        return false;
      }
    }

    boolean won(){
        if (hits.size()==word.length()){

            hits.clear();
            position.clear();
            wordnumb++;
            misses=0;
            if(wordnumb<Wordnumbers.size()) {
                if (nor) {
                    word = noWords[Wordnumbers.get(wordnumb)];
                } else {
                    word = engWords[Wordnumbers.get(wordnumb)];
                }
            }

            return true;
        }
        else{
            return false;
        }
    }
    boolean gameover(){
        if (misses>9){
            hits.clear();
            position.clear();
            misses=0;
            wordnumb++;
            if(wordnumb<Wordnumbers.size()) {
                if (nor) {
                    word = noWords[Wordnumbers.get(wordnumb)];
                } else {
                    word = engWords[Wordnumbers.get(wordnumb)];
                }
            }
            return true;
        }
        else {
            return false;
        }
    }
    int getLength() {
        return word.length();
    }
    ArrayList getHits(){
        return hits;
    }
    int getMisses(){
        return misses;
    }
    ArrayList getPositions(){
        return position;
    }
    String getWord(){

        return word;
    }
    int getTotalWords(){
        return noWords.length;
    }








}



