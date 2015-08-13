/**
 * Surface.java
 *
 * @author Pranav Lakshminarayanan
 */
package org.artoolkit.ar.base.rendering;

import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLES10;
import java.io.IOException;
import org.artoolkit.ar.base.readers.STLReader;

/**
 * Representation of any surface.
 */
public class Surface extends Shape {

    // CONSTRUCTORS ============================================================
    /**
     * Default constructor will throw IOException demanding file.
     *
     * @throws IOException
     */
    public Surface() throws IOException {
        throw new IOException("Initialize Surface using a filename");
    }

    /**
     * Constructor for Surface class.
     *
     * @param filename name of file representing surface
     */
    public Surface(String filename) throws IOException {
        this.setArrays(filename);
    }

    // METHODS =================================================================
    /**
     *
     * @param filename
     */
    private void setArrays(String filename) throws IOException {

        STLReader or = new STLReader(filename);
        
        float[] vertices = or.getVertices();
        float[] colors;
        short[] indices = or.getIndices();

        mVertexBuffer = RenderUtils.buildFloatBuffer(vertices);
        mColorBuffer = RenderUtils.buildFloatBuffer(null);
        mIndexBuffer = RenderUtils.buildShortBuffer(indices);

    }

    /**
     * Create surface from arrays.
     *
     * NOTES: 
     *  Length of each color indicator: 4 
     *  Length of each vertex: 3 
     *  Number of elements: 18
     *
     * @param unused unused
     */
    public void draw(GL10 unused) {

        GLES10.glColorPointer(4, GLES10.GL_FLOAT, 0, mColorBuffer);
        GLES10.glVertexPointer(3, GLES10.GL_FLOAT, 0, mVertexBuffer);

        GLES10.glEnableClientState(GLES10.GL_COLOR_ARRAY);
        GLES10.glEnableClientState(GLES10.GL_VERTEX_ARRAY);

        GLES10.glDrawElements(
                GLES10.GL_TRIANGLES, 36, 
                GLES10.GL_UNSIGNED_SHORT, mIndexBuffer);
        
        GLES10.glDisableClientState(GLES10.GL_COLOR_ARRAY);
        GLES10.glDisableClientState(GLES10.GL_VERTEX_ARRAY);

    }

}
