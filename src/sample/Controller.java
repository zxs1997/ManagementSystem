package sample;

import Entity.Users;
import dao.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {
    @FXML
    private TextField lg_username;

    @FXML
    private PasswordField lg_password;

    @FXML
    void logIn(){
        String username = lg_username.getText();
        String password = lg_password.getText();
        UserDAO userDAO = new UserDAO();
        try {
            ArrayList<Users> a = userDAO.DoQuery("SELECT * FROM userinfo");
            for (Users u:a
            ) {
                System.out.println(u.toString()+" "+username+" "+password);
                System.out.println(u.getUsername()+" "+u.getPassword());
                if ((u.getUsername().equals(username)) && (u.getPassword().equals(password))) {
                    System.out.println("登录成功");
                    return;
                }
            }
            System.out.println("登录失败");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void register(){
        Stage stage = (Stage) lg_username.getScene().getWindow();
        // Swap screen
        try {
            Parent root = FXMLLoader.load(getClass().getResource("scenes/registerScene.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
