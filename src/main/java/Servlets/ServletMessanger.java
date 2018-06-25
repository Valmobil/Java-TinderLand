package Servlets;

import DAO.MessagesDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ServletMessanger extends HttpServlet {
    private UUID currentUser;

    public ServletMessanger(UUID currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MessagesDAO messages = new MessagesDAO();
        messages.setCurrentUser(currentUser);

        //Fill model for FreeMarker - List Generation
        Map<String,Object> model = new HashMap<>();

        model.put("chats", messages.get("WHERE m.messagesuserfromid = " + currentUser +
                "and messagesuserfromid = " + req.getParameter("id")  +
                "ORDER BY messagesDateTime DESC"));

        if (model.get(0)==null) {
            model.remove(0);
        };
        model.put("speakToName",req.getParameter("name"));

        String htmlTemplate = "chat.html";
        FreeMarkerService freeMarkerService = new FreeMarkerService(model, htmlTemplate, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
