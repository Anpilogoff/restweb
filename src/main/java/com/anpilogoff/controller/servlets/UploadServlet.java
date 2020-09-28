package com.anpilogoff.controller.servlets;

import com.anpilogoff.model.dao.Dao;
import com.anpilogoff.model.dao.UserDAO;
import com.anpilogoff.model.entity.User;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.parser.JSONParser;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        //to do rewrite path on Jelastic disk space path

        User user = gson.fromJson((JsonElement) req.getSession(false).getAttribute("user"), User.class);
        System.out.println(user);


        Path userDir = Paths.get("E:\\restweb\\src\\main\\webapp\\dynamic\\images\\avatars\\" + user.getNickname());

        Part part = req.getPart("avatar");
        String f_name = part.getSubmittedFileName();

        if (!Files.exists(userDir)) {
            Files.createDirectory(userDir);
            log.info("directory successfully created:  " + userDir);


            File avatar_file = new File(userDir + "\\" + part.getSubmittedFileName());
            Files.createFile(Paths.get(String.valueOf(avatar_file)));
            log.info("empty avatar file successfully created" + avatar_file);

            BufferedInputStream is = new BufferedInputStream(part.getInputStream());
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(avatar_file));

            final byte[] bytes = new byte[1024];
            long x = System.nanoTime();
            while (is.read(bytes) != -1) {
                bos.write(bytes);
            }
            long y = System.nanoTime();
            System.out.println(x-y);

            bos.close();
            is.close();

            String uploadedFile = dao.uploadPhoto(user.getNickname(),f_name);
            log("bytes successfully wrote to file:  " + avatar_file);
            System.out.println(uploadedFile + " from upload servlet IF part");
           if(uploadedFile.length()>0){
               System.out.println("photo UPLOADED");
               System.out.println(uploadedFile);
               req.getSession(false).setAttribute("avatar_name",uploadedFile);
               resp.setContentType("img/*");
               resp.sendRedirect(req.getServletContext().getContextPath() + "/home");
           }

        }
    }
}