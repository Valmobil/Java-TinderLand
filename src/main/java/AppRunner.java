
import DAO.LikesDAO;
import Servlets.*;
import org.eclipse.jetty.server.Authentication;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.FilterMapping;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;
import java.util.UUID;

public class AppRunner {
    public static void main(String[] args) throws Exception {
        final UUID currentUser = null; // = UUID.randomUUID();
        //Clear all likes from DB;
        //final LikesDAO likesDB = new LikesDAO();
        //likesDB.deleteAll(currentUser);

        new Server(8001) {{
            setHandler(new ServletContextHandler() {{
                           addFilter(FilterAuth.class, "/*", EnumSet.of(DispatcherType.REQUEST));

                           // servlet with usere like page
                           addServlet(new ServletHolder(new ServletUser(currentUser)), "/users");
                           // servlet with common exchange entity (CSS, Other files)
                           addServlet(new ServletHolder(new ServletFiles()), "/files/*");
                           // this servlet passed liked list of users
                           addServlet(new ServletHolder(new ServletList(currentUser)), "/list");
                           // this servlet passes messager page
                           addServlet(new ServletHolder(new ServletMessanger(currentUser)), "/msg");
                           // this servlet help to enter credentionals
                           addServlet(new ServletHolder(new ServletLogins(currentUser)), "/login");

                       }}
            );
            start();
            join();
        }};
    }
}

