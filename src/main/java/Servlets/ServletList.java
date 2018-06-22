package Servlets;

import DAO.LikesDAOarray;
import DAO.UsersDAO;
import Models.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ServletList extends HttpServlet {
    private UUID currentUser;
    private LikesDAOarray likesDAOarray;

    public ServletList(UUID currentUser, LikesDAOarray likesDAOarray) {
        this.currentUser = currentUser;
        this.likesDAOarray = likesDAOarray;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Fill model for FreeMarker
        Users userToModel;
        List<Users> list = UsersDAO.getWithLikes();
        if (list.size()  == 0) {
            resp.getWriter().write("Have no more users");
            return;
        }

        Map<String,List<Users>> model = new HashMap<>();
        model.put("users",UsersDAO.getWithLikes());
/*          model.put("position",userToModel.getUserPosition());
            model.put("lastlogin", String.valueOf(userToModel.getUserLastLogin()));
            model.put("pictureLink",userToModel.getUserLinkPhoto());
            model.put("id",userToModel.getUserId().toString());*/
        String htmlTemplate = "people-list.html";
        FreeMarkerService freeMarkerService = new FreeMarkerService();
        freeMarkerService.FreeMarkerServiceList(model, htmlTemplate, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
