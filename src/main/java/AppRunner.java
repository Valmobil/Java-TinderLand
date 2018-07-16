
import servlets.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class AppRunner {
    public static void main(String[] args) throws Exception {
        int firstArg = 8001;
        if (args.length > 0) {
            firstArg = Integer.parseInt(args[0]);
        }
        new Server(firstArg) {{
        //new Server(8001) {{
            setHandler(new ServletContextHandler() {{
                           addFilter(FilterAuth.class, "/*", EnumSet.of(DispatcherType.REQUEST));

                           // servlet with usere like page
                           addServlet(new ServletHolder(new UserServlet()), "/users");
                           // servlet with common exchange entity (CSS, Other files)
                           addServlet(new ServletHolder(new FilesServlet()), "/files/*");
                           // this servlet passed liked list of users
                           addServlet(new ServletHolder(new ListServlet()), "/list");
                           // this servlet passes messager page
                           addServlet(new ServletHolder(new MessangerServlet()), "/msg/*");
                           // this servlet help to enter credentionals
                           addServlet(new ServletHolder(new LoginsServlet()), "/login");

                       }}
            );
            start();
            join();
        }};
    }
}

