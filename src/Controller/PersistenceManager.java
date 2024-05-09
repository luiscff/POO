package Controller;

import Model.Model;

import java.io.*;

public class PersistenceManager {

    public void saveState (String filename, Model model) throws IOException {

            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(model);
            oos.flush();
            oos.close();
    }

    public Model loadState (String filename) throws IOException, ClassNotFoundException {

            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Model model = (Model) ois.readObject();
            ois.close();
            return model;

    }


}

