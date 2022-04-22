package com.lingzhi.crm.mail.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.lingzhi.crm.mail.domain.EmailAccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.MailMessage;
import org.springframework.stereotype.Service;
import com.lingzhi.crm.mail.mapper.MailContentInfoMapper;
import com.lingzhi.crm.mail.domain.MailContentInfo;
import com.lingzhi.crm.mail.service.IMailContentInfoService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import static com.lingzhi.crm.common.constant.Constants.MAIL_MESSAGE_ID;
import static utils.MailUtils.connectReceiveMail;

/**
 * 邮件基础信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-15
 */
@Service
public class MailContentInfoServiceImpl implements IMailContentInfoService 
{
    @Autowired
    private MailContentInfoMapper mailContentInfoMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询邮件基础信息
     * 
     * @param mailId 邮件基础信息ID
     * @return 邮件基础信息
     */
    @Override
    public MailContentInfo selectMailContentInfoById(Long mailId)
    {
        return mailContentInfoMapper.selectMailContentInfoById(mailId);
    }

    /**
     * 查询邮件基础信息列表
     * 
     * @param mailContentInfo 邮件基础信息
     * @return 邮件基础信息
     */
    @Override
    public List<MailContentInfo> selectMailContentInfoList(MailContentInfo mailContentInfo)
    {
        return mailContentInfoMapper.selectMailContentInfoList(mailContentInfo);
    }

    /**
     * 新增邮件基础信息
     * 
     * @param mailContentInfo 邮件基础信息
     * @return 结果
     */
    @Override
    public int insertMailContentInfo(MailContentInfo mailContentInfo)
    {
        return mailContentInfoMapper.insertMailContentInfo(mailContentInfo);
    }

    /**
     * 修改邮件基础信息
     * 
     * @param mailContentInfo 邮件基础信息
     * @return 结果
     */
    @Override
    public int updateMailContentInfo(MailContentInfo mailContentInfo)
    {
        return mailContentInfoMapper.updateMailContentInfo(mailContentInfo);
    }

    /**
     * 批量删除邮件基础信息
     * 
     * @param mailIds 需要删除的邮件基础信息ID
     * @return 结果
     */
    @Override
    public int deleteMailContentInfoByIds(Long[] mailIds)
    {
        return mailContentInfoMapper.deleteMailContentInfoByIds(mailIds);
    }

    /**
     * 删除邮件基础信息信息
     * 
     * @param mailId 邮件基础信息ID
     * @return 结果
     */
    @Override
    public int deleteMailContentInfoById(Long mailId)
    {
        return mailContentInfoMapper.deleteMailContentInfoById(mailId);
    }

    @Override
    public int updateAndSaveMail(List<EmailAccountInfo> accountInfoList) {
        if(accountInfoList == null || accountInfoList.size() < 1){
            //为空时  赋值为所有已录入的邮箱信息
            accountInfoList = new LinkedList<>();
        }
        List<MailContentInfo> updateMailList = new LinkedList<>();
        accountInfoList.forEach(account -> {

        });
        return 0;
    }

    @Override
    public List<MailContentInfo> getMailListByAccount(EmailAccountInfo accountInfo) {
        List<MailContentInfo> contentInfoList = new LinkedList<>();
        try {
            //获取该账户连接信息
            Store store = connectReceiveMail(accountInfo);
            //获得收件箱
            Folder folder = store.getFolder("INBOX");
            //以读写模式打开收件箱
            folder.open(Folder.READ_WRITE);
            //获得收件箱的邮件列表
            List<Message> messageList = Arrays.stream(folder.getMessages()).collect(Collectors.toList());

            contentInfoList = parseMessage(messageList);

            // 打印不同状态的邮件数量
            System.out.println("收件箱中共" + messageList.size() + "封邮件!");
            System.out.println("收件箱中共" + folder.getUnreadMessageCount() + "封未读邮件!");
            System.out.println("收件箱中共" + folder.getNewMessageCount() + "封新邮件!");
            System.out.println("收件箱中共" + folder.getDeletedMessageCount() + "封已删除邮件!");
        }catch (Exception e){
            e.printStackTrace();
        }
        return contentInfoList;
    }

    @Override
    public int receiveMail() {
        //TODO 获取所有已绑定邮箱账号
        List<EmailAccountInfo> accountInfoList = new LinkedList<>();
        EmailAccountInfo tempAccountInfo = new EmailAccountInfo();
        tempAccountInfo.setEmailPassword("cfqpvnsvpttwbffc");
        tempAccountInfo.setEmailAddress("771608610@qq.com");
        accountInfoList.add(tempAccountInfo);
        List<MailContentInfo> contentInfoList = new LinkedList<>();
        accountInfoList.forEach(accountInfo -> {
            List<MailContentInfo> tempContentInfoList = getMailListByAccount(accountInfo);
            contentInfoList.addAll(tempContentInfoList);
        });
        if(contentInfoList.size() > 0){
            mailContentInfoMapper.insertMailContentInfoList(contentInfoList);
        }
        return 1;
    }

    /**
     * 批量解析邮件内容
     * @param messageList
     * @return
     */
    List<MailContentInfo> parseMessage(List<Message> messageList) throws Exception{
        List<MailContentInfo> contentInfoList = new LinkedList<>();
        messageList.forEach(message -> {
            MimeMessage msg = (MimeMessage)message;
            try {
                if(isSynMessage(msg.getMessageID())){
                    return;
                }
                MailContentInfo contentInfo = new MailContentInfo();
                //获取主题
                contentInfo.setMailSubject(getSubject(msg));

//                contentInfo.setSendDate(getSentDate(msg, null));
                //获取接件人
                contentInfo.setReceiver(getReceiveAddress(msg, null));
                //获取发件人
                contentInfo.setMailSender(getFrom(msg));
                StringBuffer content = new StringBuffer(30);
                //获取邮件内容
                getMailTextContent(msg, content);
                contentInfo.setMailPlainText(content.toString());
//                contentInfo.setMailType(msg.getContentType());
                contentInfo.setMailType("1");
                contentInfo.setMailMessageId(msg.getMessageID());
                contentInfoList.add(contentInfo);
                redisTemplate.opsForValue().set(MAIL_MESSAGE_ID + msg.getMessageID(),"1");
            }catch (Exception e){
                e.printStackTrace();
            }

        });
        return contentInfoList;
    }

//    public static String getContentType(MimeMessage msg) throws MessagingException {
//        String originContentType = msg.getContentType();
//        String contentType = originContentType.
//    }


    /**
     * 获得邮件主题
     * @param msg 邮件内容
     * @return 解码后的邮件主题
     */
    public static String getSubject(MimeMessage msg) throws UnsupportedEncodingException, MessagingException {
        return MimeUtility.decodeText(msg.getSubject());
    }

    /**
     * 获得邮件发件人
     * @param msg 邮件内容
     * @return 姓名 <Email地址>
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public static String getFrom(MimeMessage msg) throws MessagingException, UnsupportedEncodingException {
        String from = "";
        Address[] froms = msg.getFrom();
        if (froms.length < 1){
            throw new MessagingException("没有发件人!");
        }
        InternetAddress address = (InternetAddress) froms[0];
        String person = address.getPersonal();
        if (person != null) {
            person = MimeUtility.decodeText(person) + " ";
        } else {
            person = "";
        }
        from = person + "<" + address.getAddress() + ">";

        return from;
    }

    /**
     * 根据收件人类型，获取邮件收件人、抄送和密送地址。如果收件人类型为空，则获得所有的收件人
     * <p>Message.RecipientType.TO  收件人</p>
     * <p>Message.RecipientType.CC  抄送</p>
     * <p>Message.RecipientType.BCC 密送</p>
     * @param msg 邮件内容
     * @param type 收件人类型
     * @return 收件人1 <邮件地址1>, 收件人2 <邮件地址2>, ...
     * @throws MessagingException
     */
    public static String getReceiveAddress(MimeMessage msg, Message.RecipientType type) throws MessagingException {
        StringBuffer receiveAddress = new StringBuffer();
        Address[] addresss = null;
        if (type == null) {
            addresss = msg.getAllRecipients();
        } else {
            addresss = msg.getRecipients(type);
        }

        if (addresss == null || addresss.length < 1){
            throw new MessagingException("没有收件人!");
        }
        for (Address address : addresss) {
            InternetAddress internetAddress = (InternetAddress)address;
            receiveAddress.append(internetAddress.toUnicodeString()).append(",");
        }

        receiveAddress.deleteCharAt(receiveAddress.length()-1); //删除最后一个逗号

        return receiveAddress.toString();
    }

    /**
     * 获得邮件发送时间
     * @param msg 邮件内容
     * @return yyyy年mm月dd日 星期X HH:mm
     * @throws MessagingException
     */
    public static String getSentDate(MimeMessage msg, String pattern) throws MessagingException {
        Date receivedDate = msg.getSentDate();
        if (receivedDate == null){
            return "";
        }
        if (pattern == null || "".equals(pattern)){
            pattern = "yyyy年MM月dd日 E HH:mm ";
        }
        return new SimpleDateFormat(pattern).format(receivedDate);
    }

    /**
     * 判断邮件中是否包含附件
     * @param part 邮件内容
     * @return 邮件中存在附件返回true，不存在返回false
     * @throws MessagingException
     * @throws IOException
     */
    public static boolean isContainAttachment(Part part) throws MessagingException, IOException {
        boolean flag = false;
        if (part.isMimeType("multipart/*")) {
            MimeMultipart multipart = (MimeMultipart) part.getContent();
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                String disp = bodyPart.getDisposition();
                if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {
                    flag = true;
                } else if (bodyPart.isMimeType("multipart/*")) {
                    flag = isContainAttachment(bodyPart);
                } else {
                    String contentType = bodyPart.getContentType();
                    if (contentType.indexOf("application") != -1) {
                        flag = true;
                    }

                    if (contentType.indexOf("name") != -1) {
                        flag = true;
                    }
                }

                if (flag) {
                    break;
                }
            }
        } else if (part.isMimeType("message/rfc822")) {
            flag = isContainAttachment((Part)part.getContent());
        }
        return flag;
    }

    /**
     * 判断邮件是否已读
     * @param msg 邮件内容
     * @return 如果邮件已读返回true,否则返回false
     * @throws MessagingException
     */
    public static boolean isSeen(MimeMessage msg) throws MessagingException {
        return msg.getFlags().contains(Flags.Flag.SEEN);
    }

    /**
     * 判断邮件是否需要阅读回执
     * @param msg 邮件内容
     * @return 需要回执返回true,否则返回false
     * @throws MessagingException
     */
    public static boolean isReplySign(MimeMessage msg) throws MessagingException {
        boolean replySign = false;
        String[] headers = msg.getHeader("Disposition-Notification-To");
        if (headers != null){
            replySign = true;
        }
        return replySign;
    }

    /**
     * 获得邮件的优先级
     * @param msg 邮件内容
     * @return 1(High):紧急  3:普通(Normal)  5:低(Low)
     * @throws MessagingException
     */
    public static String getPriority(MimeMessage msg) throws MessagingException {
        String priority = "普通";
        String[] headers = msg.getHeader("X-Priority");
        if (headers != null) {
            String headerPriority = headers[0];
            if (headerPriority.indexOf("1") != -1 || headerPriority.indexOf("High") != -1){
                priority = "紧急";
            }
            else if (headerPriority.indexOf("5") != -1 || headerPriority.indexOf("Low") != -1){
                priority = "低";
            }
            else {
                priority = "普通";
            }
        }
        return priority;
    }



    /**
     * 获得邮件文本内容
     * @param part 邮件体
     * @param content 存储邮件文本内容的字符串
     * @throws MessagingException
     * @throws IOException
     */
    public static void getMailTextContent(Part part, StringBuffer content) throws MessagingException, IOException {
        //如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断
        boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;
        if (part.isMimeType("text/*") && !isContainTextAttach) {
            content.append(part.getContent().toString());
        } else if (part.isMimeType("message/rfc822")) {
            getMailTextContent((Part)part.getContent(),content);
        } else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                getMailTextContent(bodyPart,content);
            }
        }
    }

    /**
     * 判断指定邮件是否已同步
     * @param messageId 邮件id
     * @return
     */
    boolean isSynMessage(String messageId){
        String redisKey = MAIL_MESSAGE_ID + messageId;
        //首先判断redis中是否有
        if(redisTemplate.hasKey(redisKey)){
            return true;
        }
        //再判断mysql中是否存在
        MailContentInfo query = new MailContentInfo();
        query.setMailMessageId(messageId);
        List<MailContentInfo> contentInfoList = mailContentInfoMapper.selectMailContentInfoList(query);
        if(contentInfoList != null && contentInfoList.size() > 0){
            return true;
        }
        return false;
    }
}
