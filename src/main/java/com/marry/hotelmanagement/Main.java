package com.marry.hotelmanagement;

import org.h2.tools.Server;

import java.sql.SQLException;

public class Main {

    //Initializing the H2-Server to start the database.
    private static final Server server = new Server();

    public static void main(String[] args) throws SQLException {
        startDatabase();
        System.out.println("Hotel Management");
        stopDatabase();

    }

    /**
     * Description: Stops the H2-Database.
     */
    private static void stopDatabase() {
        server.shutdown();
    }

    /**
     * Description: Starting the H2-Database.
     * @throws SQLException: Database Exception
     */
    public static void startDatabase () throws SQLException {
        server.runTool("-tcp","-web","-ifNotExists");
    }
}
