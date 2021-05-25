package ru.geekbrains.lesson2;
//1. С этого урока будем писать приложение «Калькулятор». Выберите макет для работы с
//калькулятором. Обоснуйте, почему будете использовать именно этот тип макета:
//Выбрал GridLayout по причине того, что он наиболее подходит ко всем задачам с рабочим полем в виде
//сетки тем более, что он наследуется от простого LinearLayout. TableLayout древний и обладает наименьшим
//функционалом по сравнению с GridLayout. Для фоновой картинки пришлось задействовать FrameLayout.
//Constraintlayout избыточен для данной задачи и не оптимален, хотя перспективный с большими возможностями.
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}