package com.jk.freemarker.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.StringWriter;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

  @RequestMapping("/")
  public String index(Model model) {
    model.addAttribute("firstMsg", "첫번째");
    model.addAttribute("secondMsg", "두번째");
    model.addAttribute("thirdMsg", "세번째");

    return "index";
  }

  @RequestMapping("/first/error")
  public String msgError(Model model) {
    model.addAttribute("firstMsg", null);
    model.addAttribute("secondMsg", "두번째");
    model.addAttribute("thirdMsg", "세번째");

    return "index";
  }

  @RequestMapping(value="/first/solve")
  public String msgSolve(Model model) throws Exception {
    Map<String, Object> firstData = new LinkedHashMap<>();
    Map<String, Object> secondData = new LinkedHashMap<>();
    Map<String, Object> thirdData = new LinkedHashMap<>();
    firstData.put("firstMsg", null);
    secondData.put("secondMsg", "두번째 메시지 - 해결");
    thirdData.put("thirdMsg", "세번째 메시지 - 해결");

    model.addAttribute("first", template("first", firstData));
    model.addAttribute("second", template("second", secondData));
    model.addAttribute("third", template("third", thirdData));
    return "index2";
  }

  private String template(String templateName, Map<String, Object> data) {
    Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
    cfg.setClassForTemplateLoading(this.getClass(), "/templates/test1");
    try {
      Template template = cfg.getTemplate(templateName+".ftl");
      Writer out = new StringWriter();
      template.process(data, out);
      return out.toString();

    }catch (Exception e){
      return "";
    }
  }
}
