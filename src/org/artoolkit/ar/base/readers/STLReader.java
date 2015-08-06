/**
 * STLReader.java
 *
 * @author Pranav Lakshminarayanan
 */
package org.artoolkit.ar.base.readers;

import android.content.res.AssetManager;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Pranav
 */
public class STLReader {

    // GLOBAL VARIABLES ========================================================
    /**
     * Array of vertices.
     */
    private float[] vertices;

    /**
     * Array of indices making up faces.
     */
    private float[] indices;

    // CONSTRUCTORS ============================================================
    /**
     * Default constructor will throw IOException demanding filename.
     *
     * @throws IOException if filename not specified
     */
    public STLReader() throws IOException {
        throw new IOException("Initialize OBJReader with filename");
    }

    /**
     * Constructor using file location as parameter.
     *
     * @param filename - string of file location.
     * @throws FileNotFoundException if file not found
     * @throws IOException if error while reading
     */
    public STLReader(String filename)
            throws FileNotFoundException, IOException {
        this.read(filename);
    }

    // ACCESSORS ===============================================================
    /**
     * Accessor method for vertex array.
     *
     * @return this.vertices
     */
    public float[] getVertices() {
        return this.vertices;
    }

    /**
     * Accessor method for index array.
     *
     * @return this.indices
     */
    public float[] getIndices() {
        return this.indices;
    }

    // METHODS =================================================================
    /**
     * Read file and populate arrays.
     *
     * @param filename file name, including path
     * @throws FileNotFoundException if file path wrong
     * @throws IOException if error while reading
     */
    private void read(String filename)
            throws FileNotFoundException, IOException {
        
        BufferedReader r = new BufferedReader(new FileReader(filename));

        ArrayList<Float> vList = new ArrayList<Float>();
        ArrayList<Float> iList = new ArrayList<Float>();

        String line;
        while ((line = r.readLine()) != null) {
            String[] ln = line.split(" ");
            if (ln[0].toLowerCase().equals("v")) {
                for (int i = 1; i < ln.length; i++) {
                    vList.add(Float.parseFloat(ln[i]));
                }
            } else if (ln[0].toLowerCase().equals("f")) {
                for (int i = 1; i < ln.length; i++) {
                    iList.add(Float.parseFloat(ln[i]));
                }
            }
        }
        r.close();

        vertices = new float[vList.size()];
        vertices = toFloatArray(vList);

        indices = new float[iList.size()];
        indices = toFloatArray(iList);
        
    }

    /**
     * Convert list to array of generic type.
     * @param fl List of Floats
     * @return array of floats
     */
    private float[] toFloatArray(ArrayList<Float> fl) {
        float[] f = new float[fl.size()];
        for (int i = 0; i < fl.size(); i++) {
            f[i] = (float) fl.get(i);
        }
        return f;
    }

}
