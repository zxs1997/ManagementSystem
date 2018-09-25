package sample.Controller;

import Entity.Students;
import dao.StudentsDAO;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;

public class Controller_stu_update {
    public TextField stu_update_name;
    public TextField stu_update_number;
    public TextField stu_update_sex;
    public TextField stu_update_class;

    public void stu_update_confirm(ActionEvent actionEvent) {
        //防止输入空值
        if (stu_update_name.getText().equals("")&&stu_update_number.getText().equals("")&&stu_update_sex.getText().equals("")
                &&stu_update_class.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.NONE,"请输入数据", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        //更新数据
        StudentsDAO studentsDAO = new StudentsDAO();
        ArrayList<Students> arrayList = null;
        try {
            studentsDAO.DoStuUpdate(stu_update_name.getText(),stu_update_number.getText(),stu_update_sex.getText(),
                    stu_update_class.getText());
            //提示更新成功
            Alert alert = new Alert(Alert.AlertType.NONE,"更新成功！",ButtonType.OK);
            alert.showAndWait();
            //关闭更新数据窗口
            Stage stage =  (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            stage.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void stu_update_return(ActionEvent actionEvent) {
        Stage stage =  (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
