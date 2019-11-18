package mx.com.liverpool.sshConnect.Util;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import mx.com.liverpool.sshConnect.Pojos.Servidor;

import java.awt.Container;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;



public class Ssh {
    // TODO code application logic here
    private Session session;
    private Channel channel;
    private boolean acabe=false;
    private Ssh self = this;
    private String output="";
    public Ssh(Servidor server) throws JSchException, InterruptedException {

        self.setAcabe(false);
        JSch jsch = new JSch();
        self.self.session = jsch.getSession(server.getUser(), server.getIp(), server.getPort());
        self.session.setPassword(server.getPass());
        self.session.setConfig("StrictHostKeyChecking", "no");

    }

    public void lanzaComandos(final String[] cmds, final String endswith) throws JSchException, InterruptedException {
        int limite=100;
        self.session.connect(8000);
        while(!self.session.isConnected()){
            limite=limite-1;
            if(limite==0)
                throw new InterruptedException();
            else
                Thread.sleep(100);
        }
        self.channel = self.session.openChannel("shell");
        try {
            PipedInputStream pis = new PipedInputStream();
            final PipedOutputStream pos = new PipedOutputStream(pis);

            self.channel.setInputStream(pis);
            self.channel.setOutputStream(new OutputStream() {

                private int cmdIndx = 0;
                private String line = "";
                @Override
                public void write(int b) throws IOException {
                    char c = (char) b;
                    if (c == '\n') {
                        logout(line);
                        self.setOutput(self.getOutput()+"\n"+line);
                        System.out.println(self.getOutput());
                        line = "";
                    } else {
                        line += c;
                        logout(line);
                        if (line.endsWith(endswith) || line.endsWith("$") || line.endsWith("Password:") || line.endsWith("Remedy:")) {
                            String cmd = cmds[cmdIndx];
                            cmdIndx++;
                            pos.write(cmd.getBytes());
                        }
                    }
                }

                public void logout(String line) {
                    if (line.startsWith("logout")) {
//                        System.out.println("...logout...");
                        self.setOutput(self.getOutput()+"\n"+line);
                        self.disconnect();
                        self.setAcabe(true);

                    }
                }
            });

            self.channel.connect(3 * 1000);

        } catch (Exception e) {
            System.out.println(e);
            self.disconnect();
        }
    }

    public void disconnect() {
        self.channel.disconnect();
        self.session.disconnect();
//        System.exit(0);
    }

    public boolean isAcabe() {
        return self.acabe;
    }

    public void setAcabe(boolean acabe) {
        self.acabe = acabe;
    }

    public Ssh getSelf() {
        return self;
    }

    public void setSelf(Ssh self) {
        this.self = self;
    }

    public String getOutput() {
        return self.output;
    }

    public void setOutput(String output) {
        self.output = output;
    }
}
