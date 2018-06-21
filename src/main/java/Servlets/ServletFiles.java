package Servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class ServletFiles extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // set encoding for output BEFORE getting writer
        resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        // output
        PrintWriter wr = resp.getWriter();
        // take the path from the command line (HttpServletRequest)
        String url = req.getPathInfo();

        if (url!=null) {
            // declare file in 'assets' subfolder
            File f = new File("./src/main/java/lib/templates", url);
            // input
             BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(f), String.valueOf(StandardCharsets.UTF_8)));
                // now copying
                String s; while ((s = br.readLine()) != null) wr.print(s);
                // close all
                br.close();
            } catch (FileNotFoundException e) {
                // file not found
                wr.print(String.format("given file name '%s' not found",f.getAbsoluteFile().toString()));
            } catch (IOException e) {
                // something strange happened
                wr.print(String.format("strange thing is happened while reading the file'%s'\n",f.getAbsoluteFile().toString()));
            }
        } else {
            wr.print("you should pass the file name after slash");
        }
        // close writer
        wr.close();
    }
}
