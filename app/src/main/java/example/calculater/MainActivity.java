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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int i;

    private final int KEY_0 = 0;
    private final int KEY_1 = 1;
    private final int KEY_2 = 2;
    private final int KEY_3 = 3;
    private final int KEY_4 = 4;
    private final int KEY_5 = 5;
    private final int KEY_6 = 6;
    private final int KEY_7 = 7;
    private final int KEY_8 = 8;
    private final int KEY_9 = 9;
    private final int KEY_CLEAR = 10;
    private final int KEY_MINUS = 11;
    private final int KEY_PLUS = 12;
    private final int KEY_EQUAL = 13;
    private final int KEY_Dot = 14;
    private final int KEY_Times = 15;
    private final int KEY_Divide = 16;



    int buttonId[] = {R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
            R.id.buttonClear, R.id.buttonMinus, R.id.buttonPlus, R.id.buttonEqual, R.id.buttonDot,
            R.id.buttonTimes, R.id.buttonDivide
    };

    Button button[];
    TextView displayTv;
    ScrollView scrollView;
    float total = 0;
    float value;
    String nowValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = new Button[buttonId.length];
        displayTv = (TextView) findViewById(R.id.display);
        scrollView = (ScrollView) findViewById(R.id.scv);

        for (i = 0; i < buttonId.length; i++) {
            button[i] = (Button) findViewById(buttonId[i]);
            button[i].setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View view) {

        scrollView.scrollTo(0, displayTv.getBottom());
//


        for (i = 0; i < buttonId.length; i++) {
            if (view == button[i]) {
                if (i < 10) {
                    String nowValue = displayTv.getText().toString();
                    nowValue += i;
                    displayTv.setText(nowValue);
                }

                switch (i){
                    case KEY_CLEAR:
                        total = 0;
                        displayTv.setText("");
                        break;

                    case KEY_PLUS:
                        nowValue = displayTv.getText().toString();
                        nowValue += "+";
                        displayTv.setText(nowValue);
                        break;
                    case KEY_MINUS:
                        nowValue = displayTv.getText().toString();
                        nowValue += "-";
                        displayTv.setText(nowValue);

                        break;
                    case KEY_EQUAL:
                        nowValue = String.valueOf(total);
                        displayTv.setText(nowValue);
                }



            }

        }

    }

    private float calc(){
        return (float) 0.0;
    }


}
