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
		// 创建SAXReader对象
		SAXReader saxReader = new SAXReader();
		try {
			// 通过saxReader的read方法加载books.xml对象
			Document document = saxReader.read(new File("books.xml"));
			// 获取根节点
			Element bookStore =  document.getRootElement();
			// 获取迭代器
			Iterator<Element> it = bookStore.elementIterator();
			while(it.hasNext()) {
				index++;
				System.out.println("===========开始解析第" + index + "本书=================");
				Element book = it.next();
				List<Attribute> attrList = book.attributes();
				for(Attribute attr : attrList) {
					String name = attr.getName();
					String value = attr.getValue();
					System.out.println("属性名：" + name + "--属性值：" + value);
				}
				Iterator<Element> it2 = book.elementIterator();
				while(it2.hasNext()) {
					Element node = it2.next();
					String name = node.getName();
					String value = node.getStringValue();
					System.out.println("节点名：" + name + "--节点值：" + value);
				}
				System.out.println("===========结束解析第" + index + "本书=================");
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createXml() {
		// 1.创建document对象，代表整个xml文档
		Document document = DocumentHelper.createDocument();
		// 2.创建根节点
		Element rss = document.addElement("rss");
		rss.addAttribute("version", "2.0");
		Element channel = rss.addElement("channel");
		Element title = channel.addElement("title");
		title.setText("第一条新闻");
		File file = new File("rss.xml");
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("utf-8");
			XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
			// 设置是否转义，默认值是true，代表转义
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
