package com.anpilogoff.controller.servlets;

import com.anpilogoff.model.dao.Dao;
import com.anpilogoff.model.dao.UserDAO;
import com.anpilogoff.model.entity.User;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**Servlet will use to get file from html form, write it on disk space(directory) and insert uploaded file name to DB
 * 
 */
@MultipartConfig
public class UploadServlet extends HttpServlet {
    private Dao dao;
    private Gson gson;
    private Logger log = null;


    @Override
    public void init() {
        dao = new UserDAO();
        gson = new Gson();
        log = Logger.getLogger(UploadServlet.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /** user content-storing-directory variable initialized with "Path"-object returned as result of calling
         * @see Paths#get(URI) method and in success case will represent content-directory of current user session */
        //todo rewrite path on Jelastic disk space path

        User user = gson.fromJson((JsonElement) req.getSession(false).getAttribute("user"), User.class);
        Path userDir = Paths.get("E:\\restweb\\src\\main\\webapp\\dynamic\\images\\avatars\\" + user.getNickname());
        System.out.println("E:\\restweb\\src\\main\\webapp\\dynamic\\images\\avatars\\" + user.getNickname());


        Part part = req.getPart("avatar");
        String f_name = part.getSubmittedFileName();
        String uploadedFile = null;

        if (!Files.exists(userDir)) {
            Files.createDirectory(userDir);
            log.info("directory successfully created:  " + userDir);

            File avatar_file = new File(userDir + File.separator + part.getSubmittedFileName());
            Files.createFile(Paths.get(String.valueOf(avatar_file)));
            log.info("empty avatar file successfully created" + avatar_file);

            BufferedInputStream is = new BufferedInputStream(part.getInputStream());
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(avatar_file));

            final byte[] bytes = new byte[4*1024];
          //  long x = System.nanoTime();
            while (is.read(bytes) != -1) {
                bos.write(bytes);
            }
          //  long y = System.nanoTime();
          //  System.out.println(x-y);
            bos.flush();
            bos.close();
            is.close();

            log.info("bytes successfully wrote to file:  " + avatar_file);

            uploadedFile = dao.uploadPhoto(user.getNickname(),f_name);
            }

        if(uploadedFile.length()>0  ){
            resp.setContentType("img/jpeg");

            req.getSession(false).setAttribute("avatar",uploadedFile);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getServletContext().getContextPath() + "/home");
        }
    }
}