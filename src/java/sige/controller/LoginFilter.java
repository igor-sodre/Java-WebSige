/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sige.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sige.modelo.entidade.Usuario;


public class LoginFilter implements Filter {

    private final static String FILTER_APPLIED = "_security_filter_applied";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hreq = (HttpServletRequest) request;
        HttpServletResponse hresp = (HttpServletResponse) response;
        HttpSession session = hreq.getSession();

        String paginaAtual = new String(hreq.getRequestURL());

        if (request.getAttribute(FILTER_APPLIED) == null
                && paginaAtual != null && (!paginaAtual.endsWith("login.xhtml"))) {
            request.setAttribute(FILTER_APPLIED, Boolean.TRUE);

            Usuario usuarioLogado = null;
            if (session.getAttribute("usuarioLogado") != null) {
                usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
            }

            if ((usuarioLogado == null) || (usuarioLogado.getId() == null)) {
                hresp.sendRedirect("login.xhtml");
                return;
            }
        }
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
