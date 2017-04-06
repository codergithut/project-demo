package poi;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/3/17
 * @description
 */

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExportDocImpl
{
    public void testWord(){
        try{
            FileInputStream in = new FileInputStream("D:\\entity\\dataBase.doc");//载入文档
            POIFSFileSystem pfs = new POIFSFileSystem(in);
            HWPFDocument hwpf = new HWPFDocument(pfs);
            Range range = hwpf.getRange();//得到文档的读取范围
            TableIterator it = new TableIterator(range);
            EntityUtil.Propertie pro;
            List<EntityUtil.Propertie> pros=null;
            EntityUtil entity;

            //迭代文档中的表格
            while (it.hasNext()) {
                Table tb = (Table) it.next();
                //迭代行，默认从0开始
                pros=new ArrayList<EntityUtil.Propertie>();
                TableRow tableName = tb.getRow(0);
                String className = getTdValue(tableName.getCell(0));
                for (int i = 1; i < tb.numRows(); i++) {
                    TableRow tr = tb.getRow(i);
                    pro=new EntityUtil.Propertie();
                    if(tr.numCells()>6){
                        TableCell name = tr.getCell(2);//取得单元格
                        TableCell typeName = tr.getCell(3);
                        TableCell constraint = tr.getCell(7);
                        TableCell describe = tr.getCell(1);
                        TableCell number = tr.getCell(4);
                        pro.setPropertiesName(getTdValue(name));
                        pro.setPropertiesType(getTdValue(typeName));
                        pro.setPropertiesConstraint(getTdValue(constraint));
                        pro.setPropertiesDescribe(getTdValue(describe));
                        pro.setPropertiesNumber(getTdValue(number));
                        pros.add(pro);
                    }
                }
                if(pros.size()>0){
                    entity = new EntityUtil(className,className,pros);
                    entity.getBeanByData();
                    entity.getSqlByData();
                }
            } //end while
        }catch(Exception e){
            e.printStackTrace();
        }
    }//end method

    private String getTdValue(TableCell tableCell){
        String result=null;
        for(int k=0;k<tableCell.numParagraphs();k++){
            Paragraph para = tableCell.getParagraph(k);
             result = para.text().trim();
        }
        return result;
    }


    public void testWord1(){
        try {
            //word 2003： 图片不会被读取
            InputStream is = new FileInputStream(new File("D:\\entity\\dataBase.doc"));
            WordExtractor ex = new WordExtractor(is);
            String text2003 = ex.getText();
            System.out.println(text2003);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        ExportDocImpl exportDoc=new ExportDocImpl();
        exportDoc.testWord();

    }
}
