package servletcontrollers;

import databaseentity.JavaEvent;
import databaseentity.JavaEventDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * This servlet acts as a page controller for the application, handling all requests from the user.
 */
public class ControllerServlet  extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private JavaEventDAO javaEventDAO;

    public void init(){
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        javaEventDAO = new JavaEventDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        try {
            switch (action){
                case "/new":
                    showNewForm(req, resp);
                    break;
                case "/insert":
                    insertEvent(req, resp);
                    break;
                case "/delete":
                    deleteEvent(req, resp);
                    break;
                case "/edit":
                    showEditForm(req, resp);
                    break;
                case "/update":
                    updateEvent(req, resp);
                    break;
                default:
                    listEvents(req, resp);
                    break;
            }
        }catch (SQLException ex){
            throw new ServletException(ex);
        }
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("EventForm.jsp");
        dispatcher.forward(req, resp);
    }

    private void insertEvent(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        Date dateOfEvent = Date.valueOf(req.getParameter("dateOfEvent"));

        JavaEvent newJavaEvent = new JavaEvent(title, description, dateOfEvent);
        javaEventDAO.insertJavaEvent(newJavaEvent);
        resp.sendRedirect("list");
    }

    private void deleteEvent(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        JavaEvent event = new JavaEvent(id);
        javaEventDAO.deleteJavaEvent(event);
        resp.sendRedirect("list");
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException,
            ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        JavaEventDAO javaEventDAO = new JavaEventDAO();
        JavaEvent existingEvent = javaEventDAO.getJavaEvent(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/EventForm.jsp");
        req.setAttribute("javaEvent", existingEvent);
        dispatcher.forward(req, resp);
    }

    private void updateEvent(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        Date dateOfEvent = Date.valueOf(req.getParameter("dateOfEvent"));

        JavaEvent event = new JavaEvent(id, title, description, dateOfEvent);
        javaEventDAO.updateJavaEvent(event);
        resp.sendRedirect( "list");
    }

    private void listEvents(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException,
            IOException {
        List<JavaEvent> eventList = javaEventDAO.listAllJavaEvents();
        req.setAttribute("javaEvent", eventList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/JavaEventsList.jsp");
        dispatcher.forward(req, resp);
    }

}
