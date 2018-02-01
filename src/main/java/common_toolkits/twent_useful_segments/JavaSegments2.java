package common_toolkits.twent_useful_segments;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;

/**
 * @author luzj
 * @package common_toolkits.twent_useful_segments
 * @description: 专门讲使用Thumbnail工具包处理图片，对图片进行缩小、裁剪、旋转等
 * @date 2018/2/1 16:38
 */
public class JavaSegments2 {

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

    public static void main(String[] args) {
        JavaSegments2 segments2 = new JavaSegments2();
        String from = "F:\\壁纸\\13.jpg",to="F:\\xx.jpg";
        segments2.createConstantPic(from,to);
    }

}
