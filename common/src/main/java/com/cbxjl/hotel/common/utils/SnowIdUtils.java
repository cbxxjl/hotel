package com.cbxjl.hotel.common.utils;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huawei
 */
public class SnowIdUtils {
    /**
     * 私有的 静态内部类
     */
    private static class SnowFlake {

        /**
         * 内部类对象（单例模式）
         */
        private static final SnowFlake SNOW_FLAKE = new SnowFlake();
        /**
         * 起始的时间戳
         */
        private final long START_TIMESTAMP = 1557489395327L;
        /**
         * 序列号占用位数
         */
        private final long SEQUENCE_BIT = 12;
        /**
         * 机器标识占用位数
         */
        private final long MACHINE_BIT = 10;
        /**
         * 时间戳位移位数
         */
        private final long TIMESTAMP_LEFT = SEQUENCE_BIT + MACHINE_BIT;
        /**
         * 最大序列号  （4095）
         */
        private final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);
        /**
         * 最大机器编号 （1023）
         */
        private final long MAX_MACHINE_ID = ~(-1L << MACHINE_BIT);
        /**
         * 生成id机器标识部分
         */
        private long machineIdPart;
        /**
         * 序列号
         */
        private long sequence = 0L;
        /**
         * 上一次时间戳
         */
        private long lastStamp = -1L;

        /**
         * 构造函数初始化机器编码
         */
        private SnowFlake() {
            //模拟这里获得本机机器编码
            long localIp = 9527;
            InetAddress ia = null;
            try {
                ia = InetAddress.getLocalHost();
                localIp = ipToLong(ia.getHostAddress());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            //localIp & MAX_MACHINE_ID最大不会超过1023,在左位移12位
            machineIdPart = (localIp & MAX_MACHINE_ID) << SEQUENCE_BIT;
            ia = null;
        }

        /**
         * 获取雪花ID
         */
        public synchronized long nextId() {
            long currentStamp = timeGen();
            //避免机器时钟回拨
            if (currentStamp < lastStamp) {
                // //服务器时钟被调整了,ID生成器停止服务.
                throw new RuntimeException(String.format("时钟已经回拨.  Refusing to generate id for %d milliseconds", lastStamp - currentStamp));
            }
            if (currentStamp == lastStamp) {
                // 每次+1
                sequence = (sequence + 1) & MAX_SEQUENCE;
                // 毫秒内序列溢出
                if (sequence == 0) {
                    // 阻塞到下一个毫秒,获得新的时间戳
                    currentStamp = getNextMill();
                }
            } else {
                //不同毫秒内，序列号置0
                sequence = 0L;
            }
            lastStamp = currentStamp;
            //时间戳部分+机器标识部分+序列号部分
            return (currentStamp - START_TIMESTAMP) << TIMESTAMP_LEFT | machineIdPart | sequence;
        }

        /**
         * 阻塞到下一个毫秒，直到获得新的时间戳
         */
        private long getNextMill() {
            long mill = timeGen();
            //
            while (mill <= lastStamp) {
                mill = timeGen();
            }
            return mill;
        }

        /**
         * 返回以毫秒为单位的当前时间
         */
        protected long timeGen() {
            return System.currentTimeMillis();
        }
    }

    /**
     * 获取long类型雪花ID
     */
    public static long uniqueLong() {
        return SnowFlake.SNOW_FLAKE.nextId();
    }

    /**
     * 获取String类型雪花ID
     */
    public static String uniqueLongHex() {
        return String.format("%016x", uniqueLong());
    }

    /**
     * ip转long型
     *
     * @param ip ip地址
     * @return long
     */
    public static long ipToLong(String ip) {
        String[] ipArray = ip.split("\\.");
        List<Long> ipNums = new ArrayList<>();
        for (int i = 0; i < 4; ++i) {
            ipNums.add(Long.parseLong(ipArray[i].trim()));
        }
        return ipNums.get(0) * 256L * 256L * 256L +
                ipNums.get(1) * 256L * 256L +
                ipNums.get(2) * 256L +
                ipNums.get(3);
    }

    public static String getIP(long ipaddr) {
        long y = ipaddr % 256;
        long m = (ipaddr - y) / (256 * 256 * 256);
        long n = (ipaddr - 256 * 256 * 256 * m - y) / (256 * 256);
        long x = (ipaddr - 256 * 256 * 256 * m - 256 * 256 * n - y) / 256;
        return m + "." + n + "." + x + "." + y;
    }

//    /**
//     * 测试
//     */
//    public static void main(String[] args) throws InterruptedException {
//        //计时开始时间
//        long start = System.currentTimeMillis();
//        final CountDownLatch latch = new CountDownLatch(100);
//        final Map<Long, Integer> map = new ConcurrentHashMap<>();
//        for (int i = 0; i < 100; i++) {
//            //创建100个线程
//            new Thread(() -> {
//                for (int s = 0; s < 2000; s++) {
//                    long snowID = SnowIdUtils.uniqueLong();
//                    System.out.println("生成雪花ID=" + snowID);
//                    Integer put = map.put(snowID, 1);
//                    if (put != null) {
//                        throw new RuntimeException("主键重复");
//                    }
//                }
//                latch.countDown();
//            }).start();
//        }
//        latch.await();
//        System.out.println("生成20万条雪花ID总用时=" + (System.currentTimeMillis() - start));
//    }
}