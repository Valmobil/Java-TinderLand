package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import DAO.LoginsDAO;

public class LoginsServlet extends HttpServlet {

    LoginsDAO loginsDAO = new LoginsDAO();
    private UUID currentUser;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        Map<String, Object> model = new HashMap<>();
        //Send Login HTML page using FreeMarker
        String htmlTemplate = "login.html";
        FreeMarkerService freeMarkerService = new FreeMarkerService(model, htmlTemplate, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        LoginsDAO loginsDAO = new LoginsDAO();
        Cookes cookes = new Cookes();
        this.currentUser = loginsDAO.getFromDB(req.getParameter("inputEmail"), req.getParameter("inputPassword"));
        if (this.currentUser == null) {
            //Generate Login HTMP Page
            doGet(req,resp);
        } else {
            //Create cookie with current user Id
            cookes.setCokies(resp,"U_ID", this.currentUser.toString(), 120);
            resp.sendRedirect("/postlogin");
        }
    }
}