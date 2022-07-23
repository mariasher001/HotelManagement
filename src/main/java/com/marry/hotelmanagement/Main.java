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
    private static Scene scene;

    public static void main(String[] args) throws SQLException {
        startDatabase();
        System.out.println("Hotel Management");
        launch(args);
        stopDatabase();

    }

    /**
     * Description: Changes the Root of Scenes.
     *
     * @param fxml
     * @throws IOException
     */
    public static void setScene(String fxml) throws IOException {
        scene.setRoot(loadFxml(fxml));
    }

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(loadFxml("Primary.fxml"));
        stage.setTitle("HotelManagement");
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFxml(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/" + fxml));
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
     *
     * @throws SQLException: Database Exception
     */
    public static void startDatabase() throws SQLException {
        server.runTool("-tcp", "-web", "-ifNotExists");
    }
}
