/**
 * STLReader.java
 *
 * @author Pranav Lakshminarayanan
 */
package org.artoolkit.ar.base.readers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;

/**
 *
 * @author Pranav
 */
public class STLReader extends SurfaceReader {

    // CONSTRUCTORS ============================================================
    /**
     * Constructor using file location as parameter.
     *
     * @param file string of file location.
     * @throws FileNotFoundException if file not found
     * @throws IOException if error while reading
     */
    public STLReader(String file)
            throws FileNotFoundException, IOException {
        this.filename = file;
        if (this.checkFileType(".stl")) {
            this.read(this.filename);
        } else {
            throw new IOException("Incorrect file type");
        }
    }

    /**
     * Constructor using file location as parameter.
     *
     * @param is InputStream from file
     * @throws FileNotFoundException if file not found
     * @throws IOException if error while reading
     */
    public STLReader(InputStream is)
            throws FileNotFoundException, IOException {
        this.read(is);

    }

    // METHODS =================================================================
    @Override
    protected final void read(String file)
            throws FileNotFoundException, IOException {

        BufferedReader r = new BufferedReader(new FileReader(file));

        ArrayList<Float> vList = new ArrayList<Float>();

        String line;
        while ((line = r.readLine()) != null) {
            String[] ln = line.trim().split(" ");
            if (ln[0].toLowerCase().equals("vertex")) {
                for (int i = 1; i < ln.length; i++) {
                    vList.add(Float.parseFloat(ln[i]));
                }
            }
        }
        r.close();

        this.vertices = new float[vList.size()];
        this.vertices = toFloatArray(vList);

        this.indices = new short[this.vertices.length / 3];
        for (int j = 0; j < this.indices.length; j++) {
            this.indices[j] = (short) j;
        }

    }

    // In Android applications, use InputStream from /assets
    @Override
    protected final void read(InputStream is)
            throws FileNotFoundException, IOException {

        // Convert input stream to String
        byte[] b = new byte[is.available()];
        is.read(b);
        String read = new String(b);

        // Read for all 
        ArrayList<Float> vList = new ArrayList<Float>();
        BufferedReader rdr = new BufferedReader(new StringReader(read));
        String line;
        while ((line = rdr.readLine()) != null) {
            String[] ln = line.trim().split(" ");
            if (ln[0].toLowerCase().equals("vertex")) {
                for (int i = 1; i < ln.length; i++) {
                    vList.add(Float.parseFloat(ln[i]));
                }
            }
        }

        rdr.close();
        this.vertices = new float[vList.size()];
        this.vertices = toFloatArray(vList);

        int indLen = this.vertices.length * 2 / 3;
        this.indices = new short[indLen];
        for (int j = 0; j < indLen / 2; j++) {
            this.indices[j] = (short) j;
            this.indices[indLen - 1 - j] = (short) j;
        }
       
    }

    // HELPER METHODS ==========================================================
    /**
     * Convert list to array of generic type.
     *
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
