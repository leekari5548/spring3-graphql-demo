package cn.leekari.controller;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class VersionController {

    @QueryMapping
    public String version() {
        return "0.0.1";
    }
}
