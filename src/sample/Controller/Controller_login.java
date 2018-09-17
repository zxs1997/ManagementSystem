package sample.Controller;

import Entity.Users;
import dao.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller_login {
    @FXML
    private TextField lg_username;

    @FXML
    private PasswordField lg_password;

    @FXML
    void logIn() {
        String username = lg_username.getText();
        String password = lg_password.getText();
        //先判断有没有输入完整
        if (username.equals("")||password.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"请完整输入用户名与密码");
            alert.showAndWait();
            return;
        }
        UserDAO userDAO = new UserDAO();
        try {
            ArrayList<Users> a = userDAO.DoQuery("SELECT * FROM userinfo");
            for (Users u : a
            ) {
                System.out.println(u.toString() + " " + username + " " + password);
                System.out.println(u.getUsername() + " " + u.getPassword());
                if ((u.getUsername().equals(username)) && (u.getPassword().equals(password))) {
                    System.out.println("登录成功");
                    return;
                }
            }

            //登录失败提示
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "登录失败！请先注册", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK){
                alert.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void register() {
        Stage stage = (Stage) lg_username.getScene().getWindow();
        // Swap screen
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sample/scenes/registerScene.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
