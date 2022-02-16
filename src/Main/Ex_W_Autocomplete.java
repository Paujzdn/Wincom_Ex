package Main;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Pau.jzdn 16/2/2022
 *
 *
 * This code will offer up to 4 suggested “auto-complete” based on the letters typed (not case sensitive)
 * sorted alphabetically
 * from a keyword pool
 * for a given prefix.
 *
 */
public class Ex_W_Autocomplete {
    public static void main(String[] args) {
        String input = "OW";
        List<String> suggestions = Autocomple_Word(input); //Autocomplete method() called, and max 4 legal suggestions returned
        for(int i = 0; i < suggestions.size(); i++) { //Print suggestion if required
            System.out.print(suggestions.get(i)+ "\n");
        }
    }

    /**
     * @param KeyWords (pool of keywords)
     * @param prefx    (string typed on the query input)
     * @return the first 4 possible words following the first keyword hit
     * This method takes the pool of words and creates a sorted alphabetical list with those
     * Afterwards it creates a hashtable with the words sorted and an increasing index as a key for each
     * The code iterates through the hashtable (making sure both can be matched)
     * until it finds the first word that starts with the input string, then it fills a list with that word and the following it.
     * Returns a list of 4 words, ignoring if those are legal or not (legal meaning it is true that the word starts with the input)
     * because it is possible that there aren't 4 words starting with the same prefix
     */
    public static List<String> resultats_W(List<String> KeyWords, String prefx) {
        List<String> res = new ArrayList<String>(); //A list of the possible preddicted words
        Collections.sort(KeyWords);//Sorting the keyword pool
        Hashtable<Integer, String> results = new Hashtable<>(); //Hashtable creator
        for (int i = 0; i < KeyWords.size(); i++) {//Fill hashtable with sorted keywords
            results.put(i, KeyWords.get(i));
        }
        //Iterator for hashtable
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).toLowerCase(Locale.ROOT).startsWith(prefx.toLowerCase(Locale.ROOT))) {//Checking every key value to see if it starts with the input
                for (int y = i; y < results.size(); y++) { //Adding 4 possible words to the prediction list
                    res.add(results.get(y));
                }
                break; //break to make sure we only store the first possible word
            }
        }
        return res;
    }

    /**
     * @param prf (input string)
     * @return correct legal list of words (legal meaning all of the words do start with de input string and are sorted alphabetically)
     * This method creates a list with all the possible keywords
     * It makes sure that the argument isn't blank or it is not a string
     * Calls a method that returns a list of possible predictions, and the filters the ones that aren't legal.
     * Returns de final predicted list of words
     */
    public static List<String> Autocomple_Word(String prf) {
        List<String> keyW = new ArrayList<String>(Arrays.asList("Pandora", "Pinterest", "Paypal",
                "Pg&e", "Project free tv", "Priceline", "Press democrat", "Progressive", "Project runway",
                "Proactive", "Programming", "Progeria", "Progesterone", "Progenex", "Procurable", "Processor",
                "Proud", "Print", "Prank", "Bowl", "Owl", "River", "Phone", "Kayak", "Stamps", "Reprobe")); //Create the list of keywords
        if (prf == null || prf.isBlank()) { //Check if the argument is a valid input
            return null;
        }
        List<String> predict = resultats_W(keyW, prf);
        //Create prediction list, with only legal words
        for (int i = 0; i < predict.size(); i++) {
            if (!predict.get(i).toLowerCase(Locale.ROOT).startsWith(prf.toLowerCase(Locale.ROOT))) {
                predict.remove(i);
                i--;
            }
        }
        //Create final suggestion list, if empty (no suggestion found) return string input
        List<String> fpredict = new ArrayList<>();
        if (!predict.isEmpty()) {
            fpredict = predict.stream().limit(4).toList();
        } else {
            fpredict.add(prf);
        }
        return fpredict;
    }

}
