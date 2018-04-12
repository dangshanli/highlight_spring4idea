package common_toolkits.parseExcel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luzj
 * @description: 解析Excel业务代码，业务逻辑:
 * <p>
 * 1.总体思路：
 * 将Excel解析成 KpiInfoVo数据
 * <p>
 * 将标红的记录制作成一条记录插入table：
 * KpiInfoVo添加level字段
 * 针对标红的记录寻找他的父指标，用于生成parent_id字段
 * 根据空格以及父子关系决定is_space字段
 * 其他的都比较简单
 * <p>
 * ord重排
 * @date 2018/4/10
 */
@Service
public class ParseKpiExcel {

    public static final String SINGLE_SPACE = " ";

    /**
     * 构建完整的KpiInfoVo的数据结构
     * @param excel
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    public List<KpiInfoVo> constructKpiInfo(File excel) throws IOException, InvalidFormatException {
        List<KpiInfoVo> kpiInfoVos = null;
        kpiInfoVos = addParentKpiCode(makeKpiInfoVo(excelToList(excel)));
        return kpiInfoVos;
    }

    /**
     * 解析Excel中的数据到list中
     *
     * @param excel
     * @return 解析结果集合
     * @throws IOException
     * @throws InvalidFormatException
     */
    private List<KpiInfoVo> excelToList(File excel) throws IOException, InvalidFormatException {
        List<KpiInfoVo> KpiInfoVos = new ArrayList<>();
        Workbook wb = WorkbookFactory.create(excel);
        //todo 打印workbook信息
        System.out.println(wb.getNumberOfSheets() + "个sheet");
        System.out.println("sheet name:");
        wb.forEach(sheet -> {
            System.out.println("\t" + sheet.getSheetName());
        });

        //todo 提取Excel中的内容
        Sheet sheet = wb.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();
        sheet.forEach(row -> {
            KpiInfoVo KpiInfoVo = new KpiInfoVo();
            row.forEach(cell -> {
                if (cell.getRowIndex() >= 1) {
                    if (cell.getColumnIndex() == 0 && cell != null) {
                        KpiInfoVo.setKpiName(dataFormatter.formatCellValue(cell));
                        KpiInfoVo.setIsAdd(isRed(cell));
                    }
                    if (cell.getColumnIndex() == 1 && cell != null)
                        KpiInfoVo.setKpiCode(dataFormatter.formatCellValue(cell));
                    if (cell.getColumnIndex() == 2 && cell != null)
                        KpiInfoVo.setUnit(dataFormatter.formatCellValue(cell));
                }
            });
            KpiInfoVo.setOrd(row.getRowNum() * 10);
            KpiInfoVos.add(KpiInfoVo);
        });
//        System.out.println(KpiInfoVos);
        return KpiInfoVos;
    }

    private List<KpiInfoVo> makeKpiInfoVo(List<KpiInfoVo> KpiInfoVos) {
        for (KpiInfoVo k : KpiInfoVos) {
            int index = StringUtils.lastIndexOf(k.getKpiName(), SINGLE_SPACE);
            if (index == -1)
                k.setLevel(1);
            else if (index == 1)
                k.setLevel(2);
            else
                k.setLevel(3);
        }

        return KpiInfoVos;
    }

    /**
     * 对进行了指标分级的指标集，里面的标红指标加入父指标kpiCode
     *
     * @param KpiInfoVos
     * @return 加入父指标后的指标集
     */
    private static List<KpiInfoVo> addParentKpiCode(List<KpiInfoVo> KpiInfoVos) {
        //      当前指标序列的一级指标    当前指标序列的二级指标
        KpiInfoVo currentOneLevel = null, currentTwoLevel = null;

        for (KpiInfoVo k : KpiInfoVos) {
            if (k.getLevel() == 1)
                currentOneLevel = k;
            if (k.getLevel() == 2)
                currentTwoLevel = k;

            if (k.getIsAdd() == 1) {
                if (k.getLevel() == 3 && currentTwoLevel != null)
                    k.setParentKpiCode(currentTwoLevel.getKpiCode());
                if (k.getLevel() == 2 && currentOneLevel != null)
                    k.setParentKpiCode(currentOneLevel.getKpiCode());
                if (k.getLevel() == 1)
                    k.setParentKpiCode(null);
            }
        }

        for (KpiInfoVo l : KpiInfoVos) {
            System.out.println(l);
        }
        return KpiInfoVos;
    }

    /**
     * 判断单元格是否为红色
     *
     * @param cell
     * @return 红色：1 非红色：0
     */
    private int isRed(Cell cell) {
        XSSFCellStyle cellStyle = (XSSFCellStyle) cell.getCellStyle();
        XSSFColor color = cellStyle.getFillForegroundColorColor();
        byte[] bytes = null;
        if (color != null && color instanceof XSSFColor) {
            bytes = color.getARGB();
            if (bytes != null) {
                java.awt.Color awtColor = new java.awt.Color(change(bytes[1]), change(bytes[2]), change(bytes[3]), change(bytes[0]));
                if (awtColor.equals(java.awt.Color.RED))
                    return 1;
            }
        }
        return 0;
    }

    /**
     * 将poi的argb转化成awt的标准argb数值
     *
     * @param number
     * @return
     */
    private int change(int number) {
        return ((number & 0x0f0) >> 4) * 16 + (number & 0x0f);
    }

}
