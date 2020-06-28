package manager;

import db.DBConnectionProvider;
import model.Comment;
import model.Task;
import model.User;
import model.UserType;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CommentManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private UserManager userManager = new UserManager();
    private TaskManager taskManager = new TaskManager();

    public void addComment(Comment comment) {
        try {
            String sql = "INSERT INTO comment(task_id,user_id,comment,date) VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, comment.getTask().getId());
            preparedStatement.setInt(2, comment.getUser().getId());
            preparedStatement.setString(3, comment.getComment());
            preparedStatement.setString(4, sdf.format(comment.getDate()));

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                comment.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    public List<Comment> getAllCommentsByUserId(int userId) {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT * FROM comment WHERE user_id=?";
        try {
            PreparedStatement prstatement = connection.prepareStatement(sql);
            prstatement.setInt(1, userId);
            ResultSet resultSet = prstatement.executeQuery();
            while (resultSet.next()) {
                comments.add(getCommentFromResaltset(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }
    public List<Comment> getAllCommentsByTaskId(int taskId) {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT * FROM comment WHERE task_id=?";
        try {
            PreparedStatement prstatement = connection.prepareStatement(sql);
            prstatement.setInt(1, taskId);
            ResultSet resultSet = prstatement.executeQuery();
            while (resultSet.next()) {
                comments.add(getCommentFromResaltset(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT * FROM comment";
        try {
            PreparedStatement prstatement = connection.prepareStatement(sql);
            ResultSet resultSet = prstatement.executeQuery();
            while (resultSet.next()) {
                comments.add(getCommentFromResaltset(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    public void deleteCommentById(int commentId) {
        String sql = "DELETE FROM comment WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, commentId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Comment getCommentById(int commentId) {
        String sql = " SELECT * FROM comment WHERE id  = " +commentId;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {

                return getCommentFromResaltset(resultSet);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Comment getCommentFromResaltset(ResultSet resultSet) {
            try {
                return Comment.builder()
                        .id(resultSet.getInt(1))
                        .task(taskManager.getById(resultSet.getInt(2)))
                        .user(userManager.getById(resultSet.getInt(3)))
                        .comment(resultSet.getString(4))
                        .date(sdf.parse(resultSet.getString(5)))
                        .build();
            } catch (SQLException | ParseException e) {
                e.printStackTrace();
            }
        return null;
    }
}