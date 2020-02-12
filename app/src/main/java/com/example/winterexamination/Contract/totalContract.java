package com.example.winterexamination.Contract;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.winterexamination.View.MainView.SurfAdapter;

import org.json.JSONObject;

import java.util.List;

public interface totalContract {

    interface Surf extends totalContract{

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

    interface totalModel extends totalContract{
        boolean add(String data);
        List load()throws Exception;
    }
    interface totalView extends totalContract{
        void show();

        interface EditPass extends totalView{
            int getInputType();
            void setInputType(int value);
            String getContent();
            void setContent(String string);
            Button getButton();
        }

        interface StartView extends totalView {
            EditText[] getEdit();
            void failShow();
            void show(String mes,String take);
        }

        interface DataUpView extends totalView{
            void failShow();
        }

    }
    interface totalPresenter extends totalContract{

        interface DataUpPresenter extends totalPresenter{
            void upLoad();
            void setData(String a,String b);
            void setData(int a,int b,int c);
            void setData(String image[]);
        }

        interface EditPres extends  totalPresenter{
            void click();
        }
        interface StartPres extends totalPresenter{
            void start();
        }
    }
}
