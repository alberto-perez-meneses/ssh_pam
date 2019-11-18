package mx.com.liverpool.sshConnect.Util;

import com.jcraft.jsch.JSchException;
import mx.com.liverpool.sshConnect.Pojos.RequestPojo;
import mx.com.liverpool.sshConnect.Pojos.Servidor;

public class SshRuntime {

    public String init(RequestPojo requestPojo){
        // TODO code application logic here
        String ip=requestPojo.getPam();
        String usuario=requestPojo.getUser();
        String password=requestPojo.getPassword();
        int port=22;
        int tipoConexion=0;
        String comandos="";

        Servidor pum = new Servidor();
        pum.setIp(ip);
        pum.setPass(new Encode().decode(password));
        pum.setPort(port);
        pum.setUser(usuario);

        String output="";
        try {

            for (String comando:requestPojo.getScript()) {
                System.out.println(comando);
            }
            Ssh myssh = new Ssh(pum);
            String[] cmds=requestPojo.getScript();
            myssh.lanzaComandos(cmds, "#");
            while (myssh.isAcabe() == false) {
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println(myssh.getOutput());
            myssh.disconnect();
            output=myssh.getOutput();
        } catch (JSchException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Hay algo mal con la autenticación");
            output="Hay algo mal con la autenticación";
        } finally {
            return output;
        }
    }
}
