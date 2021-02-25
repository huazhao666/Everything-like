package com.huazhao.ui;

import com.huazhao.model.FileMeta;
import com.huazhao.service.FileService;
import com.huazhao.task.FileScannerSingle;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created with Intellij IDEA
 * Description:
 * User : 花朝
 * Date : 2021-02-24
 * Time : 12:40
 */
public class AppController implements Initializable {
    @FXML
    public GridPane rootPane;
    @FXML
    public Label srcDirectory;
    @FXML
    public TextField searchField;
    @FXML
    public TableView<FileMeta> fileTable;
    @FXML
    public TableColumn<FileMeta,String> nameColumn;
    @FXML
    public TableColumn<FileMeta,String> sizeColumn;
    @FXML
    public TableColumn<FileMeta,String> lastModifiedColumn;

    private final FileService fileService = new FileService();
    private final FileScannerSingle fileScanner = new FileScannerSingle();
    @FXML
    public void choose(MouseEvent mouseEvent) {
        DirectoryChooser chooser = new DirectoryChooser();
        File root = chooser.showDialog(rootPane.getScene().getWindow());
        if(root == null){
            return;
        }
        Thread thread = new Thread(() ->{
            fileScanner.scan(root);
        });
        thread.start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StringProperty stringProperty = searchField.textProperty();
        stringProperty.addListener((observable, oldValue, newValue) -> {
            System.out.println("old:|" + oldValue + "|");
            System.out.println("new: |" + newValue + "|");

            List<FileMeta> fileList = fileService.queryName(newValue.trim());
            Platform.runLater(() ->{
                fileTable.getItems().clear();
                fileTable.getItems().addAll(fileList);
            });
        });
    }
}
