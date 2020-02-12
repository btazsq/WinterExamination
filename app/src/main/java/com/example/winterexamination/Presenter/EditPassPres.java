package com.example.winterexamination.Presenter;

import android.view.View;

import com.example.winterexamination.Contract.totalContract;
import com.example.winterexamination.Part.EditPassword;

public class EditPassPres implements totalContract.totalPresenter.EditPres {
    EditPassword editPassword;

    public EditPassPres(EditPassword editPassword) {
        this.editPassword = editPassword;
        editPassword.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditPassPres.this.click();
            }
        });
    }

    @Override
    public void click() {
        if (editPassword.getInputType()==128){
            editPassword.setInputType(129);
        }else {
            editPassword.setInputType(128);
        }
    }
}
