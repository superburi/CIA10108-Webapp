package com.howard.rentalmytrack.controller_orm;

import com.howard.rentalmytrack.service_orm.RentalMyTrackServiceImpl_ORM;
import com.howard.rentalmytrack.vo.RentalMyTrackVo_ORM;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@WebServlet("/rentalmytrack/track.do")
public class RentalMyTrackController_ORM extends HttpServlet {

    private RentalMyTrackServiceImpl_ORM service;

    @Override
    public void init() throws ServletException {
        service = new RentalMyTrackServiceImpl_ORM();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        String forwardPath = "";
        switch (action) {
            case "getAll":
                forwardPath = getAllTracks(req, res);
                break;
            case "compositeQuery":
                forwardPath = getCompositeTracksQuery(req, res);
                break;
            default:
                forwardPath = "/index.jsp";
        }

        res.setContentType("text/html; charset=UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
        dispatcher.forward(req, res);
    }

    private String getAllTracks(HttpServletRequest req, HttpServletResponse res) {
        String page = req.getParameter("page");
        int currentPage = (page == null) ? 1 : Integer.parseInt(page);

        List<RentalMyTrackVo_ORM> trackList = service.getAllTracks(currentPage);

        if (req.getSession().getAttribute("trackPageQty") == null) {
            int trackPageQty = service.getPageTotal();
            req.getSession().setAttribute("trackPageQty", trackPageQty);
        }

        req.setAttribute("trackList", trackList);
        req.setAttribute("currentPage", currentPage);

        return "/rentalmytrack/orm/listAllTracks.jsp";
    }

    private String getCompositeTracksQuery(HttpServletRequest req, HttpServletResponse res) {
        Map<String, String[]> map = req.getParameterMap();

        if (map != null) {
            List<RentalMyTrackVo_ORM> trackList = service.getTracksByCompositeQuery(map);
            req.setAttribute("trackList", trackList);
        } else {
            return "/index.jsp";
        }
        return "/rentalmytrack/orm/listCompositeQueryTracks.jsp";
    }

}
