package DBPersistence.SqlCombined;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by lab on 2014/12/12.
 */
public class SqlActionServlet extends HttpServlet {
    String driverName = "com.mysql.jdbc.Driver";
    String Url = "jdbc:mysql://localhost:3306/dmail";
    String username = "root";
    String password = "12345";
    CombinedDemo demo = CombinedDemo.getInstance();

    {
        try {
            demo.setConnection(demo.getConnection(Url,username,password,driverName));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Do get");
        String sql1 = req.getParameter("sql1");
        String sql2 = req.getParameter("sql2");

        String[] insertUserSql = new String[]{sql1,sql2};
        Object[][] userValue = new Object[][]{new Object[]{"user1","test","SYSU"},new Object[]{"good1",12,"user1"}};

        boolean result1 = demo.executeBatchTemplate(insertUserSql,userValue);

        resp.getWriter().print(result1);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void destroy() {

    }
}
