package common_toolkits.twent_useful_segments;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author luzj
 * @package common_toolkits.twent_useful_segments
 * @description: 专门讲使用Thumbnail工具包处理图片，对图片进行缩小、裁剪、旋转等
 * @date 2018/2/1 16:38
 */
public class JavaSegments2 {

    /**
     * 创建固定大小的图 width:400 height:500
     * @param from
     * @param to
     */
    public void createConstantPic(String from,String to){
        try {
            Thumbnails.of(from)
                    .size(400, 500)
                    .toFile(to);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("整完了，看看["+to+"]目录是否成功");
        }
    }

    /**
     * 常用的Thumbnail api,比例缩小，旋转，转格式
     * @param from
     * @param to
     */
    public void createScalePic(String from,String to){
        try {
            Thumbnails.of(from)
                    .scale(0.25f)//todo 按比例缩小0.25左右
                    .rotate(180)//旋转180度
                    .outputFormat("png")//转换图片格式
                    .toFile(to);//输出到文件
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("传输完结，查看目录["+to+"]");
        }
    }

    /**
     * 将缩略图写到输出流
     * @param from 图片来源
     * @param to 图片输出流来源
     * @return
     * @throws IOException
     */
    private OutputStream createOutStream(String from,String to) throws IOException {
        OutputStream os = new FileOutputStream(to);
        Thumbnails.of(from)
                .scale(0.1)
                .toOutputStream(os);
        return os;
    }

    /**
     * 将缩略图输出到BufferedImage
     * @param from 图片来源
     * @param to 图片出口
     * @throws IOException
     */
    private void createBufferedImage(String from,String to) throws IOException {
        BufferedImage bufferedImage = Thumbnails.of(from)
                .scale(0.1f)
                .asBufferedImage();
        ImageIO.write(bufferedImage,"png",new File(to));
    }

    public static void main(String[] args) {
        JavaSegments2 segments2 = new JavaSegments2();
        String from = "F:\\壁纸\\13.jpg",to="F:\\yy.png";
//        segments2.createConstantPic(from,to);
        segments2.createScalePic(from,to);
    }

}
