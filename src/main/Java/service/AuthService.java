package service;


import dao.AuthDao;
import model.Auth;
import service.dto.Page;
import util.PasswordEncryptionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuthService {
    private AuthDao authDao;
    private BookingService bookingService;

    public AuthService() {
        authDao = new AuthDao();
        bookingService = new BookingService();
    }

    public void register(Auth auth) {
        auth.setPassword(PasswordEncryptionUtil.encryptPassword(auth.getPassword()));
        authDao.register(auth);
    }
    public void registerAdmin(Auth auth) {
        auth.setPassword(PasswordEncryptionUtil.encryptPassword(auth.getPassword()));
        authDao.registerAdmin(auth);
    }

    public Auth getAuth(String email) {
        List<Auth> auths=authDao.getAllAuth();
        return auths.stream().filter(auth -> auth.getEmail().equals(email)).findFirst().orElse(null);
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String usernameOrEmail = req.getParameter("username");
        String password = req.getParameter("password");
        var auth = authDao.findByUsernameOrEmail(usernameOrEmail);
        if (auth != null && PasswordEncryptionUtil.checkPassword(password, auth.getPassword())) {
            HttpSession session = req.getSession();
//            session.setAttribute("isLoggedIn", true);
                session.setAttribute("auth",auth);
            if (auth.getRole().getName().equals("ADMIN")) {
                resp.sendRedirect(req.getContextPath() + "/admin?id="+ auth.getId());
            } else {
                resp.sendRedirect(req.getContextPath() + "/hotel-page?id="+ auth.getId());
            }
        }

    }


    public boolean checkEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        boolean emailExists = authDao.checkEmailExists(email);
        if (emailExists) {
            // Email tồn tại, chuyển hướng đến trang thay đổi mật khẩu
            req.getRequestDispatcher("/auth/change-password.jsp");
        } else {
            // Email không tồn tại, hiển thị thông báo lỗi
            req.getRequestDispatcher("/auth/ForgotPassword.jsp");

        }
        return emailExists;
    }

    public void updatePassword(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String encryptedPassword = PasswordEncryptionUtil.encryptPassword(password);
        if (authDao.checkEmailExists(email)) {
            authDao.updatePassword(email, encryptedPassword);
            req.getRequestDispatcher("/auth/login.jsp");
        } else {
            req.getRequestDispatcher("/auth/change-password.jsp");
        }
    }
    public Auth findByID(int id){
        return authDao.findByID(id);
    }
    public Auth findAll(){
        return authDao.findAll();
    }
    public Auth findByNameAndEmail(String name,String email){
        return authDao.findByUsernameAndEmail(name,email);
    }

    public void update(Auth auth, int id){
        auth.setId(id);
        authDao.update(auth);
    }
    public Page<Auth> findAllPage(int page, String search){
        return authDao.findAllPage(page, search);
    }
}


