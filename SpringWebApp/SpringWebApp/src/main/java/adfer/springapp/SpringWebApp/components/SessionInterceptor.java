package adfer.springapp.SpringWebApp.components;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        
        HttpSession session = request.getSession();
        String currentUrl = request.getRequestURI();

        if(session.getAttribute("employee")!=null ){
            @SuppressWarnings("unchecked")
            //The cast type is safe, since pagesList´s type is List<String>
            List<String> accessPages= (List<String>) session.getAttribute("pagesList");
            if (!response.isCommitted() && !accessPages.contains(currentUrl)) {
                response.sendRedirect(accessPages.get(0));
            }
        }
        else{
            if(!response.isCommitted() && !currentUrl.equals("/login")){
                response.sendRedirect("/login");
            }
        }

    }

}



