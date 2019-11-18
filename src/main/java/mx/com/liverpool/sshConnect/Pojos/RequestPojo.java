package mx.com.liverpool.sshConnect.Pojos;

public class RequestPojo {

    Integer transactionId;
    String[] script;
    String user;
    String password;
    Integer state;
    String pam;


    public RequestPojo() {
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getPam() {
        return pam;
    }

    public String[] getScript() {
        return script;
    }

    public void setScript(String[] script) {
        this.script = script;
    }

    public void setPam(String pam) {
        this.pam = pam;
    }
}
