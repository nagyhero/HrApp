package com.supportingonline.hrapp.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.supportingonline.hrapp.InterFaces.OnPressView;
import com.supportingonline.hrapp.R;

public class MyProgressDialog extends BottomSheetDialogFragment {

    private OnPressView onPressView;


    public MyProgressDialog(OnPressView onPressView) {
        this.onPressView = onPressView;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.progress_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button cancelButton = view.findViewById(R.id.d_progress_btn);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPressView.onclick(v);
            }
        });

    }
}
