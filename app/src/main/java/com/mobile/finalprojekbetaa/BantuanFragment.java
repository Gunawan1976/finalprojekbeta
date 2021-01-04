package com.mobile.finalprojekbetaa.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mobile.finalprojekbetaa.R;

public class BantuanFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
                View root = inflater.inflate(R.layout.fragment_bantuan, container, false);
        return root;
    }
}