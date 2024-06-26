package com.howard.rentalmytrack.controller;


import com.howard.rentalmytrack.dao.RentalMyTrackDaoImpl;
import com.howard.rentalmytrack.service.RentalMyTrackService;
import com.howard.rentalmytrack.vo.RentalMyTrackVo;
import com.yu.rental.model.Rental;
import com.yu.rental.model.RentalDAOHibernateImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/rentalmytrack/Track.do")
public class TestController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if (action.equals("addTrack")) {

            Integer rNo = Integer.valueOf(req.getParameter("rNo"));
            Integer memNo = Integer.valueOf(req.getParameter("memNo"));
            String expRentalDate_str = req.getParameter("expRentalDate");
            Date expRentalDate =null;
            if (expRentalDate_str != null) {
                expRentalDate = Date.valueOf(expRentalDate_str);
            }

            RentalMyTrackService rentalMyTrackService = new RentalMyTrackService();
            rentalMyTrackService.addTrack(rNo, memNo, expRentalDate);

            // 以下為service層
            RentalMyTrackDaoImpl rentalMyTrackDao = new RentalMyTrackDaoImpl();
            RentalMyTrackVo rentalMyTrackVo = rentalMyTrackDao.findByPK(rNo, memNo);
            if (rentalMyTrackVo != null) {

                res.setContentType("text/plain; charset=UTF-8");
                res.getWriter().write("created");

            }

        }

        if (action.equals("get")) {

            Integer rNo = Integer.valueOf(req.getParameter("rNo"));
            RentalDAOHibernateImpl rentalDAOHibernate = new RentalDAOHibernateImpl();
            Rental rental = rentalDAOHibernate.getByPK(rNo);

            req.setAttribute("rental", rental);
            String url = "/template/malefashion-master/shop-details.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);

        }

        if (action.equals("getTrackList")) {

            Integer memNo = Integer.valueOf(req.getParameter("memNo"));
            RentalMyTrackDaoImpl rentalMyTrackDao = new RentalMyTrackDaoImpl();
            RentalDAOHibernateImpl rentalDAOHibernate = new RentalDAOHibernateImpl();
            List<Rental> rentalList = new ArrayList<>();
            List<RentalMyTrackVo> trackList = rentalMyTrackDao.getByMemNo(memNo);

            for (RentalMyTrackVo trackVo : trackList) {
                Rental rental = rentalDAOHibernate.getByPK(trackVo.getrNo());
                rentalList.add(rental);
            }

            req.setAttribute("list", rentalList);
            String url = "/template/malefashion-master/tracklist.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);

        }

        if (action.equals("deleteTrack")) {
            res.setContentType("text/plain; charset=UTF-8");
            Integer rNo = Integer.valueOf(req.getParameter("rNo"));
            Integer memNo = Integer.valueOf(req.getParameter("memNo"));

            RentalMyTrackDaoImpl rentalMyTrackDao = new RentalMyTrackDaoImpl();
            rentalMyTrackDao.delete(rNo, memNo);
            RentalMyTrackVo rentalMyTrackVo = rentalMyTrackDao.findByPK(rNo, memNo);

            if (rentalMyTrackVo == null) {
                res.getWriter().write("deleteSuccess");
            }

        }

        if (action.equals("updateTrack")) {

            res.setContentType("text/plain; charset=UTF-8");

            Integer rNo = Integer.valueOf(req.getParameter("rNo"));
            Integer memNo = Integer.valueOf(req.getParameter("memNo"));
            Date expRentalDate = Date.valueOf(req.getParameter("expRentalDate"));

            RentalMyTrackDaoImpl rentalMyTrackDao = new RentalMyTrackDaoImpl();
            RentalMyTrackVo rentalMyTrackVo = new RentalMyTrackVo();
            rentalMyTrackVo.setrNo(rNo);
            rentalMyTrackVo.setMemNo(memNo);
            rentalMyTrackVo.setExpRentalDate(expRentalDate);

            rentalMyTrackDao.update(rentalMyTrackVo);

            RentalMyTrackVo track1 = rentalMyTrackDao.findByPK(rNo, memNo);
            if (expRentalDate.equals(track1.getExpRentalDate())) {
                res.getWriter().write("updateSuccess");
            }

        }


    }

}
