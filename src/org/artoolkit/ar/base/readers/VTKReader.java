/**
 * VTKReader.java
 *
 * @author Pranav Lakshminarayanan
 */
package org.artoolkit.ar.base.readers;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Pranav
 */
public class VTKReader extends SurfaceReader {

    // CONSTRUCTORS ============================================================
    /**
     * Default constructor will throw IOException demanding filename.
     *
     * @throws IOException if filename not specified
     */
    public VTKReader() throws IOException {
        throw new UnsupportedOperationException("Reader not supported");
    }

    /**
     * Constructor using file location as parameter.
     *
     * @param filename - string of file location.
     * @throws FileNotFoundException if file not found
     * @throws IOException if error while reading
     */
    public VTKReader(String filename)
            throws FileNotFoundException, IOException {
        throw new UnsupportedOperationException("Reader not supported");
    }

    // METHODS =================================================================
    @Override
    protected void read()
            throws FileNotFoundException, IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
