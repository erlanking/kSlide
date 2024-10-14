package com.example.slide;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private int[] images = {R.drawable.biba, R.drawable.jack, R.drawable.eddsheran, R.drawable.week}; // Убедитесь, что вы используете правильные изображения
    private ImageSliderAdapter adapter;
    private Handler handler = new Handler(Looper.getMainLooper());
    private int slideInterval = 3000; // Интервал 3 секунды
    private int currentPage = 0; // Начинаем с первого изображения

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.viewPager);
        adapter = new ImageSliderAdapter(this, images);
        viewPager2.setAdapter(adapter);

        startAutoSlide(); // Запускаем автослайд-шоу
    }

    private void startAutoSlide() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // Устанавливаем текущий элемент
                viewPager2.setCurrentItem(currentPage, true);

                currentPage++;

                // Если индекс больше или равен длине массива, сбрасываем на 0
                if (currentPage >= images.length) {
                    currentPage = -1;
                }


                handler.postDelayed(this, slideInterval);
            }
        };

        // Запускаем автослайд-шоу
        handler.postDelayed(runnable, slideInterval);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacksAndMessages(null); // Останавливаем автопрокрутку при приостановке активности
    }

    @Override
    protected void onResume() {
        super.onResume();
        startAutoSlide(); // Возобновляем автопрокрутку
    }
}
