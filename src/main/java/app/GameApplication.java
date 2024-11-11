package app;

import view.MainFrame;

public class GameApplication{
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame(new PlayGameUseCaseFactory().createController());
        mainFrame.start();
    }
}
