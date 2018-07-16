package servlets;

import DAO.LikesDAO;
import DAO.UsersDAO;
import models.Likes;
import models.Users;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UserServlet extends HttpServlet {
    private UUID currentUser;

   /*   @Override
    protected void doGet_Simple(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Writer out = resp.getWriter();
        //resp.getWriter().write("Hello Tinder User");

        resp.getWriter().write(readFileToString(new File("C:\\Users\\vtrapezn\\IdeaProjects\\TinderLand\\src\\main\\java\\Lib\\user_simple.html")));
        ;
    }*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //Get current user form cookies
        Cookes cookes = new Cookes();
        currentUser = UUID.fromString(cookes.getCookieValue(req, "U_ID"));


        //Take first Photo/user
        UsersDAO usersDAO = new UsersDAO();

        int firstLine = 0;
        Users userToModel;
        List<Users> list = usersDAO.get("usersid not in (select distinct likeslikeduserid from likes where likescurrentuserid = '" + currentUser + "')");
        if (list.size()  == 0) {
            resp.sendRedirect("list");
            return;
        } else {
            userToModel = list.get(firstLine);
        }
        //Fill model for FreeMarker
        Map<String,Object> model = new HashMap<>();
        model.put("name",userToModel.getUserFirstName());
        model.put("pictureLink",userToModel.getUserLinkPhoto());
        model.put("id",userToModel.getUserId().toString());
        String htmlTemplate = "like-page.html";
        FreeMarkerService freeMarkerService = new FreeMarkerService(model, htmlTemplate, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LikesDAO likesDAO = new LikesDAO();
        //write answer to DB if not exists
        if (currentUser != null) {
            likesDAO.insert(new Likes(currentUser, UUID.fromString(req.getParameter("id")), req.getParameter("choice")));
        }
        doGet(req,resp);
    }
}
