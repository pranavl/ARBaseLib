/**
 * STLSurface.java
 *
 * @author Pranav Lakshminarayanan
 */
package org.artoolkit.ar.base.rendering;

import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLES10;
import java.io.IOException;
import java.io.InputStream;
import org.artoolkit.ar.base.readers.STLReader;

/**
 * Representation of a surface read from a .STL file.
 */
public class STLSurface extends Shape {

    // FIELDS ==================================================================
    /**
     * Name of the file being represented.
     */
    private String file;
    
    // CONSTRUCTORS ============================================================
    /**
     * Default constructor will throw IOException demanding file.
     *
     * @throws IOException .
     */
    public STLSurface() throws IOException {
        throw new IOException("Initialize Surface using a filename");
    }

    /**
     * Constructor for Surface class.
     *
     * @param filename name of file representing surface
     * @throws IOException .
     */
    public STLSurface(String filename) throws IOException {
        this.file = filename;
        STLReader rdr = new STLReader(filename);
        this.setArrays(rdr);   
    }

    /**
     * Constructor for Surface class.
     *
     * @param is InputStream from file being read
     * @throws IOException .
     */
    public STLSurface(InputStream is) throws IOException {
        STLReader rdr = new STLReader(is);
        this.setArrays(rdr);
    }

    // ACCESSORS ==============================================================
    /**
     * Name of the file being represented.
     * 
     * @return filename
     */
    public String getFilename() {
        return this.file;
    }

    // METHODS =================================================================
    /**
     * Configure surface from reader.
     * 
     * @param rdr STLReader used to read STL file
     * @throws IOException .
     */
    private void setArrays(STLReader rdr) throws IOException {

        this.vertices = rdr.getVertices();
        this.indices = rdr.getIndices();

        this.colors = new float[this.indices.length * 4];
        for (int i = 0; i < colors.length; i++) {
            if ((i + 1) % 2 != 0) {
                this.colors[i] = 0;
            } else {
                this.colors[i] = 1.0f;
            }
        }

        mVertexBuffer = RenderUtils.buildFloatBuffer(this.vertices);
        mColorBuffer = RenderUtils.buildFloatBuffer(this.colors);
        mIndexBuffer = RenderUtils.buildShortBuffer(this.indices);

    }

    /**
     * Create surface from arrays.
     *
     *
     * @param unused unused
     */
    public void draw(GL10 unused) {

        GLES10.glColorPointer(4, GLES10.GL_FLOAT, 0, mColorBuffer);
        GLES10.glVertexPointer(3, GLES10.GL_FLOAT, 0, mVertexBuffer);

        GLES10.glEnableClientState(GLES10.GL_COLOR_ARRAY);
        GLES10.glEnableClientState(GLES10.GL_VERTEX_ARRAY);

        GLES10.glDrawElements(
                GLES10.GL_TRIANGLES, this.indices.length,
                GLES10.GL_UNSIGNED_SHORT, mIndexBuffer);
        
        GLES10.glDisableClientState(GLES10.GL_COLOR_ARRAY);
        GLES10.glDisableClientState(GLES10.GL_VERTEX_ARRAY);

    }

}
