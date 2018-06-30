package Servlets;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

public class FilterAuth implements Filter {

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
        if (request.getServletPath().equals("/files") || request.getServletPath().equals("/login")) {
            chain.doFilter(req, resp);
            return;
        }
        Cookes c = new Cookes();
        if (active) {
            if (c.getCookieValue(request,"U_ID") == null) {
                RequestDispatcher rd = req.getRequestDispatcher("login");
                rd.forward(req, resp);
            } else {

                chain.doFilter(req, resp);
            }
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

