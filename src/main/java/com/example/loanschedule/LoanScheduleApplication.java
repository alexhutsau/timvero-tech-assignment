package com.example.loanschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LoanScheduleApplication {

  public static void main(String[] args) {
    SpringApplication.run(LoanScheduleApplication.class, args);
  }
}

@RestController
@RequestMapping("/loan")
class LoanController {

  @GetMapping("/schedule")
  public List<Payment> getLoanSchedule(
      @RequestParam double amount, @RequestParam double rate, @RequestParam int term) {
    return LoanCalculator.calculateSchedule(amount, rate, term);
  }
}

class LoanCalculator {

  public static List<Payment> calculateSchedule(double amount, double rate, int term) {
    List<Payment> schedule = new ArrayList<>();

    double monthlyRate = rate / 100 / 12;
    double annuity =
        (amount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -term));
    double remainingBalance = amount;

    for (int i = 1; i <= term; i++) {
      double interestPayment = remainingBalance * monthlyRate;
      double principalPayment = annuity - interestPayment;
      remainingBalance = remainingBalance - principalPayment;
      schedule.add(
          new Payment(i, annuity, principalPayment, interestPayment, remainingBalance));
    }

    return schedule;
  }
}

class Payment {

  private int month;
  private double annuityPayment;
  private double principal;
  private double interest;
  private double balance;

  public Payment(int month, double annuityPayment, double principal, double interest, double balance) {
    this.month = month;
    this.annuityPayment = annuityPayment;
    this.principal = principal;
    this.interest = interest;
    this.balance = balance;
  }

  public int getMonth() {
    return month;
  }

  public double getAnnuityPayment() {
    return annuityPayment;
  }

  public double getPrincipal() {
    return principal;
  }

  public double getInterest() {
    return interest;
  }

  public double getBalance() {
    return balance;
  }
}
