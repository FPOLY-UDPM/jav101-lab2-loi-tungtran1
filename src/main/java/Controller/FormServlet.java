package Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/form/update", "/form/create"})
public class FormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // ⚙️ Dữ liệu mẫu ban đầu khi mở form
        Map<String, Object> map = new HashMap<>();
        map.put("fullname", "Nguyễn Văn Tèo");
        map.put("gender", true);
        map.put("country", "VN");

        req.setAttribute("user", map);
        req.getRequestDispatcher("/form/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String uri = req.getRequestURI();

        // 🧩 Lấy dữ liệu từ form
        String fullname = req.getParameter("fullname");
        String gender = req.getParameter("gender");
        String country = req.getParameter("country");

        // 🗃️ Gói dữ liệu lại vào map
        Map<String, Object> map = new HashMap<>();
        map.put("fullname", fullname);
        map.put("gender", gender);
        map.put("country", country);
        req.setAttribute("user", map);

        // 🧭 Điều hướng
        if (uri.contains("/form/update")) {
            req.setAttribute("capnhat", "Update success!");
            req.getRequestDispatcher("/form/form.jsp").forward(req, resp);
        } 
        else if (uri.contains("/form/create")) {
            // Chuyển sang trang themmoi.jsp
        	req.setAttribute("caller", "update");
        	req.getRequestDispatcher("/form/themmoi.jsp").forward(req, resp);
        }
    }
}
