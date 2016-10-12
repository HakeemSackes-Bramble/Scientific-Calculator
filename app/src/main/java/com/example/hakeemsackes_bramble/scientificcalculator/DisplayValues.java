package com.example.hakeemsackes_bramble.scientificcalculator;

import static com.example.hakeemsackes_bramble.scientificcalculator.MainActivity.EMPTYSTRING;
import static com.example.hakeemsackes_bramble.scientificcalculator.MainActivity.equation;

/**
 * Created by hakeemsackes-bramble on 10/7/16.
 */

public class DisplayValues {

     static String displayText(){
        String text = EMPTYSTRING;
        for (int i = 0; i < equation.size(); i++) {
            text += equation.get(i);
        }
        return text;
    }


}
