package com.anpilogoff.controller.servlets;

import com.anpilogoff.model.dao.UserDAO;
import com.anpilogoff.model.entity.User;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@MultipartConfig
public class MusicUploadServlet extends HttpServlet {
    private Gson gson;
    private UserDAO dao;

    @Override
    public void init() throws ServletException {
        gson = new Gson();
        dao = new UserDAO();



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("song");

        User user = gson.fromJson((JsonElement) req.getSession(false).getAttribute("user"), User.class);

        Path path = Paths.get("E:\\restweb\\src\\main\\webapp\\dynamic\\sounds\\users\\" + user.getNickname());
        if (!Files.exists(path)){

            Files.createDirectory(path);
            System.out.println("dir created");
        }

        InputStream is = part.getInputStream();

        FileOutputStream fos = new FileOutputStream(new File(path + File.separator + part.getSubmittedFileName()));


        int read = 0;
        final byte[] bytes = new byte[1024];

        while ((read = is.read(bytes)) != -1) {
            fos.write(bytes, 0, read);
        }





        byte[] buffer = new byte[1024];

        while (is.read(buffer) != -1) {
            fos.write(buffer);
        }
        fos.flush();
       boolean isSuccessfully =  dao.uploadSong(user.getNickname(), part.getSubmittedFileName());
        System.out.println(isSuccessfully);

        File file = new File("E:\\restweb\\src\\main\\webapp\\dynamic\\sounds\\users\\" + user.getNickname());
        File [] songs = file.listFiles();
        req.getSession(false).setAttribute("songs", songs);
        resp.sendRedirect(req.getServletContext().getContextPath() + "/userhome");

    }

}