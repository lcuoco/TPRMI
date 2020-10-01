package rmi.cuoco.thread;

import rmi.cuoco.server.inter.IChat;
import rmi.cuoco.server.object.Message;
import rmi.cuoco.server.object.Utilisateur;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.List;

public class ThreadPoll extends Thread {
    int length = 0;
    IChat chat;
    public ThreadPoll() {
        try {
            LocateRegistry.getRegistry(9002);
            this.chat = (IChat) Naming.lookup("rmi://localhost:9002/chat");
            this.length =  this.chat.getChat().size();
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        while(true)
        {
            int size = 0;
            try {
                size = this.chat.getChat().size();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            if(size > length)
            {

            }
        }
    }
}
