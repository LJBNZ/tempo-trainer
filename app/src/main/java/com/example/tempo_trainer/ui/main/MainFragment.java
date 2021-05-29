package com.example.tempo_trainer.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.example.tempo_trainer.R;


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

        // cache heavily accessed views
        startButton = mainFragment.findViewById(R.id.startButton);
        backswingTimeField = mainFragment.findViewById(R.id.backswingTime);
        downswingTimeField = mainFragment.findViewById(R.id.downswingTime);
        tempoSlider = mainFragment.findViewById(R.id.tempoSlider);

        return mainFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

}