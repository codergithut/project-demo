import org.json.JSONObject;
import org.json.XML;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/6/22
 * @description
 */
public class Test {
    public static void main(String[] args) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<Message>\n" +
                "    <Head>\n" +
                "        <ASID>AS100</ASID>\n" +
                "        <AreaCode>320684</AreaCode>\n" +
                "        <BizMsgID>320684170518003619</BizMsgID>\n" +
                "<DigitalSign>cb63dbf9cd279a9a30c3ac388b170f09323a06974d2dda8ad997a5082c0d8be331b4c3ce0d7b7274edc98340ee2eabf58c80b7501e87c1c8408c7ddd271ef920615aa8da2cfbee6af60b61aa9cd287c09d08ac744875adfd5e92c653cdc5051c90d514920d3c77ef34777025fef5cc69c0a5451757aabfffa75c1c4ce32c7ed3</DigitalSign>\n" +
                "        <CertCount>0</CertCount>\n" +
                "        <CreateDate>2017-05-18T15:53:59</CreateDate>\n" +
                "        <EstateNum>320684400203GG00005W00000000</EstateNum>\n" +
                "        <ParcelID>320684400203GG00005</ParcelID>\n" +
                "        <PreCertID>苏(2017)海门市不动产证明第0000035号</PreCertID>\n" +
                "        <PreEstateNum>320684400203GG00005W00000000</PreEstateNum>\n" +
                "        <ProofCount>0</ProofCount>\n" +
                "        <RecFlowID>15IF53508TX3171L</RecFlowID>\n" +
                "        <RecType>9000101</RecType>\n" +
                "        <RegOrgID>111223344</RegOrgID>\n" +
                "        <RegType>900</RegType>\n" +
                "        <RightType>24</RightType>\n" +
                "    </Head>\n" +
                "    <Data>\n" +
                "        <ZTT_GY_QLR BDCDYH=\"320684400203GG00005W00000000\" BDCQZH=\"苏(2017)海门市不动产证明第0000035号\" GJ=\"142\" GYFS=\"0\" QLRLX=\"1\" QLRMC=\"南京银行\" QXDM=\"320684\" SFCZR=\"0\" SXH=\"1\" YSDM=\"6003000000\" ZJH=\"1231232\" ZJZL=\"3\"/>\n" +
                "        <QLF_QL_DYAQ BDBZZQSE=\"234.0000\" BDCDJZMH=\"苏(2017)海门市不动产证明第0000035号\" BDCDYH=\"320684400203GG00005W00000000\" DBR=\"陈杨\" DJJG=\"海门市不动产登记中心\" DJLX=\"900\" DJSJ=\"2017-05-18T09:23:29\" DJYY=\"合同设立\" DYBDCLX=\"2\" DYFS=\"1\" DYJELX=\"1\" DYR=\"羊羊羊\" QSZT=\"1\" QXDM=\"320684\" SCYWH=\"15HJ59414LX3130L\" YSDM=\"6002030100\" YWH=\"15HK0900L3X3130N\" ZGZQSE=\"0.0\" ZWLXJSSJ=\"2017-05-31T00:00:00\" ZWLXQSSJ=\"2017-05-01T00:00:00\" ZXDYYWH=\"20170517200900760\" ZXDYYY=\"合同设立\" ZXSJ=\"2017-05-18T15:47:25\"/>\n" +
                "        <DJT_DJ_SLSQ AJZT=\"2\" DJDL=\"900\" DJXL=\"9999804\" JSSJ=\"2017-05-18T15:57:25\" QXDM=\"320684\" SFWTAJ=\"0\" SLRY=\"陈杨\" SLSJ=\"2017-05-18T15:53:59\" SQFBCZ=\"0\" SQZSBS=\"0\" YSDM=\"6004010000\" YWH=\"15IF53508TX3171L\" ZL=\"无居民1\"/>\n" +
                "        <DJF_DJ_SJ QXDM=\"320684\" SFBCSJ=\"0\" SFEWSJ=\"0\" SFSJSY=\"1\" SJLX=\"2\" SJMC=\"q文档\" SJSJ=\"2017-05-18T15:53:50\" SJSL=\"1\" YS=\"1\" YSDM=\"6004030000\" YWH=\"15IF53508TX3171L\"/>\n" +
                "        <DJF_DJ_SQR QLRMC=\"南京银行\" QLRZJH=\"1231232\" QLRZJZL=\"3\" QXDM=\"320684\" YSDM=\"6004020000\" YWH=\"15IF53508TX3171L\" YWRMC=\"羊羊羊\" YWRZJH=\"123123\" YWRZJZL=\"3\"/>\n" +
                "    </Data>\n" +
                "</Message>";

        /* 第一种方法，使用JSON-JAVA提供的方法 */
        //将xml转为json
        JSONObject xmlJSONObj = XML.toJSONObject(xml);
        //设置缩进
        String jsonPrettyPrintString = xmlJSONObj.toString(4);
        //输出格式化后的json
        System.out.println(jsonPrettyPrintString);

    }
}