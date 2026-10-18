package com.app.designpatterns.singleton;

public class ConnectionPoolManager {

    private static volatile ConnectionPoolManager instance;
    private final String poolId;

    ConnectionPoolManager(){
        this.poolId="POOL-ACTIVE-" + System.currentTimeMillis();;
    }

     public static ConnectionPoolManager getInstance(){
        ConnectionPoolManager localInstance = instance;
         if(instance == null) {
             synchronized (ConnectionPoolManager.class){
                 localInstance = instance = new ConnectionPoolManager();
             }
         }
         return localInstance;
    }

    public void executeQuery(String query) {
        System.out.println("Executing on [" + poolId + "]: " + query);
    }

}
