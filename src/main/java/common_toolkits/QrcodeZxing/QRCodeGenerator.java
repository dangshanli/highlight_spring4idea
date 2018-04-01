package common_toolkits.QrcodeZxing;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * @author luzj
 * @description: 使用Google.zxing 生成二维码
 * 2.二维码可以一 是图片的形式、也可以是byte[]字节码形式
 * 3.二维码可以包含URL，字符串信息：电话号码、邮件地址，地理位置
 * @date 2018/4/1
 */
public class QRCodeGenerator {

    public static final String QR_CODE_IMAGE_PATH = "C:\\sftp_test\\myQRCode.png";//保存二维码图片的位置

    /**
     * 生成二维码图片
     * @param text 二维码包含的信息字符串，可以使URL，电话号码等
     * @param width 图片宽度
     * @param height 图片高度
     * @param filePath 图片保存路径
     * @throws WriterException 生成二维码失败
     * @throws IOException 图片位置IO错误
     */
    public void generatorQRCodeImage(String text,int width,int height,String filePath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE,width,height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix,"PNG",path);
    }


    /**
     * 生成二维码以字节数组形式，这在web应用中常见，后台传给前台的是字节数组，放在response的body中
     * @param text
     * @param width
     * @param height
     * @return 二维码字节数组
     * @throws WriterException
     * @throws IOException
     */
    public byte[] getQRCodeBytes(String text,int width,int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text,BarcodeFormat.QR_CODE,width,height);
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix,"PNG",pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray();
        return pngData;

    }


    /**
     * 读取二维码信息
     * @param qrCodeImage 二维码图片路径
     * @return
     * @throws IOException
     */
    public String decodeQrCode(File qrCodeImage) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(qrCodeImage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));

        try {
            Result result = new MultiFormatReader().decode(binaryBitmap);
            return result.getText();
        } catch (NotFoundException e) {
            System.err.println("这张图不是二维码");
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
   /*     try {
            new QRCodeGenerator().generatorQRCodeImage("https://www.baidu.com",350,350,QR_CODE_IMAGE_PATH);
        } catch (WriterException e) {
            System.err.println("生成二维码失败");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("二维码路径错误");
            e.printStackTrace();
        }*/

        try {
            String result = new QRCodeGenerator().decodeQrCode(new File(QR_CODE_IMAGE_PATH));
            System.out.println(result);
        } catch (IOException e) {
            System.err.println("二维码路径不对");
            e.printStackTrace();
            return;
        }
    }
}
