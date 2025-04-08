package io.renren.modules.sys.controller;

import io.renren.common.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Home page tips
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public Result<String> index(){
        return new Result<String>().ok("Backend management system starts");
    }
}
