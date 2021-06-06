package com.example.tempo_trainer.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.tempo_trainer.R;

import java.text.DecimalFormat;


public class MainFragment extends Fragment {

    private MainViewModel viewModel;

    // high-touch views
    private Button startButton;
    private SeekBar tempoSlider;
    private TextView tempoSliderValueText;
    private SeekBar speedSlider;
    private TextView speedSliderValueText;
    private TextView backswingTimeText;
    private TextView downswingTimeText;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View mainFragment = inflater.inflate(R.layout.main_fragment, container, false);

        // get view model from main activity
        viewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);

        // cache heavily accessed views
        startButton = mainFragment.findViewById(R.id.startButton);
        tempoSlider = mainFragment.findViewById(R.id.tempoSlider);
        tempoSliderValueText = mainFragment.findViewById(R.id.tempoSliderValueText);
        speedSlider = mainFragment.findViewById(R.id.speedSlider);
        speedSliderValueText = mainFragment.findViewById(R.id.speedSliderValueText);
        downswingTimeText = mainFragment.findViewById(R.id.downswingTimeText);
        backswingTimeText = mainFragment.findViewById(R.id.backswingTimeText);

        // set seek bar default and max steps
        tempoSlider.setMax(viewModel.numTempoSteps);
        tempoSlider.setProgress(viewModel.defaultTempoStep);

        speedSlider.setMax(viewModel.numDownswingSteps);
        speedSlider.setProgress(viewModel.defaultDownswingStep);

        TextView tempoRatioLowText = mainFragment.findViewById(R.id.tempoSliderLowText);
        tempoRatioLowText.setText(formatRatio(viewModel.minTempoRatio));
        TextView tempoRatioHighText = mainFragment.findViewById(R.id.tempoSliderHighText);
        tempoRatioHighText.setText(formatRatio(viewModel.maxTempoRatio));

        // set up view listeners
        startButton.setOnClickListener(buttonClickListener);
        tempoSlider.setOnSeekBarChangeListener(tempoSliderListener);
        speedSlider.setOnSeekBarChangeListener(speedSliderListener);

        // set up observers of view model data
        viewModel.getRunning().observe(this, runningObserver);
        viewModel.getBackswingTime().observe(this, backswingTimeObserver);
        viewModel.getDownswingTime().observe(this, downswingTimeObserver);
        viewModel.getTempoRatio().observe(this, tempoRatioObserver);

        return mainFragment;
    }

    private String formatRatio(double ratio) {
        return String.format("%s:1", new DecimalFormat("#.#").format(ratio));
    }

    private final Observer<Boolean> runningObserver = new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean running) {
            if (running) {
                startButton.setText(R.string.button_running_text);
            } else {
                startButton.setText(R.string.button_stopped_text);
            }
        }
    };

    private final Observer<Double> backswingTimeObserver = new Observer<Double>() {
        @Override
        public void onChanged(Double time) {
            DecimalFormat df = new DecimalFormat("0.000");
            backswingTimeText.setText(String.format("%s sec", df.format(time)));
        }
    };

    private final Observer<Double> downswingTimeObserver = new Observer<Double>() {
        @Override
        public void onChanged(Double time) {
            DecimalFormat df = new DecimalFormat("0.000");
            downswingTimeText.setText(String.format("%s sec", df.format(time)));
        }
    };

    private final Observer<Double> tempoRatioObserver = new Observer<Double>() {
        @Override
        public void onChanged(Double ratio) {
            tempoSliderValueText.setText(formatRatio(ratio));
        }
    };

    private final View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewModel.toggleRunning();
        }
    };

    private final SeekBar.OnSeekBarChangeListener tempoSliderListener =
            new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            viewModel.forceStop();
            viewModel.setTempoStep(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    };

    private final SeekBar.OnSeekBarChangeListener speedSliderListener =
            new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            viewModel.forceStop();
            viewModel.setDownswingStep(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}