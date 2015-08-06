/**
 * General representation of a shape defined by vertices and edges
 */
package org.artoolkit.ar.base.rendering;

import java.nio.FloatBuffer;

/**
 *
 * @author Pranav
 */
public class Shape {
    
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
    protected FloatBuffer mIndexBuffer;
    
    // TRANSFORMATIONS =========================================================
    /**
     * Rotation.
     * @param axis axis about which to rotate
     * @param deg degrees to rotate
     */
    public void rotate(String axis, float deg) {
    }

    /**
     * Translation.
     * @param xt along x axis
     * @param yt along y axis
     * @param zt along z axis
     */
    public void translate(float xt, float yt, float zt) {
        if (this.mVertexBuffer.hasArray()) {
            float[] verts = this.mVertexBuffer.array();
            if (xt > 0) translateHelper(verts, 0, xt);
            if (yt > 0) translateHelper(verts, 1, yt);
            if (zt > 0) translateHelper(verts, 2, zt);
        }
    }
    
    /**
     * Helps translate in one direction.
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
