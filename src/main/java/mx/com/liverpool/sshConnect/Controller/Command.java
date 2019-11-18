package mx.com.liverpool.sshConnect.Controller;
import mx.com.liverpool.sshConnect.Pojos.RequestPojo;
import mx.com.liverpool.sshConnect.Util.Encode;
import mx.com.liverpool.sshConnect.Util.SshRuntime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class Command {

    @RequestMapping(value = "test",method = RequestMethod.GET )
    @ResponseBody
    public String list(){
        System.out.println("hola mundo");
        return "{\"status\":\"ok\"}";
    }


    @RequestMapping(value = "exec",method = RequestMethod.POST )
    @ResponseBody
    public String doScript(@RequestBody RequestPojo script){
        //System.out.println("hola mundo");
        for (String command:script.getScript()) { String encode = new Encode().encode(command); }
        return script.toString();
    }


    @RequestMapping(value = "do",method = RequestMethod.POST )
    @ResponseBody
    public String doCommand(@RequestBody RequestPojo script){
        return new SshRuntime().init(script);
    }
}