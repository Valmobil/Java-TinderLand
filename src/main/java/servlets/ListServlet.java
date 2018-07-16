package servlets;

import DAO.UsersDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ListServlet extends HttpServlet {
    UsersDAO usersDAO = new UsersDAO();
    private UUID currentUser;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get current user form cookies
        Cookes cookes = new Cookes();
        currentUser = UUID.fromString(cookes.getCookieValue(req, "U_ID"));
        //Fill model for FreeMarker
        //Users userToModel;
/*        List<Users> list = usersDAO.get("");
        if (list.size()  == 0) {
            resp.getWriter().write("Have no more users");
            return;
        }*/

        Map<String,Object> model = new HashMap<>();
        model.put("users",usersDAO.get("usersid in (select distinct likeslikeduserid from likes where likescurrentuserid = '" + currentUser + "' and likesvalue = 'Like')"));
/*          model.put("position",userToModel.getUserPosition());
            model.put("lastlogin", String.valueOf(userToModel.getUserLastLogin()));
            model.put("pictureLink",userToModel.getUserLinkPhoto());
            model.put("id",userToModel.getUserId().toString());*/
        String htmlTemplate = "people-list.html";
        FreeMarkerService freeMarkerService = new FreeMarkerService(model, htmlTemplate, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
