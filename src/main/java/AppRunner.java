
import Servlets.ServletUser;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.Servlet;
import java.util.UUID;

public class AppRunner  {


    public static void main(String[] args) throws Exception{

        ServletUser.virtualDB();

        Server s = new Server(8001);
        ServletContextHandler h = new ServletContextHandler();
        ServletUser svUser = new ServletUser();
        ServletHolder svh = new ServletHolder((Servlet) svUser);
        h.addServlet(svh,"/users");
        s.setHandler(h);
        s.start();
        s.join();
    }
}
