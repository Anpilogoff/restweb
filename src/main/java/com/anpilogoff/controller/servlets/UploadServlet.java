package com.anpilogoff.controller.servlets;

import com.anpilogoff.model.dao.Dao;
import com.anpilogoff.model.dao.UserDAO;
import com.anpilogoff.model.entity.User;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.xml.XmlConfiguration;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URL;
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
    public void init() {
        dao = new UserDAO();
        gson = new Gson();
    }


    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        User user = gson.fromJson((JsonElement) req.getSession(false).getAttribute("user"), User.class);

        /**
         * user content-storing-directory variable initialized with "Path"-object returned as result of calling
         * @see Paths#get(URI) method and in success case will represent content-directory of current user session */
         //todo rewrite path on Jelastic disk space path
         Path userDir = Paths.get("E:\\restweb\\src\\main\\webapp\\dynamic\\images\\avatars\\" + user.getNickname());

        /**
         * @see Part - class represents a part or form item that was received within a multipart/form-data POST request
         */
        Part part = req.getPart("avatar");

        /**
         * Variable represents uploaded file name, inserted in data base and setted as current session attribute
         */
        String uploadedFile = null;


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
            log.info("directory successfully created:  " + userDir);

            File avatar_file = new File(userDir + File.separator + part.getSubmittedFileName());
            Files.createFile(Paths.get(String.valueOf(avatar_file)));
            log.info("empty avatar file successfully created" + avatar_file);

            BufferedInputStream is = new BufferedInputStream(part.getInputStream());
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(avatar_file));

            final byte[] bytes = new byte[4*1024];



            //long x = System.nanoTime();
            while (is.read(bytes) != -1) {
                bos.write(bytes);
            }
            //long y = System.nanoTime();


            bos.close();
            is.close();
            log.info("bytes successfully wrote to file:  " + avatar_file);

            uploadedFile = dao.uploadPhoto(user.getNickname(),part.getSubmittedFileName());
            System.out.println("photo uploaded...user has been redirected to login.html");
            req.getSession(false).setAttribute("avatar", avatar_file);

            resp.sendRedirect(req.getServletContext().getContextPath() + "/home");
            }

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
