package com.example.winterexamination.presenter;

import android.view.View;

import com.example.winterexamination.contract.TotalContract;
import com.example.winterexamination.part.EditPassword;

public class EditPassPres implements TotalContract.TotalPresenter.EditPres {
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
