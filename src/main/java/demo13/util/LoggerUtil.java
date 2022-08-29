package demo13.util;

import java.util.logging.*;

/**
 * @author 25043
 */
public class LoggerUtil {
    /**
     * 定义全局logger
     */
    private static final Logger logger = Logger.getLogger("myLogger");
    /**
     * 对外提供获取Logger的方法
     * 将日志级别设置为Level.INFO，将记录严重消息。
     * ERROR、WARN、INFO、ALL
     * WARN level表明会出现潜在错误的情形。
     * ERROR:它可能是系统的一个错误，比较严重
     * Level ALL是最低等级的，用于打开所有日志记录。
     * Warning级别：这可能是一个问题，或许不是。举个例子，如果环境中的信息出现变化，比如数据库连接断开，这
     * 个场景应该被记录为Warning，而非Error。通过观察Warning日志能够让我们快速找到导致错误的起始原因，
     * Warning使用时需要特别注意，放置使它变得没有意义。在服务端应用断开连接的场景下，
     * 使用Warning是比较合适的，但是如果在一个桌面程序上，当连接断开时进行Warning，就没有太大必要，因为这个场景经常出现，使用Info就足够了。
     * OFF Level是最高等级的，用于关闭所有日志记录。
     * Info级别：在正常情况下需要被记录的重要信息，例如：系统初始化成功，服务启动或者停止以及成功的处理了重要的业务。
     * 等级排序：OFF，SEVERE，WARNING，INFO...ALL（从高到低）
     *
     * @return 返回Logger对象
     */
    public static Logger getLogger() {
        //设置日志等级
        logger.setLevel(Level.ALL);
        for (Handler h : logger.getHandlers()) {
            //防止出现多个日志文件
            h.close();
        }
        try {
            //日志文件的输出路径
            FileHandler fileHandler = new FileHandler("D:\\log.txt", true);
            //设置日志的输出格式
            fileHandler.setFormatter(new MyFormat());
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return logger;
    }
}