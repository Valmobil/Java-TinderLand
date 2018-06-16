package Servlets;

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
    static int index;
    static UUID currentUser = UUID.randomUUID();
    public static void virtualDB() {
        UsersDAO.insert(new Users("Virginia!", "https://vignette.wikia.nocookie.net/9b99c9b5-5597-45bb-97d4-5d7494c0f964/scale-to-width-down/1000"));
        UsersDAO.insert(new Users("!!! Marianna !!!!", "https://images.unsplash.com/photo-1508606572321-901ea443707f?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=aae2a05e1585a697cb891c202e68bb78&auto=format&fit=crop&w=1864&q=80"));
        index = -1;
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
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setDirectoryForTemplateLoading(new File("./src/main/java/Lib"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);

        Map<String,String> model = new HashMap<String, String>();

        if (index < UsersDAO.get -1) {
            index++;
        } else {
            index = 0;
        }


        model.put("name",usersProfiles.get(index).getUserFirstName());
        model.put("pictureLink",usersProfiles.get(index).getUserLinkPhoto());
        model.put("id",usersProfiles.get(index).getUserId().toString());

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
        usersLikes.add(new Likes(currentUser, UUID.fromString(req.getParameter("id")), req.getParameter("choice")));
        //resp.getWriter().write(req.getParameter("choice"));
        doGet(req,resp);
    }
}
