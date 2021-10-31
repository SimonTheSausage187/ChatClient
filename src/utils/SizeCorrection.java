package utils;

import clientGUI.ClientGUIFrame;

public class SizeCorrection extends Thread{
    ClientGUIFrame cl = null;

    public SizeCorrection(ClientGUIFrame cl){
        this.cl = cl;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true){
            if (cl.chatText.getHeight() > cl.mainPanel.getHeight() - cl.userInputText.getHeight()) {
                cl.chatText.setSize(cl.chatText.getWidth(), cl.mainPanel.getHeight() - cl.userInputText.getHeight());
            }
        }
    }
}
