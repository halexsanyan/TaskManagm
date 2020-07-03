package servlet;

import manager.TaskManager;
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

@WebServlet(urlPatterns = "/taskList")
public class TaskListServlet extends HttpServlet {


    private TaskManager taskManager = new TaskManager();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Task> allTasks = taskManager.getAllTasksByUserId(user.getId());
        req.setAttribute("tasks", allTasks);
        req.getRequestDispatcher("/WEB-INF/taskList.jsp").forward(req, resp);
    }
}

