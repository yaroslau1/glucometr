package com.work.controller;

import com.work.comPort.ComPortConnectivity;
import com.work.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;


@Controller
public class MainController {

    /*First method on start application*/
    /*Попадаем сюда на старте приложения (см. параметры аннтоции и настройки пути после деплоя) */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        System.out.println(Arrays.toString(ComPortConnectivity.getPortNames()));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "Please, introduce yourself");
        modelAndView.addObject("user", new User());
        modelAndView.addObject("comPortList", ComPortConnectivity.getPortNames());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public ModelAndView printHello(@ModelAttribute("user")User user, ModelAndView model) {
        model.addObject("message", "Hello, " + user.getName());
        model.addObject("comPortList", ComPortConnectivity.getPortNames());
        model.setViewName("index");
        return model;
    }

    @RequestMapping(value = "/refreshComPortNames", method = RequestMethod.POST)
    public ModelAndView refreshComPortNames(ModelAndView model) {
        model.addObject("message", "Please, introduce yourself");
        model.addObject("comPortList", ComPortConnectivity.getPortNames());
        model.setViewName("index");
        System.out.println(Arrays.toString(ComPortConnectivity.getPortNames()));
        return model;
    }

    @RequestMapping(value = "/connectToComPort", method = RequestMethod.POST)
    public ModelAndView connectToComPort(@RequestParam("category") String comPortName, ModelAndView model) {
        model.addObject("message", "Please, introduce yourself");
        model.addObject("messageComPort", comPortName);
        model.addObject("comPortList", ComPortConnectivity.getPortName());
        model.setViewName("index");
        System.out.println(comPortName);
        System.out.println(ComPortConnectivity.getPortName());
        ComPortConnectivity connectivity = new ComPortConnectivity(ComPortConnectivity.getPortName());
        connectivity.openPort();
        return model;
    }

    public ModelAndView test(){
        ModelAndView model = new ModelAndView();
        model.setViewName("test");
        //getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        return model;
    }
}
