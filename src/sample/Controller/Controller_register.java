package sample.Controller;

import Entity.Users;
import dao.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller_register {


    public TextField reg_username;
    
    public TextField reg_email;
    public PasswordField reg_password;
    public PasswordField reg_password2;

    @FXML
    public void registerConfirm(ActionEvent actionEvent) {
        System.out.println(reg_username.getText()+" "+
                reg_password.getText()+" "+reg_password2.getText()+" "+" "+
                reg_email.getText());
        //先判断是否输入完整
        if (reg_username.getText().equals("")||reg_password.getText().equals("")||reg_password2.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.NONE,"请输入用户名与密码", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        //再判断两次密码是否一样
        if (!reg_password.getText().equals(reg_password2.getText())){
            Alert alert = new Alert(Alert.AlertType.NONE,"两次密码不相同", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        UserDAO userDAO = new UserDAO();
        try {
            ArrayList<Users> a = userDAO.DoQuery("SELECT * FROM userinfo");
            for (Users u:a
                 ) {
                if (u.getUsername().equals(reg_username.getText())){
                    Alert alert = new Alert(Alert.AlertType.NONE,"该用户名已经被注册", ButtonType.OK);
                    alert.showAndWait();
                    return;
                }
            }
            userDAO.DoRegInsertion(reg_username.getText(),reg_password.getText(),reg_email.getText());
            Alert alert = new Alert(Alert.AlertType.NONE,"注册成功！",ButtonType.OK);
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnLogin(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        // Swap screen
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sample/scenes/LogInPage.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
