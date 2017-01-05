import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.w3c.dom.stylesheets.LinkStyle;

public class DOM4J {
	public void parseXml() {
		int index = 0;
		// ����SAXReader����
		SAXReader saxReader = new SAXReader();
		try {
			// ͨ��saxReader��read��������books.xml����
			Document document = saxReader.read(new File("books.xml"));
			// ��ȡ���ڵ�
			Element bookStore =  document.getRootElement();
			// ��ȡ������
			Iterator<Element> it = bookStore.elementIterator();
			while(it.hasNext()) {
				index++;
				System.out.println("===========��ʼ������" + index + "����=================");
				Element book = it.next();
				List<Attribute> attrList = book.attributes();
				for(Attribute attr : attrList) {
					String name = attr.getName();
					String value = attr.getValue();
					System.out.println("��������" + name + "--����ֵ��" + value);
				}
				Iterator<Element> it2 = book.elementIterator();
				while(it2.hasNext()) {
					Element node = it2.next();
					String name = node.getName();
					String value = node.getStringValue();
					System.out.println("�ڵ�����" + name + "--�ڵ�ֵ��" + value);
				}
				System.out.println("===========����������" + index + "����=================");
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createXml() {
		// 1.����document���󣬴�������xml�ĵ�
		Document document = DocumentHelper.createDocument();
		// 2.�������ڵ�
		Element rss = document.addElement("rss");
		rss.addAttribute("version", "2.0");
		Element channel = rss.addElement("channel");
		Element title = channel.addElement("title");
		title.setText("��һ������");
		File file = new File("rss.xml");
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("utf-8");
			XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
			// �����Ƿ�ת�壬Ĭ��ֵ��true������ת��
			writer.setEscapeText(false);
			writer.write(document);
			writer.close();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DOM4J dom4j = new DOM4J();
		dom4j.parseXml();
		dom4j.createXml();
	}

}
