package util.ui;

import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import util.MainProcess.MainProcess;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.MainProcess.ProcessResult;

import java.io.File;


public class JsonToIcsConverter extends Application{
    @Override
    public void start(Stage primaryStage) {
        TextField inputPathField = new TextField("拖入 JSON 文件或输入路径");
        // 设置默认输出路径为用户的下载文件夹
        String userHome = System.getProperty("user.home");
        TextField outputPathField = new TextField(userHome + "/Downloads/output.ics");

        Button convertButton = new Button("生成 ICS 文件");
        Button fileChooserButton = new Button("选择文件");

        // 设置拖放功能
        inputPathField.setOnDragOver(event -> {
            if (event.getGestureSource() != inputPathField && event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        inputPathField.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                success = true;
                // 假设拖入了一个文件
                inputPathField.setText(db.getFiles().getFirst().getAbsolutePath());
            }
            event.setDropCompleted(success);
            event.consume();
        });

        fileChooserButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("选择 JSON 文件");
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                inputPathField.setText(file.getAbsolutePath());
            }
        });


        convertButton.setOnAction(event -> {
            try {
                ProcessResult processResult = MainProcess.mainUiArgs(inputPathField.getText(), outputPathField.getText());
                if(processResult.isSuccess()) {
                    showAlert("成功", "ICS 文件已成功生成!");
                }else {
                    showAlert("系统错误", processResult.getMessage());
                }

            } catch (Exception e) {
                showAlert("系统错误", e.getMessage());
            }
        });
        HBox hBox = new HBox(10, inputPathField, fileChooserButton);
        VBox root = new VBox(10,hBox, outputPathField, convertButton);

        Scene scene = new Scene(root, 400, 150);

        primaryStage.setTitle("JSON 到 ICS 转换器");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // 显示成功对话框
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
