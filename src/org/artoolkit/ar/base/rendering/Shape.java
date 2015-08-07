/**
 * General representation of a shape defined by vertices and edges.
 * 
 * Consists of global variables and transformations to the shape.
 */
package org.artoolkit.ar.base.rendering;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

/**
 *
 * @author Pranav
 */
public abstract class Shape {

    // GLOBAL VARIABLES ========================================================
    /**
     * Vertices array.
     */
    protected float[] vertices;
    
    /**
     * Colors array.
     */
    protected float[] colors;
    
    /**
     * Indices array.
     */
    protected byte[] indices;

    /**
     * Vertices.
     */
    protected FloatBuffer mVertexBuffer;

    /**
     * Colors for each vertex.
     */
    protected FloatBuffer mColorBuffer;

    /**
     * Indices to construct faces.
     */
    protected ByteBuffer mIndexBuffer;

    // TRANSFORMATIONS =========================================================
    /**
     * Rotation.
     *
     * @param axis axis about which to rotate
     * @param deg degrees to rotate
     */
    public void rotate(String axis, float deg) {
    }

    /**
     * Translation.
     *
     * @param xt along x axis
     * @param yt along y axis
     * @param zt along z axis
     */
    public void translate(float xt, float yt, float zt) {
        translateHelper(vertices, 0, xt);
        translateHelper(vertices, 1, yt);
        translateHelper(vertices, 2, zt);
        this.mVertexBuffer = RenderUtils.buildFloatBuffer(vertices);
    }

    /**
     * Helps translate in one direction.
     *
     * @param v array of vectors
     * @param ind index at which to start
     * @param t how much to translate
     */
    private void translateHelper(float[] v, int ind, float t) {
        for (; ind < v.length; ind += 3) {
            v[ind] += t;
        }
    }

}
