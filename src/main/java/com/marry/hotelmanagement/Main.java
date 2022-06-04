package com.marry.hotelmanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.h2.tools.Server;
import org.hibernate.loader.ast.spi.Loader;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {

    //Initializing the H2-Server to start the database.
    private static final Server server = new Server();

    public static void main(String[] args) throws SQLException {
        startDatabase();
        System.out.println("Hotel Management");
        launch(args);
        stopDatabase();

    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(loadFxml("/fxml/Primary.fxml"));
        stage.setTitle("HotelManagement");
        stage.setScene(scene);
        stage.show();
    }

    private Parent loadFxml(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
        return loader.load();
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
