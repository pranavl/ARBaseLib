/**
 * SurfaceReader.java
 *
 * @author Pranav Lakshminarayanan
 */

package org.artoolkit.ar.base.readers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author Pranav
 */
public abstract class SurfaceReader {

    // GLOBAL VARIABLES ========================================================
    /**
     * Array of indices making up faces.
     */
    protected float[] vertices;

    /**
     * Array of indices making up faces.
     */
    protected short[] indices;
    
    /**
     * Name of the file being read.
     */
    protected String filename;

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
    public short[] getIndices() {
        return this.indices;
    }

    /**
     * Accessor method for file name.
     * 
     * @return name of file being read, null if not specified
     */
    public String getFilename() {
        return this.filename;
    }
    
    // METHODS =================================================================
    /**
     * Read file into SurfaceReader object.
     *
     * @param filename name of file being read
     * @throws FileNotFoundException if file not found
     * @throws IOException if error while reading
     */
    protected abstract void read(String filename)
            throws FileNotFoundException, IOException;
    
    /**
     * Read file into SurfaceReader object.
     *
     * @param is InputStream of file being read
     * @throws FileNotFoundException if file not found
     * @throws IOException if error while reading
     */
    protected abstract void read(InputStream is)
            throws FileNotFoundException, IOException;

    /**
     * Check if file type is valid.
     * 
     * @param exten expected file extension
     * @return true if extensions match, false otherwise
     */
    public boolean checkFileType(String exten) {
        String ext = this.filename.substring(this.filename.lastIndexOf("."));
        if (ext.toLowerCase().equals(exten)) {
            return true;
        }
        return false;
    }
    
}
