package com.example.hakeemsackes_bramble.scientificcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Stack;

import static com.example.hakeemsackes_bramble.scientificcalculator.DisplayValues.displayText;


public class MainActivity extends AppCompatActivity {
    static final String TAG = "eqlist";
    static final String EMPTYSTRING = "";


    static ArrayList<String> equation = new ArrayList<>();
    // i'll need a place to represent each users input in an array for processing
    static double answer;
    // a place to store the double value of the answer
    static String eqDisplay = EMPTYSTRING;
    // i might need to store the answer as a string before displaying it // not used
    static String numberAsString = EMPTYSTRING;
    // a placeholder for holding the string number values before adding to equation arrayList

    //int index = 0;
    // NOT NEEDED made to keep track of the number of buttons the user has clicked in order to log the location
    int place = 0;
    // a place holder seperate from index. needed for adding multiple numbers
    static ArrayList<Integer> multiplyAndDivide = new ArrayList<>();
    //the idea is to make a pemdas ordered list of all of the index values of the operations
    static ArrayList<Integer> addAndSubtract = new ArrayList<>();
    // same as above
    static Stack<Integer> openParenthesis = new Stack<>();
    static ArrayList<Integer> closeParenthesis = new ArrayList<>();

    static boolean isAnswer;
    // needed in order to make the necessary subtraction values for the equation in one button


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView screenDisplay = (TextView) findViewById(R.id.eqDisplay);
        screenDisplay.setText(displayText());
    }

    public void getNumber(View v) {
        Log.d("wtf", "getboolean: number clicked" + (isAnswer));
        equation.add(EMPTYSTRING);
        Button text = (Button) v;
        String command = text.getText().toString();
        boolean isminus = command.equals("-");
        Log.d("wtf", "getboolean: " + (isAnswer));
        if (isAnswer && !isminus) {
            equation.remove(EMPTYSTRING);
            return;
        } else {

            if ((numberAsString.length() != 0 || isAnswer) && command.equals("-")) {
                equation.add(command);
                place = equation.size() - 1;
                numberAsString = EMPTYSTRING;
            } else {
                numberAsString += command;
                equation.set(place, numberAsString);
            }
        }
        isAnswer = false;
        TextView screenDisplay = (TextView) findViewById(R.id.eqDisplay);
        screenDisplay.setText(displayText());
        equation.remove(EMPTYSTRING);
        Log.d(TAG, "getNumber: " + equation);
    }


    public void getFunctions(View v) {
        // ex: trig, power, log, factorial operations, parens, euler, and pi
        // different from the getCommand function because they have the issue of
        // needing to add multiplication symbol when necessary.
        // parens, pi, and Eulers number is included in this for the very issue stated above
        equation.add(EMPTYSTRING);
        Button text = (Button) v;
        String command = text.getText().toString();
        equation.add(command);
        TextView screenDisplay = (TextView) findViewById(R.id.eqDisplay);
        screenDisplay.setText(displayText());
        equation.remove(EMPTYSTRING);

    }

    public void Parenthesis(View v) {
        equation.add(EMPTYSTRING);
        Button text = (Button) v;
        String command = text.getText().toString();
        place = equation.size();
        equation.add(command);
        TextView screenDisplay = (TextView) findViewById(R.id.eqDisplay);
        screenDisplay.setText(displayText());
        equation.remove(EMPTYSTRING);
    }

    public void clearInput(View v) { // clear the last value of user input
        equation.clear();
        answer = 0;
        place = 0;
        multiplyAndDivide.clear();
        addAndSubtract.clear();
        numberAsString = EMPTYSTRING;
        isAnswer = false;
        TextView screenDisplay = (TextView) findViewById(R.id.eqDisplay);
        screenDisplay.setText(displayText());
        TextView answerDisplay = (TextView) findViewById(R.id.ansDisplay);
        answerDisplay.setText(EMPTYSTRING);
    }

    public void getAnswer(View v) {
        try {
//
            Answer.doPemdas(equation);
            answer = Double.valueOf(equation.get(0));
            int answerInt = (int) answer;
            if (answerInt == answer) {
                TextView screenDisplay = (TextView) findViewById(R.id.ansDisplay);
                screenDisplay.setText(EMPTYSTRING + answerInt);
                isAnswer = true;
                place = equation.size();
                return;
            }
            TextView screenDisplay = (TextView) findViewById(R.id.ansDisplay);
            screenDisplay.setText(EMPTYSTRING + answer);


        } catch (NumberFormatException r) {
            TextView screenDisplay = (TextView) findViewById(R.id.ansDisplay);
            screenDisplay.setText("-ERROR-");
        } catch (IndexOutOfBoundsException t) {
            TextView screenDisplay = (TextView) findViewById(R.id.ansDisplay);
            screenDisplay.setText("-ERROR-");
        }
        isAnswer = true;
        place = equation.size();
        numberAsString = EMPTYSTRING;
        Log.d("wtf", "getboolean in answer: " + (isAnswer));
    }

    public void getCommands(View v) { // get Input from user and get users preferred operations
        numberAsString = EMPTYSTRING;
        equation.add(EMPTYSTRING);
        Button text = (Button) v;
        String command = text.getText().toString();
        equation.add(command);
        TextView screenDisplay = (TextView) findViewById(R.id.eqDisplay);
        screenDisplay.setText(displayText());
        equation.remove(EMPTYSTRING);
        Log.d(TAG, "getCommand: " + equation);
        place = equation.size();
        Log.d(TAG, "getCommands: " + place);
        isAnswer = false;

        if (command.equals("/") || command.equals("*")) {
            multiplyAndDivide.add(place - 1);
            Log.d(TAG, "lodcation of multiplication: " + multiplyAndDivide.toString());
        } else if (command.equals("+") || command.equals("-")) {
            addAndSubtract.add(place - 1);
            Log.d(TAG, "location of addition: " + addAndSubtract.toString());
        } else if (command.equals("(")) {
            openParenthesis.add(place - 1);
            Log.d("openparenthesis", openParenthesis.toString());
        } else if (command.equals(")")) {
            closeParenthesis.add(0, place - 1);
            Log.d("closeparenthesis", closeParenthesis.toString());
        }
    }

//    private String displayText(){
//        String text = EMPTYSTRING;
//        for (int i = 0; i < equation.size(); i++) {
//            text += equation.get(i);
//        }
//        return text;
//    }

    public void delete(View v) {
        if (equation.size() != 0) {
            equation.remove(equation.size() - 1);
            place = equation.size();
            numberAsString = EMPTYSTRING;
            TextView screenDisplay = (TextView) findViewById(R.id.eqDisplay);
            screenDisplay.setText(displayText());
            TextView answerDisplay = (TextView) findViewById(R.id.ansDisplay);
            answerDisplay.setText(EMPTYSTRING);
            isAnswer = equation.size()%2==1;
        }

    }

}


