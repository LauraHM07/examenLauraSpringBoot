package com.noticiero.laura.noticiero.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.noticiero.laura.noticiero.model.Noticia;
import com.noticiero.laura.noticiero.model.Usuario;

@Controller
@RequestMapping("/noticias")
public class NoticiaController {
    
    @GetMapping(value="/list")
    public ModelAndView list(Model model) {
        ModelAndView modelAndView = new ModelAndView("noticias/list");

        modelAndView.addObject("noticias", getNoticias());
        modelAndView.addObject("title", "noticias");

        return modelAndView;
    }

    @GetMapping(path = { "/edit/{codigo}" })
    public ModelAndView edit(@PathVariable(name = "codigo", required = true) int codigo) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("noticia", getNoticia(codigo));
        modelAndView.setViewName("noticias/edit");

        return modelAndView;
    }

    @GetMapping(path = { "/create" })
    public ModelAndView create(Noticia noticia) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("noticia", new Noticia());
        modelAndView.setViewName("noticias/new");

        return modelAndView;
    }

    @PostMapping(path = { "/save" })
    public ModelAndView save(Noticia noticia) {

        int round = (int) (Math.random()*(100+5));

        noticia.setCodigo(round);

        List<Noticia> noticias = getNoticias();
        noticias.add(noticia);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("noticias", noticias);
        modelAndView.setViewName("noticias/list");

        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Noticia noticia) {

        List<Noticia> noticias = getNoticias();

        int indexOf = noticias.indexOf(noticia);

        noticias.set(indexOf, noticia);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("noticias", noticias);
        modelAndView.setViewName("noticias/list");

        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(@PathVariable(name = "codigo", required = true) int codigo) {

        List<Noticia> noticias = getNoticias();
        noticias.remove(getNoticia(codigo));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("noticias", noticias);
        modelAndView.setViewName("noticias/list");
        
        return modelAndView;
    }

    // @PostMapping()
    // public String bienvenido(Usuario usuario, HttpSession session){
    //     session.setAttribute("usuario", usuario);

    //     return "list";
    // }

    // @GetMapping()
    // public String logout(HttpSession session) {
    //     session.invalidate();

    //     return "list";
    // }

    private List<Noticia> getNoticias() {
        List<Noticia> noticias = new ArrayList<>();

        noticias.add(new Noticia(1, "Titular 1", "Descripción 1"));
        noticias.add(new Noticia(2, "Titular 2", "Descripción 2"));
        noticias.add(new Noticia(3, "Titular 3", "Descripción 3"));
        noticias.add(new Noticia(4, "Titular 4", "Descripción 4"));

        return noticias;
    }

    private Noticia getNoticia(int codigo) {
        List<Noticia> noticias = getNoticias();
        
        int indexOf = noticias.indexOf(new Noticia(codigo));

        return noticias.get(indexOf);
    }
}
