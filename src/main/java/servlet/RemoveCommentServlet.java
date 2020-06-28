package servlet;

import manager.CommentManager;
import model.Comment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/removeComment")
public class RemoveCommentServlet extends HttpServlet {
    private CommentManager commentManager = new CommentManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int commentId = Integer.parseInt(id);
        Comment comment = commentManager.getCommentById(commentId);
        int taskId = comment.getTask().getId();
        commentManager.deleteCommentById(commentId);
        resp.sendRedirect("/taskDetail?taskId=" + taskId);
    }
}
