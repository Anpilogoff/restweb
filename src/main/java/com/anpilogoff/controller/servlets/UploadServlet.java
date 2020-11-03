package com.anpilogoff.controller.servlets;

import com.anpilogoff.model.dao.Dao;
import com.anpilogoff.model.dao.UserDAO;
import com.anpilogoff.model.entity.User;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import lombok.SneakyThrows;
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
import java.nio.file.attribute.FileAttribute;


/**That servlet is use to get uploading from html page img/* avatar file, create directory with current user nickname,
 * create avatar file in that directory, write bytes in it and insert this file name to database for further loading
 * during "home.html" page rendering
 * @see MultipartConfig annotation gives opportunity to get uploading file without special decoding using
 * @see Part class object
 */

@MultipartConfig
public class UploadServlet extends HttpServlet {
    private Dao dao;
    private Gson gson;
    private Logger log = Logger.getLogger(UploadServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("img/jpg");
        System.out.println("GETT");
//
//        Path path = Paths.get("E:\\restweb\\src\\main\\webapp\\resources" +"\\" +req.getSession(false).getAttribute("avatar"));
//        byte[]bytes = Files.(path..
//        byte[]bbytes = com.sun.xml.internal.messaging.saaj.util.Base64.encode(bytes);
//        System.out.println(Arrays.toString(bbytes));
//      String g = new String(bbytes, "UTF-8");
//        int xxx = bytes.length;
//        req.getSession(false).setAttribute("bytes",g);
//        resp.setContentLength(xxx);
//        resp.getWriter().write(g,0,g.length());

        //Осталось совсеееем чучуть и будет эта аватарина подгружаться, неслип девелопмент Ё
    }

    @Override
    public void init() {
        dao = new UserDAO();
        gson = new Gson();
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        User user = gson.fromJson((JsonElement) req.getSession(false).getAttribute("user"), User.class);
        /**
         * user content-storing-directory variable initialized with "Path"-object returned as result of calling
         * @see Paths#get(URI) method and in success case will represent content-directory of current user session */
        //todo rewrite path on Jelastic disk space path
        Path userDir = Paths.get("E:\\restweb\\src\\main\\webapp\\dynamic\\images\\avatars\\" + user.getNickname());
        Path tempDir = Paths.get("E:\\restweb\\src\\main\\webapp\\dynamic\\images\\avatars\\" + user.getNickname());


        /**
         * @see Part - class represents a part or form item that was received within a multipart/form-data POST request
             */
            Part part = req.getPart("avatar");

        /**
         * Variable represents uploaded file name, inserted in data base and setted as current session attribute
         */
        String uploadedFile = null;
        File avatar_file;

        /**
         * That method use to create directory/(if not exists) for current user's images represents as
         * @see Path variable initialized with a result of
         * @see Paths#get(Uri iri) method with income string argument such as:
         * string "E:\\restweb\\src\\main\\webapp\\dynamic\\images\\avatars\\" + "nickname" of current request  user
         * Then method creates and initialize object of future file-system file representing through
         * @see File class object with income string value  argument obtained on previous step and then with a help of
         * @see Files class
         * @see Files#createFile(Path, FileAttribute[]) method - creates an EMPTY-file in file-system...
         * After file has been created - method get InputStream from
         * @see Part class object using it's method
         * @see Part#getInputStream(),
         * create OutputStream with injected as argument previously created new EMPTY-file, wrap that streams to
         * BufferedIn/Output streams and exucute it's bytes writing..
         * After streams closing - current user's nickname and uploaded file are using as argument in
         * @see UserDAO#uploadPhoto(String, String) method which returnable result initialize  previous declared
         * string variable "uploadedFile" which will be set as sessiaon attribute
         */
        if (!Files.exists(userDir)) {
            Files.createDirectory(userDir);
            log.info("User directory successfully created:  " + userDir);
            avatar_file = new File(userDir + File.separator + part.getSubmittedFileName());
            Files.createFile(Paths.get(String.valueOf(avatar_file.toPath())));
            System.out.println("avatar File:   "+ avatar_file);

            log.info("Empty avatar file successfully created:  " + avatar_file);

//            File file = new File(tempDir + File.separator + part.getSubmittedFileName());
//            Files.createFile(file.toPath());

//            System.out.println(file);


            System.out.println("temp file crated and marked to for deleting on exit");
         //   log.info("Temporary file successfully created: " + tempfile.getAbsolutePath());

            InputStream is = new BufferedInputStream(part.getInputStream());
            FileOutputStream bos = new FileOutputStream(avatar_file);
//            FileOutputStream boos = new FileOutputStream(file); //tempfile


            byte[] bytes = new byte[1024];
            while (is.read(bytes) != -1) {
                bos.write(bytes);
//                boos.write(bytes);
            }

//            boos.flush();
            bos.flush();

           // Files.copy(avatar_file.toPath(),new FileOutputStream(tempfile));


            log.info("bytes successfully wrote to file:  " + avatar_file);

            uploadedFile = dao.uploadPhoto(user.getNickname(), part.getSubmittedFileName());


            is.close();
//            boos.close();
            bos.close();

            System.out.println("upload dao method worked correctly:  dao methods!!!");

            System.out.println("upload method warker successfully");
            System.out.println("photo uploaded...user has been redirected to login.html");


            req.getSession(false).setAttribute("avatar", uploadedFile);
            resp.sendRedirect(req.getServletContext().getContextPath() + "/home");


//           path.toFile().deleteOnExit();

//            if(!File.createTempFile(userTempDir)){
//                Files.createDirectory(userTempDir);
//                log.info("User'temporary directory successfully created: " + userTempDir);
//
//                File temp_file = new File(userTempDir + File.separator + part.getSubmittedFileName());
//                Files.copy(avatar_file.toPath(),new FileOutputStream(temp_file));
//
//               // Files.createFile(Paths.get(String.valueOf(temp_file)));
//                log.info("Empty avatar temporary file successully created: " + temp_file );
//                System.out.println(temp_file);
//                System.out.println("file has been copied");
//
//                JsonElement userJson = (JsonElement) req.getSession(false).getAttribute("user");
//                JsonElement profileJson = (JsonElement) req.getSession(false).getAttribute("profile");
//                JsonElement credentials = (JsonElement) req.getSession(false).getAttribute("credentials");
//
//                req.getSession(false).invalidate();
//                HttpSession session = req.getSession(true);
//
//                User userFromSession = gson.fromJson(userJson, User.class);
//
//                session.setAttribute("user", userJson);
//                session.setAttribute("profile", profileJson);
//                session.setAttribute("credentials",credentials);
//
//
//                //req.getRequestDispatcher("/home").forward(req,resp);
        }


//
//            BufferedInputStream iss = new BufferedInputStream(part.getInputStream());
//            BufferedOutputStream boos = new BufferedOutputStream(new FileOutputStream(temp_file));
//========================================================================================================
//        uploadedFile = dao.uploadPhoto(user.getNickname(), part.getSubmittedFileName());
//
//
//        System.out.println("photo uploaded...user has been redirected to login.html");
//
//        req.getSession(false).setAttribute("avatar", uploadedFile);
//        Thread.sleep(3500);
//        resp.sendRedirect(req.getServletContext().getContextPath() + "/home");


//        if(uploadedFile.length()>0  ){
//            resp.setContentType("img/*");
//
//            req.getSession(false).setAttribute("avatar",uploadedFile);
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }


    }
}
