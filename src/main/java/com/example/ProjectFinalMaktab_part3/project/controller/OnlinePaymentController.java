package com.example.ProjectFinalMaktab_part3.project.controller;

import cn.apiclub.captcha.Captcha;
import com.example.ProjectFinalMaktab_part3.project.captchaUtil.CaptchaUtil;
import com.example.ProjectFinalMaktab_part3.project.model.Customer;
import com.example.ProjectFinalMaktab_part3.project.model.OnlinePayment;
import com.example.ProjectFinalMaktab_part3.project.service.Impl.OnlinePaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/onlinePayment")
public class OnlinePaymentController {

    private OnlinePaymentServiceImpl onlinePaymentService;

    public OnlinePaymentController(OnlinePaymentServiceImpl onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }
    @GetMapping("/register")
    public String registerUser(Model model) {
        OnlinePayment onlinePayment=new OnlinePayment();
        getCaptcha(onlinePayment);
        model.addAttribute("onlinePayment", onlinePayment);
        return "onlinePayment";
    }

    @PostMapping("/save")
    public String saveUser(
            @ModelAttribute OnlinePayment onlinePayment,
            Model model
    ) {
        if(onlinePayment.getCaptcha().equals(onlinePayment.getHiddenCaptcha())) {
            onlinePaymentService.save(onlinePayment);
            model.addAttribute("message", "onlinePayment Registered successfully!");
            return "redirect:allUsers";
        }
        else {
            model.addAttribute("message", "Invalid Captcha");
            getCaptcha(onlinePayment);
            model.addAttribute("onlinePayment", onlinePayment);
        }
        return "onlinePayment";
    }

    private void getCaptcha(OnlinePayment onlinePayment) {
        Captcha captcha = CaptchaUtil.createCaptcha(240, 70);
        onlinePayment.setHiddenCaptcha(captcha.getAnswer());
        onlinePayment.setCaptcha(""); // value entered by the User
        onlinePayment.setRealCaptcha(CaptchaUtil.encodeCaptcha(captcha));

    }
}
