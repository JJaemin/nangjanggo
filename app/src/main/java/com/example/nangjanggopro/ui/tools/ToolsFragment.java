package com.example.nangjanggopro.ui.tools;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.nangjanggopro.R;

import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.ALARM_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;
    TextView timerView;
    TimerTask timerTask;
    Timer timer = new Timer();
    Button btn_start, btn_finish;
    EditText timeMM, timeSS;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);

        timerView = (TextView) root.findViewById(R.id.timer);
        btn_start = root.findViewById(R.id.btn_start);
        btn_finish = root.findViewById(R.id.btn_finish);
        timeMM = root.findViewById(R.id.timeMM);
        timeSS = root.findViewById(R.id.timeSS);


        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                

                    startTimerTask();
            }
        });

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimerTask();
            }
        });


        return root;
    }




    private void startTimerTask() {
        stopTimerTask();
        timerTask = new TimerTask() {

            int intMM = Integer.parseInt(timeMM.getText().toString());
            int intMM2 = intMM * 60;
            int intSS = Integer.parseInt(timeSS.getText().toString());
            int count = intMM2+intSS;

            @Override
            public void run() {
                count--;



                final int intMM3 = count/60;
                final int intSS2 = count%60;

                timerView.post(new Runnable() {
                    @Override
                    public void run() {
                        timerView.setText(intMM3+"분"+intSS2+"초");
                        if(count <= 0){
                            timerView.setText("0분 0초");
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask,0 ,1000);
    }


    private void stopTimerTask()
    {
        if(timerTask != null)
        {
            timerView.setText(timeMM.getText().toString()+"분" +timeSS.getText().toString()+"초");
            timerTask.cancel();
            timerTask = null;
        }
    }

}