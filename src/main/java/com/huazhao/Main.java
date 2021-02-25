package com.huazhao;

import com.huazhao.service.DBService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Created with Intellij IDEA
 * Description:
 * User : 花朝
 * Date : 2021-02-24
 * Time : 12:35
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = Main.class.getClassLoader().getResource("app.fxml");
        if(url == null){
            throw new RuntimeException("app.fxml没有找到");
        }
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        primaryStage.setTitle("本地搜索工具");
        primaryStage.setHeight(600);
        primaryStage.setWidth(1000);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        DBService service = new DBService();
        service.init();
        launch(args);
    }

}
