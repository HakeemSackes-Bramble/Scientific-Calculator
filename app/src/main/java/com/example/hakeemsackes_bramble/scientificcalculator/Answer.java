package com.example.hakeemsackes_bramble.scientificcalculator;

import android.app.Activity;
import android.util.Log;

import java.util.List;
import java.util.Stack;

import static com.example.hakeemsackes_bramble.scientificcalculator.Equations.add;
import static com.example.hakeemsackes_bramble.scientificcalculator.Equations.cos;
import static com.example.hakeemsackes_bramble.scientificcalculator.Equations.divide;
import static com.example.hakeemsackes_bramble.scientificcalculator.Equations.factorial;
import static com.example.hakeemsackes_bramble.scientificcalculator.Equations.ln;
import static com.example.hakeemsackes_bramble.scientificcalculator.Equations.log;
import static com.example.hakeemsackes_bramble.scientificcalculator.Equations.multiply;
import static com.example.hakeemsackes_bramble.scientificcalculator.Equations.power;
import static com.example.hakeemsackes_bramble.scientificcalculator.Equations.sin;
import static com.example.hakeemsackes_bramble.scientificcalculator.Equations.subtract;
import static com.example.hakeemsackes_bramble.scientificcalculator.Equations.tan;
import static java.lang.Math.sqrt;

/**
 * Created by hakeemsackes-bramble on 10/8/16.
 */

public class Answer extends Activity {
    static List<String> doPemdas(List<String> equation) {
        List<String> list = parenthesis(equation);
        Log.d("new equation", list.toString());

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("e")) {
                list.set(i, String.valueOf(Math.E));
                addMultiplier(i, list);
            }
            if (list.get(i).equals("pi")) {
                list.set(i, String.valueOf(Math.PI));
                addMultiplier(i, list);
            }
            if (list.get(i).equals("!")) {

                double value1 = Double.valueOf(list.get(i - 1));
                list.set(i, String.valueOf(factorial(value1)));
                list.remove(i - 1);
                i -= 1;
                Log.d("FACTORIAL", list.toString());
            }
            if (list.get(i).equals("tan")) {
                double value1 = Double.valueOf(list.get(i + 1));
                list.set(i, String.valueOf(tan(value1)));
                list.remove(i + 1);
                addMultiplier(i, list);
                Log.d("FACTORIAL", list.toString());

            }
            if (list.get(i).equals("cos")) {
                double value1 = Double.valueOf(list.get(i + 1));
                list.set(i, String.valueOf(cos(value1)));
                list.remove(i + 1);
                addMultiplier(i, list);
                Log.d("FACTORIAL", list.toString());
            }
            if (list.get(i).equals("sin")) {
                double value1 = Double.valueOf(list.get(i + 1));
                list.set(i, String.valueOf(sin(value1)));
                list.remove(i + 1);
                addMultiplier(i, list);
                Log.d("FACTORIAL", list.toString());
            }
            if (list.get(i).equals("log")) {
                double value1 = Double.valueOf(list.get(i + 1));
                list.set(i, String.valueOf(log(value1)));
                list.remove(i + 1);
                addMultiplier(i, list);
                Log.d("FACTORIAL", list.toString());
            }
            if (list.get(i).equals("ln")) {
                double value1 = Double.valueOf(list.get(i + 1));
                list.set(i, String.valueOf(ln(value1)));
                list.remove(i + 1);
                addMultiplier(i, list);
                Log.d("FACTORIAL", list.toString());

            }
            if (list.get(i).equals("sqrt")) {
                double value1 = Double.valueOf(list.get(i + 1));
                list.set(i, String.valueOf(sqrt(value1)));
                list.remove(i + 1);
                addMultiplier(i, list);
                Log.d("FACTORIAL", list.toString());

            }
        }
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).equals("^")) {
                double value2 = Double.valueOf(list.get(i + 1));
                double value1 = Double.valueOf(list.get(i - 1));
                list.set(i + 1, String.valueOf(power(value1, value2)));
                list.remove(i);
                list.remove(i - 1);
                i -= 1;
                Log.d("POWER", list.toString());

            }
        }
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).equals("/")) {
                double value2 = Double.valueOf(list.get(i + 1));
                double value1 = Double.valueOf(list.get(i - 1));
                list.set(i + 1, String.valueOf(divide(value1, value2)));
                list.remove(i);
                list.remove(i - 1);
                i -= 1;
                Log.d("DIVISION", list.toString());

            } else if (list.get(i).equals("*")) {
                double value2 = Double.valueOf(list.get(i + 1));
                double value1 = Double.valueOf(list.get(i - 1));
                list.set(i + 1, String.valueOf(multiply(value1, value2)));
                list.remove(i);
                list.remove(i - 1);
                i -= 1;
                Log.d("MULTIPLICATION", list.toString());
            }

        }
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).equals("+")) {
                double value2 = Double.valueOf(list.get(i + 1));
                double value1 = Double.valueOf(list.get(i - 1));
                list.set(i + 1, String.valueOf(add(value1, value2)));
                list.remove(i);
                list.remove(i - 1);
                i -= 1;
                Log.d("ADDITION", list.toString());

            } else if (list.get(i).equals("-")) {
//                if (isWordfunction(list, i + 1)) {
//
//                } else {
                double value2 = Double.valueOf(list.get(i + 1));
                double value1 = Double.valueOf(list.get(i - 1));
                list.set(i + 1, String.valueOf(subtract(value1, value2)));
                list.remove(i);
                list.remove(i - 1);
                i -= 1;
                Log.d("SUBTRACTION", list.toString());

            }
        }
        Log.d("", "doPemdas: " + list.toString());
        return list;
    }

    static List<String> parenthesis(List<String> equation1) {
        List<String> equate = equation1;
        Stack<Integer> openParens = new Stack<>();
        int closedParen = 0;
        for (int i = 0; i < equate.size(); i++) {
            if (equate.get(i).equals("(")) {
                openParens.push(i + addMultiplier(i, equate));
            }
            if (equate.get(i).equals(")")) {
                closedParen++;
                int lastParens = openParens.pop();
                doPemdas(equate.subList(lastParens + 1, i));
                Log.d("parens", equate.toString());
                equate.remove(lastParens);
                equate.remove(lastParens + 1);
                i = lastParens;
            }
        }
        return equate;
    }

    static int addMultiplier(int index, List<String> list) {
        int i = 0;
        if (index - 1 < 0) {
            return 0;
        }
        if (!(list.get(index - 1).equals("/") || list.get(index - 1).equals("+") ||
                list.get(index - 1).equals("-") || list.get(index - 1).equals("*")
                || list.get(index - 1).equals("("))) {
            list.add(index, "*");
            i++;
        }
        return i;
    }

    static boolean isWordfunction(List<String> list, int index) {
        boolean isword = false;
        String[] wordproblems = {
                "sin",
                "cos",
                "tan",
                "ln",
                "log",
                "e",
                "pi",
                "sqrt",
                "("
        };
        for (int i = 0; i < wordproblems.length; i++) {
            isword = wordproblems[i].equals(list.get(index + 1));
            if (isword) break;
        }
        return isword;
    }

    //TextView screenDisplay = (TextView) findViewById(R.id.ansDisplay);
    // screenDisplay.setText(EMPTYSTRING + answer);


    //------- NOT USED --------- NOT USED ---------- NOT USED
//    static void getAnswerRecurse(int i, List<String> equation) {
//        ArrayList<Integer> order = new ArrayList<>();
//        List<String> newEquation = equation;
//        order.addAll(multiplyAndDivide);
//        order.addAll(addAndSubtract);
//        for (int j = 0; j < order.size(); j++) {
//
//        }
//    }


}
