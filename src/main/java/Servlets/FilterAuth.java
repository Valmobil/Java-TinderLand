package Servlets;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FilterAuth implements Filter {

    //String originalURL;
    private UUID currentUser;
    private FilterConfig filterConfig;
    private Boolean active;
    private String requestLastLink;

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
        HttpServletResponse responce = (HttpServletResponse) resp;
        if (active) {
            //Ignore filter for some servlet calls
            if (request.getServletPath().equals("/files") || request.getServletPath().equals("/login")) {
                chain.doFilter(req, resp);
                return;
            }
            //If login already passed take cookies and plase it to original request
            if (request.getServletPath().equals("/postlogin")) {
                if (this.requestLastLink.equals("/msg")) {
                    responce.sendRedirect("list");
                } else {
                    responce.sendRedirect("users");
                }
            }

            Cookes c = new Cookes();
            if (c.getCookieValue(request, "U_ID") == null) {
                this.requestLastLink = request.getServletPath();
                //this.originalURL = request.getServletPath();
                responce.sendRedirect("login");
                return;
            }
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {
        active = null;
    }
}

