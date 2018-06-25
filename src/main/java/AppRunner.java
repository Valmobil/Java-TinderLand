
import DAO.LikesDAO;
import Servlets.ServletFiles;
import Servlets.ServletList;
import Servlets.ServletMessanger;
import Servlets.ServletUser;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.UUID;

public class AppRunner {
    public static void main(String[] args) throws Exception {
        final UUID currentUser = UUID.randomUUID();
        //Clear all likes from DB;
        final LikesDAO likesDB = new LikesDAO();
        likesDB.deleteAll(currentUser);

        new Server(8001) {{
            setHandler(new ServletContextHandler() {{
                           // servlet with usere like page
                           addServlet(new ServletHolder(new ServletUser(currentUser)),"/users");
                           // servlet with common exchange entity (CSS, Other files)
                           addServlet(new ServletHolder(new ServletFiles()), "/files/*");
                           // this servlet passed liked list of users
                           addServlet(new ServletHolder(new ServletList(currentUser)), "/list");
                           // this servlet passes messager page
                           addServlet(new ServletHolder(new ServletMessanger(currentUser)), "/msg");
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

