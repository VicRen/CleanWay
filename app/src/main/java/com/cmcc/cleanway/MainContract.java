package com.cmcc.cleanway;

public class MainContract {

    public interface View {

    }

    public interface Presenter {

        void takeView(View view);

        void destroy();

        void buttonClicked();
    }
}
