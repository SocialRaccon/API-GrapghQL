package itst.social_raccoon_api.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import itst.social_raccoon_api.Models.Hello;
import itst.social_raccoon_api.Services.HelloService;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("hello")
public class HelloControlller implements HelloService{

    @GetMapping
    public Hello getHello(@PathParam("name") String name) {
        if (name == null || name.isEmpty()) name = "World";
        return new Hello(name);
    }
}