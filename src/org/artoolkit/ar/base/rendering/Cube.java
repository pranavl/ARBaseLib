/**
 * Cube.java
 *
 * @author Pranav Lakshminarayanan
 */
package org.artoolkit.ar.base.rendering;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES10;

/**
 * Simple class to render a colored cube.
 */
public class Cube extends Shape {

    // CONSTRUCTORS ============================================================
    /**
     * Default constructor for Cube.
     */
    public Cube() {
        this(1.0f);
    }

    /**
     * Cube centered at origin with edge length specified.
     *
     * @param size edge length
     */
    public Cube(float size) {
        this(size, 0.0f, 0.0f, 0.0f);
    }

    /**
     * Cube centered at (x,y,z) with edge length specified.
     *
     * @param size edge length
     * @param x x-coordinate of center
     * @param y y-coordinate of center
     * @param z z-coordinate of center
     */
    public Cube(float size, float x, float y, float z) {
        setArrays(size, x, y, z);
    }

    // METHODS =================================================================
    /**
     * Cube centered at (x,y,z) with edge length specified.
     *
     * @param size edge length
     * @param x x-coordinate of center
     * @param y y-coordinate of center
     * @param z z-coordinate of center
     */
    private void setArrays(float size, float x, float y, float z) {

        float hs = size / 2.0f;

        float[] ver = {
            x - hs, y - hs, z - hs, // 0
            x + hs, y - hs, z - hs, // 1
            x + hs, y + hs, z - hs, // 2
            x - hs, y + hs, z - hs, // 3
            x - hs, y - hs, z + hs, // 4
            x + hs, y - hs, z + hs, // 5
            x + hs, y + hs, z + hs, // 6
            x - hs, y + hs, z + hs, // 7
        };
        this.vertices = ver;

        float c = 1.0f;
        float[] col = {
            0, 0, 0, c, // 0 black
            c, 0, 0, c, // 1 red
            c, c, 0, c, // 2 yellow
            0, c, 0, c, // 3 green
            0, 0, c, c, // 4 blue
            c, 0, c, c, // 5 magenta
            c, c, c, c, // 6 white
            0, c, c, c, // 7 cyan
        };
        this.colors = col;

        byte[] ind = {
            0, 4, 5, 0, 5, 1,
            1, 5, 6, 1, 6, 2,
            2, 6, 7, 2, 7, 3,
            3, 7, 4, 3, 4, 0,
            4, 7, 6, 4, 6, 5,
            3, 0, 1, 3, 1, 2
        };
        this.indices = ind;

        mVertexBuffer = RenderUtils.buildFloatBuffer(vertices);
        mColorBuffer = RenderUtils.buildFloatBuffer(colors);
        mIndexBuffer = RenderUtils.buildByteBuffer(indices);

    }

    /**
     * Create surface from arrays.
     *
     * NOTES: Length of each color indicator: 4 Length of each vertex: 3 Number
     * of elements: 36
     *
     * @param unused unused
     */
    public void draw(GL10 unused) {

        GLES10.glColorPointer(4, GLES10.GL_FLOAT, 0, mColorBuffer);
        GLES10.glVertexPointer(3, GLES10.GL_FLOAT, 0, mVertexBuffer);

        GLES10.glEnableClientState(GLES10.GL_COLOR_ARRAY);
        GLES10.glEnableClientState(GLES10.GL_VERTEX_ARRAY);

        GLES10.glDrawElements(
                GLES10.GL_TRIANGLES, 36, GLES10.GL_UNSIGNED_BYTE, mIndexBuffer);

        GLES10.glDisableClientState(GLES10.GL_COLOR_ARRAY);
        GLES10.glDisableClientState(GLES10.GL_VERTEX_ARRAY);

    }

}
