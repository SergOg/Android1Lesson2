package ru.geekbrains.lesson2;
//1. С этого урока будем писать приложение «Калькулятор». Выберите макет для работы с
//калькулятором. Обоснуйте, почему будете использовать именно этот тип макета:
//Выбрал GridLayout по причине того, что он наиболее подходит ко всем задачам с рабочим полем в виде
//сетки тем более, что он наследуется от простого LinearLayout. TableLayout древний и обладает наименьшим
//функционалом по сравнению с GridLayout. Для фоновой картинки пришлось задействовать FrameLayout.
//Constraintlayout избыточен для данной задачи и не оптимален, хотя перспективный с большими возможностями.

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;  //Поле результата
    private Numbers numbers;    //Числа для операций
    private int num = 0;

    private final static String KEY_NUMBERS = "Numbers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numbers = new Numbers();
        initViews();
    }

    // Сохранение данных
    @Override
    public void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putParcelable(KEY_NUMBERS, numbers);
    }

    // Восстановление данных
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        numbers = (Numbers) instanceState.getParcelable(KEY_NUMBERS);
        setTextButtons();
    }

    // Отображение данных на экране только одного значения, например, введенных цифр
    // Далее, т.к. не ввели операцию, то и введенные цифры не стали числом "Number1"
    // Придется вводить первое число еще раз после поворота экрана
    // Если ввели операцию и повернули экран, она не отображается, но дальнейшая логика выполняется
    private void setTextButtons() {
        setTextButton(textView, numbers.getNumber());
    }

    // Установить текст на TextView
    private void setTextButton(TextView textView, String buttonText) {
        textView.setText(String.format(Locale.getDefault(), "%s", buttonText));
    }

    private void addNumToString(String strIn) {
        String s = numbers.getNumber();
        s = s + strIn;
        setTextButton(textView, s);
        numbers.setNumber(s);
        num = 1;
    }

    private void makeOperation(String strIn, int i) {
        int a = 0;
        if (num == 1) {
            String s = numbers.getNumber();
            a = Integer.parseInt(s);
        }
        if ((numbers.getNumber1() == 0) && (num == 1)) {
            numbers.setNumber1(a);
            if (i != 0) {
                setTextButton(textView, strIn);
            }

        } else if ((numbers.getNumber2() == 0) && (num == 1)) {
            numbers.setNumber2(a);
            numbers.operations(numbers.getOperation());
            setTextButton(textView, String.valueOf(numbers.getResult()));
            numbers.setNumber2(0);
            if (i == 0) {
                numbers.setNumber1(0);
            } else {
                numbers.setNumber1(numbers.getResult());
            }
        }
        numbers.setNumber("");
        num = 0;
        numbers.setOperation(i);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_1:
                addNumToString("1");
                break;
            case R.id.button_2:
                addNumToString("2");
                break;
            case R.id.button_3:
                addNumToString("3");
                break;
            case R.id.button_4:
                addNumToString("4");
                break;
            case R.id.button_5:
                addNumToString("5");
                break;
            case R.id.button_6:
                addNumToString("6");
                break;
            case R.id.button_7:
                addNumToString("7");
                break;
            case R.id.button_8:
                addNumToString("8");
                break;
            case R.id.button_9:
                addNumToString("9");
                break;
            case R.id.button_0:
                addNumToString("0");
                break;
            case R.id.button_00:
                addNumToString("00");
                break;
            case R.id.button_del:
                makeOperation("/", 1);
                break;
            case R.id.button_multi:
                makeOperation("*", 2);
                break;
            case R.id.button_minus:
                makeOperation("-", 3);
                break;
            case R.id.button_plus:
                makeOperation("+", 4);
                break;
            case R.id.button_equal:
                makeOperation("=", 0);
                break;
            case R.id.button_point:
                setTextButton(textView, ",");
                break;
            default:
                setTextButton(textView, "?");
                break;
        }
    }

    private void initViews() {
        textView = findViewById(R.id.button_result);
        buttonsClickListener();
    }

    private void buttonsClickListener() {
        Button button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(this);
        Button button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(this);
        Button button3 = findViewById(R.id.button_3);
        button3.setOnClickListener(this);
        Button button4 = findViewById(R.id.button_4);
        button4.setOnClickListener(this);
        Button button5 = findViewById(R.id.button_5);
        button5.setOnClickListener(this);
        Button button6 = findViewById(R.id.button_6);
        button6.setOnClickListener(this);
        Button button7 = findViewById(R.id.button_7);
        button7.setOnClickListener(this);
        Button button8 = findViewById(R.id.button_8);
        button8.setOnClickListener(this);
        Button button9 = findViewById(R.id.button_9);
        button9.setOnClickListener(this);
        Button button0 = findViewById(R.id.button_0);
        button0.setOnClickListener(this);
        Button button00 = findViewById(R.id.button_00);
        button00.setOnClickListener(this);
        Button buttonEqual = findViewById(R.id.button_equal);
        buttonEqual.setOnClickListener(this);
        Button buttonDel = findViewById(R.id.button_del);
        buttonDel.setOnClickListener(this);
        Button buttonMulti = findViewById(R.id.button_multi);
        buttonMulti.setOnClickListener(this);
        Button buttonMinus = findViewById(R.id.button_minus);
        buttonMinus.setOnClickListener(this);
        Button buttonPlus = findViewById(R.id.button_plus);
        buttonPlus.setOnClickListener(this);
        Button buttonPoint = findViewById(R.id.button_point);
        buttonPoint.setOnClickListener(this);
    }
}