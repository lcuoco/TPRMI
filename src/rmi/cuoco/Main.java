package rmi.cuoco;


import rmi.cuoco.server.object.Message;
import rmi.cuoco.server.object.Utilisateur;
import rmi.cuoco.server.inter.IChat;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        IChat chat;
            try {
                LocateRegistry.getRegistry(9002);
                chat = (IChat) Naming.lookup("rmi://localhost:9002/chat");
                chat.addMessage(new Message("hello", new Utilisateur("1", "Lucas")));
                List<Message> test=  chat.getChat();


                if(test.size() != 0) test.forEach( t -> System.out.println(t.getContenu()));

            } catch (NotBoundException | MalformedURLException | RemoteException e) {
                e.printStackTrace();
            }
        }

}
