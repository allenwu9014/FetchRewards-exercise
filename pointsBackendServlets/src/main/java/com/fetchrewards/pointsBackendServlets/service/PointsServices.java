package com.fetchrewards.pointsBackendServlets.service;

import com.fetchrewards.pointsBackendServlets.entity.BalanceItem;
import com.fetchrewards.pointsBackendServlets.entity.SpendItem;
import com.fetchrewards.pointsBackendServlets.entity.Transaction;

import javax.swing.*;
import java.util.*;

public class PointsServices {
//    private List<Transaction> pointsList;

    private PriorityQueue<Transaction> pointsList;
    private  Map<String, Integer> balanceTable = new HashMap<>();
    private TimestampComparator timestampComparator = new TimestampComparator();

    class TimestampComparator implements Comparator<Transaction> {
        @Override
        public int compare(Transaction o1, Transaction o2) {
            return o1.getTimestamp().compareTo(o2.getTimestamp());
        }
    }

    public PointsServices() {

        this.pointsList = new PriorityQueue<Transaction>(timestampComparator);
    }

    public boolean AddTransaction(Transaction transaction){

            if (balanceTable.containsKey(transaction.getPayer())) {
                balanceTable.put(transaction.getPayer(),
                        balanceTable.get(transaction.getPayer()) + transaction.getPoints());

            } else {
                balanceTable.put(transaction.getPayer(), transaction.getPoints());
            }

            return   pointsList.add(transaction);


    }

    public List<SpendItem> SpendPoints(int pointsNumber) {
        List<SpendItem> spendList = new ArrayList<>();

        PriorityQueue<Transaction> temPointsList = new PriorityQueue<>(timestampComparator);

        doMinusPointsIntegration();

        while (pointsNumber > 0) {

            if (pointsList.isEmpty()) {
                spendList = null;
                return spendList;

            }

            Transaction temTransaction = pointsList.poll();
            int temPoints = temTransaction.getPoints();
            
            if (temPoints <= pointsNumber) {
                temTransaction.setPoints(0);
                pointsNumber -= temPoints;
                SpendItem spendItem = new SpendItem(temTransaction.getPayer(), -1 * temPoints);
                spendList.add(spendItem);
                balanceTable.put(temTransaction.getPayer(),
                        balanceTable.get(temTransaction.getPayer()) - temPoints
                        );

                temPointsList.add(temTransaction);
            }
            else {

                temPoints -= pointsNumber;

                temTransaction.setPoints(temPoints);

                temPointsList.add(temTransaction);
                SpendItem spendItem = new SpendItem(temTransaction.getPayer(), -1 * pointsNumber);
                spendList.add(spendItem);

                balanceTable.put(temTransaction.getPayer(),
                        balanceTable.get(temTransaction.getPayer()) - pointsNumber);

                temPointsList.add(temTransaction);
                break;

            }

        }

        while (!pointsList.isEmpty()) {
            temPointsList.add(pointsList.poll());
        }
        pointsList = temPointsList;


        return spendList;
    }

    public Map<String, Integer> ReturnBalances() {

//       List<BalanceItem> balanceList = new ArrayList<>();
//
//
//       PriorityQueue<Transaction> temPointsList = new PriorityQueue<>(timestampComparator);
//
//       while(!pointsList.isEmpty()) {
//           Transaction temTransaction = pointsList.poll();
//           if (temTransaction.getPoints() <= 0) {
//               temPointsList.add(temTransaction);
//           }
//           if (!balanceTable.containsKey(temTransaction.getPayer())) {
//               balanceTable.put(temTransaction.getPayer(), temTransaction.getPoints());
//           } else {
//               int count = balanceTable.get(temTransaction.getPayer());
//               count += temTransaction.getPoints();
//               balanceTable.put(temTransaction.getPayer(), count);
//           }
//        temPointsList.add(temTransaction);
//       }
//        pointsList = temPointsList;
//
//       balanceTable.forEach((payer, count) -> {
//           balanceList.add(new BalanceItem(payer, count));
//       });

       return balanceTable;
    }

 public void doMinusPointsIntegration() {

        PriorityQueue<Transaction> temTransactionList = new PriorityQueue<>(timestampComparator);
        Queue<Transaction> minusPointsList = new LinkedList<>();
        while (!pointsList.isEmpty()) {
            Transaction temTransaction = pointsList.poll();
            if (temTransaction.getPoints() < 0) {
                minusPointsList.add(temTransaction);
            } else {
                temTransactionList.add(temTransaction);
            }
        }
        while (!minusPointsList.isEmpty()) {
            Transaction temMinusTrans = minusPointsList.poll();
            int temMinusPoints = temMinusTrans.getPoints();

            while (!temTransactionList.isEmpty()) {
                Transaction temTransaction = temTransactionList.poll();
                int temPoints = temTransaction.getPoints();
            if (temPoints + temMinusPoints >= 0) {
                temTransaction.setPoints(temPoints + temMinusPoints);
                pointsList.add(temTransaction);
                break;
            } else {
                temMinusPoints += temPoints;
                    temTransaction.setPoints(0);

                    pointsList.add(temTransaction);


            }

        }
            while (!temTransactionList.isEmpty()) {
                pointsList.add(temTransactionList.poll());
            }
        }

 }


}
