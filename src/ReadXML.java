
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class ReadXML {






    public void ReadData(){
        MarcDump md=new MarcDump();

        try {
            File inputFile = new File("C:\\Users\\Enian Tafa\\IdeaProjects\\ReadXML\\src\\TEST.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
           // System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("record");
          //  System.out.println("----------------------------");

            MarcRecord marcRecord=new MarcRecord();
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
               // System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;


                    //System.out.println("record"
                         //   + eElement.getAttribute("record"));



                    String ib =  eElement
                            .getElementsByTagName("ib")
                            .item(0)
                            .getTextContent();


                    marcRecord.ib=ib;
                    //System.out.println("ISBN: "+ib);






                    String in = eElement
                            .getElementsByTagName("in")
                            .item(0)
                            .getTextContent();

                   marcRecord.in=in;
                    //System.out.println("ISSN: "+in);






                    String di = eElement
                            .getElementsByTagName("di")
                            .item(0)
                            .getTextContent();

                    marcRecord.di=di;
                    //System.out.println("Input Date: "+di);





                    String ju = eElement
                            .getElementsByTagName("ju")
                            .item(0)
                            .getTextContent();

                    marcRecord.ju=ju;
                    //System.out.println("Date of Publication: "+ju);





                    String TA = eElement
                            .getElementsByTagName("TA")
                            .item(0)
                            .getTextContent();
                    marcRecord.TA=TA;
                    //System.out.println("lANGUAGEcODE: "+TA);







                    String ti = eElement
                            .getElementsByTagName("ti")
                            .item(0)
                            .getTextContent();

                    marcRecord.ti=ti;
                    //System.out.println("title: "+ti);
                    NodeList nodes = doc.getElementsByTagName("ti");
                    int mti = nodes.getLength();
                    marcRecord.mti=mti;






                    String lw = eElement
                            .getElementsByTagName("lw")
                            .item(0)
                            .getTextContent();

                    marcRecord.lw=lw;
                    //System.out.println("Lead Word: "+lw);




                    String ed= eElement
                            .getElementsByTagName("ed")
                            .item(0)
                            .getTextContent();
                    marcRecord.ed=ed;
                    //System.out.println("edition: "+ed);






                    String pl= eElement
                            .getElementsByTagName("pl")
                            .item(0)
                            .getTextContent();
                    marcRecord.pl=pl;
                    //System.out.println("placeofpublication: "+pl);






                    String ui= eElement
                            .getElementsByTagName("ui")
                            .item(0)
                            .getTextContent();

                    marcRecord.ui=ui;
                    //System.out.println("publisher: "+ui);





                    String pa= eElement
                            .getElementsByTagName("pa")
                            .item(0)
                            .getTextContent();
                    marcRecord.pa=pa;
                    //System.out.println("pagination: "+pa);





                    String il= eElement
                            .getElementsByTagName("il")
                            .item(0)
                            .getTextContent();
                    marcRecord.il=il;
                    //System.out.println("Ilustration: "+il);





                    String fo= eElement
                            .getElementsByTagName("fo")
                            .item(0)
                            .getTextContent();
                    marcRecord.fo=fo;
                    //System.out.println("Dimension: "+fo);





                    String bm= eElement
                            .getElementsByTagName("bm")
                            .item(0)
                            .getTextContent();
                    marcRecord.bm=bm;
                    //System.out.println("accompanying_material: "+bm);





                    String re= eElement
                            .getElementsByTagName("re")
                            .item(0)
                            .getTextContent();
                    marcRecord.re=re;
                    //System.out.println("series: "+re);





                    String rn= eElement
                            .getElementsByTagName("rn")
                            .item(0)
                            .getTextContent();
                    marcRecord.rn=rn;


                    String an= eElement
                            .getElementsByTagName("an")
                            .item(0)
                            .getTextContent();
                    marcRecord.an=an;
                    //System.out.println("notes: "+an);

                    NodeList nodes1 = doc.getElementsByTagName("an");
                    int man = nodes1.getLength();
                    marcRecord.man=man;





                    String sa= eElement
                            .getElementsByTagName("sa")
                            .item(0)
                            .getTextContent();
                    marcRecord.sa=sa;
                    //System.out.println("abstract: "+sa);

                    NodeList nodes2 = doc.getElementsByTagName("sa");
                    int msa = nodes2.getLength();
                    marcRecord.msa=msa;






                    String tr= eElement
                            .getElementsByTagName("tr")
                            .item(0)
                            .getTextContent();
                    marcRecord.tr=tr;

                    NodeList nodes3 = doc.getElementsByTagName("tr");
                    int mtr = nodes3.getLength();
                    marcRecord.mtr=mtr;
                    //System.out.println("subjectterm: "+tr);






                    String cc = eElement
                            .getElementsByTagName("cc")
                            .item(0)
                            .getTextContent();
                    marcRecord.cc=cc;
                    //System.out.println("classnumber: "+cc);




                    String au= eElement
                            .getElementsByTagName("au")
                            .item(0)
                            .getTextContent();
                    marcRecord.au=au;
                    //System.out.println("author: "+au);

                    NodeList nodes4 = doc.getElementsByTagName("au");
                    int mau = nodes4.getLength();
                    marcRecord.mau=mau;




                    String ca= eElement
                            .getElementsByTagName("ca")
                            .item(0)
                            .getTextContent();
                    marcRecord.ca=ca;
                    //System.out.println("Corporateauthor: "+ca);
                    NodeList nodes5 = doc.getElementsByTagName("ca");
                    int mca = nodes5.getLength();
                    marcRecord.mca=mca;




                    md.proccesRecord(marcRecord);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

}
}
