package com.example.tempo_trainer.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.example.tempo_trainer.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;


public class MainFragment extends Fragment {

    private MainViewModel viewModel;

    private Button startButton;
    private EditText backswingTimeField;
    private EditText downswingTimeField;
    private SeekBar tempoSlider;

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
        backswingTimeField = mainFragment.findViewById(R.id.backswingTime);
        downswingTimeField = mainFragment.findViewById(R.id.downswingTime);
        tempoSlider = mainFragment.findViewById(R.id.tempoSlider);

        // set default values before listeners are attached
        backswingTimeField.setText(String.valueOf(viewModel.defaultBackswingDuration));
        downswingTimeField.setText(String.valueOf(viewModel.defaultDownswingDuration));

        int maxTempoSliderStep = (int) (viewModel.maxRatio - viewModel.minRatio) * 10;
        tempoSlider.setMax(maxTempoSliderStep);
        int defaultTempoSliderStep = (int) (viewModel.defaultRatio - viewModel.minRatio) * 10;
        tempoSlider.setProgress(defaultTempoSliderStep);

        // set up view listeners
        startButton.setOnClickListener(buttonClickListener);
        backswingTimeField.addTextChangedListener(backswingFieldWatcher);
        downswingTimeField.addTextChangedListener(downswingFieldWatcher);
        tempoSlider.setOnSeekBarChangeListener(tempoSliderListener);

        // set up observers of view model data
        viewModel.getRunning().observe(this, runningObserver);
        viewModel.getBackswingDuration().observe(this, backswingDurationObserver);
        viewModel.getDownswingDuration().observe(this, downswingDurationObserver);
        viewModel.getTempoRatio().observe(this, tempoRatioObserver);

        return mainFragment;
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

    private final Observer<Double> backswingDurationObserver = new Observer<Double>() {
        @Override
        public void onChanged(Double duration) {
            backswingTimeField.removeTextChangedListener(backswingFieldWatcher);
            DecimalFormat df = new DecimalFormat("0.###",
                    new DecimalFormatSymbols(Locale.getDefault()));
            backswingTimeField.setText(df.format(duration));
            backswingTimeField.addTextChangedListener(backswingFieldWatcher);
            System.out.println("setting backswing text");
        }
    };

    private final Observer<Double> downswingDurationObserver = new Observer<Double>() {
        @Override
        public void onChanged(Double duration) {
            downswingTimeField.removeTextChangedListener(downswingFieldWatcher);
            DecimalFormat df = new DecimalFormat("0.###",
                    new DecimalFormatSymbols(Locale.getDefault()));
            downswingTimeField.setText(df.format(duration));
            downswingTimeField.addTextChangedListener(downswingFieldWatcher);
            System.out.println("setting downswing text");
        }
    };

    private final Observer<Double> tempoRatioObserver = new Observer<Double>() {
        @Override
        public void onChanged(Double duration) {
//            TODO
        }
    };


    private final View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewModel.toggleRunning();
        }
    };

    private final TextWatcher backswingFieldWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            viewModel.forceStop();
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            try {
                Double duration = Double.parseDouble(s.toString());
                viewModel.setBackswingDuration(duration);
            } catch (Exception e) {
                backswingTimeField.setError("Invalid backswing duration");
            }
        }
    };

    private final TextWatcher downswingFieldWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            viewModel.forceStop();
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            try {
                Double duration = Double.parseDouble(s.toString());
                viewModel.setDownswingDuration(duration);
            } catch (Exception e) {
                downswingTimeField.setError("Invalid downswing duration");
            }
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}