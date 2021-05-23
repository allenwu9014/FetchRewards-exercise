package com.fetchrewards.pointsBackendServlets.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fetchrewards.pointsBackendServlets.entity.BalanceItem;
import com.fetchrewards.pointsBackendServlets.service.PointsServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ReturnBalancesServlet", value = "/ReturnBalances")
public class ReturnBalancesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        PointsServices pointsServices = (PointsServices)session.getAttribute("userSession");
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        if (pointsServices == null) {

            mapper.writeValue(response.getWriter(), "Please add Transactions first!");
            return;
        }
        Map<String, Integer> balanceItemList = pointsServices.ReturnBalances();
        mapper.writeValue(response.getWriter(), balanceItemList);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
