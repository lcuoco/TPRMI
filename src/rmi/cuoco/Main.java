package rmi.cuoco;


import rmi.cuoco.server.manager.ClientManager;
import rmi.cuoco.server.object.Message;
import rmi.cuoco.server.object.Utilisateur;
import rmi.cuoco.server.inter.IChat;
import rmi.cuoco.thread.ThreadPoll;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Main {
ClientManager clientManager;

    protected Main() throws RemoteException {
    }

    public static void main(String[] args) throws RemoteException {
        ClientManager clientManager = new ClientManager();


            Scanner sc = new Scanner(System.in);
            System.out.println("Entrez votre pseudo : ");
            int id = clientManager.firstConnexion();
            Utilisateur user = new Utilisateur( id, sc.nextLine());
            ThreadPoll tp = new ThreadPoll((IChat) clientManager.getInstance(), user);
            tp.start();
            System.out.println("Connexion du client : " + user.getId());
            while(true)
            {
                clientManager.addMessage(new Message(sc.nextLine(), user));


            }


        }

}
