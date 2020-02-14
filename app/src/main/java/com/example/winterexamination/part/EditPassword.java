package com.example.winterexamination.Part;

import android.widget.Button;
import android.widget.EditText;

import com.example.winterexamination.Contract.TotalContract;
import com.example.winterexamination.R;

public class EditPassword implements TotalContract.TotalView.EditPass {
    private Button button;
    private EditText editText;

    public EditPassword(Button button, EditText editText) {
        this.button = button;
        this.editText = editText;
        editText.setInputType(129);
        button.setBackgroundResource(R.mipmap.eye_close);
    }

    @Override
    public void show() {
        if (editText.getInputType() == 128){//128是显示，129是隐藏
            button.setBackgroundResource(R.mipmap.eye_open);
        }else {
            button.setBackgroundResource(R.mipmap.eye_close);
        }
    }

    @Override
    public int getInputType() {
        return editText.getInputType();
    }

    @Override
    public void setInputType(int value) {
        editText.setInputType(value);
        show();
    }

    @Override
    public String getContent() {
        return editText.getText().toString();
    }

    @Override
    public void setContent(String string) {
        editText.setText(string);
        show();
    }

    @Override
    public Button getButton() {
        return button;
    }
}
