package com.fetchrewards.pointsBackendServlets.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fetchrewards.pointsBackendServlets.entity.AddTransactionRequestBody;
import com.fetchrewards.pointsBackendServlets.entity.AddTransactionResponseBody;
import com.fetchrewards.pointsBackendServlets.entity.Transaction;
import com.fetchrewards.pointsBackendServlets.service.PointsServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddTransactionsServlet", value = "/AddTransactionsServlet")
public class AddTransactionsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PointsServices pointsServices = (PointsServices)session.getAttribute("userSession");
        if (pointsServices == null) {
            pointsServices = new PointsServices();
            session.setAttribute("userSession", pointsServices);
        }
        ObjectMapper mapper = new ObjectMapper();
        AddTransactionRequestBody body = mapper.readValue(request.getReader(), AddTransactionRequestBody.class);
        AddTransactionResponseBody resultResponse;
        Transaction transaction = new Transaction(body.payer, body.points, body.timestamp);
        if (pointsServices.AddTransaction(transaction)) {
            resultResponse = new AddTransactionResponseBody("OK");
        } else {
            resultResponse = new AddTransactionResponseBody("Failure");
        }
        response.setContentType("application/json");
        mapper.writeValue(response.getWriter(), resultResponse);
    }
}

