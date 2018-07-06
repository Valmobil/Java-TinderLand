package Servlets;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import DAO.LoginsDAO;

public class ServletLogins extends HttpServlet {

    LoginsDAO loginsDAO = new LoginsDAO();
    private UUID currentUser;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        Map<String, Object> model = new HashMap<>();

        //Send form using FreeMarker
        String htmlTemplate = "login.html";
        FreeMarkerService freeMarkerService = new FreeMarkerService(model, htmlTemplate, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        LoginsDAO loginsDAO = new LoginsDAO();
        this.currentUser = loginsDAO.getFromDB(req.getParameter("inputEmail"), req.getParameter("inputPassword"));
        if (this.currentUser == null) {
            //Generate form in Get
            doGet(req,resp);
        } else {
            //Create cookie with current user Id
            Cookie cookie = new Cookie("U_ID", this.currentUser.toString());
            cookie.setMaxAge(120);
            resp.addCookie(cookie);
            //RequestDispatcher rd = req.getRequestDispatcher("postlogin");
            //rd.forward(req, resp);
            resp.sendRedirect("/postlogin");
        }
    }
}