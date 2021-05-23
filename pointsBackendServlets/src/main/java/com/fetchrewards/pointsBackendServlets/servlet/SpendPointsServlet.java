package com.fetchrewards.pointsBackendServlets.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fetchrewards.pointsBackendServlets.entity.SpendItem;
import com.fetchrewards.pointsBackendServlets.entity.SpendPointsRequestBody;
import com.fetchrewards.pointsBackendServlets.entity.SpendPointsResponseBody;
import com.fetchrewards.pointsBackendServlets.service.PointsServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SpendPointsServlet", value = "/SpendPointsServlet")
public class SpendPointsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PointsServices pointsServices = (PointsServices)session.getAttribute("userSession");
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        if (pointsServices == null) {

            mapper.writeValue(response.getWriter(), "Please add Transactions first!");
            return;
        }
        SpendPointsRequestBody requestBody = mapper.readValue(request.getReader(), SpendPointsRequestBody.class);
        SpendPointsResponseBody responseBody;

        List<SpendItem> spendItemList = pointsServices.SpendPoints(requestBody.getPoints());

        if (spendItemList.isEmpty()) {
            mapper.writeValue(response.getWriter(), "Your points are not enough to pay!");
            return;
        }
        mapper.writeValue(response.getWriter(), spendItemList);

    }
}
