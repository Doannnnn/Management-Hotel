package controller;


import model.Auth;
import model.Role;
import service.AuthService;
import service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "authController", urlPatterns = "/auth")
@MultipartConfig(
        location = "D:\\Management-Hotel\\src\\main\\webapp\\hotel\\img\\room\\avatar",
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
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
            case "check-login" -> checkLogin(req, resp);

            default -> logout(req, resp);
//            default -> start(req, resp);

        }
    }



    private void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        Auth auth = (Auth) session.getAttribute("auths");
        if (auth != null) {
            // Người dùng đã đăng nhập
            String username = auth.getEmail();
            // Xử lý tương ứng khi người dùng đã đăng nhập
            // Gửi phản hồi thành công (ví dụ: mã HTTP 200)
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println("User is logged in");
        } else {
            // Người dùng chưa đăng nhập, xử lý tương ứng
            // Gửi phản hồi không thành công (ví dụ: mã HTTP 401 - Unauthorized)
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().println("User is not logged in");
        }
    }


    private void showRegisterAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", roleService.getRoles());
        req.setAttribute("message", req.getParameter("message"));
        req.getRequestDispatcher("auth/admin.jsp").forward(req, resp);
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        session.invalidate();
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
            case "edit" -> edit(req, resp);
            default -> login(req, resp);
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String role = getRoleFromSomewhere(req);
        authService.update(getAuthRequest(req),Integer.parseInt(req.getParameter("id")));
        if (role.equals("ADMIN")) {
            resp.sendRedirect(req.getContextPath() + "/admin?id=" + Integer.parseInt(req.getParameter("id")));
        } else if (role.equals("USER")) {
            resp.sendRedirect(req.getContextPath() + "/hotel-page?id="+Integer.parseInt(req.getParameter("id")));
        } else {
            resp.sendRedirect("/auth");
        }
    }




    private String getRoleFromSomewhere(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return (String) session.getAttribute("role");
    }

private Auth getAuthRequest(HttpServletRequest req) throws ServletException, IOException {
    Part part = req.getPart("img");
    String img = extractFileName(part);
    part.write(img);

    String name = req.getParameter("name");
    String email = req.getParameter("email");
    String phone = req.getParameter("phone");
    String address = req.getParameter("address");
    return new Auth(img,name,email,phone,address);
}


    private void registerAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        authService.registerAdmin(getUserByRequestAdmin(req));
        req.getRequestDispatcher("/admin/index.jsp").forward(req,resp);
    }

    private void changepassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        authService.updatePassword(req, resp);
        resp.sendRedirect("/auth?message=change-password Success");
    }

    private void ForgotPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!authService.checkEmail(req, resp)) {
            resp.sendRedirect("/auth?message=Invalid username ");
        } else {
            req.getRequestDispatcher("/auth/change-password.jsp").forward(req, resp);
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        authService.login(req, resp);
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
        Role role = new Role(Integer.parseInt(idRole));
        return new Auth(img, name, email, phone, address, password, role);
    }

    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return null;
    }

    @Override
    public void init() throws ServletException {
        authService = new AuthService();
        roleService = new RoleService();
    }
}
