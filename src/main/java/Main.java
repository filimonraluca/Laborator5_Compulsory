import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        Main app = new Main();
        app.testCreateSave();
        //app.testLoadView();
        /*try
        {
            app.shellTest(args);
        }
        catch (UnknownCommandException | IllegalNrOfArgsException e)
        {
            e.printStackTrace();
        }*/

       // shellTestWithClasses(args);
    }

    /**
     * Aceasta metoda permite citirea comenzilor de la
     * tastatura, identificarea lor si executarea
     */

    private void shellTest(String args[]) throws UnknownCommandException, IllegalNrOfArgsException {
        CatalogUtil catalogUtil = new CatalogUtil();
        if (args[0].equals("load"))
        {
            if (args.length!=2) {
                throw new IllegalNrOfArgsException();
            }
            else
            {
                Catalog catalog = null;
                try {
                    catalog = CatalogUtil.load(args[1]);
                } catch (InvalidCatalogException e) {
                    e.printStackTrace();
                }
            }
        }
        else if (args[0].equals("view")){
            if (args.length!=3){
                throw new IllegalNrOfArgsException();
            }
            else{
                Catalog catalog = null;
                try {
                    catalog = CatalogUtil.load(args[1]);
                } catch (InvalidCatalogException e) {
                    e.printStackTrace();
                }
                Document doc = catalog.findById(args[2]);
                CatalogUtil.view(doc);
            }
        }
        else if (args[0].equals("list")){
            if (args.length!=2){
                throw new IllegalNrOfArgsException();
            }
            else {
                Catalog catalog = null;
                try {
                    catalog = CatalogUtil.load(args[1]);
                } catch (InvalidCatalogException e) {
                    e.printStackTrace();
                }
                List<Document> list = catalog.getDocuments();
                System.out.println(list);
            }

        }
        else {
            throw new UnknownCommandException();
        }
    }


    /**
     * Aceasta metoda permite citirea comenzilor de la
     * tastatura, identificarea lor si executarea folosind
     * clasele corespunzatoare (CommandLoad, CommandView, CommandList)
     */


    private static void shellTestWithClasses(String[] args)
    {
        if (args[0].equals("list")) {
            Commands listComm = new CommandList(args);
            try {
                listComm.execute();
            } catch (IllegalNrOfArgsException e) {
                e.printStackTrace();
            }
        }
        if (args[0].equals("load")) {
            Commands loadComm = new CommandLoad(args);
            try {
                loadComm.execute();
            } catch (IllegalNrOfArgsException e) {
                e.printStackTrace();
            }
        }
        if (args[0].equals("view")) {
            Commands viewComm = new CommandView(args);
            try {
                viewComm.execute();
            } catch (IllegalNrOfArgsException e) {
                e.printStackTrace();
            }
        }
    }

    private void testCreateSave() {
        Catalog catalog =
                new Catalog("Java Resources", "./catalog.xml");
        Document doc1 = new Document("java1", "Java Course 1",
                "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        Document doc2 = new Document("java1", "Java Course 1",
                "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        doc1.addTag("type", "Slides");
        try {
            catalog.add(doc1);
            catalog.add(doc2);
        } catch (NotUniqueIdException e) {
            e.printStackTrace();
        }
        CatalogUtil.save(catalog);
    }


    private void testLoadView()  {
        Catalog catalog = null;
        try {
            catalog = CatalogUtil.load("./catalog.xml");
        } catch (InvalidCatalogException e) {
            e.printStackTrace();
        }
        Document doc = catalog.findById("java1");
        CatalogUtil.view(doc);
    }

}