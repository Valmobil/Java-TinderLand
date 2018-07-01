package Servlets;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

public class FilterAuth implements Filter {

    String originalURL;
    private UUID currentUser;
    private FilterConfig filterConfig;
    private Boolean active;


    @Override
    public void init(FilterConfig filterConfig
    ) throws ServletException {
        this.filterConfig = filterConfig;
        if (active == null)
            active = true;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (active) {
            //Ignore filter for some servlet calls
            if (request.getServletPath().equals("/files") || request.getServletPath().equals("/login")) {
                chain.doFilter(req, resp);
                return;
            }
            //If login already passed take cookies and plase it to original request
            if (request.getServletPath().equals("/postlogin")) {
                RequestDispatcher rd = request.getRequestDispatcher(this.originalURL);
                rd.forward(request, resp);
                return;
            }
            Cookes c = new Cookes();
            if (c.getCookieValue(request, "U_ID") == null) {
                this.originalURL = request.getServletPath();
                RequestDispatcher rd = req.getRequestDispatcher("login");
                rd.forward(req, resp);
                return;
            }
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {
        active = null;
    }

    public UUID getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UUID currentUser) {
        this.currentUser = currentUser;
    }

}

