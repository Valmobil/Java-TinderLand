package Servlets;

import DAO.LikesDAOarray;
import DAO.UsersDAO;
import Models.Likes;
import Models.Users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.apache.commons.io.FileUtils.openOutputStream;
import static org.apache.commons.io.FileUtils.readFileToString;

public class ServletUser extends HttpServlet {
    private UUID currentUser;
    private UsersDAO usersDAO;
    private LikesDAOarray likesDAO;

    public ServletUser(UUID currentUser, LikesDAOarray likesDAO) {
        this.currentUser = currentUser;
        this.likesDAO = likesDAO;
    }
   /*   @Override
    protected void doGet_Simple(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Writer out = resp.getWriter();
        //resp.getWriter().write("Hello Tinder User");

        resp.getWriter().write(readFileToString(new File("C:\\Users\\vtrapezn\\IdeaProjects\\TinderLand\\src\\main\\java\\Lib\\user_simple.html")));
        ;
    }*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Take first Photo/user
        UsersDAO usersDAO = new UsersDAO();

        int firstLine = 0;
        Users userToModel;
        List<Users> list = usersDAO.get();
        if (list.size()  == 0) {
            //ServletList.doGet(req,resp);
            RequestDispatcher rd = req.getRequestDispatcher("list");
            rd.forward(req,resp);
            //getServletContext().getNamedDispatcher("/users").forward(req, resp);
            return;
        } else {
            userToModel = list.get(firstLine);
        }
        //Fill model for FreeMarker
        Map<String,String> model = new HashMap<String, String>();
        model.put("name",userToModel.getUserFirstName());
        model.put("pictureLink",userToModel.getUserLinkPhoto());
        model.put("id",userToModel.getUserId().toString());
        String htmlTemplate = "user_form.ftlh";
        FreeMarkerService freeMarkerService = new FreeMarkerService(model, htmlTemplate, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //write answer to DB
        likesDAO.insert(new Likes(currentUser, UUID.fromString(req.getParameter("id")), req.getParameter("choice")));
        //resp.getWriter().write(req.getParameter("choice"));
        doGet(req,resp);
    }
}
