package example.calculater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int i;
    private final int KEY_PLUS = 10;
    private final int KEY_MINUS = 11;
    private final int KEY_Times = 12;
    private final int KEY_Divide = 13;
    private final int KEY_EQUAL = 14;
    private final int KEY_Dot = 15;
    private final int KEY_CLEAR = 16;
    private final int PMTD = 17;

    /* calc */
    long letterNum;
    double totalValue = 0;
    ArrayList<Long> exponantialNum = new ArrayList<>();
    ArrayList<Character> letterValue = new ArrayList<>();
    ArrayList<Double> singleValue = new ArrayList<>();
    DecimalFormat decimalFormat = new DecimalFormat("################.#############");
    int asd;
    long startLetterNum = 0, endLetterNum = 0;
    boolean byStatusNum = false;
    int expoNum_idx = 0;
    /*------*/

    int buttonId[] = {R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
            R.id.buttonPlus, R.id.buttonMinus, R.id.buttonTimes, R.id.buttonDivide,
            R.id.buttonEqual, R.id.buttonDot, R.id.buttonClear
    };

    Button button[];
    TextView displayTv, totalTv;
    ScrollView scrollView;
    String nowValue;
    int lastValue = 99;
    boolean byFstLetter = true;
    boolean byZeroWatcher = false;  /*To protect double Zero when byFstLetter+1 is true*/
    boolean byDotWatcher = false;   /*To protect double Dot when ~~~*/

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = new Button[buttonId.length];
        displayTv = (TextView) findViewById(R.id.display);
        scrollView = (ScrollView) findViewById(R.id.scv);
        totalTv = (TextView) findViewById(R.id.displayTotal);

        for (i = 0; i < buttonId.length; i++) {
            button[i] = (Button) findViewById(buttonId[i]);
            button[i].setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View view) {

        scrollView.scrollTo(0, displayTv.getBottom());


        for (i = 0; i < buttonId.length; i++) {
            if (view == button[i]) {
                String nowValue = displayTv.getText().toString();

                if (0 < i && i < 10) {

                    /*
                    if (2 < nowValue.length()) {
                        secondLastValue = Integer.parseInt("" + nowValue.charAt(nowValue.length() - 2));
                    }
                    */

                    nowValue += i;
                    lastValue = i;
                    displayTv.setText(nowValue);
                    byFstLetter = false;

                    /*
                    if (byFstLetter && lastValue == 0) {
                        nowValue = nowValue.substring(0, nowValue.length() - 1);
                    }
                    */

                }

                if (i == 0) {
                    if ((byFstLetter && lastValue != 0) || (!byZeroWatcher && !byFstLetter)) {
                        nowValue += i;
                        lastValue = i;
                        displayTv.setText(nowValue);
                        if (byFstLetter) byZeroWatcher = true;
                        byFstLetter = false;
                    }
                }

                switch (i) {
                    case KEY_CLEAR:
                        displayTv.setText("");
                        byFstLetter = true;
                        lastValue = KEY_CLEAR;
                        break;
                    case KEY_PLUS:


                        if (lastValue == KEY_MINUS || lastValue == KEY_Times || lastValue == KEY_Divide) {
                            nowValue = nowValue.substring(0, nowValue.length() - 1);
                            nowValue += "+";
                            lastValue = KEY_PLUS;
                        } else if (lastValue != KEY_PLUS) {
                            nowValue += "+";
                            lastValue = KEY_PLUS;
                        }
                        displayTv.setText(nowValue);
                        byFstLetter = true;
                        break;

                    case KEY_MINUS:

                        if (lastValue == KEY_PLUS || lastValue == KEY_Times || lastValue == KEY_Divide) {
                            nowValue = nowValue.substring(0, nowValue.length() - 1);
                            nowValue += "-";
                            lastValue = KEY_MINUS;
                        } else if (lastValue != KEY_MINUS) {
                            nowValue += "-";
                            lastValue = KEY_MINUS;
                        }
                        displayTv.setText(nowValue);
                        byFstLetter = true;
                        break;

                    case KEY_Times:

                        if (lastValue == KEY_MINUS || lastValue == KEY_PLUS || lastValue == KEY_Divide) {
                            nowValue = nowValue.substring(0, nowValue.length() - 1);
                            nowValue += "×";
                            lastValue = KEY_Times;
                        } else if (lastValue != KEY_Times) {
                            nowValue += "×";
                            lastValue = KEY_Times;
                        }
                        displayTv.setText(nowValue);
                        byFstLetter = true;
                        break;

                    case KEY_Divide:
                        if (lastValue == KEY_MINUS || lastValue == KEY_Times || lastValue == KEY_PLUS) {
                            nowValue = nowValue.substring(0, nowValue.length() - 1);
                            nowValue += "÷";
                            lastValue = KEY_Divide;
                        } else if (lastValue != KEY_Divide) {
                            nowValue += "÷";
                            lastValue = KEY_Divide;
                        }
                        displayTv.setText(nowValue);
                        byFstLetter = true;
                        break;
                    case KEY_Dot:
                        if (!byFstLetter && !byDotWatcher) {
                            nowValue += ".";
                            displayTv.setText(nowValue);
                            byDotWatcher = true;
                            lastValue = KEY_Dot;
                        }
                        break;
                    case KEY_EQUAL:
                        calc(nowValue);
                        lastValue = KEY_EQUAL;
                        letterValue.clear();
                        exponantialNum.clear();
                        break;
                }


            }

        }
        //totalTv.setText(lastValue + " " + byFstLetter + " " + byZeroWatcher + " " + byDotWatcher);
        if (lastValue != 0) byZeroWatcher = false;
        if (byFstLetter) byDotWatcher = false;
    }

    public double calc(String nowValue) {
        expoNum_idx = 0;
        startLetterNum = 0;
        endLetterNum = 0;
        exponantialNum.clear();
        singleValue.clear();
        letterValue.clear();

        nowValue += null;

        for (letterNum = 0; letterNum < nowValue.length(); letterNum++) {

            letterValue.add(nowValue.charAt((int) letterNum));
            asd = (int) letterValue.get((int) letterNum);


            if ((47 < asd && asd < 58) || letterValue.get((int) letterNum).equals('.')) {

                if (!byStatusNum) {
                    startLetterNum = letterNum;
                    exponantialNum.add((long) -1);
                    byStatusNum = true;
                }
                /*
                displayTv.setText(startLetterNum + " " + endLetterNum + " " + byStatusNum);
                totalTv.setText("" + letterValue.get((int) letterNum));
                */
                exponantialNum.set(expoNum_idx, exponantialNum.get(expoNum_idx) + 1);
            } else if (byStatusNum) {
                endLetterNum = letterNum;
                byStatusNum = false;
                expoNum_idx += 1;
                totalTv.setText(startLetterNum + " " + endLetterNum);

                singleValue.add(Double.parseDouble(nowValue.subSequence((int) startLetterNum, (int) endLetterNum).toString()));

                if (startLetterNum == 0) {
                    totalValue = singleValue.get(0);
                } else {
                    switch (letterValue.get((int) startLetterNum - 1)) {
                        case '+':
                            totalValue += singleValue.get(expoNum_idx - 1);
                            break;
                        case '-':
                            totalValue -= singleValue.get(expoNum_idx - 1);
                            break;
                        case '×':
                            totalValue *= singleValue.get(expoNum_idx - 1);
                            break;
                        case '÷':
                            totalValue /= singleValue.get(expoNum_idx - 1);
                            break;
                    }
                }

            }
            // if (letterValue.get(expoNum_idx + 1) == null) {
            //    totalTv.setText("Let it go!");
            // }

        }
        if (decimalFormat.format(totalValue).length() <= 9) {
            totalTv.setText(decimalFormat.format(totalValue));
        }
        totalTv.setText(totalValue + "");

        return 0;
    }


}
