package dao;

import Entity.Students;
import utils.DBHelper02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentsDAO {

    public ArrayList<Students> DoQuery(String SQL) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        connection = DBHelper02.getConnection();
        //新建ArrayList用来返回结果
        ArrayList<Students> arrayList = new ArrayList<>();
        //开始执行SQL语句,并返回结果
        try {
            preparedStatement = connection.prepareStatement(SQL);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //处理返回的结果
        while (resultSet.next()){
            /*Students students = new Students(resultSet.getString("stu_name"),
                    resultSet.getString("stu_sex"),
                    resultSet.getString("stu_class"),
                    resultSet.getString("stu_number"));*/

            Students students = new Students();
            students.setName(resultSet.getString("stu_name"));
            students.setSex(resultSet.getString("stu_sex"));
            students.setClass_room(resultSet.getString("stu_class"));
            students.setNumber(resultSet.getString("stu_number"));
            arrayList.add(students);
        }
        //关闭连接
        if (connection != null) {
            connection.close();
            connection = null;
        }
        if (preparedStatement != null) {
            preparedStatement.close();
            preparedStatement = null;
        }
        return arrayList;
    }

    public static void main(String[] args) throws SQLException {
        StudentsDAO dao = new StudentsDAO();
        ArrayList<Students> a = dao.DoQuery("SELECT * FROM stu_info");
        for (Students s:a
             ) {
            System.out.println(s.toString());
        }
    }


}
