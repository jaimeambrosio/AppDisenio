/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis.filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jaime Ambrosio
 */
@WebFilter(filterName = "ServletFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
public class ServletFilter implements Filter {

    public ServletFilter() {
    }    

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
     
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
         if (response instanceof HttpServletResponse && request instanceof HttpServletRequest) {
            HttpServletRequest requestTemp = (HttpServletRequest) request;
            HttpServletResponse responseTemp = (HttpServletResponse) response;
            HttpSession session = requestTemp.getSession(false);
            String requestPath = requestTemp.getRequestURI();
            if (requestPath.contains("inicio.jsp")) {
                if (session != null && session.getAttribute("usuarioLogeado") != null) {
                     chain.doFilter(request, response);
                }else{
                    responseTemp.sendRedirect("login.jsp");
                }
            }else
            {
                 chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
    }
    
    
}
