package DBPersistence.FileCompile;

import DBPersistence.FileCompile.ncompiler.CharSequenceCompiler;
import DBPersistence.FileCompile.ncompiler.CharSequenceCompilerException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by lab on 2014/12/12.
 */
public class CompileActionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CompileAction.main(null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (CharSequenceCompilerException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


        String actionName = req.getParameter("actionName");
        String action = req.getParameter("action");
        System.out.println(actionName);
        System.out.println(action);

        PrintWriter out = resp.getWriter();

//        CompileAction compileAction = new CompileAction();
        CharSequenceCompiler<ILogi> compiler =
                new CharSequenceCompiler<ILogi>(this.getClass().getClassLoader(), null);
        Class<ILogi> compiledLogi = null;
        try {
            compiledLogi = compiler.compile("DBPersistence.FileCompile."+actionName,action,null,new Class<?>[]{ILogi.class});
        } catch (CharSequenceCompilerException e) {
            e.printStackTrace();
            out.print(false);
            return;
        }
        ILogi logi = null;
        try {
            logi = compiledLogi.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            out.print(false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            out.print(false);
            return;
        }
        logi.doHold(null,null);
        out.print(true);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
