package com.example.hakeemsackes_bramble.scientificcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.hakeemsackes_bramble.scientificcalculator.DisplayValues.displayText;


public class MainActivity extends AppCompatActivity {
    static final String TAG = "eqlist";
    static final String EMPTYSTRING = "";
    static ArrayList<String> equation = new ArrayList<>();             // i'll need a place to represent each users input in an array for processing
    static double answer;                                              // a place to store the double value of the answer
    static String eqDisplay = EMPTYSTRING;                             // i might need to store the answer as a string before displaying it // not used
    static String numberAsString = EMPTYSTRING;                        // a placeholder for holding the string number values before adding to equation arrayList
    //int index = 0;                                                   // made to keep track of the number of buttons the user has clicked in order to log the location/ might not need it
    int place = 0;                                                     // a place holder seperate from index. needed for adding multiple numbers
    static ArrayList<Integer> multiplyAndDivide = new ArrayList<>();   //the idea is to make a pemdas ordered list of all of the index values of the operations
    static ArrayList<Integer> addAndSubtract = new ArrayList<>();      // same as above
    static ArrayList<Integer> openParenthesis = new ArrayList<>();
    static ArrayList<Integer> closeParenthesis = new ArrayList<>();
    static boolean isAnswer = false;                                   // needed in order to make the necessary subtraction values for the equation in one button





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getNumber(View v) {
        equation.add(EMPTYSTRING);
        Button text = (Button) v;
        String command = text.getText().toString();
        if ((numberAsString.length() != 0 && command.equals("-")) || isAnswer) {
            equation.add(command);
            place = equation.size()-1;
            numberAsString=EMPTYSTRING;
        } else {
            numberAsString += command;
            equation.set(place, numberAsString);
        }
        isAnswer = false;
        TextView screenDisplay = (TextView) findViewById(R.id.eqDisplay);
        screenDisplay.setText(displayText());
        equation.remove(EMPTYSTRING);
        Log.d(TAG, "getNumber: " + equation);
    }


    public void getCommand(View v) { // ex: trig, power, log, factorial  operations
//        Math.tan();
//        Math.cos();
//        Math.sin();
//        Math.log();
        // factorial
//        int input;
//        int starter = 1;// the number that precedes the factorial sign
//        for (int i = 1; i < input ; i++) {
//            starter *= i;
//        }
    }

    public void Parenthesis(View v){
        equation.add(EMPTYSTRING);
        Button text = (Button) v;
        String command = text.getText().toString();
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
        TextView screenDisplay = (TextView) findViewById(R.id.eqDisplay);
        screenDisplay.setText(displayText());
        TextView answerDisplay = (TextView) findViewById(R.id.ansDisplay);
        answerDisplay.setText(EMPTYSTRING);
    }

    public void getAnswer(View v) {
        try {
//            if (equation.size() == 0) {
//                answer = 0;
//            } else {
//                try {
//                    answer = Double.valueOf(equation.get(0));
//                } catch (NumberFormatException error) {
//                    if (equation.get(0).equals("-")) {
//                        answer = 0;
//                    } else {
//                        TextView screenDisplay = (TextView) findViewById(R.id.ansDisplay);
//                        screenDisplay.setText("-ERROR-");
//                        return;
//                    }
//                }
//            }
//            for (int i = 0; i < equation.size() - 1; i++) {
//                if (equation.get(i).equals("/")) {
//                    double denominator = Double.valueOf(equation.get(i + 1));
//                    answer = divide(answer, denominator);
//
//                } else if (equation.get(i).equals("+")) {
//                    double denominator = Double.valueOf(equation.get(i + 1));
//                    answer = add(answer, denominator);
//
//                } else if (equation.get(i).equals("-")) {
//                    double denominator = Double.valueOf(equation.get(i + 1));
//                    answer = subtract(answer, denominator);
//
//                } else if (equation.get(i).equals("*")) {
//                    double multiple = Double.valueOf(equation.get(i + 1));
//                    answer = multiply(answer, multiple);
//                }
            Answer.doPemdas(equation);
            TextView screenDisplay = (TextView) findViewById(R.id.ansDisplay);
            screenDisplay.setText(EMPTYSTRING + answer);
        } catch (NumberFormatException r) {
            TextView screenDisplay = (TextView) findViewById(R.id.ansDisplay);
            screenDisplay.setText("-ERROR-");
        } catch(IndexOutOfBoundsException t){
            TextView screenDisplay = (TextView) findViewById(R.id.ansDisplay);
            screenDisplay.setText("-ERROR-");
        }
        isAnswer = true;
        place = equation.size();
        numberAsString = EMPTYSTRING;
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

        if (command.equals("/") || command.equals("*")) {
            multiplyAndDivide.add(place - 1);
            Log.d(TAG, "lodcation of multiplication: " + multiplyAndDivide.toString());
        } else if (command.equals("+") || command.equals("-")) {
            addAndSubtract.add(place - 1);
            Log.d(TAG, "location of addition: " + addAndSubtract.toString());
        }else if(command.equals("(")){
            openParenthesis.add(place-1);
            Log.d("openparenthesis", openParenthesis.toString());
        }else if(command.equals(")")){
            closeParenthesis.add(0,place-1);
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
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> hi = new ArrayList<>();
        hi.add(0);
        hi.add(1);
        hi.add(2);
        hi.add(hi.size()-2, 0);
        System.out.println(hi);
    }

}


