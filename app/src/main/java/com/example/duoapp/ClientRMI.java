package com.example.duoapp;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;

import lipermi.handler.CallHandler;
import lipermi.net.Client;

public class ClientRMI extends AsyncTask<Void, Void, MainActivity> {


    public ClientRMI() throws IOException {

        CallHandler callHandler = new CallHandler();

        Client clientApp = new Client("localhost", 15015, callHandler);

        IServerRMI inter = (IServerRMI) clientApp.getGlobal(IServerRMI.class);

        ArrayList<String> aS = inter.obtindreCursos();

        clientApp.close();


    }

    @Override
    protected MainActivity doInBackground(Void... voids) {
        return null;
    }
}
