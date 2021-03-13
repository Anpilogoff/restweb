package com.anpilogoff.controller.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;


public class MusicStreamServlet extends HttpServlet {


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("poskhugyufytdt");
        File file = new File(req.getServletContext().getRealPath("/dynamic/sounds/music"));
        File[] files = file.listFiles();
        //позже сделать красиво в виде "ArtistName : SongName" <-- JsonElement
     JsonArray names = new JsonArray();

//                names.putAll(files);
        for(File cur : files){
            names.add(cur.getName());
        }

        for(Object x  : names){
            System.out.println(x);
        }





        String x = new Gson().toJson(names);


        System.out.println("FILES:  " + files.length);






        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");


        System.out.println("xxxxxxxxxxxxxxxx");

        PrintWriter out =  resp.getWriter();
        out.write(x);

        out.flush();
        out.close();
    };
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String songName = (String) req.getAttribute("song");


        System.out.println("gettt");

        //File file =  dao.searhAudio(); =>

        File file2 = new File(req.getServletContext().getRealPath( "/dynamic/sounds/music/" + req.getSession(false).getAttribute("songName")));
        System.out.println("FILE??????????????????? ->>>>>   " + file2.isFile()  );

        System.out.println(req.getSession(false).getAttribute("songName"));
        System.out.println(req.getSession(false).getAttribute("songName").toString());
        String songName = req.getSession(false).getAttribute("songName").toString();
        System.out.println(songName);

        System.out.println("from musci stream serv     " + songName);



        File file = new File(req.getServletContext().getRealPath("/dynamic/sounds/music/" + songName));
        System.out.println(req.getServletContext() + "       Sevlet Context");
        System.out.println(req.getServletContext().getContextPath()  +   "    Servle Context --> ontext path");
        System.out.println(req.getContextPath() +   "      req.getConetxPath");
        System.out.println(file.isFile() + "   is file?");

        System.out.println( file.length());

        FileInputStream in = new FileInputStream(file);
        OutputStream out = resp.getOutputStream();
        resp.setContentLength((int) file.length());
        resp.setContentType("application/octet-stream; audio/*; charset=unicode");
        resp.addHeader("Content-Disposition", "attachment; filename=" + file.getName() +"\"");
        resp.setStatus(200);
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "*");

        byte[] byteArray = Files.readAllBytes(file.toPath());
        out.write(byteArray);




//        byte[]buffer = new byte[4096];
//        int read = 0;
//        while((read = in.read(buffer))!=-1){
//            out.write(buffer,0,read);
//        }

        out.flush();
        in.close();
        out.close();





        //todo Checking database on named song containing





    //todo: if server contains named song -
    // resp.setContentLength();
//    resp.setCharacterEncoding(maybe base64);
    }
}
