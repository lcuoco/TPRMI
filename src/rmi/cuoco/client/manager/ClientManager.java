package rmi.cuoco.client.manager;

import rmi.cuoco.client.Main;
import rmi.cuoco.server.inter.ICallbackListenner;
import rmi.cuoco.server.inter.IChat;
import rmi.cuoco.server.object.Message;
import rmi.cuoco.server.object.Utilisateur;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ClientManager {
    int compteur = -1;
    IChat chat = null;
    List<Message> messages = new ArrayList<>();

    public ClientManager() {
        init();
    }

    public Remote init()
    {
        try {
            ICallbackListenner callbackListenner = new Main();

            LocateRegistry.getRegistry(9002);
            this.chat = (IChat) Naming.lookup("rmi://localhost:9002/chat");
            this.chat.register(callbackListenner);
            this.messages = chat.getChat();

        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
        return this.chat;
    }

    /**
     * deprecated
     * @param user
     * @throws RemoteException
     */
    public void afficherChat(Utilisateur user) throws RemoteException {
        this.messages = this.chat.getChat();
        this.messages.forEach( message ->
        {
            if(messages.indexOf(message) > compteur)
            {
                if(user.getId() != message.getUser().getId()) System.out.println(message.getUser().getNom() + " : " +  message.getContenu());
                compteur = messages.indexOf(message);
            }
        });
    }

    public void addMessage(Message message) throws RemoteException {
        this.chat.addMessage(message);

    }

    public void getMessage() throws RemoteException {
        this.messages = chat.getChat();
    }

    public int firstConnexion()
    {
        return this.chat.firstConnexion();
    }

    public Object getInstance() {
        return this.chat;
    }
}
