package sample.Controller;

import Entity.Students;
import dao.StudentsDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;

public class Controller_content {
    public TableView<Students> stu_tableView;
    public TableColumn stu_name_col;
    public TableColumn stu_number_col;
    public TableColumn stu_sex_col;
    public TableColumn stu_class_col;


    public void show_stuTableView(ActionEvent actionEvent) throws SQLException {

        //表格行 与 bean属性的绑定
        stu_name_col.setCellValueFactory(
                new PropertyValueFactory<Students,String>("name")
        );
        stu_number_col.setCellValueFactory(
                new PropertyValueFactory<Students,String>("number")
        );
        stu_sex_col.setCellValueFactory(
                new PropertyValueFactory<Students,String>("sex")
        );
        stu_class_col.setCellValueFactory(
                new PropertyValueFactory<Students,String>("class_room")
        );
        //此list用于装载数据
        ObservableList<Students> stu_list = FXCollections.observableArrayList();

        StudentsDAO studentsDAO = new StudentsDAO();
        String SQL = "SELECT * FROM stu_info";
        ArrayList<Students> arrayList = studentsDAO.DoQuery(SQL);
        for (Students student:arrayList
             ) {
            stu_list.add(new Students(student.getName(),student.getSex(),student.getClass_room(),student.getNumber()));
        }

        stu_tableView.setItems(stu_list);
        System.out.println("显示数据");
    }
}
