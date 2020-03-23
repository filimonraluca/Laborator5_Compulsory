import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private String name;
    private String path;
    private List<Document> documents = new ArrayList<>();

    public Catalog() {
    }

    public Catalog(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public void add(Document doc) throws NotUniqueIdException{
        for (Document d:documents)
        {
            if(d.getId().equals(doc.getId()))
                throw new NotUniqueIdException();
        }
        documents.add(doc);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    /**
     * Parcurge toate documentele din Catalogul curent
     * si il returneaza pe cel cu id-ul dat ca parametru
     *
     * @param  id   un id asociat unui document
     * @return      documentul cu id-ul specificat
     */
    public Document findById(String id) {
        for (Document document:documents)
            if (document.getId().equals(id))
                return document;
        return null;
    }

    public String getPath() {
        return path;
    }

    /**
     * Aceasta metoda creaza un fisier de cu extensia .html
     * in care scrie informatii despre un catalog folsind
     * metoda toHtml ce returneaza aceste informatii intre
     * taguri de paragraf.
     */

    public void reportHtml()
    {
        File file = new File("./report.html");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(this.toHtml());
            for (Document document:documents) {
                bw.write(document.toHtml());
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String toHtml()
    {
        return "<p> Catalogul " + name  +
                " cu path-ul: " + path  +
                " si documentele: " + "</p>";
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", documents=" + documents +
                '}';
    }
}