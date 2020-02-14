package com.example.winterexamination.Contract;

import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

import com.example.winterexamination.View.MainView.SurfAdapter;

import java.util.List;

public interface TotalContract {

    interface Surf extends TotalContract {

        interface SurfBar extends Surf{
            void setContext(SurfView view);
            void setWhichPage(int num)throws Exception;
        }

        interface SurfView extends Surf{
            int getWhichPage();
            void setWhichPage(int whichPage)throws Exception;
            RecyclerView getRe();
            public SurfAdapter getAdapter();
            void getData();
        }

    }

    interface TotalModel extends TotalContract {
        boolean add(String data);
        List load()throws Exception;
    }
    interface TotalView extends TotalContract {
        void show();

        interface EditPass extends TotalView {
            int getInputType();
            void setInputType(int value);
            String getContent();
            void setContent(String string);
            Button getButton();
        }

        interface StartView extends TotalView {
            EditText[] getEdit();
            void failShow();
            void show(String mes,String take);
        }

        interface DataUpView extends TotalView {
            void failShow();
        }

    }
    interface TotalPresenter extends TotalContract {

        interface DataUpPresenter extends TotalPresenter {
            void upLoad();
            void setData(String a,String b);
            void setData(int a,int b,int c);
            void setData(String image[]);
        }

        interface EditPres extends TotalPresenter {
            void click();
        }
        interface StartPres extends TotalPresenter {
            void start();
        }
    }
}
