package com.example.soap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.Iterator;
import java.util.List;

public class SoapUtil {


    public static void main(String[] args) throws DocumentException {
        System.out.println("开始解析soap...");

        String deptXML = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<soap:Envelope\n" +
                "    xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
                "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "    xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n" +
                "    <soap:Body>\n" +
                "        <GetNewDataResponse\n" +
                "            xmlns=\"sobey.com.LBControlService/\">\n" +
                "            <GetNewDataResult>\n" +
                "                <RtnValue>\n" +
                "                    <ClientState IP=\"172.21.40.99\" LASTTIME=\"2012-07-06 15:50:58\" />\n" +
                "                    <GenaralInfo DeviceID=\"470\" Status=\"0\" DataCaptureIP=\"xw-netmanage-2\" DataCaptureTime=\"2012-07-06 15:23:20\" >\n" +
                "                        <key> 22 </key>\n" +
                "                        <key> 2 3 </key>\n" +
                "                    </GenaralInfo>\n" +
                "                    <GenaralInfo DeviceID=\"470\" Status=\"0\" DataCaptureIP=\"xw-netmanage-2\" DataCaptureTime=\"2012-07-06 15:23:22\" >\n" +
                "                        <key22> 24 </key22>\n" +
                "                    </GenaralInfo>\n" +
                "                    <GenaralInfo DeviceID=\"469\" Status=\"0\" DataCaptureIP=\"xw-netmanage-2\" DataCaptureTime=\"2012-07-06 15:24:04\" >\n" +
                "                        <key> 55 </key>\n" +
                "                    </GenaralInfo>\n" +
                "                </RtnValue>\n" +
                "            </GetNewDataResult>\n" +
                "        </GetNewDataResponse>\n" +
                "    </soap:Body>\n" +
                "</soap:Envelope>";

        Document document = DocumentHelper.parseText(deptXML);
        Element rootElement = document.getRootElement();
        // 获取Body下的所有子节点
        List<?> bodyChild = rootElement.elements();
        Iterator<?> iterator = bodyChild.iterator();

        Element clientState = rootElement.element("Body")
                .element("GetNewDataResponse").element("GetNewDataResult").element("RtnValue")
                .element("ClientState");
        System.out.println(clientState.attribute("IP").getValue());

        List<Element> elements = rootElement.element("Body")
                .element("GetNewDataResponse").element("GetNewDataResult").element("RtnValue").elements("GenaralInfo");
        for (Element element : elements) {
            Iterator it = element.elements().iterator();
            while (it.hasNext()) {
                Element next = (Element)it.next();
                System.out.println(next.getName());
                System.out.println(next.getText());
            }

        }

        recursionParse(iterator);
    }

    private static void recursionParse(Iterator<?> iterator) {
        while (iterator.hasNext()) {
            Element element = (Element) iterator.next();
            System.out.println(element.getName()+"==="+element.getText());
            recursionParse(element.elements().iterator());
        }
    }
    
}

<dependency>
    <groupId>dom4j</groupId>
    <artifactId>dom4j</artifactId>
    <version>1.6.1</version>
</dependency>
