package servlet;

import manager.CommentManager;
import manager.TaskManager;
import manager.UserManager;
import model.Comment;
import model.Task;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/addComment")
public class AddCommentServlet extends HttpServlet {

   // private UserManager userManager = new UserManager();
    private TaskManager taskManager = new TaskManager();
    private CommentManager commentManager = new CommentManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String comment = req.getParameter("comment");
        int taskId = Integer.parseInt(req.getParameter("taskId"));
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Task task = taskManager.getById(taskId);

        commentManager.addComment(Comment.builder()
                .task(task)
                .user(user)
                .comment(comment)
                .date(new Date())
                .build());
        resp.sendRedirect("/taskDetail?taskId="+taskId);

    }
}

