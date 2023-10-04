package controller;


import model.Auth;
import model.Role;
import service.AuthService;
import service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "authController", urlPatterns = "/auth")
public class AuthController extends HttpServlet {
    private AuthService authService;
    private RoleService roleService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "register" -> showRegister(req, resp);
            case "register_admin" -> showRegisterAdmin(req, resp);
//            case "reset-password" -> showForgotPassword(req, resp);
//            case "log out" -> logout(req,resp);
            default -> logout(req,resp);
//            default -> start(req, resp);

        }
    }

    private void start(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("auth/admin.jsp").forward(req, resp);
    }

    private void showRegisterAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", roleService.getRoles());
        req.setAttribute("message", req.getParameter("message"));
        req.getRequestDispatcher("auth/admin.jsp").forward(req,resp);
    }
    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        session.invalidate();
        req.getRequestDispatcher("/auth/login.jsp").forward(req, resp);
    }

//    private void showForgotPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("message", req.getParameter("message"));
//        req.getRequestDispatcher("/auth/login.jsp").forward(req,resp);
//    }

    private void showLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", req.getParameter("message"));
        req.getRequestDispatcher("/auth/login.jsp").forward(req, resp);
    }

    private void showRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", req.getParameter("message"));
        req.getRequestDispatcher("/auth/ForgotPassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "register" -> register(req, resp);
            case "reset-password" -> ForgotPassword(req, resp);
            case "change-password" -> changepassword(req, resp);
            case "register_admin" -> registerAdmin(req, resp);
            default -> login(req, resp);
        }
    }
    private void registerAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        authService.registerAdmin(getUserByRequestAdmin(req));
        resp.sendRedirect("/hotel?message=Register Success");
    }

    private void changepassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        authService.updatePassword(req, resp);
        resp.sendRedirect("/auth?message=change-password Success");
    }

    private void ForgotPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!authService.checkEmail(req, resp)) {
            resp.sendRedirect("/auth?message=Invalid username ");
        }else{
            req.getRequestDispatcher("/auth/change-password.jsp").forward(req, resp);
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (!authService.login(req, resp)) {
            resp.sendRedirect("/auth?message=Invalid username or password");
        }
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        authService.register(getUserByRequest(req));
        resp.sendRedirect("/auth?message=Register Success");
    }

    private Auth getUserByRequest(HttpServletRequest req) {
        String img = req.getParameter("img");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String password = req.getParameter("password");
        return new Auth(img, name, email, phone, address, password);
    }
    private Auth getUserByRequestAdmin(HttpServletRequest req) {
        String img = req.getParameter("img");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String password = req.getParameter("password");
        String idRole = req.getParameter("role");
        Role role = new Role (Integer.parseInt(idRole));
        return new Auth(img, name, email, phone, address, password,role);
    }

    @Override
    public void init() throws ServletException {
        authService = new AuthService();
        roleService=new RoleService();
    }
}