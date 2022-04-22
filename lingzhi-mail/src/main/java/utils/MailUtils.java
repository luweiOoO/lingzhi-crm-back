package utils;

import com.lingzhi.crm.mail.domain.EmailAccountInfo;
import com.lingzhi.crm.mail.service.impl.MailContentInfoServiceImpl;
import com.sun.mail.util.MailSSLSocketFactory;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.*;

/**
 * @Author: LuWei
 * @Description
 * @Date: Created in 16:10 2021/6/10
 * @Modified By:
 */
public class MailUtils {

    /**
     * 连接接收邮箱
     * @param accountInfo
     * @return
     * @throws Exception
     */
    public static Store connectReceiveMail(EmailAccountInfo accountInfo) throws Exception{
        //准备连接邮件服务器的会话信息
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imap");
        props.setProperty("mail.imap.host", "imap.qq.com");
        props.setProperty("mail.imap.port", "143");

        // 创建Session实例对象
        Session session = Session.getInstance(props);

        // 创建IMAP协议的Store对象
        Store store = session.getStore("imap");

        // 连接邮件服务器
        store.connect(accountInfo.getEmailAddress(), accountInfo.getEmailPassword());
        return store;
    }

    /**
     * 连接发送邮箱
     * @param accountInfo
     * @return
     */
    public static Transport connectSendMail(EmailAccountInfo accountInfo) throws GeneralSecurityException {
        Properties prop = new Properties();
        prop.setProperty("mail.host", accountInfo.getSendHost());
        prop.setProperty("mail.smtp.port", accountInfo.getSendPort());
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        //设置超时时间为20秒
        prop.setProperty("mail.smtp.timeout", "20000");

        if("1".equals(accountInfo.getSendSsl())){
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.ssl.socketFactory", sf);
        }


        //1、创建session
        Session session = Session.getInstance(prop);
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        //2、通过session得到transport对象
        Transport ts;
        //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
        try {
            ts = session.getTransport();
            ts.connect(accountInfo.getSendHost(), accountInfo.getEmailAddress(), accountInfo.getEmailPassword());
            return ts;
        } catch (NoSuchProviderException e1) {
            e1.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 测试邮箱发送是否正常连接
     * @return
     */
    public static boolean isSendConnect(EmailAccountInfo accountInfo){
        Transport transport = null;
        try {
            transport = connectSendMail(accountInfo);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return false;
        }
        if(transport != null){
            return true;
        }
        return false;
    }

    /**
     * 测试邮件接收是否正常连接
     * @return
     */
    public static boolean isReceiveConnect(EmailAccountInfo accountInfo){
        try {
            Store store = connectReceiveMail(accountInfo);
            return store.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws Exception {

        EmailAccountInfo accountInfo = new EmailAccountInfo();
        accountInfo.setEmailAddress("771608610@qq.com");
        accountInfo.setEmailPassword("cfqpvnsvpttwbffc");
        MailContentInfoServiceImpl service = new MailContentInfoServiceImpl();
        service.getMailListByAccount(accountInfo);

//        // 定义连接POP3服务器的属性信息
//        String pop3Server = "pop.qq.com";
//        String protocol = "pop3";
//        String username = "771608610@qq.com";
////        String password = "luwei771608610"; // QQ邮箱的SMTP的授权码，什么是授权码，它又是如何设置？
//        String password = "cfqpvnsvpttwbffc"; // QQ邮箱的SMTP的授权码，什么是授权码，它又是如何设置？
//
//        Properties props = new Properties();
//        props.setProperty("mail.transport.protocol", protocol); // 使用的协议（JavaMail规范要求）
//        props.setProperty("mail.smtp.host", pop3Server); // 发件人的邮箱的 SMTP服务器地址
//
//        // 获取连接
//        Session session = Session.getDefaultInstance(props);
//        session.setDebug(false);
//
//        // 获取Store对象
//        Store store = session.getStore(protocol);
//        store.connect(pop3Server, username, password); // POP3服务器的登陆认证
//
//        // 通过POP3协议获得Store对象调用这个方法时，邮件夹名称只能指定为"INBOX"
//        Folder folder = store.getFolder("INBOX");// 获得用户的邮件帐户
//        folder.open(Folder.READ_WRITE); // 设置对邮件帐户的访问权限
//
//        Message[] messages = folder.getMessages();// 得到邮箱帐户中的所有邮件
//
//        for (Message message : messages) {
//            String subject = message.getSubject();// 获得邮件主题
//            Address from = (Address) message.getFrom()[0];// 获得发送者地址
//            String type = message.getContentType();// 获得邮件类型
//            System.out.println("邮件的主题为: " + subject + "\t发件人地址为: " + from);
//            System.out.println();
//            System.out.println("邮件类型为: " + type);
//            System.out.println();
//            System.out.println("邮件发送时间为：" + message.getSentDate());
//
//            System.out.println("邮件的内容为：");
//            message.writeTo(System.out);// 输出邮件内容到控制台
//        }
//
//        folder.close(false);// 关闭邮件夹对象
//        store.close(); // 关闭连接对象
    }
}
