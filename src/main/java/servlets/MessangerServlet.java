package servlets;

import DAO.ChatDAO;
import DAO.MessagesDAO;
import models.Messages;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MessangerServlet extends HttpServlet {
    private UUID currentUser;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        //Get current user form cookies
        Cookes cookes = new Cookes();
        currentUser = UUID.fromString(cookes.getCookieValue(req, "U_ID"));
        cookes.updateCookieTime(req, resp, "U_ID");

        MessagesDAO messagesDAO = new MessagesDAO();
        messagesDAO.setCurrentUser(currentUser);
        ChatDAO chatDAO = new ChatDAO();
        chatDAO.setCurrentUser(currentUser);

        //Fill model for FreeMarker - List Generation
        Map<String,Object> model = new HashMap<>();

        model.put("chats", chatDAO.get("(m.messagesuserfromid = '" + currentUser +
                "' and messagesusertoid = '" + req.getParameter("userid")  +
                "') or (m.messagesusertoid = '" + currentUser +
                "' and messagesuserfromid = '" + req.getParameter("userid")  + "') ORDER BY messagesDateTime ASC"));

        model.put("speaktoname",req.getParameter("name"));
        model.put("speaktouserId",req.getParameter("userid"));

        String htmlTemplate = "chat.html";
        FreeMarkerService freeMarkerService = new FreeMarkerService(model, htmlTemplate, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        MessagesDAO messagesDAO = new MessagesDAO();
        //write answer to DB if not exists
        if (req.getParameter("messagetext").length() > 0) {
            messagesDAO.insert(new Messages(currentUser, UUID.fromString(req.getParameter("userid")), req.getParameter("messagetext"), new Timestamp(System.currentTimeMillis())));
        }
        //resp.getWriter().write(req.getParameter("choice"));
        doGet(req,resp);
    }
}
