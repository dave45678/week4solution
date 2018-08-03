package com.example.week4solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class HomeController {
    @Autowired
    AccountRepository transactions;

    @RequestMapping("/")
    public String listTransactions(Model model) {

        Double balance = 0.0;
        for (Transaction t : transactions.findAll()) {
            if (t.getAction().equalsIgnoreCase("deposit")) {
                balance += t.getAmount();
            } else {
                balance -= t.getAmount();
            }
        }

        model.addAttribute("balance",balance);//Lesson 4
        model.addAttribute("transactions",transactions.findAll());//Lesson 10
            return "list";
    }

    @GetMapping("/deposit")
    public String deposit(Model model){
        model.addAttribute("transaction", new Transaction());
        return "depositform";
    }

    @PostMapping("/deposit")
    public String processDeposit(@Valid Transaction transaction, BindingResult result){
        if (result.hasErrors()){
            return "depositform";
        }
        transactions.save(transaction);
        return "redirect:/";
    }

    @GetMapping("/withdraw")
    public String withdraw(Model model){
        model.addAttribute("transaction", new Transaction());
        return "withdrawform";
    }

    @PostMapping("/withdraw")
    public String processwithdraw(@Valid Transaction transaction, BindingResult result){
        if (result.hasErrors()){
            return "withdrawform";
        }
        transactions.save(transaction);
        return "redirect:/";
    }


}
