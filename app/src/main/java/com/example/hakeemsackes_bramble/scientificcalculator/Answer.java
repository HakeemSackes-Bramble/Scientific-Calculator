package com.example.hakeemsackes_bramble.scientificcalculator;

import android.app.Activity;
import android.util.Log;

import java.util.List;

import static com.example.hakeemsackes_bramble.scientificcalculator.Equations.add;
import static com.example.hakeemsackes_bramble.scientificcalculator.Equations.divide;
import static com.example.hakeemsackes_bramble.scientificcalculator.Equations.factorial;
import static com.example.hakeemsackes_bramble.scientificcalculator.Equations.multiply;
import static com.example.hakeemsackes_bramble.scientificcalculator.Equations.power;
import static com.example.hakeemsackes_bramble.scientificcalculator.Equations.subtract;

/**
 * Created by hakeemsackes-bramble on 10/8/16.
 */

public class Answer extends Activity {
    static List<String> doPemdas(List<String> equation) {

        for (int i = 0; i < equation.size(); i++) {
            if (equation.get(i).equals("!")) {
                double value1 = Double.valueOf(equation.get(i - 1));
                equation.set(i, String.valueOf(factorial(value1)));
                equation.remove(i - 1);
                i -= 1;
                Log.d("FACTORIAL", equation.toString());

            }
        }
        for (int i = 0; i < equation.size() - 1; i++) {
            if (equation.get(i).equals("^")) {
                double value2 = Double.valueOf(equation.get(i + 1));
                double value1 = Double.valueOf(equation.get(i - 1));
                equation.set(i + 1, String.valueOf(power(value1, value2)));
                equation.remove(i);
                equation.remove(i - 1);
                i -= 1;
                Log.d("POWER", equation.toString());

            }
        }
        for (int i = 0; i < equation.size() - 1; i++) {
            if (equation.get(i).equals("/")) {
                double value2 = Double.valueOf(equation.get(i + 1));
                double value1 = Double.valueOf(equation.get(i - 1));
                equation.set(i + 1, String.valueOf(divide(value1, value2)));
                equation.remove(i);
                equation.remove(i - 1);
                i -= 1;
                Log.d("DIVISION", equation.toString());

            } else if (equation.get(i).equals("*")) {
                double value2 = Double.valueOf(equation.get(i + 1));
                double value1 = Double.valueOf(equation.get(i - 1));
                equation.set(i + 1, String.valueOf(multiply(value1, value2)));
                equation.remove(i);
                equation.remove(i - 1);
                i -= 1;
                Log.d("MULTIPLICATION", equation.toString());
            }

        }
        for (int i = 0; i < equation.size() - 1; i++) {
            if (equation.get(i).equals("+")) {
                double value2 = Double.valueOf(equation.get(i + 1));
                double value1 = Double.valueOf(equation.get(i - 1));
                equation.set(i + 1, String.valueOf(add(value1, value2)));
                equation.remove(i);
                equation.remove(i - 1);
                i -= 1;
                Log.d("ADDITION", equation.toString());

            } else if (equation.get(i).equals("-")) {
                double value2 = Double.valueOf(equation.get(i + 1));
                double value1 = Double.valueOf(equation.get(i - 1));
                equation.set(i + 1, String.valueOf(subtract(value1, value2)));
                equation.remove(i);
                equation.remove(i - 1);
                i -= 1;
                Log.d("SUBTRACTION", equation.toString());
            }
        }
        Log.d("", "doPemdas: " + equation.toString());
       return equation;
    }




    //TextView screenDisplay = (TextView) findViewById(R.id.ansDisplay);
    // screenDisplay.setText(EMPTYSTRING + answer);


    //------- NOT USED --------- NOT USED ---------- NOT USED
//    static void getAnswerRecurse(int i, ArrayList<String> equation) {
//        ArrayList<Integer> order = new ArrayList<>();
//        ArrayList<String> newEquation = equation;
//        order.addAll(multiplyAndDivide);
//        order.addAll(addAndSubtract);
//        for (int j = 0; j < order.size(); j++) {
//
//        }
//    }


}
