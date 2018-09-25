package sample.Controller;

import Entity.Students;
import dao.StudentsDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DBHelper02;

import java.sql.SQLException;
import java.util.ArrayList;

public class Controller_stu_insert {

    public TextField stu_insert_name;
    public TextField stu_insert_number;
    public TextField stu_insert_sex;
    public TextField stu_insert_class;


    public void stu_insert_return(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void stu_insert_confirm(ActionEvent actionEvent) {
        //防止输入空值
        if (stu_insert_name.getText().equals("") && stu_insert_number.getText().equals("") && stu_insert_sex.getText().equals("")
                && stu_insert_class.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.NONE, "请输入数据", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        //先防止插入重复的数据
        StudentsDAO studentsDAO = new StudentsDAO();
        String SQL = "SELECT * FROM stu_info";
        ArrayList<Students> arrayList = null;
        try {
            arrayList = studentsDAO.DoQuery(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Students student : arrayList
        ) {
            if (student.getName().equals(stu_insert_name.getText()) && student.getNumber().equals(stu_insert_number.getText())
                    && student.getSex().equals(stu_insert_sex.getText()) && student.getClass_room().equals(stu_insert_class.getText())) {
                Alert alert = new Alert(Alert.AlertType.NONE, "该学生已经存在！", ButtonType.OK);
                alert.showAndWait();
                return;
            }
        }
        //插入数据
        try {
            studentsDAO.DoStuInsertion(stu_insert_name.getText(), stu_insert_number.getText(), stu_insert_sex.getText(),
                    stu_insert_class.getText());
            //提示插入成功
            Alert alert = new Alert(Alert.AlertType.NONE, "插入成功！", ButtonType.OK);
            alert.showAndWait();
            //关闭插入数据窗口
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
