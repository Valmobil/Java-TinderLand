package Servlets;

import DAO.LikesDAO;
import DAO.UsersDAO;
import Models.Likes;
import Models.Users;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import static org.apache.commons.io.FileUtils.openOutputStream;
import static org.apache.commons.io.FileUtils.readFileToString;

public class ServletUser extends HttpServlet {
    static int index = -1;
    static UUID currentUser = UUID.randomUUID();
    UsersDAO usersDAO = new UsersDAO();
    LikesDAO likesDAO = new LikesDAO();
 /*   @Override
    protected void doGet_Simple(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Writer out = resp.getWriter();
        //resp.getWriter().write("Hello Tinder User");

        resp.getWriter().write(readFileToString(new File("C:\\Users\\vtrapezn\\IdeaProjects\\TinderLand\\src\\main\\java\\Lib\\user_simple.html")));
        ;


    }*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setDirectoryForTemplateLoading(new File("./src/main/java/Lib"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);


        Map<String,String> model = new HashMap<String, String>();

        if (index < usersDAO.getUsersSize() - 1) {
            index++;
        } else {
            index = 0;
        }

        Users userToModel = usersDAO.get(index);

        model.put("name",userToModel.getUserFirstName());
        model.put("pictureLink",userToModel.getUserLinkPhoto());
        model.put("id",userToModel.getUserId().toString());

        Template template = cfg.getTemplate("user_form.ftlh");
        Writer out = resp.getWriter();

        try {
            template.process(model,out);
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //write answer to DB
        likesDAO.insert(new Likes(currentUser, UUID.fromString(req.getParameter("id")), req.getParameter("choice")));
        //resp.getWriter().write(req.getParameter("choice"));
        doGet(req,resp);
    }
}
