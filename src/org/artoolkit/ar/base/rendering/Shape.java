/**
 * General representation of a shape defined by vertices and edges.
 *
 * Consists of global variables and transformations to the shape.
 * 
 * @author Pranav Lakshminarayanan
 */
package org.artoolkit.ar.base.rendering;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Abstract representation of a Shape, 
 * made up of vertices and indices defining faces.
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
    protected short[] indices;

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
    protected ShortBuffer mIndexBuffer;

    // TRANSFORMATIONS =========================================================
    /**
     * Linear scaling of shape.
     *
     * @param sc scaling factor
     */
    public void scale(float sc) {
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] *= sc;
        }
        this.mVertexBuffer = RenderUtils.buildFloatBuffer(vertices);
    }

    /**
     * Rotation about the X-axis.
     *
     * @param theta angle in radians to rotate
     */
    public void rotateX(float theta) {
        for (int i = 1; i < vertices.length; i += 3) {
            float yo = vertices[i];
            float zo = vertices[i + 1];
            vertices[i] = yo * (float) Math.cos(theta)
                    - zo * (float) Math.sin(theta);
            vertices[i + 1] = yo * (float) Math.sin(theta)
                    + zo * (float) Math.cos(theta);
        }
        this.mVertexBuffer = RenderUtils.buildFloatBuffer(vertices);
    }

    /**
     * Rotation about the Y-axis.
     *
     * @param theta angle in radians to rotate
     */
    public void rotateY(float theta) {
        for (int i = 0; i < vertices.length; i += 3) {
            float xo = vertices[i];
            float zo = vertices[i + 2];
            vertices[i] = zo * (float) Math.cos(theta)
                    - xo * (float) Math.sin(theta);
            vertices[i + 2] = zo * (float) Math.sin(theta)
                    + xo * (float) Math.cos(theta);
        }
        this.mVertexBuffer = RenderUtils.buildFloatBuffer(vertices);
    }

    /**
     * Rotation about the Z-axis.
     *
     * @param theta angle in radians to rotate
     */
    public void rotateZ(double theta) {
        for (int i = 0; i < vertices.length; i += 3) {
            float xo = vertices[i];
            float yo = vertices[i + 1];
            vertices[i] = xo * (float) Math.cos(theta)
                    - yo * (float) Math.sin(theta);
            vertices[i + 1] = xo * (float) Math.sin(theta)
                    + yo * (float) Math.cos(theta);
        }
        this.mVertexBuffer = RenderUtils.buildFloatBuffer(vertices);
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
