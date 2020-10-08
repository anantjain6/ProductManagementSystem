package me.anant.PMS.Helper;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Component
public class ModelAndViewProviderHelper {

    public ModelAndView generateModelAndView(String viewName,String attributeName,
                                             Object atrributeValue)
    {
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject(attributeName, atrributeValue);
        return modelAndView;
    }

    public ModelAndView generateModelAndView(String viewName, Map<String,Object> atrributeNameAndValueMap)
    {
        ModelAndView modelAndView=new ModelAndView(viewName);
        modelAndView.addAllObjects(atrributeNameAndValueMap);
        return modelAndView;
    }
}
