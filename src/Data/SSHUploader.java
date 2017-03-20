package Data;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import org.apache.commons.io.IOUtils;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;



public class SSHUploader {

Session session = null;

public SSHUploader(){

}

public void connect(String ip){
try {
		//System.out.println(ip);
        JSch jsch = new JSch();
        session = jsch.getSession("root", ip/*"172.31.3.49"*/, 22);
        session.setPassword("R0laBill");
	    //Assert.assertTrue(up.executeCommand("").contains("172.31.3.49")); 
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}

public String executeCommand(String script) throws JSchException, IOException, InterruptedException{
    
	System.out.println("Comando introduzido: "+ script);
    String str = null;
    ChannelExec channel = (ChannelExec) session.openChannel("exec");
    ((ChannelExec) channel).setCommand(script);

    InputStream in = channel.getInputStream();
    OutputStream out = channel.getOutputStream();
    InputStream outt=((ChannelExec) channel).getErrStream();
    
    channel.connect();
    //SACA AS MENSAGENS DE ERRO DA CONSOLA
    StringWriter writer = new StringWriter();
    IOUtils.copy(outt, writer, "ASCII");
    String theString = writer.toString();
    System.out.println("Mensagem de erro:"+theString);
    //

    out.flush();

    byte[] tmp = new byte[4069];
    while (true) {
        while (in.available() > 0) {
            int i = in.read(tmp, 0, 4069);
            if (i < 0)
                break;
            
            str= new String(tmp, 0, i);
        }
        if (channel.isClosed()) {
            //System.out.println("exit-status: " + channel.getExitStatus());
            break;
        }
        try {
            Thread.sleep(1000);
        } catch (Exception ee) {
            System.out.println(ee);
        }
    }
    
    channel.disconnect();
    //System.out.println("Sudo disconnect");
    str+=theString;
    System.out.println("output: "+str);
    return str;
}

public void disconnect(){
    session.disconnect();
	}
}