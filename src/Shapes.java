import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.ARBVertexArrayObject.glBindVertexArray;
import static org.lwjgl.opengl.ARBVertexArrayObject.glGenVertexArrays;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.GL_FLOAT;

import static org.lwjgl.opengl.GL20.*;

public class Shapes {
    private int vertexCount;
    private int vboId;


    public Shapes(float[] shape){

        // creates id
        vboId = glGenBuffers();
        // bind the id to vertex format
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        // make an memory
        FloatBuffer shapeBuffer = MemoryUtil.memAllocFloat(shape.length);
        // uploads the vertices to gpu
        glBufferData(GL_ARRAY_BUFFER, shapeBuffer, GL_STATIC_DRAW);


        glEnableVertexAttribArray(0);
        glVertexAttribPointer(
            0, // id
            2, // how many items on coordinates ex. 0.5f, 0.5f
            GL_FLOAT, // data type
            false,  // false for raw numbers
             6 * Float.BYTES,  // how many bytes for next vertex items
            0   // where to start to read a byte '0' means 0 byte to read
        );
        glVertexAttribPointer(
                1,
                4,
                GL_FLOAT,
                false,
                6 * Float.BYTES,
                2 * Float.BYTES
        );

        // unbind the vertices for clearing up
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        // also clears memory
        MemoryUtil.memFree(shapeBuffer);

        vertexCount = shape.length/6;
    }

    public void render() {
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawArrays(GL_TRIANGLES, 0 , vertexCount );

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }
}
