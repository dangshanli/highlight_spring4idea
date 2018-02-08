package common_toolkits.twent_useful_segments;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

/**
 * @author luzj
 * @package common_toolkits.twent_useful_segments
 * @description: 遍历路径下的文件文件列表，添加过滤器功能
 * @date 2018/2/8 10:14
 */
public class DirectoryOperator {

    /**
     * 1.直接列出目录下的文件名
     * 2.添加名称过滤器的列出文件列表
     * 3.添加过滤器的列出目录下的File对象
     *
     * @param directory
     */
    public void showFileList(String directory) {
        File dir = new File(directory);

        //todo 列出目录下的文件以及目录名，打印
        String[] children = dir.list();
        printFileList(children);

        //todo 加入fileName过滤功能，以`.`开头的文件过滤掉
        FilenameFilter filenameFilter = new FilenameFilter() {
            //重写accept，指定返回规则,过滤掉以`片`结尾的文件名
            @Override
            public boolean accept(File dir, String name) {
                return !name.startsWith(".") && !name.endsWith("片");
            }
        };
        children = dir.list(filenameFilter);
        printFileList(children);

        //todo 子目录可以作为File文件对象，然后遍历
        File[] files = dir.listFiles();
        //过滤掉非目录的文件
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        };
        files = dir.listFiles(fileFilter);
        for (File file : files) {
            System.out.print(file.getName()+"  ");
        }
    }

    /**
     * 打印文件列表
     *
     * @param children
     */
    private static void printFileList(String[] children) {
        if (children != null) {
            for (String child : children) {
                System.out.print(child + "  ");
            }
            System.out.println();
        } else {
            System.out.println(children + " 不是目录或者不包含文件!!!");
        }
    }

    public static void main(String[] args) {
        new DirectoryOperator().showFileList("F:\\testio");
    }

}
