
import DAO.LikesDAOarray;
import DAO.UsersDAOarray;
import Servlets.ServletFiles;
import Servlets.ServletList;
import Servlets.ServletUser;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.Servlet;
import java.util.UUID;

public class AppRunner {


    public static void main(String[] args) throws Exception {

        //InitializeVirtualDB
        final UsersDAOarray usersDB = new UsersDAOarray();
        final LikesDAOarray likesDB = new LikesDAOarray();
        final UUID currentUser = UUID.randomUUID();

       /* Server s = new Server(8001);
        ServletContextHandler h = new ServletContextHandler();
        ServletUser svUser = new ServletUser(currentUser,usersDB,likesDB);
        ServletList svList = new ServletList(currentUser,usersDB,likesDB);
        ServletHolder svhc = new ServletHolder((Servlet) svUser);
        ServletHolder svhl = new ServletHolder((Servlet) svList);
        h.addServlet(svhc,"/users");
        h.addServlet(svhl,"/list");
        h.addServlet(svhl,"/static/*");
        s.setHandler(h);
        s.start();
        s.join();*/

        new Server(8001) {{
            setHandler(new ServletContextHandler() {{
                           // servlet with freemarker template
                           addServlet(new ServletHolder(new ServletUser(currentUser,usersDB,likesDB)),"/users");
                           // servlet with common exchange entity
                           addServlet(new ServletHolder(new ServletFiles()), "/files/*");
                           // this servlet passes file content to the HTML page, classic way with setting charset
                           addServlet(new ServletHolder(new ServletList(currentUser,usersDB,likesDB)), "/list");
                           // this servlet passes file content to the HTML page not paying attention to charset
                           //addServlet(new ServletHolder(new ServletG()), "/g/*");
                           // this servlet makes file available to download
                           //addServlet(new ServletHolder(new ServletH()), "/h/*");
                           // this servlet makes file available to download
                           //addServlet(new ServletHolder(new ServletR()), "/r/*");
                       }}
            );
            start();
            join();
        }};
    }
}

