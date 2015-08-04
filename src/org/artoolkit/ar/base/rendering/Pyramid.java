/**
 *  Pyramid.java
 *  Custom class for pyramid shaped surface
 */

/**
 * @author Pranav Lakshminarayanan
 */
package org.artoolkit.ar.base.rendering;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES10;

/**
 * Simple class to render a colored cube.
 */
public class Pyramid {

    /**
     * Vertices.
     */
    private FloatBuffer mVertexBuffer;
    
    /**
     * Colors for each vertex.
     */
    private FloatBuffer mColorBuffer;
    
    /**
     * Indices to construct faces.
     */
    private ByteBuffer mIndexBuffer;

    /**
     * Default constructor for Pyramid.
     */
    public Pyramid() {
        this(1.0f);
    }

    /**
     * Pyramid centered at origin with height specified.
     * @param height height of pyramid
     */
    public Pyramid(float height) {
        this(height, 0.0f, 0.0f, 0.0f);
    }

    /**
     * Pyramid centered at (x,y,z) with height specified.
     * @param height height of pyramid
     * @param x x-coordinate of center of base
     * @param y y-coordinate of center of base
     * @param z z-coordinate of center of base
     */
    public Pyramid(float height, float x, float y, float z) {
        setArrays(height, x, y, z);
    }

    /**
     * Specify vertices, colors, and faces of pyramid.
     * @param height height of pyramid
     * @param x x-coordinate of center of base
     * @param y y-coordinate of center of base
     * @param z z-coordinate of center of base
     */
    private void setArrays(float height, float x, float y, float z) {

        float hs = height / 2.0f;

        float[] vertices = {
            x - hs, y - hs, z,  // 0
            x + hs, y - hs, z,  // 1
            x + hs, y + hs, z,  // 2
            x - hs, y + hs, z,  // 3
            x, y, z + height    // 4, peak
        };

        float c = 1.0f;
        float[] colors = {
            0, 0, 0, c, // 0 black
            c, 0, 0, c, // 1 red
            c, c, 0, c, // 2 yellow
            0, c, 0, c, // 3 green
            0, 0, c, c  // 4 blue
        };

        byte[] indices = {
            0, 1, 2, 0, 2, 3,
            0, 4, 1, 1, 4, 2,
            2, 4, 3, 3, 4, 0 
        };

        mVertexBuffer = RenderUtils.buildFloatBuffer(vertices);
        mColorBuffer = RenderUtils.buildFloatBuffer(colors);
        mIndexBuffer = RenderUtils.buildByteBuffer(indices);

    }

    /**
     * Create surface from arrays.
     * 
     * NOTES: 
     *  Length of each color indicator: 4
     *  Length of each vertex:          3
     *  Number of elements:             18
     * 
     * @param unused 
     */
    public void draw(GL10 unused) {
        
        GLES10.glColorPointer(4, GLES10.GL_FLOAT, 0, mColorBuffer);
        GLES10.glVertexPointer(3, GLES10.GL_FLOAT, 0, mVertexBuffer);

        GLES10.glEnableClientState(GLES10.GL_COLOR_ARRAY);
        GLES10.glEnableClientState(GLES10.GL_VERTEX_ARRAY);

        GLES10.glDrawElements(
                GLES10.GL_TRIANGLES, 18, GLES10.GL_UNSIGNED_BYTE, mIndexBuffer);
                
        GLES10.glDisableClientState(GLES10.GL_COLOR_ARRAY);
        GLES10.glDisableClientState(GLES10.GL_VERTEX_ARRAY);

    }

}
