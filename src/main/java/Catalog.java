import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private String name;
    private String path;
    private List<Document> documents = new ArrayList<>();

    public Catalog(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public void add(Document doc) {
        documents.add(doc);
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
}