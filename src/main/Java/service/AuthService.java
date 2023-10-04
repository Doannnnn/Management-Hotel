package service;


import dao.AuthDao;
import model.Auth;
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

    public AuthService() {
        authDao = new AuthDao();
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

    public boolean login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String usernameOrEmail = req.getParameter("username");
        String password = req.getParameter("password");
        var auth = authDao.findByUsernameOrEmail(usernameOrEmail);
        if (auth != null && PasswordEncryptionUtil.checkPassword(password, auth.getPassword())) {
            HttpSession session = req.getSession();
            session.setAttribute("auth", auth); // luu vo kho
            if (auth.getRole().getName().equals("ADMIN")) {
                session.setAttribute("auth",getAuth("username") );
                req.getRequestDispatcher("/admin/room.jsp").forward(req,resp);
//                Chuyển hướng trang admin
            } else {
                //Chuyển hướng trang người dùng
                session.setAttribute("auth",getAuth("username") );
                req.getRequestDispatcher("/hotel/").forward(req,resp);
            }
            return true;
        }
        return false;
    }

    public boolean checkEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        boolean emailExists = authDao.checkEmailExists(email);
        if (emailExists) {
            // Email tồn tại, chuyển hướng đến trang thay đổi mật khẩu
            req.getRequestDispatcher("/auth/change-password.jsp");
        } else {
            // Email không tồn tại, hiển thị thông báo lỗi
            RequestDispatcher dispatcher = req.getRequestDispatcher("/auth/ForgotPassword.jsp");

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
}
