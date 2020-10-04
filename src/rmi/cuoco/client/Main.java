package rmi.cuoco.client;


import rmi.cuoco.client.manager.ClientManager;
import rmi.cuoco.server.inter.ICallbackListenner;
import rmi.cuoco.server.object.Message;
import rmi.cuoco.server.object.Utilisateur;
import rmi.cuoco.server.inter.IChat;
import rmi.cuoco.client.thread.ThreadPoll;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Main  extends UnicastRemoteObject implements ICallbackListenner {
ClientManager clientManager;
static Utilisateur utilisateurCourant;

    public Main() throws RemoteException {
    }

    public static void main(String[] args) throws RemoteException {
            ClientManager clientManager = new ClientManager();


            Scanner sc = new Scanner(System.in);
            System.out.println("Entrez votre pseudo : ");
            int id = clientManager.firstConnexion();
            utilisateurCourant = new Utilisateur( id, sc.nextLine());
//            ThreadPoll tp = new ThreadPoll((IChat) clientManager.getInstance(), user);
//            tp.start();
            System.out.println("Connexion du client : " + utilisateurCourant.getId());
            while(true)
            {
                clientManager.addMessage(new Message(sc.nextLine(), utilisateurCourant));


            }


        }

    @Override
    public void getMessage(Message message) throws RemoteException {
        if(utilisateurCourant.getId() != message.getUser().getId())System.out.println(message.getUser().getNom() +" : " + message.getContenu());
    }
}
