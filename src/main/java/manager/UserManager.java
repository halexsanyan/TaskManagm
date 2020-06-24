package manager;


import db.DBConnectionProvider;
import model.User;
import model.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public User getById(int id) {
        String sql = "SELECT * FROM user WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {

                return getUserFromResaltset(resultSet);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addUser(User user) {
        try {
            String sql="INSERT INTO user(name,surname,email,password,type,picture_url) VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getSurname());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.setString(5,user.getUserType().name());
            preparedStatement.setString(6,user.getPictureUrl());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys=preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                user.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public User getByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email =?";
        try {
            PreparedStatement prstatement = connection.prepareStatement(sql);
            prstatement.setString(1, email);
            ResultSet resultSet = prstatement.executeQuery();
            if (resultSet.next()) {

                return getUserFromResaltset(resultSet);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM user WHERE email =? AND password=?";
        try {
            PreparedStatement prstatement = connection.prepareStatement(sql);
            prstatement.setString(1, email);
            prstatement.setString(2, password);
            ResultSet resultSet = prstatement.executeQuery();
            if (resultSet.next()) {

                return getUserFromResaltset(resultSet);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try {
            PreparedStatement prstatement = connection.prepareStatement(sql);
            ResultSet resultSet = prstatement.executeQuery();
            while (resultSet.next()) {
                users.add(getUserFromResaltset(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void removeUserById(int id) {
        String sql = "DELETE FROM user WHERE id=" + id;
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private User getUserFromResaltset(ResultSet resultSet) {
        try {
            return User.builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString(2))
                    .surname(resultSet.getString(3))
                    .email(resultSet.getString(4))
                    .password(resultSet.getString(5))
                    .userType(UserType.valueOf(resultSet.getString(6)))
                    .pictureUrl(resultSet.getString(7))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
