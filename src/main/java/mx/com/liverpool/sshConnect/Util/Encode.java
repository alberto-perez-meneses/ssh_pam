package mx.com.liverpool.sshConnect.Util;

import com.google.common.io.BaseEncoding;

public class Encode {

    public String decode(String encodedText){

        byte[] decodedBlob = BaseEncoding.base64().decode(encodedText);
        String decodedText =  new String(decodedBlob);

        return decodedText;
    }

    public String encode(String text){
        String encodedText = BaseEncoding.base64().encode(text.getBytes());

        return encodedText;
    }
}
