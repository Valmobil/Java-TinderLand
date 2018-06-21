package Servlets;

import DAO.LikesDAOarray;
import DAO.UsersDAOarray;
import Models.Users;

import javax.jws.soap.SOAPBinding;
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
    private UsersDAOarray usersDAOarray;
    private LikesDAOarray likesDAOarray;

    public ServletList(UUID currentUser, UsersDAOarray usersDAOarray, LikesDAOarray likesDAOarray) {
        this.currentUser = currentUser;
        this.usersDAOarray = usersDAOarray;
        this.likesDAOarray = likesDAOarray;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Fill model for FreeMarker
        Users userToModel;
        List<Users> list = usersDAOarray.getWithLikes();
        if (list.size()  == 0) {
            resp.getWriter().write("Have no more users");
            return;
        }

        Map<String,List<Users>> model = new HashMap<>();
        model.put("users",usersDAOarray.getWithLikes());
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
