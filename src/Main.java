import java.io.*;


public class Main {

    public static void main(String[] args) throws IOException {


        MarcDump mc = new MarcDump();

        //put fields in buffer


         ReadXML readXML=new ReadXML();
           readXML.ReadData();

        //mc.UpdateDirectory();
        // compile MARC record
        mc.CompileMARCRecord();
        mc.Writer();


        }
    }



