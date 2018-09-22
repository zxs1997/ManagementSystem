package sample.Controller;

import Entity.Students;
import dao.StudentsDAO;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller_content {
    public TableView<Students> stu_tableView;
    public TableColumn stu_name_col;
    public TableColumn stu_number_col;
    public TableColumn stu_sex_col;
    public TableColumn stu_class_col;
    public Button stu_button;

    enum TABLE_SELECTED {
        nothing, stu_info, course_info;
    }

    TABLE_SELECTED table_selected = TABLE_SELECTED.nothing;


    public void show_stuTableView(ActionEvent actionEvent) throws SQLException {

        //表格行 与 bean属性的绑定
        stu_name_col.setCellValueFactory(
                new PropertyValueFactory<Students, String>("name")
        );
        stu_number_col.setCellValueFactory(
                new PropertyValueFactory<Students, String>("number")
        );
        stu_sex_col.setCellValueFactory(
                new PropertyValueFactory<Students, String>("sex")
        );
        stu_class_col.setCellValueFactory(
                new PropertyValueFactory<Students, String>("class_room")
        );
        //此list用于装载数据
        ObservableList<Students> stu_list = FXCollections.observableArrayList();

        StudentsDAO studentsDAO = new StudentsDAO();
        String SQL = "SELECT * FROM stu_info";
        ArrayList<Students> arrayList = studentsDAO.DoQuery(SQL);
        for (Students student : arrayList
        ) {
            stu_list.add(new Students(student.getName(), student.getSex(), student.getClass_room(), student.getNumber()));
        }

        stu_tableView.setItems(stu_list);
        //将当前表格设为学生表
        table_selected = TABLE_SELECTED.stu_info;

    }

    public void show_courseTableView(ActionEvent actionEvent) {
        //将当前表格设为课程表
        table_selected = TABLE_SELECTED.course_info;
    }

    public void insertData(ActionEvent actionEvent) {

        switch (table_selected) {
            case stu_info:
                System.out.println("现在是学生表");
                //弹出插入提示框
                Stage primaryStage = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("views/stu_insert_view.fxml"));
                    primaryStage.setTitle("插入数据");
                    primaryStage.setScene(new Scene(root, 600, 400));
                    primaryStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case course_info:
                System.out.println("现在是课程表");
                break;
            default:
                System.out.println("nothing");
                break;
        }
    }

    public void deleteData(ActionEvent actionEvent) {
        switch (table_selected) {
            case stu_info:
                Students students = stu_tableView.getSelectionModel().getSelectedItem();
                if (students == null){
                    //没选择记录的时候
                    break;
                }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"是否要删除此条记录？",ButtonType.APPLY,ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult()==ButtonType.APPLY){
                    System.out.println(students.getNumber()+"要被删掉了，惨");
                    //执行删除操作
                    StudentsDAO studentsDAO = new StudentsDAO();
                    String number = students.getNumber();
                    try {
                        studentsDAO.DoStuDelete(number);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Alert alert1 = new Alert(Alert.AlertType.NONE,"删除成功！",ButtonType.OK);
                    alert1.showAndWait();
                    //模拟按键被点击：刷新了页面
                    stu_button.fire();
                }


            default:
                break;
        }


    }

}
