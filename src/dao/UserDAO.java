package dao;

import Entity.Users;
import utils.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {


    public ArrayList<Users> DoQuery(String SQL) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        connection = DBHelper.getConnection();
        //新建ArrayList用来返回结果
        ArrayList<Users> arrayList = new ArrayList<>();
        //开始执行SQL语句,并返回结果
        try {
            preparedStatement = connection.prepareStatement(SQL);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //处理返回的结果
        while (resultSet.next()){
            Users user = new Users();
            user.setId(resultSet.getInt("userid"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            arrayList.add(user);
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

    public void DoRegInsertion(String username, String password, String email) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        connection = DBHelper.getConnection();
        String SQL = "INSERT INTO userinfo(username,password,email) VALUES ('"+
                username+"','"+password+"','"+email+"');";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            Boolean result = preparedStatement.execute();
            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
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

    }

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        ArrayList<Users> a = new ArrayList<>();
        try {
            a = userDAO.DoQuery("SELECT * FROM userinfo");
            for (Users u:a
                 ) {
                System.out.println(u.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
