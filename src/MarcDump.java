import java.io.*;


public class MarcDump {

    int i;
    int m;
    int length;
    int startpos;


    public String field_separator ;
    public String record_separator;
    public java.lang.String record_label;
    public java.lang.String directory;
    public java.lang.String data_fields;
    public java.lang.String buffer;
    public java.lang.String tag;
    public java.lang.String dummytag;
    public java.lang.String lang1;
    public java.lang.String lang2;





    public String title = "Unimarc uitvoer";


    public void proccesRecord(MarcRecord marcRecord) {


        //put fields in buffer

        RecordIndentifier();

        ISBN(marcRecord);


        ISSN(marcRecord);


        GeneralProcessing(marcRecord);


        LanguageOfText(marcRecord);


        TitleField(marcRecord);


        EditionStatement(marcRecord);


        Publication(marcRecord);


        PhysicalDescription(marcRecord);

        Series(marcRecord);


        Notes(marcRecord);


       Summary(marcRecord);


      UncontrolledSubjectTerms(marcRecord);


        UDC(marcRecord);


      // PersonalName(marcRecord);


       CorporateBodyName(marcRecord);


        OriginatingSource(marcRecord);




        // compile MARC record
        CompileMARCRecord();



    }
//initialise variables

    public MarcDump()

    {

         field_separator = "^";
        record_separator = "\\";
        record_label = "";
        data_fields = "";
        directory = "";
    }





   // Record identifier. Mandatory field.
    //Here we put the ADLIB record number
    //No indicators, no subfields

    public void RecordIndentifier(){
        buffer = "%0";
        tag = "001";
        UpdateDirectory();
    }







ReadXML readXML=new ReadXML();

         //ISBN
        //USa is ISBN

    public void ISBN(MarcRecord marcRecord)  {


     if (!marcRecord.ib.equals("")){
            buffer = "##\u001Fa" + marcRecord.ib;
            tag = "010";
            UpdateDirectory();
        }
    }







         //ISSN
        //a is ISSN

        public void ISSN(MarcRecord marcRecord)
    {

            dummytag = marcRecord.in;
            if (!dummytag.equals("")) {
                buffer = "##\u001Fa" + dummytag;
                tag = "011";
                UpdateDirectory();
            }
    }








    //General processing data
        //Fixed field, 36 positions.
        public void GeneralProcessing(MarcRecord marcRecord) {

         // pos 0 - 7: input date in format YYYYMMD


            buffer = buffer + "##\u001Fa" + marcRecord.di.substring(0,4) + marcRecord.di.substring(5,6)+ marcRecord.di.substring(7,8);

        /* pos 8 - 16: publication date*/


            if (marcRecord.ju.length() == 4) {  /* only a 4-digit year can be added*/
            buffer = buffer + "d" + marcRecord.ju + "####";
        } else {
         // unknown year of publication
         buffer = buffer + "#########";
      }

      // pos 17 - 19: target audience code
      buffer = buffer + "###";

      // pos 20: government publication
      buffer = buffer + "u"; // 'unknown'

      // pos 21: modified record code
      buffer = buffer + "0";

      // pos 22 - 24: language of cataloguing
      buffer = buffer + "alb";   // = albanian

      // pos 25: transliteration code
      buffer = buffer + "a"; // ISO

      // pos 26 - 29: character sets
         // pos 26 - 27: G0 set: 01 is ISO 646
         buffer = buffer + "01";
         // pos 28 - 29: G1 set
         buffer = buffer + "##";

      // pos 30 - 33: additional character sets: blank
      buffer = buffer + "####";

      // pos 34 - 35: script of title - 'ba' = latin*/
            buffer = buffer + "ba";

            // and paste it:
            tag = "100";

            UpdateDirectory();
        }








      //Language
      //      a is language of text

            public void LanguageOfText(MarcRecord marcRecord){

                 if (!marcRecord.TA.equals("")) {


                 if (lang1 == "de") {lang2 = "ger";}                            // german

           if (lang1 == "el") {lang2 = "gre";}                             // greek
           if (lang1 == "en") {lang2 = "eng";}                           // english
         if (lang1 == "eo") lang2 = "esp";                        // espreranto
         if (lang1 == "fr") lang2 = "fre";                            // french
         if (lang1 == "it") lang2 = "ita";                           // italian
         if (lang1 == "la") lang2 = "lat";                             // latin
         if (lang1 == "nl") lang2 = "dut";                             // dutch
         if (lang1 == "ru") lang2 = "rus";                           // russian
         if (lang1 == "sh") lang2 = "scr";            // serbo-croatian (roman)
         if (lang1 == "sq") lang2 = "alb";                          //albanian
         if (lang2 == "") {
            // assume default
             lang2.equals("alb");
         }
         buffer = "##\u001Fa" + lang2;
         tag = "101";
          UpdateDirectory();

}}








        //title field
                public void TitleField(MarcRecord marcRecord)
                {
            int i = 1;

                while (i <= marcRecord.mti) {
                    if (i == 1) {
                        buffer = "1#\u001Fa";
                        if (marcRecord.lw != "") {
                            buffer = buffer + marcRecord.lw + "";
                        }
                    } else {
                        buffer = buffer + "";
                    }
                    buffer = buffer + marcRecord.ti;
                    i = i + 1;
                }

      // update directory and data_fields
           tag = "200";
                    UpdateDirectory();
                 }










        //Edition statement
        //a is Edition statement

                public void EditionStatement(MarcRecord marcRecord){



                    if (!marcRecord.ed.equals("")) {
                    buffer = "##\u001Fa" + marcRecord.ed;
                    tag = "205";
                        UpdateDirectory();
                }
             }













                        //Publication, distribution, etc.
                        //a is Place of publication
                        //c is Name of publisher
                        //d is Date of Publication

           public void Publication(MarcRecord marcRecord){

               if (!marcRecord.pl.equals("")) {
                buffer = "\u001Fa" + marcRecord.pl;
            }

               if (!marcRecord.ui.equals("")) {
                buffer = buffer + "\u001Fc" + marcRecord.ui;
            }

               if (!marcRecord.ju.equals("")) {
                buffer = buffer + "\u001Fd" + marcRecord.ju;
            }
            if (!buffer.equals("")){
                    buffer = "##" + buffer;
                    tag = "210";
                   UpdateDirectory();

                }
    }











        //Physical description
        //a is specific material designation (number of pages)
        //c is other physical details (illustrations)
        //d is dimensions
        //e is accompanying material

        public void PhysicalDescription(MarcRecord marcRecord) {


            if (!marcRecord.pa.equals("")) {
                    buffer = "\u001Fa" + marcRecord.pa;
                }

            if (!marcRecord.il.equals("")) {
                    buffer = buffer + "\u001Fb" + marcRecord.il;
                }

            if (!marcRecord.fo.equals("")) {
                    buffer = buffer + "\u001Fc" + marcRecord.fo;
                }
            if (!marcRecord.bm.equals("")) {
                    buffer = buffer + "\u001Fd" + marcRecord.bm;
                }
                if (!buffer.equals("")) {
                    buffer = "##" + buffer;
                    tag = "215";
                UpdateDirectory();
                }
            }
















        //Series
        //a is series title
        //h is number of a part

                    public void Series(MarcRecord marcRecord)
                    {
                        if (!marcRecord.re.equals("")) {
                        buffer = "\u001Fa" + marcRecord.re;
                    }

                        if (!marcRecord.rn.equals("")) {
                        buffer = buffer + "\u001Fh" + marcRecord.rn;
                    }
                    if (!buffer.equals("")) {
                        buffer = "##" + buffer;
                        tag = "225";
                        UpdateDirectory();
                    }

                    }









       // Notes
        //a is text of note. Not repeatable.

       public void Notes(MarcRecord marcRecord){


        int i = 1;
        while (i <= marcRecord.man) {
        if (i == 1) {
        buffer = "##\u001Fa";
        } else {
        buffer = buffer + ". - ";
        }
        buffer = buffer + marcRecord.an;
        i = i + 1;
        }
        if (!buffer.equals("")) {
        tag = "300";
               UpdateDirectory();
        }
}













       // Summary or abstract
//a is summary. Not repeatable.

        public void Summary(MarcRecord marcRecord){


        int i=1;
        while(i<=marcRecord.msa){
        if(i==1){
        buffer= "##\u001Fa";
        }else{
        buffer=buffer+"";
        }
        buffer=buffer+marcRecord.sa;
        i=i+1;
        }
        if(!buffer.equals("")){
        tag="330";
         UpdateDirectory();
        }

        }











        //uncontrolled subject terms
        public void UncontrolledSubjectTerms(MarcRecord marcRecord){


        i = 1;
        while (i <= marcRecord.mtr) {
        if (i == 1) {
        buffer = "0#";
        }
        buffer = buffer + "\u001Fa" + marcRecord.tr;
        i = i + 1;
        }
        if (!buffer.equals("")) {
        tag = "610";
            UpdateDirectory();
        }
}










        //UDC
        //According to the Unimarc specifications, 675 is not repeatable.
        //Therefor we will only take the first UDC code.

      public void UDC(MarcRecord marcRecord)
        {
            if (!marcRecord.cc.equals(""))
            {
        buffer = "##\u001Fa" + marcRecord.cc;
        tag = "675";
        UpdateDirectory();
        }
    }









       // personal name - alternative intellectual responsibility
        //This tag is used when the cataloguing system does not distinguish
        //between primary and secondary responsibility
        //a is last name
        //b is first name or initials
        //The Unimarc specifications are not clear whether to add a comma
        //after the last name. In some examples we see a comma, in some examples
        //we don't. In this adapl we don't use a comma.


      public void PersonalName(MarcRecord marcRecord){

        i=1;

        tag="701";
        while(i<=marcRecord.mau){


            int index = marcRecord.au.indexOf(",",1);
            int endindex= marcRecord.au.length();
        buffer= "#1\u001Fa" +marcRecord.au.substring(0,index);

            marcRecord.au.substring(index,endindex);

            String str = marcRecord.au.substring(index,endindex);
        buffer=buffer+ "\u001Fb" +str.trim();
        i=i+1;
        UpdateDirectory();
        }
        }











       // corporate body name - alternative intellectual responsibility
       // This tag is used when the cataloguing system does not distinguish
       // between primary and secondary responsibility

        public void CorporateBodyName(MarcRecord marcRecord)
        {
        i=1;

        tag="711";
        while(i<=marcRecord.mca){
        buffer="02\u001Fa"+marcRecord.ca;
        i=i+1;

        UpdateDirectory();
        }
        }







        // originating source
        //a is country code, according to ISO 3166 standard
        //b is agency
        //(g is cataloguing rules, but ISBD is not mentioned in list of available
        //codes)

        public void OriginatingSource(MarcRecord marcRecord)
        {
            buffer= "#0\u001Faal\u001FbNational Library";
        tag="801";

            UpdateDirectory();
        }









        // update directory and data fields
        //the first position in the data part is 0!

       public void UpdateDirectory() {

       if (!buffer.equals("")) {

           buffer = buffer + field_separator;
           int a= buffer.length();
           String numberAsString = String.valueOf(a);
           StringBuilder sb = new StringBuilder();
           while(sb.length()+numberAsString.length()<=4) {
               sb.append('0');
           }
           sb.append(a);
           String paddedNumberAsString = sb.toString();


           int b= data_fields.length();
           String numberAsString1 = String.valueOf(b);
           StringBuilder sb1 = new StringBuilder();
           while(sb1.length()+numberAsString1.length()<=5) {
               sb1.append('0');
           }
           sb1.append(a);
           String paddedNumberAsString1 = sb1.toString();

           directory = directory + tag + paddedNumberAsString + "~" + paddedNumberAsString1;

           data_fields = data_fields + buffer;
        buffer = "";
        }
         }








        //compile MARC record
       // Marc record consists of:
        //record label (length = 24, pos 0 - 23)
        //directory
        //data fields
        //end of record


public String CompileMARCRecord()  {
    // close record by adding an end-of-record to data part
    data_fields = data_fields + record_separator;

     // pos 0 - 4: length of entire record
        length=24+directory.length()+field_separator.length()+data_fields.length();
    int a= length;
    String numberAsString = String.valueOf(a);
    StringBuilder sb = new StringBuilder();
    while(sb.length()+numberAsString.length()<=5) {
        sb.append('0');
    }
    sb.append(a);
    String paddedNumberAsString = sb.toString();

        record_label=paddedNumberAsString;

    // pos 5 = record status. For now we use 'n' for 'new record'
    record_label = record_label + "n";

    // pos 6 = type of record. We use 'a' for 'language materials, printed'
    record_label = record_label + "a";

    //pos 7 = bibliographic level. We use a 'm' for monographic
    record_label = record_label + "m";

    // pos 8 = hierarchical level code. '#' is undefined
    record_label = record_label + "#";

    // pos 9 = undefined -> a blank
    record_label = record_label + "";

    // pos 10 = indicator length. Always '2' in Unimarc
    record_label = record_label + "2";

    // pos 11 = subfield indicator length. Always '2' in Unimarc
    record_label = record_label + "2";

    // pos 12 - 16 = base address of data
   length = 24 + directory.length() + field_separator.length();

    int b= length;
    String numberAsString1 = String.valueOf(b);
    StringBuilder sb1 = new StringBuilder();
    while(sb1.length()+numberAsString1.length()<=5) {
        sb1.append('0');
    }
    sb.append(b);
    String paddedNumberAsString1 = sb.toString();
      record_label = record_label +  paddedNumberAsString1;

    // pos 17 = encoding level. '#' is full level
    record_label = record_label + "#";

    // pos 18 = descriptive cataloguing form. '#' is full ISBD
    record_label = record_label + "#";

    // pos 19 is undefined -> a blank
    record_label = record_label + "";

    // pos 20 is length of 'length of field'. 4 in Unimarc
    record_label = record_label + "4";

    // pos 21 is length of 'Starting character position'. 5 in Unimarc
    record_label = record_label + "5";

    // pos 22 is length of implementation defined portion. 0 in Unimarc
    record_label = record_label + "0";

    // pos 23 is undefined -> a blank
    record_label = record_label + "";

    // glue everything together in one big record*/

    String marc = record_label + directory + field_separator + data_fields + record_separator;




        System.out.print(marc);


        return marc;
    }


    public void Writer()
            throws IOException {

        String str = CompileMARCRecord();
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Enian Tafa\\IdeaProjects\\MarcDump\\src\\MARC.txt"));
        writer.write(str);

        writer.close();
    }
}

