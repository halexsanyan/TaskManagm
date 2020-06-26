package servlet;


import manager.CommentManager;
import manager.TaskManager;
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
import java.util.List;

@WebServlet(urlPatterns = "/taskDetail")
public class TaskDetailServlet extends HttpServlet {
    private TaskManager taskManager = new TaskManager();
    private CommentManager commentManager = new CommentManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String taskId = req.getParameter("taskId");
        int id = Integer.parseInt(taskId);

       // HttpSession session = req.getSession();
       // User user = (User) session.getAttribute("user");
        //Task task=(Task) session.getAttribute("task");
        Task taskById =taskManager.getById(id);
        List<Comment> comments=commentManager.getAllCommentsByTaskId(id);
        req.setAttribute("taskDetail",taskById);
        req.setAttribute("taskComments", comments);
        req.getRequestDispatcher("/WEB-INF/taskDetail.jsp").forward(req,resp);
    }
}
