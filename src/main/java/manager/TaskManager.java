package manager;

import db.DBConnectionProvider;
import model.Task;
import model.TaskStatus;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public UserManager userManager = new UserManager();

    public Task getById(int id) {
        String sql = "SELECT * FROM task WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {

                return getTaskFromResaltset(resultSet);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean create(Task task) {
        String sql = "INSERT INTO task(name,description,deadline,status,user_id) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement prstatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prstatement.setString(1, task.getName());
            prstatement.setString(2, task.getDescription());
            if (task.getDeadline() != null) {
                prstatement.setString(3, sdf.format(task.getDeadline()));
            } else {
                prstatement.setString(3, null);
            }
            prstatement.setString(4, task.getTaskStatus().name());
            prstatement.setInt(5, task.getUserID());
            prstatement.executeUpdate();

            ResultSet rs = prstatement.getGeneratedKeys();
            if (rs.next()) {
                task.setId(rs.getInt(1));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void update(int id, String status) {
        String sql = "UPDATE task SET status = '" + status + "' WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean delete(int id) {
        String sql = "DELETE FROM task WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<Task> getAllTasksByUserId(int userId) {
        List<Task> tasks = new ArrayList<Task>();
        String sql = "SELECT * FROM task WHERE user_id=?";
        try {
            PreparedStatement prstatement = connection.prepareStatement(sql);
            prstatement.setInt(1, userId);
            ResultSet resultSet = prstatement.executeQuery();
            while (resultSet.next()) {
                tasks.add(getTaskFromResaltset(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<Task>();
        String sql = "SELECT * FROM task ";
        try {
            PreparedStatement prstatement = connection.prepareStatement(sql);
            ResultSet resultSet = prstatement.executeQuery();
            while (resultSet.next()) {
                tasks.add(getTaskFromResaltset(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    private Task getTaskFromResaltset(ResultSet resultSet) {

        try {
            try {
                Task task =new Task();
                task.setId(resultSet.getInt(1));
                task.setName(resultSet.getString(2));
                task.setDescription(resultSet.getString(3));
                task.setDeadline(resultSet.getString(4) == null ? null : sdf.parse(resultSet.getString(4)));
                task.setTaskStatus(TaskStatus.valueOf(resultSet.getString(5)));
                task.setUserID(resultSet.getInt(6));
                task.setUser(userManager.getById(task.getUserID()));
                return task;
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
