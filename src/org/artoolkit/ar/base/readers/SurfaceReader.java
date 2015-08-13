/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.artoolkit.ar.base.readers;

import java.io.FileNotFoundException;
import java.io.IOException;

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

    // METHODS =================================================================
    /**
     * Read file into SurfaceReader object.
     *
     * @param filename file to read
     * @throws FileNotFoundException if file not found
     * @throws IOException if error while reading
     */
    protected abstract void read(String filename)
            throws FileNotFoundException, IOException;

    /**
     * Check if file type is valid.
     *
     * @param filename file to read
     * @return true if valid file type, false otherwise
     */
    protected abstract boolean checkFileType(String filename);

}
