package common_toolkits.sftputil;

import com.jcraft.jsch.SftpProgressMonitor;

import java.io.File;
import java.math.BigDecimal;

/**
 * @author luzj
 * @description: 进度监控模块
 * @date 2018/3/19
 */
public class MyProgressMonitor implements SftpProgressMonitor {
    private long transfered;
    private long percent;
    private long total;

    @Override
    public void init(int op, String src, String dest, long max) {
        System.out.println("begin transfer!");
        System.out.println("percent:0%");
        System.out.println("max:"+max);
        total = max;
    }

    @Override
    public boolean count(long count) {
        transfered += count;
//        System.out.println("Currently transferred total size: " + transfered + " bytes");
        percent = (long) (div(transfered,total,2)*100);
//        System.out.println(percent);
        System.out.println("current percent:" + percent + "%");

//        if (percent >20) {
//            System.out.println("current percent:" + percent + "%");
//            return true;
//        }else
//            return false;
        return true;
    }

    @Override
    public void end() {
        System.out.println("end transfer!");
        System.out.println("percent 100%");
    }

    /**
     * double数值精确除法
     *
     * @param dividend
     * @param divisor
     * @param len      保留位数
     * @return
     */
    private static double div(double dividend, double divisor, int len) {
        BigDecimal big1 = new BigDecimal(dividend);
        BigDecimal big2 = new BigDecimal(divisor);
        return big1.divide(big2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
