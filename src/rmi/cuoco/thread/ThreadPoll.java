package rmi.cuoco.thread;

import rmi.cuoco.server.inter.IChat;
import rmi.cuoco.server.object.Message;
import rmi.cuoco.server.object.Utilisateur;

import java.rmi.RemoteException;

public class ThreadPoll extends Thread {
    int pos = 0;
    IChat chat;
    Utilisateur user;
    public ThreadPoll(IChat chat, Utilisateur user) {
        this.chat = chat;
        this.user = user;

    }

    @Override
    public void run()
    {
        while(true)
        {
            try {
                Message message = chat.getMessage(pos);
                if(message != null)
                {
                    pos++;
                    if(user.getId() != message.getUser().getId()) System.out.println(message.getUser().getNom() +" : " + message.getContenu());
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
