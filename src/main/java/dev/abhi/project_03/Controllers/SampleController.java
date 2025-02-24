package dev.abhi.project_03.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/say")
public class SampleController {
    @GetMapping("/hello/{name}/{times}")
    public String sayHello(@PathVariable("name") String name,
                           @PathVariable("times")int times){
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=times;i++){
            sb.append("Hello "+name+" ") ;
        }
        return sb.toString();
    }
    @GetMapping("/Bye")
    public String sayBye(){
        return "Bye everyone!";
    }
}
